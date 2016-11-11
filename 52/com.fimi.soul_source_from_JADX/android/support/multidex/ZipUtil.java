package android.support.multidex;

import com.fimi.soul.media.player.FimiMediaMeta;
import it.sephiroth.android.library.widget.ExpandableHListView;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;

final class ZipUtil {
    private static final int BUFFER_SIZE = 16384;
    private static final int ENDHDR = 22;
    private static final int ENDSIG = 101010256;

    class CentralDirectory {
        long offset;
        long size;

        CentralDirectory() {
        }
    }

    ZipUtil() {
    }

    static long computeCrcOfCentralDir(RandomAccessFile randomAccessFile, CentralDirectory centralDirectory) {
        CRC32 crc32 = new CRC32();
        long j = centralDirectory.size;
        randomAccessFile.seek(centralDirectory.offset);
        byte[] bArr = new byte[BUFFER_SIZE];
        int read = randomAccessFile.read(bArr, 0, (int) Math.min(FimiMediaMeta.AV_CH_TOP_FRONT_RIGHT, j));
        while (read != -1) {
            crc32.update(bArr, 0, read);
            j -= (long) read;
            if (j == 0) {
                break;
            }
            read = randomAccessFile.read(bArr, 0, (int) Math.min(FimiMediaMeta.AV_CH_TOP_FRONT_RIGHT, j));
        }
        return crc32.getValue();
    }

    static CentralDirectory findCentralDirectory(RandomAccessFile randomAccessFile) {
        long j = 0;
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + randomAccessFile.length());
        }
        long j2 = length - FimiMediaMeta.AV_CH_TOP_BACK_CENTER;
        if (j2 >= 0) {
            j = j2;
        }
        int reverseBytes = Integer.reverseBytes(ENDSIG);
        j2 = length;
        do {
            randomAccessFile.seek(j2);
            if (randomAccessFile.readInt() == reverseBytes) {
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                CentralDirectory centralDirectory = new CentralDirectory();
                centralDirectory.size = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & ExpandableHListView.aZ;
                centralDirectory.offset = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & ExpandableHListView.aZ;
                return centralDirectory;
            }
            j2--;
        } while (j2 >= j);
        throw new ZipException("End Of Central Directory signature not found");
    }

    static long getZipCrc(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            long computeCrcOfCentralDir = computeCrcOfCentralDir(randomAccessFile, findCentralDirectory(randomAccessFile));
            return computeCrcOfCentralDir;
        } finally {
            randomAccessFile.close();
        }
    }
}