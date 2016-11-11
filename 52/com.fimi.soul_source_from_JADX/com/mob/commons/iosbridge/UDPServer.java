package com.mob.commons.iosbridge;

import android.content.Context;
import android.util.Base64;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import com.mob.tools.MobLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C2178R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class UDPServer {
    private static UDPServer f11326a;
    private Context f11327b;
    private boolean f11328c;
    private long f11329d;

    private UDPServer(Context context) {
        this.f11327b = context.getApplicationContext();
    }

    private String m13168a(String str) {
        Process exec = Runtime.getRuntime().exec("cat /proc/net/arp");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        CharSequence readLine = bufferedReader.readLine();
        String str2 = null;
        while (readLine != null) {
            if (readLine.startsWith(str) && !readLine.contains("00:00:00:00:00:00")) {
                Matcher matcher = Pattern.compile("\\w{2}:\\w{2}:\\w{2}:\\w{2}:\\w{2}:\\w{2}").matcher(readLine);
                if (matcher.find()) {
                    str2 = matcher.group();
                    System.out.println("ipToHwAddr: " + str + " -- " + str2);
                }
            }
            readLine = bufferedReader.readLine();
        }
        exec.waitFor();
        return str2;
    }

    private void m13169a() {
        if (!this.f11328c) {
            try {
                File file = new File(C2178R.getCacheRoot(this.f11327b), ".usLock");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileLocker fileLocker = new FileLocker();
                fileLocker.setLockFile(file.getAbsolutePath());
                if (fileLocker.lock(true)) {
                    this.f11328c = true;
                    new C2152a(this, "UDPServer").start();
                    fileLocker.release();
                }
            } catch (Throwable th) {
                MobLog.getInstance().m750w(th);
            }
        }
    }

    private boolean m13171a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("imei", DeviceHelper.getInstance(this.f11327b).getIMEI());
        hashMap.put("serialno", DeviceHelper.getInstance(this.f11327b).getSerialno());
        hashMap.put("mac", DeviceHelper.getInstance(this.f11327b).getMacAddress());
        hashMap.put("model", DeviceHelper.getInstance(this.f11327b).getModel());
        hashMap.put("plat", Integer.valueOf(1));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("mac", str2);
        hashMap2.put("udpbody", str);
        hashMap.put("iosdata", hashMap2);
        String encodeToString = Base64.encodeToString(Data.AES128Encode("sdk.commonap.sdk", new Hashon().fromHashMap(hashMap)), 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("m", encodeToString));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.connectionTimeout = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
        networkTimeOut.readTimout = 30000;
        encodeToString = new NetworkHelper().httpPost("http://devs.data.mob.com/macinfo", arrayList, null, null, networkTimeOut);
        return encodeToString != null && encodeToString.contains("\"status\":200");
    }

    private byte[] m13172a(byte b, short s, byte[] bArr, int i, String str) {
        switch (b) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return m13173a(s);
            case Type.BYTE /*3*/:
                return m13174a(s, bArr, i, str);
            default:
                return null;
        }
    }

    private byte[] m13173a(short s) {
        switch (s) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(s);
                dataOutputStream.writeByte(2);
                dataOutputStream.writeInt(0);
                dataOutputStream.flush();
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            default:
                return null;
        }
    }

    private byte[] m13174a(short s, byte[] bArr, int i, String str) {
        switch (s) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                String str2 = new String(bArr, 11, i, "utf-8");
                String a = m13168a(str);
                if (a == null || !m13171a(str2, a)) {
                    return null;
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(s);
                dataOutputStream.writeByte(4);
                dataOutputStream.writeInt(0);
                dataOutputStream.flush();
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            default:
                return null;
        }
    }

    private void m13175b() {
        this.f11328c = false;
    }

    private boolean m13176c() {
        try {
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.connectionTimeout = FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS;
            networkTimeOut.readTimout = 30000;
            HashMap fromJson = new Hashon().fromJson(new NetworkHelper().httpGet("http://devs.data.mob.com/udpsconf", null, null, networkTimeOut));
            if (fromJson == null || fromJson.isEmpty() || !"200".equals(String.valueOf(fromJson.get(RMsgInfo.COL_STATUS)))) {
                return true;
            }
            fromJson = (HashMap) C2178R.forceCast(fromJson.get("switchs"), null);
            if (fromJson == null || fromJson.isEmpty()) {
                return true;
            }
            return !Constants.VIA_TO_TYPE_QQ_GROUP.equals(String.valueOf(fromJson.get("udps")));
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
            return true;
        }
    }

    private void m13177d() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(9058);
            datagramSocket.setSoTimeout(FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
            byte[] bArr = new byte[16395];
            while (this.f11328c) {
                if (System.currentTimeMillis() - this.f11329d < MiStatInterface.MAX_UPLOAD_INTERVAL || !m13176c()) {
                    DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
                    datagramSocket.receive(datagramPacket);
                    InetAddress address = datagramPacket.getAddress();
                    int port = datagramPacket.getPort();
                    short s = (short) (((bArr[0] & Util.MASK_8BIT) << 8) + (bArr[1] & Util.MASK_8BIT));
                    byte b = bArr[2];
                    int i = (bArr[6] & Util.MASK_8BIT) + ((((bArr[3] & Util.MASK_8BIT) << 24) + ((bArr[4] & Util.MASK_8BIT) << 16)) + ((bArr[5] & Util.MASK_8BIT) << 8));
                    if (i > 0) {
                        long j = ((((((long) bArr[7]) & 255) << 24) + ((((long) bArr[8]) & 255) << 16)) + ((((long) bArr[9]) & 255) << 8)) + (((long) bArr[10]) & 255);
                        CRC32 crc32 = new CRC32();
                        crc32.update(bArr, 11, i);
                        if (j != crc32.getValue()) {
                        }
                    }
                    byte[] a = m13172a(b, s, bArr, i, address.getHostAddress());
                    if (a != null) {
                        datagramSocket.send(new DatagramPacket(a, a.length, address, port));
                    }
                } else {
                    return;
                }
            }
        } catch (Throwable e) {
            MobLog.getInstance().m738d(e);
        } catch (Throwable e2) {
            MobLog.getInstance().m750w(e2);
        }
    }

    public static synchronized void start(Context context) {
        synchronized (UDPServer.class) {
            if (f11326a == null) {
                f11326a = new UDPServer(context);
            }
            f11326a.m13169a();
        }
    }

    public synchronized void stop() {
        if (f11326a != null) {
            f11326a.m13175b();
        }
    }
}
