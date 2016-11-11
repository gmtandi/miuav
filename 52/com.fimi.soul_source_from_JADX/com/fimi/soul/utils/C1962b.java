package com.fimi.soul.utils;

import android.content.Context;
import android.widget.ImageView;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.entity.WifiDistanceFile;
import com.fimi.soul.module.setting.am;
import com.fimi.soul.service.CameraSocketService;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.utils.b */
public class C1962b {
    public static final int f10058a = 58880;
    public static final int f10059b = 58885;
    public static final int f10060c = 58887;
    public static Object f10061d;

    static {
        f10061d = new Object();
    }

    public static int m12304a(byte[] bArr, int i) {
        return (bArr[i] & Util.MASK_8BIT) | ((bArr[i + 1] & Util.MASK_8BIT) << 8);
    }

    public static WifiDistanceFile m12305a(File file) {
        if (file.exists()) {
            HashMap hashMap = new HashMap();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), C1142e.f5201a));
                String str = "/media";
                WifiDistanceFile wifiDistanceFile = new WifiDistanceFile(str);
                wifiDistanceFile.setType(0);
                wifiDistanceFile.setParentStrDir("/");
                hashMap.put(str, wifiDistanceFile);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return wifiDistanceFile;
                    }
                    C1236a.m8532a("line=" + readLine, C1962b.class);
                    String[] a = C1962b.m12311a(readLine);
                    if (a.length <= 1 || readLine.startsWith("/")) {
                        readLine = "/media/:".equalsIgnoreCase(readLine) ? "/media" : readLine.replace(":", C2915a.f14760f);
                    } else {
                        WifiDistanceFile wifiDistanceFile2 = new WifiDistanceFile(a[2]);
                        wifiDistanceFile2.setSize(Long.parseLong(a[1]));
                        wifiDistanceFile2.setParentStrDir(str);
                        if (a[0].startsWith("d")) {
                            wifiDistanceFile.setType(0);
                            hashMap.put(str + "/" + a[2], wifiDistanceFile2);
                        } else {
                            wifiDistanceFile.setType(0);
                        }
                        WifiDistanceFile wifiDistanceFile3 = (WifiDistanceFile) hashMap.get(str);
                        if (wifiDistanceFile3 != null) {
                            wifiDistanceFile3.addFile(wifiDistanceFile2);
                            wifiDistanceFile2.setParentDir(wifiDistanceFile3);
                        } else {
                            wifiDistanceFile2.setParentDir(null);
                        }
                        readLine = str;
                    }
                    str = readLine;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    public static void m12306a(Context context, String str, ImageView imageView) {
        if (be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingImage))) {
            imageView.setImageResource(C1205R.drawable.file_icon_picture);
        } else if (be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingAudio))) {
            imageView.setImageResource(C1205R.drawable.file_icon_mp3);
        } else if (be.m12364a(str, context.getResources().getStringArray(C1205R.array.fileEndingVideo))) {
            imageView.setImageResource(C1205R.drawable.file_icon_video);
        } else {
            imageView.setImageResource(C1205R.drawable.file);
        }
    }

    public static void m12307a(DataOutputStream dataOutputStream, String str) {
        C1962b.m12308a(dataOutputStream, str.getBytes());
    }

    public static void m12308a(DataOutputStream dataOutputStream, byte[] bArr) {
        synchronized (f10061d) {
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.write(bArr);
                    dataOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void m12309a(String str, long j, DataInputStream dataInputStream, CameraSocketService cameraSocketService, boolean z) {
        C1236a.m8532a("localFile=" + str + "fileLenth=" + j, C1962b.class);
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        byte[] bArr = new byte[Opcodes.ACC_ANNOTATION];
        long j2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        cameraSocketService.m12127a(4, z, Constants.VIA_RESULT_SUCCESS);
        while (true) {
            int read = dataInputStream.read(bArr);
            if (read < 0) {
                break;
            }
            fileOutputStream.write(bArr, 0, read);
            fileOutputStream.flush();
            j2 += (long) read;
            long j3 = (100 * j2) / j;
            if (j2 >= j) {
                break;
            } else if (System.currentTimeMillis() - currentTimeMillis > 2000 && cameraSocketService != null) {
                currentTimeMillis = System.currentTimeMillis();
                cameraSocketService.m12127a(4, z, j3 + C2915a.f14760f);
            }
        }
        cameraSocketService.m12127a(4, z, am.f9227L);
        fileOutputStream.close();
        C1236a.m8532a("receiveFileName readlen" + j2, CameraSocketService.class);
    }

    public static void m12310a(byte[] bArr) {
        MulticastSocket multicastSocket;
        try {
            multicastSocket = new MulticastSocket();
        } catch (IOException e) {
            e.printStackTrace();
            multicastSocket = null;
        }
        try {
            InetAddress byName = InetAddress.getByName("224.0.0.1");
            if (byName.isMulticastAddress()) {
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, byName, f10058a);
                if (multicastSocket != null) {
                    try {
                        multicastSocket.send(datagramPacket);
                        multicastSocket.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
        }
    }

    private static String[] m12311a(String str) {
        String[] strArr = new String[3];
        String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (split.length <= 9) {
            return split;
        }
        String str2 = C2915a.f14760f;
        int i = 0;
        for (int i2 = 0; i2 < split.length; i2++) {
            if (!C2915a.f14760f.equalsIgnoreCase(split[i2])) {
                i++;
                if (i == 1) {
                    strArr[0] = split[i2];
                } else if (i == 5) {
                    strArr[1] = split[i2];
                } else if (i >= 9) {
                    str2 = C2915a.f14760f.equalsIgnoreCase(str2) ? str2 + split[i2] : str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + split[i2];
                }
            }
        }
        strArr[2] = str2;
        return strArr;
    }

    public static int m12312b(byte[] bArr, int i) {
        return (((bArr[i] & Util.MASK_8BIT) | ((bArr[i + 1] & Util.MASK_8BIT) << 8)) | ((bArr[i + 2] & Util.MASK_8BIT) << 16)) | ((bArr[i + 3] & Util.MASK_8BIT) << 24);
    }

    public void m12313a(String str, String str2, int i) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(f10059b);
            InetAddress byName = InetAddress.getByName(str2);
            byte[] bytes = str.getBytes();
            datagramSocket.send(new DatagramPacket(bytes, bytes.length, byName, i));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }
}
