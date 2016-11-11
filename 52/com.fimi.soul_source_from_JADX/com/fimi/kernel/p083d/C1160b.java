package com.fimi.kernel.p083d;

import android.content.Context;
import android.os.Environment;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.kernel.d.b */
public class C1160b implements SpeechSynthesizerListener {
    private static boolean f5246a = false;
    private static final String f5247d = "baiduTTS";
    private static final String f5248e = "bd_etts_ch_speech_female.dat";
    private static final String f5249f = "bd_etts_ch_text.dat";
    private static final String f5250g = "temp_license";
    private static final String f5251h = "bd_etts_speech_female_en.dat";
    private static final String f5252i = "bd_etts_text_en.dat";
    private static boolean f5253j;
    private static C1160b f5254k;
    private static Context f5255l;
    private SpeechSynthesizer f5256b;
    private String f5257c;
    private boolean f5258m;
    private C1161c f5259n;

    static {
        f5246a = false;
        f5253j = false;
    }

    private C1160b() {
    }

    public static C1160b m7951a(Context context) {
        f5255l = context;
        if (f5254k == null) {
            synchronized (C1160b.class) {
                f5254k = new C1160b();
            }
        }
        return f5254k;
    }

    private void m7952a(boolean z, String str, String str2) {
        InputStream open;
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        InputStream inputStream;
        IOException e2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file = new File(str2);
        if (z || !(z || file.exists())) {
            try {
                open = f5255l.getResources().getAssets().open(str);
                try {
                    fileOutputStream = new FileOutputStream(str2);
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileOutputStream = null;
                    inputStream = open;
                    try {
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (inputStream == null) {
                            try {
                                inputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        open = inputStream;
                        fileOutputStream2 = fileOutputStream;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e222 = e5;
                    try {
                        e222.printStackTrace();
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        if (open == null) {
                            try {
                                open.close();
                            } catch (IOException e22222) {
                                e22222.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        if (open != null) {
                            open.close();
                        }
                        throw th;
                    }
                }
                try {
                    byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
                    while (true) {
                        int read = open.read(bArr, 0, SmileConstants.MAX_SHARED_STRING_VALUES);
                        if (read < 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e222222) {
                            e222222.printStackTrace();
                        }
                    }
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e2222222) {
                            e2222222.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    inputStream = open;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (inputStream == null) {
                        inputStream.close();
                    }
                } catch (IOException e7) {
                    e2222222 = e7;
                    fileOutputStream2 = fileOutputStream;
                    e2222222.printStackTrace();
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (open == null) {
                        open.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (open != null) {
                        open.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e8) {
                e = e8;
                fileOutputStream = null;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream == null) {
                    inputStream.close();
                }
            } catch (IOException e9) {
                e2222222 = e9;
                open = null;
                e2222222.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (open == null) {
                    open.close();
                }
            } catch (Throwable th5) {
                th = th5;
                open = null;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (open != null) {
                    open.close();
                }
                throw th;
            }
        }
    }

    public static C1160b m7953b(Context context) {
        f5255l = context;
        return f5254k;
    }

    private void m7954e(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void m7955f() {
        if (this.f5257c == null) {
            this.f5257c = Environment.getExternalStorageDirectory().toString() + "/" + f5247d;
        }
        m7954e(this.f5257c);
        m7952a(false, f5248e, this.f5257c + "/" + f5248e);
        m7952a(false, f5249f, this.f5257c + "/" + f5249f);
        m7952a(false, f5250g, this.f5257c + "/" + f5250g);
        m7952a(false, "english/bd_etts_speech_female_en.dat", this.f5257c + "/" + f5251h);
        m7952a(false, "english/bd_etts_text_en.dat", this.f5257c + "/" + f5252i);
    }

    private void m7956g() {
        this.f5256b = SpeechSynthesizer.getInstance();
        this.f5256b.setContext(f5255l);
        this.f5256b.setSpeechSynthesizerListener(this);
        this.f5256b.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, this.f5257c + "/" + f5249f);
        this.f5256b.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, this.f5257c + "/" + f5248e);
        this.f5256b.setAppId(C1159a.f5243a);
        this.f5256b.setApiKey(C1159a.f5244b, C1159a.f5245c);
        AuthInfo auth = this.f5256b.auth(TtsMode.MIX);
        if (auth.isSuccess()) {
            this.f5258m = true;
            this.f5256b.setParam(SpeechSynthesizer.PARAM_SPEAKER, Constants.VIA_RESULT_SUCCESS);
            this.f5256b.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI);
            this.f5256b.initTts(TtsMode.MIX);
            this.f5256b.loadEnglishModel(this.f5257c + "/" + f5252i, this.f5257c + "/" + f5251h);
            return;
        }
        this.f5258m = false;
        auth.getTtsError().getDetailMessage();
    }

    public void m7957a() {
        if (!this.f5258m) {
            m7955f();
            m7956g();
        }
    }

    public void m7958a(C1161c c1161c) {
        this.f5259n = c1161c;
    }

    public void m7959a(String str) {
        if (str != null && this.f5256b != null) {
            try {
                if (f5246a) {
                    this.f5256b.stop();
                }
                this.f5256b.speak(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m7960b() {
        if (this.f5256b != null) {
            this.f5256b.stop();
        }
    }

    public void m7961b(String str) {
        this.f5256b.setParam(SpeechSynthesizer.PARAM_SPEAKER, str);
    }

    public void m7962c() {
        this.f5256b.release();
    }

    public void m7963c(String str) {
        this.f5256b.setParam(SpeechSynthesizer.PARAM_VOLUME, str);
    }

    public void m7964d(String str) {
        this.f5256b.setParam(SpeechSynthesizer.PARAM_PITCH, str);
    }

    public boolean m7965d() {
        return this.f5258m;
    }

    public void m7966e() {
        if (f5246a) {
            this.f5256b.stop();
        }
    }

    public void onError(String str, SpeechError speechError) {
    }

    public void onSpeechFinish(String str) {
        f5246a = false;
        if (this.f5259n != null) {
            this.f5259n.m7967a(true);
        }
    }

    public void onSpeechProgressChanged(String str, int i) {
    }

    public void onSpeechStart(String str) {
        f5246a = true;
        if (this.f5259n != null) {
            this.f5259n.m7967a(false);
        }
    }

    public void onSynthesizeDataArrived(String str, byte[] bArr, int i) {
    }

    public void onSynthesizeFinish(String str) {
    }

    public void onSynthesizeStart(String str) {
    }
}
