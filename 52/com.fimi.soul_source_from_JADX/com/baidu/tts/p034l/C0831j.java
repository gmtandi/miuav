package com.baidu.tts.p034l;

import com.baidu.tts.p027b.p028a.p032b.C0723e.C0721a;
import com.baidu.tts.p027b.p028a.p032b.C0727f.C0725b;
import com.baidu.tts.p027b.p029b.p036b.C0744b.C0743a;
import com.baidu.tts.p033m.C0719a;
import com.baidu.tts.p041e.C0786b;
import com.baidu.tts.p041e.C0790c;
import com.baidu.tts.p041e.C0791d;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0797j;
import com.fimi.soul.module.setting.am;
import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.baidu.tts.l.j */
public class C0831j extends C0719a<C0831j> {
    private C0824b f4618a;
    private C0823a f4619b;

    /* renamed from: com.baidu.tts.l.j.1 */
    /* synthetic */ class C08301 {
        static final /* synthetic */ int[] f4617a;

        static {
            f4617a = new int[C0794g.values().length];
            try {
                f4617a[C0794g.SPEED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4617a[C0794g.VOLUME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4617a[C0794g.PITCH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4617a[C0794g.TEXT_DAT_PATH.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4617a[C0794g.SPEECH_DAT_PATH.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4617a[C0794g.TTS_LICENSE_FILE_PATH.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4617a[C0794g.APP_CODE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4617a[C0794g.TEXT_ENCODE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4617a[C0794g.NOTIFICATION_COUNT_PER_SECOND.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4617a[C0794g.PRODUCT_ID.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4617a[C0794g.AUDIO_ENCODE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f4617a[C0794g.BITRATE.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f4617a[C0794g.BACKGROUND.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f4617a[C0794g.CUSTOM_SYNTH.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f4617a[C0794g.LANGUAGE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f4617a[C0794g.OPEN_XML.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f4617a[C0794g.PUNCTUATION.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f4617a[C0794g.SPEAKER.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f4617a[C0794g.STYLE.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f4617a[C0794g.TERRITORY.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                f4617a[C0794g.TTS_VOCODER_OPTIMIZATION.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f4617a[C0794g.API_KEY.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                f4617a[C0794g.SECRET_KEY.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                f4617a[C0794g.STREAM_TYPE.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                f4617a[C0794g.MIX_MODE.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
            try {
                f4617a[C0794g.SAMPLE_RATE.ordinal()] = 26;
            } catch (NoSuchFieldError e26) {
            }
        }
    }

    public C0831j() {
        this.f4618a = new C0824b();
        this.f4619b = new C0823a();
    }

    private int m6881b(C0794g c0794g, String str) {
        C0721a b = this.f4618a.m6836b();
        C0725b a = this.f4618a.m6833a();
        C0743a a2 = this.f4619b.m6831a();
        switch (C08301.f4617a[c0794g.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f4618a.m6835a(str);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f4618a.m6837b(str);
                break;
            case Type.BYTE /*3*/:
                this.f4618a.m6839c(str);
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                b.m6324e(str);
                break;
            case Type.INT /*5*/:
                b.m6326f(str);
                break;
            case Type.FLOAT /*6*/:
                b.m6328g(str);
                break;
            case Type.LONG /*7*/:
                b.m6330h(str);
                break;
            case Type.DOUBLE /*8*/:
                a.m6301a(C0791d.m6736a(str));
                break;
            case Type.ARRAY /*9*/:
                a2.m6482a(str);
                break;
            case Type.OBJECT /*10*/:
                a.m6354c(str);
                break;
            case Opcodes.T_LONG /*11*/:
                return a.m6344a(C0786b.m6732a(str));
            case Opcodes.FCONST_1 /*12*/:
                a.m6347a(C0790c.m6734a(str));
                break;
            case Opcodes.FCONST_2 /*13*/:
                a.m6360f(str);
                break;
            case Opcodes.DCONST_0 /*14*/:
                return b.m6319c(str);
            case Opcodes.DCONST_1 /*15*/:
                a.m6302i(str);
                b.m6302i(str);
                break;
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                return b.m6321d(str);
            case Opcodes.SIPUSH /*17*/:
                a.m6364h(str);
                break;
            case Opcodes.LDC /*18*/:
                a.m6356d(str);
                break;
            case am.f9249v /*19*/:
                a.m6358e(str);
                break;
            case Util.MAX_ACCOUNT_LENGTH /*20*/:
                a.m6362g(str);
                break;
            case Opcodes.ILOAD /*21*/:
                return b.m6315a(str);
            case Opcodes.LLOAD /*22*/:
                a.m6348a(str);
                break;
            case Opcodes.FLOAD /*23*/:
                a.m6351b(str);
                break;
            case Opcodes.DLOAD /*24*/:
                this.f4619b.m6832a(Integer.parseInt(str));
                break;
            case Opcodes.ALOAD /*25*/:
                C0797j valueOf;
                try {
                    valueOf = C0797j.valueOf(str);
                } catch (Exception e) {
                    valueOf = C0797j.DEFAULT;
                }
                this.f4618a.m6834a(valueOf);
                break;
            case am.f9219C /*26*/:
                b.m6318b(str);
                break;
        }
        return 0;
    }

    public int m6882a(C0794g c0794g, String str) {
        return m6881b(c0794g, str);
    }

    public C0824b m6883a() {
        return this.f4618a;
    }

    public C0823a m6884b() {
        return this.f4619b;
    }

    public C0725b m6885c() {
        return this.f4618a.m6833a();
    }

    public C0721a m6886d() {
        return this.f4618a.m6836b();
    }

    public C0743a m6887e() {
        return this.f4619b.m6831a();
    }
}
