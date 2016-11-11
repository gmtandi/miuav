package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfo;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfoEntry;
import com.facebook.cache.disk.DiskStorage.Entry;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileTreeVisitor;
import com.facebook.common.file.FileUtils;
import com.facebook.common.file.FileUtils.CreateDirectoryException;
import com.facebook.common.file.FileUtils.ParentDirNotFoundException;
import com.facebook.common.internal.CountingOutputStream;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.p122a.p123a.C2915a;

public class DefaultDiskStorage implements DiskStorage {
    private static final String CONTENT_FILE_EXTENSION = ".cnt";
    private static final String DEFAULT_DISK_STORAGE_VERSION_PREFIX = "v2";
    private static final int SHARDING_BUCKET_COUNT = 100;
    private static final Class<?> TAG;
    private static final String TEMP_FILE_EXTENSION = ".tmp";
    static final long TEMP_FILE_LIFETIME_MS;
    private final CacheErrorLogger mCacheErrorLogger;
    private final Clock mClock;
    private final File mRootDirectory;
    private final File mVersionDirectory;

    class EntriesCollector implements FileTreeVisitor {
        private final List<Entry> result;

        private EntriesCollector() {
            this.result = new ArrayList();
        }

        public List<Entry> getEntries() {
            return Collections.unmodifiableList(this.result);
        }

        public void postVisitDirectory(File file) {
        }

        public void preVisitDirectory(File file) {
        }

        public void visitFile(File file) {
            FileInfo access$100 = DefaultDiskStorage.this.getShardFileInfo(file);
            if (access$100 != null && access$100.type == FileType.CONTENT) {
                this.result.add(new EntryImpl(file, null));
            }
        }
    }

    @VisibleForTesting
    class EntryImpl implements Entry {
        private final FileBinaryResource resource;
        private long size;
        private long timestamp;

        private EntryImpl(File file) {
            Preconditions.checkNotNull(file);
            this.resource = FileBinaryResource.createOrNull(file);
            this.size = -1;
            this.timestamp = -1;
        }

        public FileBinaryResource getResource() {
            return this.resource;
        }

        public long getSize() {
            if (this.size < 0) {
                this.size = this.resource.size();
            }
            return this.size;
        }

        public long getTimestamp() {
            if (this.timestamp < 0) {
                this.timestamp = this.resource.getFile().lastModified();
            }
            return this.timestamp;
        }
    }

    class FileInfo {
        public final String resourceId;
        public final FileType type;

        private FileInfo(FileType fileType, String str) {
            this.type = fileType;
            this.resourceId = str;
        }

        public static FileInfo fromFile(File file) {
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf <= 0) {
                return null;
            }
            FileType fromExtension = FileType.fromExtension(name.substring(lastIndexOf));
            if (fromExtension == null) {
                return null;
            }
            name = name.substring(0, lastIndexOf);
            if (fromExtension.equals(FileType.TEMP)) {
                lastIndexOf = name.lastIndexOf(46);
                if (lastIndexOf <= 0) {
                    return null;
                }
                name = name.substring(0, lastIndexOf);
            }
            return new FileInfo(fromExtension, name);
        }

        public File createTempFile(File file) {
            return File.createTempFile(this.resourceId + ".", DefaultDiskStorage.TEMP_FILE_EXTENSION, file);
        }

        public File toFile(File file) {
            return new File(file, this.resourceId + this.type.extension);
        }

