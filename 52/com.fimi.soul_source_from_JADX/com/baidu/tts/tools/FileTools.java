package com.baidu.tts.tools;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileTools {
    public static void createDir(String str) {
        new File(str).mkdirs();
    }

    public static File createFile(String str) {
        return getFile(str);
    }

    public static File createFile(String str, String str2) {
        return getFile(jointPathAndName(str, str2));
    }

    public static boolean deleteFile(File file) {
        return !file.exists() || file.delete();
    }

    public static boolean deleteFile(String str) {
        return deleteFile(createFile(str));
    }

    public static String extractFileName(String str) {
        return str.substring(str.lastIndexOf(File.separator) + 1);
    }

    public static boolean fileCopy(File file, File file2) {
        return fileCopy(new FileInputStream(file), new FileOutputStream(file2));
    }

    public static boolean fileCopy(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2) {
        return fileCopy(new FileInputStream(fileDescriptor), new FileOutputStream(fileDescriptor2));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean fileCopy(java.io.FileInputStream r3, java.io.FileOutputStream r4) {
        /*
        r0 = 0;
        r1 = r3.getChannel();	 Catch:{ Exception -> 0x0019, all -> 0x0026 }
        r2 = r4.getChannel();	 Catch:{ Exception -> 0x0019, all -> 0x0026 }
        r0 = fileCopy(r1, r2);	 Catch:{ Exception -> 0x0019, all -> 0x0026 }
        r3.close();	 Catch:{ IOException -> 0x0014 }
        r4.close();	 Catch:{ IOException -> 0x0014 }
    L_0x0013:
        return r0;
    L_0x0014:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0013;
    L_0x0019:
        r1 = move-exception;
        r3.close();	 Catch:{ IOException -> 0x0021 }
        r4.close();	 Catch:{ IOException -> 0x0021 }
        goto L_0x0013;
    L_0x0021:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0013;
    L_0x0026:
        r0 = move-exception;
        r3.close();	 Catch:{ IOException -> 0x002e }
        r4.close();	 Catch:{ IOException -> 0x002e }
    L_0x002d:
        throw r0;
    L_0x002e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.tts.tools.FileTools.fileCopy(java.io.FileInputStream, java.io.FileOutputStream):boolean");
    }

    public static boolean fileCopy(String str, String str2) {
        return fileCopy(createFile(str), createFile(str2));
    }

    public static boolean fileCopy(String str, String str2, String str3, String str4) {
        return fileCopy(createFile(str, str2), createFile(str3, str4));
    }

    public static boolean fileCopy(FileChannel fileChannel, FileChannel fileChannel2) {
        try {
            fileChannel.transferTo(0, fileChannel.size(), fileChannel2);
            try {
                fileChannel.close();
                fileChannel2.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                fileChannel.close();
                fileChannel2.close();
                return true;
            } catch (IOException e3) {
                e3.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            try {
                fileChannel.close();
                fileChannel2.close();
                return true;
            } catch (IOException e32) {
                e32.printStackTrace();
                return false;
            }
        }
    }

    public static File getFile(String str) {
        File file = new File(str);
        if (!file.exists()) {
            if (str.endsWith(File.separator)) {
                file.mkdirs();
            } else {
                File file2 = new File(str.substring(0, str.lastIndexOf(File.separator)));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static File getFile(String str, String str2) {
        return getFile(jointPathAndName(str, str2));
    }

    public static boolean isFileExist(String str) {
        return new File(str).exists();
    }

    public static boolean isFileExist(Object... objArr) {
        File createFile;
        if (objArr.length == 1) {
            Object obj = objArr[0];
            createFile = obj instanceof File ? (File) obj : obj instanceof String ? createFile((String) obj) : null;
        } else if (objArr.length == 2) {
            createFile = createFile((String) objArr[0], (String) objArr[1]);
        } else {
            throw new UnknownError();
        }
        return createFile != null ? createFile.exists() : false;
    }

    public static String jointPathAndName(String str, String str2) {
        return str.endsWith(File.separator) ? str + str2 : str + File.separator + str2;
    }

    public static boolean writeFile(String str, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
