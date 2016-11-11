package com.baidu.tts.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.fimi.soul.module.droneui.DroneMap;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import org.codehaus.jackson.smile.SmileConstants;

public class CommonUtility {
    private static int[] m6952a(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        int i = 0;
        int i2 = 1;
        while (i2 < bArr.length) {
            while (i > 0 && bArr[i] != bArr[i2]) {
                i = iArr[i - 1];
            }
            if (bArr[i] == bArr[i2]) {
                i++;
            }
            iArr[i2] = i;
            i2++;
        }
        return iArr;
    }

    public static byte[] addCAFHeaderForPCMData(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        long length = ((16 * (44 + ((long) bArr.length))) * ((long) 1)) / 8;
        Object obj = new byte[]{DroneMap.f8356j, (byte) 73, (byte) 70, (byte) 70, (byte) ((int) (255 & r4)), (byte) ((int) ((r4 >> 8) & 255)), (byte) ((int) ((r4 >> 16) & 255)), (byte) ((int) ((r4 >> 24) & 255)), (byte) 87, (byte) 65, (byte) 86, (byte) 69, (byte) 102, (byte) 109, (byte) 116, SmileConstants.TOKEN_LITERAL_EMPTY_STRING, (byte) 16, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 1, (byte) 0, (byte) ((int) (255 & 16000)), (byte) ((int) ((16000 >> 8) & 255)), (byte) ((int) ((16000 >> 16) & 255)), (byte) ((int) ((16000 >> 24) & 255)), (byte) ((int) (255 & length)), (byte) ((int) ((length >> 8) & 255)), (byte) ((int) ((length >> 16) & 255)), (byte) ((int) ((length >> 24) & 255)), (byte) 2, (byte) 0, (byte) 16, (byte) 0, (byte) 100, (byte) 97, (byte) 116, (byte) 97, (byte) ((int) (255 & r2)), (byte) ((int) ((r2 >> 8) & 255)), (byte) ((int) ((r2 >> 16) & 255)), (byte) ((int) ((((long) bArr.length) >> 24) & 255))};
        Object obj2 = new byte[(obj.length + bArr.length)];
        System.arraycopy(obj, 0, obj2, 0, obj.length);
        System.arraycopy(bArr, 0, obj2, obj.length, bArr.length);
        return obj2;
    }

    public static byte[] copyBytesOfRange(byte[] bArr, int i, int i2) {
        if (i > i2 || i < 0 || i2 < 0 || i2 > bArr.length) {
            return null;
        }
        byte[] bArr2 = new byte[(i2 - i)];
        for (int i3 = i; i3 < i2; i3++) {
            bArr2[i3 - i] = bArr[i3];
        }
        return bArr2;
    }

    public static String generateSerialNumber() {
        return UUID.randomUUID().toString();
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static int indexOf(byte[] bArr, byte[] bArr2, int i) {
        try {
            int[] a = m6952a(bArr2);
            int i2 = 0;
            if (bArr.length == 0 || i >= bArr.length) {
                return -1;
            }
            while (i < bArr.length) {
                while (i2 > 0 && bArr2[i2] != bArr[i]) {
                    i2 = a[i2 - 1];
                }
                if (bArr2[i2] == bArr[i]) {
                    i2++;
                }
                if (i2 == bArr2.length) {
                    return (i - bArr2.length) + 1;
                }
                i++;
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 1;
    }

    public static byte[] shortArrayToByteArray(short[] sArr) {
        int length = sArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(sArr.length * 2);
        allocate.clear();
        allocate.order(ByteOrder.nativeOrder());
        for (int i = 0; i < length; i++) {
            allocate.putShort(i * 2, sArr[i]);
        }
        return allocate.array();
    }
}