        public String toString() {
            return this.type + "(" + this.resourceId + ")";
        }
    }

    enum FileType {
        CONTENT(DefaultDiskStorage.CONTENT_FILE_EXTENSION),
        TEMP(DefaultDiskStorage.TEMP_FILE_EXTENSION);
        
        public final String extension;

        private FileType(String str) {
            this.extension = str;
        }

        public static FileType fromExtension(String str) {
            return DefaultDiskStorage.CONTENT_FILE_EXTENSION.equals(str) ? CONTENT : DefaultDiskStorage.TEMP_FILE_EXTENSION.equals(str) ? TEMP : null;
        }
    }

    class IncompleteFileException extends IOException {
        public final long actual;
        public final long expected;

        public IncompleteFileException(long j, long j2) {
            super("File was not written completely. Expected: " + j + ", found: " + j2);
            this.expected = j;
            this.actual = j2;
        }
    }

    class PurgingVisitor implements FileTreeVisitor {
        private boolean insideBaseDirectory;

        private PurgingVisitor() {
        }

        private boolean isExpectedFile(File file) {
            boolean z = false;
            FileInfo access$100 = DefaultDiskStorage.this.getShardFileInfo(file);
            if (access$100 == null) {
                return false;
            }
            if (access$100.type == FileType.TEMP) {
                return isRecentFile(file);
            }
            if (access$100.type == FileType.CONTENT) {
                z = true;
            }
            Preconditions.checkState(z);
            return true;
        }

        private boolean isRecentFile(File file) {
            return file.lastModified() > DefaultDiskStorage.this.mClock.now() - DefaultDiskStorage.TEMP_FILE_LIFETIME_MS;
        }

        public void postVisitDirectory(File file) {
            if (!(DefaultDiskStorage.this.mRootDirectory.equals(file) || this.insideBaseDirectory)) {
                file.delete();
            }
            if (this.insideBaseDirectory && file.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                this.insideBaseDirectory = false;
            }
        }

        public void preVisitDirectory(File file) {
            if (!this.insideBaseDirectory && file.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                this.insideBaseDirectory = true;
            }
        }

        public void visitFile(File file) {
            if (!this.insideBaseDirectory || !isExpectedFile(file)) {
                file.delete();
            }
        }
    }

    static {
        TAG = DefaultDiskStorage.class;
        TEMP_FILE_LIFETIME_MS = TimeUnit.MINUTES.toMillis(30);
    }

    public DefaultDiskStorage(File file, int i, CacheErrorLogger cacheErrorLogger) {
        Preconditions.checkNotNull(file);
        this.mRootDirectory = file;
        this.mVersionDirectory = new File(this.mRootDirectory, getVersionSubdirectoryName(i));
        this.mCacheErrorLogger = cacheErrorLogger;
        recreateDirectoryIfVersionChanges();
        this.mClock = SystemClock.get();
    }

    private long doRemove(File file) {
        if (!file.exists()) {
            return 0;
        }
        return !file.delete() ? -1 : file.length();
    }

    private DiskDumpInfoEntry dumpCacheEntry(Entry entry) {
        EntryImpl entryImpl = (EntryImpl) entry;
        String str = C2915a.f14760f;
        byte[] read = entryImpl.getResource().read();
        String typeOfBytes = typeOfBytes(read);
        if (typeOfBytes.equals("undefined") && read.length >= 4) {
            str = String.format((Locale) null, "0x%02X 0x%02X 0x%02X 0x%02X", new Object[]{Byte.valueOf(read[0]), Byte.valueOf(read[1]), Byte.valueOf(read[2]), Byte.valueOf(read[3])});
        }
        return new DiskDumpInfoEntry(entryImpl.getResource().getFile().getPath(), typeOfBytes, (float) entryImpl.getSize(), str);
    }

    private FileInfo getShardFileInfo(File file) {
        FileInfo fromFile = FileInfo.fromFile(file);
        if (fromFile == null) {
            return null;
        }
        if (!getSubdirectory(fromFile.resourceId).equals(file.getParentFile())) {
            fromFile = null;
        }
        return fromFile;
    }

    private File getSubdirectory(String str) {
        return new File(this.mVersionDirectory, String.valueOf(Math.abs(str.hashCode() % SHARDING_BUCKET_COUNT)));
    }

    @VisibleForTesting
    static String getVersionSubdirectoryName(int i) {
        return String.format((Locale) null, "%s.ols%d.%d", new Object[]{DEFAULT_DISK_STORAGE_VERSION_PREFIX, Integer.valueOf(SHARDING_BUCKET_COUNT), Integer.valueOf(i)});
    }

    private void mkdirs(File file, String str) {
        try {
            FileUtils.mkdirs(file);
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_DIR, TAG, str, e);
            throw e;
        }
    }

    private boolean query(String str, boolean z) {
        File contentFileFor = getContentFileFor(str);
        boolean exists = contentFileFor.exists();
        if (z && exists) {
            contentFileFor.setLastModified(this.mClock.now());
        }
        return exists;
    }

    private void recreateDirectoryIfVersionChanges() {
        Object obj = 1;
        if (this.mRootDirectory.exists()) {
            if (this.mVersionDirectory.exists()) {
                obj = null;
            } else {
                FileTree.deleteRecursively(this.mRootDirectory);
            }
        }
        if (obj != null) {
            try {
                FileUtils.mkdirs(this.mVersionDirectory);
            } catch (CreateDirectoryException e) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_DIR, TAG, "version directory could not be created: " + this.mVersionDirectory, null);
            }
        }
    }

    private String typeOfBytes(byte[] bArr) {
        if (bArr.length >= 2) {
            if (bArr[0] == -1 && bArr[1] == -40) {
                return "jpg";
            }
            if (bArr[0] == -119 && bArr[1] == 80) {
                return "png";
            }
            if (bArr[0] == 82 && bArr[1] == (byte) 73) {
                return "webp";
            }
            if (bArr[0] == 71 && bArr[1] == (byte) 73) {
                return "gif";
            }
        }
        return "undefined";
    }

    public void clearAll() {
        FileTree.deleteContents(this.mRootDirectory);
    }

    public FileBinaryResource commit(String str, BinaryResource binaryResource, Object obj) {
        File file = ((FileBinaryResource) binaryResource).getFile();
        File contentFileFor = getContentFileFor(str);
        try {
            FileUtils.rename(file, contentFileFor);
            if (contentFileFor.exists()) {
                contentFileFor.setLastModified(this.mClock.now());
            }
            return FileBinaryResource.createOrNull(contentFileFor);
        } catch (Throwable e) {
            Throwable cause = e.getCause();
            CacheErrorCategory cacheErrorCategory = cause == null ? CacheErrorCategory.WRITE_RENAME_FILE_OTHER : cause instanceof ParentDirNotFoundException ? CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND : cause instanceof FileNotFoundException ? CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND : CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
            this.mCacheErrorLogger.logError(cacheErrorCategory, TAG, "commit", e);
            throw e;
        }
    }

    public boolean contains(String str, Object obj) {
        return query(str, false);
    }

    public FileBinaryResource createTemporary(String str, Object obj) {
        FileInfo fileInfo = new FileInfo(str, null);
        File subdirectory = getSubdirectory(fileInfo.resourceId);
        if (!subdirectory.exists()) {
            mkdirs(subdirectory, "createTemporary");
        }
        try {
            return FileBinaryResource.createOrNull(fileInfo.createTempFile(subdirectory));
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_CREATE_TEMPFILE, TAG, "createTemporary", e);
            throw e;
        }
    }

    @VisibleForTesting
    File getContentFileFor(String str) {
        FileInfo fileInfo = new FileInfo(str, null);
        return fileInfo.toFile(getSubdirectory(fileInfo.resourceId));
    }

    public DiskDumpInfo getDumpInfo() {
        List<Entry> entries = getEntries();
        DiskDumpInfo diskDumpInfo = new DiskDumpInfo();
        for (Entry dumpCacheEntry : entries) {
            DiskDumpInfoEntry dumpCacheEntry2 = dumpCacheEntry(dumpCacheEntry);
            String str = dumpCacheEntry2.type;
            if (!diskDumpInfo.typeCounts.containsKey(str)) {
                diskDumpInfo.typeCounts.put(str, Integer.valueOf(0));
            }
            diskDumpInfo.typeCounts.put(str, Integer.valueOf(((Integer) diskDumpInfo.typeCounts.get(str)).intValue() + 1));
            diskDumpInfo.entries.add(dumpCacheEntry2);
        }
        return diskDumpInfo;
    }

    public List<Entry> getEntries() {
        Object entriesCollector = new EntriesCollector();
        FileTree.walkFileTree(this.mVersionDirectory, entriesCollector);
        return entriesCollector.getEntries();
    }

    public FileBinaryResource getResource(String str, Object obj) {
        File contentFileFor = getContentFileFor(str);
        if (!contentFileFor.exists()) {
            return null;
        }
        contentFileFor.setLastModified(this.mClock.now());
        return FileBinaryResource.createOrNull(contentFileFor);
    }

    public boolean isEnabled() {
        return true;
    }

    public void purgeUnexpectedResources() {
        FileTree.walkFileTree(this.mRootDirectory, new PurgingVisitor());
    }

    public long remove(Entry entry) {
        return doRemove(((EntryImpl) entry).getResource().getFile());
    }

    public long remove(String str) {
        return doRemove(getContentFileFor(str));
    }

    public boolean touch(String str, Object obj) {
        return query(str, true);
    }

    public void updateResource(String str, BinaryResource binaryResource, WriterCallback writerCallback, Object obj) {
        File file = ((FileBinaryResource) binaryResource).getFile();
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            try {
                OutputStream countingOutputStream = new CountingOutputStream(fileOutputStream);
                writerCallback.write(countingOutputStream);
                countingOutputStream.flush();
                long count = countingOutputStream.getCount();
                if (file.length() != count) {
                    throw new IncompleteFileException(count, file.length());
                }
            } finally {
                fileOutputStream.close();
            }
        } catch (Throwable e) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.WRITE_UPDATE_FILE_NOT_FOUND, TAG, "updateResource", e);
            throw e;
        }
    }
}
