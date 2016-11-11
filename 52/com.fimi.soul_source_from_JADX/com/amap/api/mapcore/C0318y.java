package com.amap.api.mapcore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

@SuppressLint({"NewApi"})
/* renamed from: com.amap.api.mapcore.y */
public class C0318y extends TextureView implements SurfaceTextureListener {
    private static final C0421j f1931a;
    private final WeakReference<C0318y> f1932b;
    private C0420i f1933c;
    private Renderer f1934d;
    private boolean f1935e;
    private C0412e f1936f;
    private C0415f f1937g;
    private C0417g f1938h;
    private C0422k f1939i;
    private int f1940j;
    private int f1941k;
    private boolean f1942l;

    /* renamed from: com.amap.api.mapcore.y.e */
    public interface C0412e {
        EGLConfig m4258a(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    /* renamed from: com.amap.api.mapcore.y.a */
    abstract class C0413a implements C0412e {
        protected int[] f2555a;
        final /* synthetic */ C0318y f2556b;

        public C0413a(C0318y c0318y, int[] iArr) {
            this.f2556b = c0318y;
            this.f2555a = m4259a(iArr);
        }

        private int[] m4259a(int[] iArr) {
            if (this.f2556b.f1941k != 2 && this.f2556b.f1941k != 3) {
                return iArr;
            }
            int length = iArr.length;
            Object obj = new int[(length + 2)];
            System.arraycopy(iArr, 0, obj, 0, length - 1);
            obj[length - 1] = 12352;
            if (this.f2556b.f1941k == 2) {
                obj[length] = 4;
            } else {
                obj[length] = 64;
            }
            obj[length + 1] = 12344;
            return obj;
        }

        public EGLConfig m4260a(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.f2555a, null, 0, iArr)) {
                int i = iArr[0];
                if (i <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                if (egl10.eglChooseConfig(eGLDisplay, this.f2555a, eGLConfigArr, i, iArr)) {
                    EGLConfig a = m4261a(egl10, eGLDisplay, eGLConfigArr);
                    if (a != null) {
                        return a;
                    }
                    throw new IllegalArgumentException("No config chosen");
                }
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        abstract EGLConfig m4261a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);
    }

    /* renamed from: com.amap.api.mapcore.y.b */
    class C0414b extends C0413a {
        protected int f2557c;
        protected int f2558d;
        protected int f2559e;
        protected int f2560f;
        protected int f2561g;
        protected int f2562h;
        final /* synthetic */ C0318y f2563i;
        private int[] f2564j;

        public C0414b(C0318y c0318y, int i, int i2, int i3, int i4, int i5, int i6) {
            this.f2563i = c0318y;
            super(c0318y, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.f2564j = new int[1];
            this.f2557c = i;
            this.f2558d = i2;
            this.f2559e = i3;
            this.f2560f = i4;
            this.f2561g = i5;
            this.f2562h = i6;
        }

        private int m4262a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f2564j) ? this.f2564j[0] : i2;
        }

        public EGLConfig m4263a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = m4262a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = m4262a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.f2561g && a2 >= this.f2562h) {
                    a = m4262a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a3 = m4262a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a4 = m4262a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    a2 = m4262a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.f2557c && a3 == this.f2558d && a4 == this.f2559e && a2 == this.f2560f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }
    }

    /* renamed from: com.amap.api.mapcore.y.f */
    public interface C0415f {
        EGLContext m4264a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void m4265a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    /* renamed from: com.amap.api.mapcore.y.c */
    class C0416c implements C0415f {
        final /* synthetic */ C0318y f2565a;
        private int f2566b;

        private C0416c(C0318y c0318y) {
            this.f2565a = c0318y;
            this.f2566b = 12440;
        }

