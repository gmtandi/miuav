package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.WriterCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DiskStorage {

    public interface Entry {
        BinaryResource getResource();

        long getSize();

        long getTimestamp();
    }

    public class DiskDumpInfo {
        public List<DiskDumpInfoEntry> entries;
        public Map<String, Integer> typeCounts;

        public DiskDumpInfo() {
            this.entries = new ArrayList();
            this.typeCounts = new HashMap();
        }
    }

    public class DiskDumpInfoEntry {
        public final String firstBits;
        public final String path;
        public final float size;
        public final String type;

        protected DiskDumpInfoEntry(String str, String str2, float f, String str3) {
            this.path = str;
            this.type = str2;
            this.size = f;
            this.firstBits = str3;
        }
    }

    void clearAll();

    BinaryResource commit(String str, BinaryResource binaryResource, Object obj);

    boolean contains(String str, Object obj);

    BinaryResource createTemporary(String str, Object obj);

    DiskDumpInfo getDumpInfo();

    Collection<Entry> getEntries();

    BinaryResource getResource(String str, Object obj);

    boolean isEnabled();

    void purgeUnexpectedResources();

    long remove(Entry entry);

    long remove(String str);

    boolean touch(String str, Object obj);

    void updateResource(String str, BinaryResource binaryResource, WriterCallback writerCallback, Object obj);
}
