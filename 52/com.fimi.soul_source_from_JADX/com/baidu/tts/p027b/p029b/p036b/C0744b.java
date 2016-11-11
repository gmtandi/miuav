package com.baidu.tts.p027b.p029b.p036b;

import android.media.AudioTrack;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p027b.p029b.C0677a;
import com.baidu.tts.p034l.C0742c;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p041e.C0798k;
import com.baidu.tts.p047h.p048a.C0810a;
import com.baidu.tts.p047h.p048a.C0811b;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.baidu.tts.b.b.b.b */
public class C0744b extends C0741a {
    private volatile AudioTrack f4188b;
    private C0743a f4189c;
    private C0811b f4190d;
    private int f4191e;

    /* renamed from: com.baidu.tts.b.b.b.b.a */
    public class C0743a extends C0742c<C0743a> {
        private C0798k f4182a;
        private int f4183b;
        private int f4184c;
        private int f4185d;
        private float f4186e;
        private float f4187f;

        public C0743a() {
            this.f4182a = C0798k.HZ16K;
            this.f4183b = 4;
            this.f4184c = 2;
            this.f4185d = 1;
            this.f4186e = C2020f.f10933c;
            this.f4187f = C2020f.f10933c;
        }

        public int m6485a() {
            return this.f4182a.m6746a();
        }

        public void m6486a(float f) {
            this.f4186e = f;
        }

        public int m6487b() {
            return this.f4183b;
        }

        public void m6488b(float f) {
            this.f4187f = f;
        }

        public int m6489c() {
            return this.f4184c;
        }

        public int m6490d() {
            return this.f4185d;
        }

        public float m6491e() {
            return this.f4186e;
        }

        public float m6492f() {
            return this.f4187f;
        }
    }

    public C0744b() {
        this.f4190d = new C0811b();
    }

    private int m6493a(int i, int i2, int i3) {
        int i4;
        int i5 = 2;
        int minBufferSize = AudioTrack.getMinBufferSize(i, i2, i3) * 2;
        switch (i2) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                i4 = 1;
                break;
            case Type.BYTE /*3*/:
            case Opcodes.FCONST_1 /*12*/:
                i4 = 2;
                break;
            default:
                i4 = Integer.bitCount(i2);
                break;
        }
        if (i3 == 3) {
            i5 = 1;
        }
        return (minBufferSize % (i5 * i4) != 0 || minBufferSize < 1) ? 5120 : minBufferSize;
    }

    private int m6494b(int i) {
        if (i > this.f4191e) {
            this.f4191e = i;
        }
        return this.f4191e;
    }

    private void m6495b(C0828h c0828h) {
        this.f4190d.m6793a(m6498e());
        this.f4190d.m6792a();
        this.f4191e = 0;
        m6497d(c0828h);
    }

    private void m6496c(C0828h c0828h) {
        this.f4190d.m6794b();
        m6500f(c0828h);
    }

    private void m6497d(C0828h c0828h) {
        if (this.a != null) {
            this.a.m6025a(c0828h);
        }
    }

    private int m6498e() {
        return (this.f4189c.m6485a() * 2) / this.f4189c.m6484h();
    }

    private void m6499e(C0828h c0828h) {
        if (this.a != null) {
            this.a.m6026b(c0828h);
        }
    }

    private void m6500f(C0828h c0828h) {
        if (this.a != null) {
            this.a.m6027c(c0828h);
        }
    }

    public int m6501a(float f, float f2) {
        int stereoVolume = this.f4188b.setStereoVolume(f, f2);
        this.f4189c.m6486a(f);
        this.f4189c.m6488b(f2);
        return stereoVolume;
    }

    public int m6502a(int i) {
        if (i != this.f4189c.m6483g()) {
            int a = this.f4189c.m6485a();
            int b = this.f4189c.m6487b();
            int c = this.f4189c.m6489c();
            int i2 = i;
            this.f4188b = new AudioTrack(i2, a, b, c, m6493a(a, b, c), this.f4189c.m6490d());
            this.f4189c.m6481a(i);
            this.f4188b.setStereoVolume(this.f4189c.m6491e(), this.f4189c.m6492f());
            this.f4188b.play();
        }
        return 0;
    }

    public TtsError m6503a() {
        int a = this.f4189c.m6485a();
        int b = this.f4189c.m6487b();
        int c = this.f4189c.m6489c();
        this.f4188b = new AudioTrack(this.f4189c.m6483g(), a, b, c, m6493a(a, b, c), this.f4189c.m6490d());
        this.f4188b.setStereoVolume(this.f4189c.m6491e(), this.f4189c.m6492f());
        return null;
    }

    public TtsError m6504a(C0828h c0828h) {
        LoggerProxy.m6515d("AudioTrackPlayer", "enter put");
        if (c0828h != null) {
            C0828h c0828h2;
            int b = c0828h.m6861b();
            if (b == 1 || b == -1) {
                m6495b(c0828h);
            }
            byte[] d = c0828h.m6866d();
            if (d != null) {
                this.f4190d.m6795b(d.length);
            }
            while (this.f4190d.hasNext()) {
                C0810a c = this.f4190d.m6796c();
                int i = 0;
                int a = c.m6782a();
                int b2 = c.m6786b();
                while (i < b2 && this.f4188b.getPlayState() != 1) {
                    int i2 = b2 - i;
                    LoggerProxy.m6515d("AudioTrackPlayer", "before write");
                    i2 = this.f4188b.write(d, i + a, i2);
                    LoggerProxy.m6515d("AudioTrackPlayer", "writtenbytes=" + i2 + "--offset=" + i + "--dataLength=" + b2);
                    i += i2;
                }
                if (this.f4188b.getPlayState() == 1) {
                    break;
                } else if (c.m6788c()) {
                    i = c0828h.m6863c();
                    float d2 = c.m6789d();
                    i = Math.round(((float) i) * d2);
                    a = m6494b(i);
                    LoggerProxy.m6515d("AudioTrackPlayer", "percent=" + d2 + "--currentProgress=" + i + "--progress=" + a);
                    c0828h2 = (C0828h) c0828h.m6300y();
                    c0828h2.m6864c(a);
                    m6499e(c0828h2);
                }
            }
            if (b < 0) {
                c0828h2 = (C0828h) c0828h.m6300y();
                c0828h2.m6864c(c0828h.m6863c());
                m6499e(c0828h2);
                m6496c(c0828h);
            }
            LoggerProxy.m6515d("AudioTrackPlayer", "end put");
        } else {
            LoggerProxy.m6515d("AudioTrackPlayer", "put responseBag=null");
            LoggerProxy.m6515d("AudioTrackPlayer", "end put");
        }
        return null;
    }

    public void m6505a(C0677a c0677a) {
        this.a = c0677a;
    }

    public <AudioTrackPlayerParams> void m6506a(AudioTrackPlayerParams audioTrackPlayerParams) {
        this.f4189c = (C0743a) audioTrackPlayerParams;
    }

    public void m6507b() {
        this.f4188b.play();
    }

    public void m6508c() {
        this.f4188b.pause();
        this.f4188b.flush();
        this.f4188b.stop();
    }

    public TtsError m6509d() {
        this.f4188b.release();
        this.f4188b = null;
        return null;
    }
}