        public EGLContext m4266a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{this.f2566b, this.f2565a.f1941k, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (this.f2565a.f1941k == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public void m4267a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
                C0419h.m4273a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.y.g */
    public interface C0417g {
        EGLSurface m4268a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void m4269a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    /* renamed from: com.amap.api.mapcore.y.d */
    class C0418d implements C0417g {
        private C0418d() {
        }

        public EGLSurface m4270a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            EGLSurface eGLSurface = null;
            try {
                eGLSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (Throwable e) {
                Log.e("GLSurfaceView", "eglCreateWindowSurface", e);
            }
            return eGLSurface;
        }

        public void m4271a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* renamed from: com.amap.api.mapcore.y.h */
    class C0419h {
        EGL10 f2567a;
        EGLDisplay f2568b;
        EGLSurface f2569c;
        EGLConfig f2570d;
        EGLContext f2571e;
        private WeakReference<C0318y> f2572f;

        public C0419h(WeakReference<C0318y> weakReference) {
            this.f2572f = weakReference;
        }

        private void m4272a(String str) {
            C0419h.m4273a(str, this.f2567a.eglGetError());
        }

        public static void m4273a(String str, int i) {
            throw new RuntimeException(C0419h.m4275b(str, i));
        }

        public static void m4274a(String str, String str2, int i) {
            Log.w(str, C0419h.m4275b(str2, i));
        }

        public static String m4275b(String str, int i) {
            return str + " failed: " + i;
        }

        private void m4276g() {
            if (this.f2569c != null && this.f2569c != EGL10.EGL_NO_SURFACE) {
                this.f2567a.eglMakeCurrent(this.f2568b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                C0318y c0318y = (C0318y) this.f2572f.get();
                if (c0318y != null) {
                    c0318y.f1938h.m4269a(this.f2567a, this.f2568b, this.f2569c);
                }
                this.f2569c = null;
            }
        }

        public void m4277a() {
            this.f2567a = (EGL10) EGLContext.getEGL();
            this.f2568b = this.f2567a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.f2568b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (this.f2567a.eglInitialize(this.f2568b, new int[2])) {
                C0318y c0318y = (C0318y) this.f2572f.get();
                if (c0318y == null) {
                    this.f2570d = null;
                    this.f2571e = null;
                } else {
                    this.f2570d = c0318y.f1936f.m4258a(this.f2567a, this.f2568b);
                    this.f2571e = c0318y.f1937g.m4264a(this.f2567a, this.f2568b, this.f2570d);
                }
                if (this.f2571e == null || this.f2571e == EGL10.EGL_NO_CONTEXT) {
                    this.f2571e = null;
                    m4272a("createContext");
                }
                this.f2569c = null;
                return;
            }
            throw new RuntimeException("eglInitialize failed");
        }

        public boolean m4278b() {
            if (this.f2567a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.f2568b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.f2570d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            } else {
                m4276g();
                C0318y c0318y = (C0318y) this.f2572f.get();
                if (c0318y != null) {
                    this.f2569c = c0318y.f1938h.m4268a(this.f2567a, this.f2568b, this.f2570d, c0318y.getSurfaceTexture());
                } else {
                    this.f2569c = null;
                }
                if (this.f2569c == null || this.f2569c == EGL10.EGL_NO_SURFACE) {
                    if (this.f2567a.eglGetError() == 12299) {
                        Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                    }
                    return false;
                } else if (this.f2567a.eglMakeCurrent(this.f2568b, this.f2569c, this.f2569c, this.f2571e)) {
                    return true;
                } else {
                    C0419h.m4274a("EGLHelper", "eglMakeCurrent", this.f2567a.eglGetError());
                    return false;
                }
            }
        }

        GL m4279c() {
            GL gl = this.f2571e.getGL();
            C0318y c0318y = (C0318y) this.f2572f.get();
            if (c0318y == null) {
                return gl;
            }
            if (c0318y.f1939i != null) {
                gl = c0318y.f1939i.m4307a(gl);
            }
            if ((c0318y.f1940j & 3) == 0) {
                return gl;
            }
            int i = 0;
            if ((c0318y.f1940j & 1) != 0) {
                i = 1;
            }
            return GLDebugHelper.wrap(gl, i, (c0318y.f1940j & 2) != 0 ? new C0423l() : null);
        }

        public int m4280d() {
            return !this.f2567a.eglSwapBuffers(this.f2568b, this.f2569c) ? this.f2567a.eglGetError() : 12288;
        }

        public void m4281e() {
            m4276g();
        }

        public void m4282f() {
            if (this.f2571e != null) {
                C0318y c0318y = (C0318y) this.f2572f.get();
                if (c0318y != null) {
                    c0318y.f1937g.m4265a(this.f2567a, this.f2568b, this.f2571e);
                }
                this.f2571e = null;
            }
            if (this.f2568b != null) {
                this.f2567a.eglTerminate(this.f2568b);
                this.f2568b = null;
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.y.i */
    class C0420i extends Thread {
        private boolean f2573a;
        private boolean f2574b;
        private boolean f2575c;
        private boolean f2576d;
        private boolean f2577e;
        private boolean f2578f;
        private boolean f2579g;
        private boolean f2580h;
        private boolean f2581i;
        private boolean f2582j;
        private boolean f2583k;
        private int f2584l;
        private int f2585m;
        private int f2586n;
        private boolean f2587o;
        private boolean f2588p;
        private ArrayList<Runnable> f2589q;
        private boolean f2590r;
        private C0419h f2591s;
        private WeakReference<C0318y> f2592t;

        C0420i(WeakReference<C0318y> weakReference) {
            this.f2589q = new ArrayList();
            this.f2590r = true;
            this.f2584l = 0;
            this.f2585m = 0;
            this.f2587o = true;
            this.f2586n = 1;
            this.f2592t = weakReference;
        }

        private void m4284j() {
            if (this.f2581i) {
                this.f2581i = false;
                this.f2591s.m4281e();
            }
        }

        private void m4285k() {
            if (this.f2580h) {
                this.f2591s.m4282f();
                this.f2580h = false;
                C0318y.f1931a.m4306c(this);
            }
        }

        private void m4286l() {
            this.f2591s = new C0419h(this.f2592t);
            this.f2580h = false;
            this.f2581i = false;
            Object obj = null;
            GL10 gl10 = null;
            int i = 0;
            Object obj2 = null;
            Object obj3 = null;
            Object obj4 = null;
            Object obj5 = null;
            Object obj6 = null;
            Object obj7 = null;
            Runnable runnable = null;
            int i2 = 0;
            Object obj8 = null;
            while (true) {
                try {
                    synchronized (C0318y.f1931a) {
                        while (true) {
                            if (this.f2573a) {
                                synchronized (C0318y.f1931a) {
                                    m4284j();
                                    m4285k();
                                }
                                return;
                            }
                            C0318y c0318y;
                            Object obj9;
                            int i3;
                            Runnable runnable2;
                            Object obj10;
                            int i4;
                            int i5;
                            if (this.f2589q.isEmpty()) {
                                boolean z = this.f2576d;
                                boolean z2 = this.f2575c;
                                if (z != r0) {
                                    boolean z3 = this.f2575c;
                                    this.f2576d = this.f2575c;
                                    C0318y.f1931a.notifyAll();
                                    z = z3;
                                } else {
                                    z = false;
                                }
                                if (this.f2583k) {
                                    m4284j();
                                    m4285k();
                                    this.f2583k = false;
                                    obj2 = 1;
                                }
                                if (obj5 != null) {
                                    m4284j();
                                    m4285k();
                                    obj5 = null;
                                }
                                if (z && this.f2581i) {
                                    m4284j();
                                }
                                if (z && this.f2580h) {
                                    c0318y = (C0318y) this.f2592t.get();
                                    if (!(c0318y == null ? false : c0318y.f1942l) || C0318y.f1931a.m4303a()) {
                                        m4285k();
                                    }
                                }
                                if (z && C0318y.f1931a.m4304b()) {
                                    this.f2591s.m4282f();
                                }
                                if (!(this.f2577e || this.f2579g)) {
                                    if (this.f2581i) {
                                        m4284j();
                                    }
                                    this.f2579g = true;
                                    this.f2578f = false;
                                    C0318y.f1931a.notifyAll();
                                }
                                if (this.f2577e && this.f2579g) {
                                    this.f2579g = false;
                                    C0318y.f1931a.notifyAll();
                                }
                                if (obj8 != null) {
                                    obj3 = null;
                                    obj8 = null;
                                    this.f2588p = true;
                                    C0318y.f1931a.notifyAll();
                                }
                                if (m4287m()) {
                                    if (!this.f2580h) {
                                        if (obj2 != null) {
                                            obj2 = null;
                                        } else if (C0318y.f1931a.m4305b(this)) {
                                            this.f2591s.m4277a();
                                            this.f2580h = true;
                                            obj = 1;
                                            C0318y.f1931a.notifyAll();
                                        }
                                    }
                                    if (!this.f2580h || this.f2581i) {
                                        obj9 = obj4;
                                        obj4 = obj6;
                                    } else {
                                        this.f2581i = true;
                                        obj7 = 1;
                                        obj9 = 1;
                                        obj4 = 1;
                                    }
                                    if (this.f2581i) {
                                        Object obj11;
                                        int i6;
                                        if (this.f2590r) {
                                            obj3 = 1;
                                            i = this.f2584l;
                                            i3 = this.f2585m;
                                            obj11 = 1;
                                            obj6 = 1;
                                            this.f2590r = false;
                                        } else {
                                            obj6 = obj7;
                                            i6 = i2;
                                            obj11 = obj3;
                                            obj3 = obj9;
                                            i3 = i;
                                            i = i6;
                                        }
                                        this.f2587o = false;
                                        C0318y.f1931a.notifyAll();
                                        obj7 = obj4;
                                        obj4 = obj11;
                                        runnable2 = runnable;
                                        obj10 = obj8;
                                        i4 = i;
                                        i6 = i3;
                                        obj9 = obj6;
                                        obj6 = obj5;
                                        obj5 = obj3;
                                        obj3 = obj2;
                                        i5 = i6;
                                    } else {
                                        obj6 = obj4;
                                        obj4 = obj9;
                                    }
                                }
                                C0318y.f1931a.wait();
                            } else {
                                obj10 = obj8;
                                i4 = i2;
                                runnable2 = (Runnable) this.f2589q.remove(0);
                                obj9 = obj7;
                                obj7 = obj6;
                                obj6 = obj5;
                                obj5 = obj4;
                                obj4 = obj3;
                                obj3 = obj2;
                                i5 = i;
                            }
                            Object obj12;
                            if (runnable2 != null) {
                                runnable2.run();
                                i = i5;
                                obj2 = obj3;
                                obj3 = obj4;
                                obj4 = obj5;
                                obj5 = obj6;
                                obj6 = obj7;
                                obj7 = obj9;
                                obj12 = obj10;
                                runnable = null;
                                i2 = i4;
                                obj8 = obj12;
                            } else {
                                Object obj13;
                                GL10 gl102;
                                if (obj9 == null) {
                                    obj13 = obj9;
                                } else if (this.f2591s.m4278b()) {
                                    synchronized (C0318y.f1931a) {
                                        this.f2582j = true;
                                        C0318y.f1931a.notifyAll();
                                    }
                                    obj13 = null;
                                } else {
                                    synchronized (C0318y.f1931a) {
                                        this.f2582j = true;
                                        this.f2578f = true;
                                        C0318y.f1931a.notifyAll();
                                    }
                                    i = i5;
                                    obj2 = obj3;
                                    obj3 = obj4;
                                    obj4 = obj5;
                                    obj5 = obj6;
                                    obj6 = obj7;
                                    obj7 = obj9;
                                    obj12 = obj10;
                                    runnable = runnable2;
                                    i2 = i4;
                                    obj8 = obj12;
                                }
                                if (obj7 != null) {
                                    GL10 gl103 = (GL10) this.f2591s.m4279c();
                                    C0318y.f1931a.m4302a(gl103);
                                    obj7 = null;
                                    gl102 = gl103;
                                } else {
                                    gl102 = gl10;
                                }
                                if (obj != null) {
                                    c0318y = (C0318y) this.f2592t.get();
                                    if (c0318y != null) {
                                        c0318y.f1934d.onSurfaceCreated(gl102, this.f2591s.f2570d);
                                    }
                                    obj = null;
                                }
                                if (obj5 != null) {
                                    c0318y = (C0318y) this.f2592t.get();
                                    if (c0318y != null) {
                                        c0318y.f1934d.onSurfaceChanged(gl102, i4, i5);
                                    }
                                    obj5 = null;
                                }
                                c0318y = (C0318y) this.f2592t.get();
                                if (c0318y != null) {
                                    c0318y.f1934d.onDrawFrame(gl102);
                                }
                                i3 = this.f2591s.m4280d();
                                switch (i3) {
                                    case 12288:
                                        break;
                                    case 12302:
                                        obj6 = 1;
                                        break;
                                    default:
                                        C0419h.m4274a("GLThread", "eglSwapBuffers", i3);
                                        synchronized (C0318y.f1931a) {
                                            this.f2578f = true;
                                            C0318y.f1931a.notifyAll();
                                            break;
                                        }
                                }
                                obj9 = obj4 != null ? 1 : obj10;
                                runnable = runnable2;
                                gl10 = gl102;
                                i2 = i4;
                                obj8 = obj9;
                                obj12 = obj3;
                                obj3 = obj4;
                                obj4 = obj5;
                                obj5 = obj6;
                                obj6 = obj7;
                                obj7 = obj13;
                                i = i5;
                                obj2 = obj12;
                            }
                        }
                    }
                } catch (RuntimeException e) {
                    C0318y.f1931a.m4306c(this);
                    throw e;
                } catch (Throwable th) {
                    synchronized (C0318y.f1931a) {
                    }
                    m4284j();
                    m4285k();
                }
            }
        }

        private boolean m4287m() {
            return !this.f2576d && this.f2577e && !this.f2578f && this.f2584l > 0 && this.f2585m > 0 && (this.f2587o || this.f2586n == 1);
        }

        public void m4288a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (C0318y.f1931a) {
                this.f2586n = i;
                C0318y.f1931a.notifyAll();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m4289a(int r3, int r4) {
            /*
            r2 = this;
            r1 = com.amap.api.mapcore.C0318y.f1931a;
            monitor-enter(r1);
            r2.f2584l = r3;	 Catch:{ all -> 0x003c }
            r2.f2585m = r4;	 Catch:{ all -> 0x003c }
            r0 = 1;
            r2.f2590r = r0;	 Catch:{ all -> 0x003c }
            r0 = 1;
            r2.f2587o = r0;	 Catch:{ all -> 0x003c }
            r0 = 0;
            r2.f2588p = r0;	 Catch:{ all -> 0x003c }
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ all -> 0x003c }
            r0.notifyAll();	 Catch:{ all -> 0x003c }
        L_0x0019:
            r0 = r2.f2574b;	 Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x003f;
        L_0x001d:
            r0 = r2.f2576d;	 Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x003f;
        L_0x0021:
            r0 = r2.f2588p;	 Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x003f;
        L_0x0025:
            r0 = r2.m4291a();	 Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x003f;
        L_0x002b:
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ InterruptedException -> 0x0033 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0033 }
            goto L_0x0019;
        L_0x0033:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x003c }
            r0.interrupt();	 Catch:{ all -> 0x003c }
            goto L_0x0019;
        L_0x003c:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x003c }
            throw r0;
        L_0x003f:
            monitor-exit(r1);	 Catch:{ all -> 0x003c }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.y.i.a(int, int):void");
        }

        public void m4290a(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (C0318y.f1931a) {
                this.f2589q.add(runnable);
                C0318y.f1931a.notifyAll();
            }
        }

        public boolean m4291a() {
            return this.f2580h && this.f2581i && m4287m();
        }

        public int m4292b() {
            int i;
            synchronized (C0318y.f1931a) {
                i = this.f2586n;
            }
            return i;
        }

        public void m4293c() {
            synchronized (C0318y.f1931a) {
                this.f2587o = true;
                C0318y.f1931a.notifyAll();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m4294d() {
            /*
            r2 = this;
            r1 = com.amap.api.mapcore.C0318y.f1931a;
            monitor-enter(r1);
            r0 = 1;
            r2.f2577e = r0;	 Catch:{ all -> 0x002f }
            r0 = 0;
            r2.f2582j = r0;	 Catch:{ all -> 0x002f }
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ all -> 0x002f }
            r0.notifyAll();	 Catch:{ all -> 0x002f }
        L_0x0012:
            r0 = r2.f2579g;	 Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0032;
        L_0x0016:
            r0 = r2.f2582j;	 Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x0032;
        L_0x001a:
            r0 = r2.f2574b;	 Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x0032;
        L_0x001e:
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ InterruptedException -> 0x0026 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0026 }
            goto L_0x0012;
        L_0x0026:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x002f }
            r0.interrupt();	 Catch:{ all -> 0x002f }
            goto L_0x0012;
        L_0x002f:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            throw r0;
        L_0x0032:
            monitor-exit(r1);	 Catch:{ all -> 0x002f }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.y.i.d():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m4295e() {
            /*
            r2 = this;
            r1 = com.amap.api.mapcore.C0318y.f1931a;
            monitor-enter(r1);
            r0 = 0;
            r2.f2577e = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f2579g;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f2574b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ InterruptedException -> 0x001f }
            r0.wait();	 Catch:{ InterruptedException -> 0x001f }
            goto L_0x000f;
        L_0x001f:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0028 }
            r0.interrupt();	 Catch:{ all -> 0x0028 }
            goto L_0x000f;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            throw r0;
        L_0x002b:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.y.i.e():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m4296f() {
            /*
            r2 = this;
            r1 = com.amap.api.mapcore.C0318y.f1931a;
            monitor-enter(r1);
            r0 = 1;
            r2.f2575c = r0;	 Catch:{ all -> 0x0028 }
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ all -> 0x0028 }
            r0.notifyAll();	 Catch:{ all -> 0x0028 }
        L_0x000f:
            r0 = r2.f2574b;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0013:
            r0 = r2.f2576d;	 Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x002b;
        L_0x0017:
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ InterruptedException -> 0x001f }
            r0.wait();	 Catch:{ InterruptedException -> 0x001f }
            goto L_0x000f;
        L_0x001f:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0028 }
            r0.interrupt();	 Catch:{ all -> 0x0028 }
            goto L_0x000f;
        L_0x0028:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            throw r0;
        L_0x002b:
            monitor-exit(r1);	 Catch:{ all -> 0x0028 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.y.i.f():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m4297g() {
            /*
            r2 = this;
            r1 = com.amap.api.mapcore.C0318y.f1931a;
            monitor-enter(r1);
            r0 = 0;
            r2.f2575c = r0;	 Catch:{ all -> 0x0032 }
            r0 = 1;
            r2.f2587o = r0;	 Catch:{ all -> 0x0032 }
            r0 = 0;
            r2.f2588p = r0;	 Catch:{ all -> 0x0032 }
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ all -> 0x0032 }
            r0.notifyAll();	 Catch:{ all -> 0x0032 }
        L_0x0015:
            r0 = r2.f2574b;	 Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0035;
        L_0x0019:
            r0 = r2.f2576d;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x0035;
        L_0x001d:
            r0 = r2.f2588p;	 Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0035;
        L_0x0021:
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ InterruptedException -> 0x0029 }
            r0.wait();	 Catch:{ InterruptedException -> 0x0029 }
            goto L_0x0015;
        L_0x0029:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0032 }
            r0.interrupt();	 Catch:{ all -> 0x0032 }
            goto L_0x0015;
        L_0x0032:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0032 }
            throw r0;
        L_0x0035:
            monitor-exit(r1);	 Catch:{ all -> 0x0032 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.y.i.g():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m4298h() {
            /*
            r2 = this;
            r1 = com.amap.api.mapcore.C0318y.f1931a;
            monitor-enter(r1);
            r0 = 1;
            r2.f2573a = r0;	 Catch:{ all -> 0x0024 }
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ all -> 0x0024 }
            r0.notifyAll();	 Catch:{ all -> 0x0024 }
        L_0x000f:
            r0 = r2.f2574b;	 Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x0027;
        L_0x0013:
            r0 = com.amap.api.mapcore.C0318y.f1931a;	 Catch:{ InterruptedException -> 0x001b }
            r0.wait();	 Catch:{ InterruptedException -> 0x001b }
            goto L_0x000f;
        L_0x001b:
            r0 = move-exception;
            r0 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0024 }
            r0.interrupt();	 Catch:{ all -> 0x0024 }
            goto L_0x000f;
        L_0x0024:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0024 }
            throw r0;
        L_0x0027:
            monitor-exit(r1);	 Catch:{ all -> 0x0024 }
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.y.i.h():void");
        }

        public void m4299i() {
            this.f2583k = true;
            C0318y.f1931a.notifyAll();
        }

        public void run() {
            setName("GLThread " + getId());
            try {
                m4286l();
            } catch (InterruptedException e) {
            } finally {
                C0318y.f1931a.m4301a(this);
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.y.j */
    class C0421j {
        private static String f2593a;
        private boolean f2594b;
        private int f2595c;
        private boolean f2596d;
        private boolean f2597e;
        private boolean f2598f;
        private C0420i f2599g;

        static {
            f2593a = "GLThreadManager";
        }

        private C0421j() {
        }

        private void m4300c() {
            if (!this.f2594b) {
                this.f2595c = Opcodes.ACC_DEPRECATED;
                if (this.f2595c >= Opcodes.ACC_DEPRECATED) {
                    this.f2597e = true;
                }
                this.f2594b = true;
            }
        }

        public synchronized void m4301a(C0420i c0420i) {
            c0420i.f2574b = true;
            if (this.f2599g == c0420i) {
                this.f2599g = null;
            }
            notifyAll();
        }

        public synchronized void m4302a(GL10 gl10) {
            boolean z = true;
            synchronized (this) {
                if (!this.f2596d) {
                    m4300c();
                    String glGetString = gl10.glGetString(7937);
                    if (this.f2595c < Opcodes.ACC_DEPRECATED) {
                        this.f2597e = !glGetString.startsWith("Q3Dimension MSM7500 ");
                        notifyAll();
                    }
                    if (this.f2597e) {
                        z = false;
                    }
                    this.f2598f = z;
                    this.f2596d = true;
                }
            }
        }

        public synchronized boolean m4303a() {
            return this.f2598f;
        }

        public synchronized boolean m4304b() {
            m4300c();
            return !this.f2597e;
        }

        public boolean m4305b(C0420i c0420i) {
            if (this.f2599g == c0420i || this.f2599g == null) {
                this.f2599g = c0420i;
                notifyAll();
                return true;
            }
            m4300c();
            if (this.f2597e) {
                return true;
            }
            if (this.f2599g != null) {
                this.f2599g.m4299i();
            }
            return false;
        }

        public void m4306c(C0420i c0420i) {
            if (this.f2599g == c0420i) {
                this.f2599g = null;
            }
            notifyAll();
        }
    }

    /* renamed from: com.amap.api.mapcore.y.k */
    public interface C0422k {
        GL m4307a(GL gl);
    }

    /* renamed from: com.amap.api.mapcore.y.l */
    class C0423l extends Writer {
        private StringBuilder f2600a;

        C0423l() {
            this.f2600a = new StringBuilder();
        }

        private void m4308a() {
            if (this.f2600a.length() > 0) {
                Log.v("GLSurfaceView", this.f2600a.toString());
                this.f2600a.delete(0, this.f2600a.length());
            }
        }

        public void close() {
            m4308a();
        }

        public void flush() {
            m4308a();
        }

        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    m4308a();
                } else {
                    this.f2600a.append(c);
                }
            }
        }
    }

    /* renamed from: com.amap.api.mapcore.y.m */
    class C0424m extends C0414b {
        final /* synthetic */ C0318y f2601j;

        public C0424m(C0318y c0318y, boolean z) {
            this.f2601j = c0318y;
            super(c0318y, 8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    static {
        f1931a = new C0421j();
    }

    public C0318y(Context context) {
        super(context);
        this.f1932b = new WeakReference(this);
        m3232a();
    }

    public C0318y(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1932b = new WeakReference(this);
        m3232a();
    }

    private void m3232a() {
        setSurfaceTextureListener(this);
    }

    private void m3238e() {
        if (this.f1933c != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public void m3242b() {
        this.f1933c.m4296f();
    }

    public void m3243c() {
        this.f1933c.m4297g();
    }

    protected void finalize() {
        try {
            if (this.f1933c != null) {
                this.f1933c.m4298h();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f1935e && this.f1934d != null) {
            int b = this.f1933c != null ? this.f1933c.m4292b() : 1;
            this.f1933c = new C0420i(this.f1932b);
            if (b != 1) {
                this.f1933c.m4288a(b);
            }
            this.f1933c.start();
        }
        this.f1935e = false;
    }

    protected void onDetachedFromWindow() {
        if (this.f1933c != null) {
            this.f1933c.m4298h();
        }
        this.f1935e = true;
        super.onDetachedFromWindow();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        onSurfaceTextureSizeChanged(getSurfaceTexture(), i3 - i, i4 - i2);
        super.onLayout(z, i, i2, i3, i4);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.f1933c.m4294d();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.f1933c.m4295e();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.f1933c.m4289a(i, i2);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void queueEvent(Runnable runnable) {
        this.f1933c.m4290a(runnable);
    }

    public void requestRender() {
        this.f1933c.m4293c();
    }

    public void setRenderMode(int i) {
        this.f1933c.m4288a(i);
    }

    public void setRenderer(Renderer renderer) {
        m3238e();
        if (this.f1936f == null) {
            this.f1936f = new C0424m(this, true);
        }
        if (this.f1937g == null) {
            this.f1937g = new C0416c();
        }
        if (this.f1938h == null) {
            this.f1938h = new C0418d();
        }
        this.f1934d = renderer;
        this.f1933c = new C0420i(this.f1932b);
        this.f1933c.start();
    }
}
