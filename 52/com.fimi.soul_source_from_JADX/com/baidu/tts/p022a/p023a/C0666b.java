package com.baidu.tts.p022a.p023a;

import android.util.Log;
import com.baidu.speechsynthesizer.utility.SpeechDecoder;
import com.baidu.tts.tools.CommonUtility;

/* renamed from: com.baidu.tts.a.a.b */
public class C0666b implements C0665c<byte[], byte[]> {
    public void m5965a() {
    }

    public byte[] m5966a(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[0];
        Object obj = new short[((length * 50) / 2)];
        int[] iArr = new int[1];
        try {
            if (new SpeechDecoder().decode(bArr, length, obj, iArr, length * 50, 0) != 0) {
                Log.e("AudioDecoderAdapter", "ret != 0");
                return bArr2;
            }
            int i = iArr[0] / 2;
            Object obj2 = new short[i];
            System.arraycopy(obj, 0, obj2, 0, i);
            return CommonUtility.shortArrayToByteArray(obj2);
        } catch (Exception e) {
            e.printStackTrace();
            return bArr2;
        }
    }
}
