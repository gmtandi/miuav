package com.p017b.p020c;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import com.p017b.p018a.C0620b;
import java.lang.ref.WeakReference;

/* renamed from: com.b.c.i */
class C0656i extends C0650c {
    private static final long f3977a = -1;
    private final WeakReference<ViewPropertyAnimator> f3978b;

    C0656i(View view) {
        this.f3978b = new WeakReference(view.animate());
    }

    public long m5875a() {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        return viewPropertyAnimator != null ? viewPropertyAnimator.getDuration() : f3977a;
    }

    public C0650c m5876a(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.x(f);
        }
        return this;
    }

    public C0650c m5877a(long j) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setDuration(j);
        }
        return this;
    }

    public C0650c m5878a(Interpolator interpolator) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setInterpolator(interpolator);
        }
        return this;
    }

    public C0650c m5879a(C0620b c0620b) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            if (c0620b == null) {
                viewPropertyAnimator.setListener(null);
            } else {
                viewPropertyAnimator.setListener(new C0657j(this, c0620b));
            }
        }
        return this;
    }

    public long m5880b() {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        return viewPropertyAnimator != null ? viewPropertyAnimator.getStartDelay() : f3977a;
    }

    public C0650c m5881b(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.xBy(f);
        }
        return this;
    }

    public C0650c m5882b(long j) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setStartDelay(j);
        }
        return this;
    }

    public C0650c m5883c(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.y(f);
        }
        return this;
    }

    public void m5884c() {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.start();
        }
    }

    public C0650c m5885d(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.yBy(f);
        }
        return this;
    }

    public void m5886d() {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public C0650c m5887e(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.rotation(f);
        }
        return this;
    }

    public C0650c m5888f(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.rotationBy(f);
        }
        return this;
    }

    public C0650c m5889g(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.rotationX(f);
        }
        return this;
    }

    public C0650c m5890h(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.rotationXBy(f);
        }
        return this;
    }

    public C0650c m5891i(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.rotationY(f);
        }
        return this;
    }

    public C0650c m5892j(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.rotationYBy(f);
        }
        return this;
    }

    public C0650c m5893k(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.translationX(f);
        }
        return this;
    }

    public C0650c m5894l(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.translationXBy(f);
        }
        return this;
    }

    public C0650c m5895m(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.translationY(f);
        }
        return this;
    }

    public C0650c m5896n(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.translationYBy(f);
        }
        return this;
    }

    public C0650c m5897o(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.scaleX(f);
        }
        return this;
    }

    public C0650c m5898p(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.scaleXBy(f);
        }
        return this;
    }

    public C0650c m5899q(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.scaleY(f);
        }
        return this;
    }

    public C0650c m5900r(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.scaleYBy(f);
        }
        return this;
    }

    public C0650c m5901s(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.alpha(f);
        }
        return this;
    }

    public C0650c m5902t(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.f3978b.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.alphaBy(f);
        }
        return this;
    }
}
