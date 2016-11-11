package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public class ViewPropertyAnimatorCompat {
    static final ViewPropertyAnimatorCompatImpl IMPL;
    private static final String TAG = "ViewAnimatorCompat";
    private WeakReference<View> mView;

    interface ViewPropertyAnimatorCompatImpl {
        void alpha(View view, float f);

        void alphaBy(View view, float f);

        void cancel(View view);

        long getDuration(View view);

        Interpolator getInterpolator(View view);

        long getStartDelay(View view);

        void rotation(View view, float f);

        void rotationBy(View view, float f);

        void rotationX(View view, float f);

        void rotationXBy(View view, float f);

        void rotationY(View view, float f);

        void rotationYBy(View view, float f);

        void scaleX(View view, float f);

        void scaleXBy(View view, float f);

        void scaleY(View view, float f);

        void scaleYBy(View view, float f);

        void setDuration(View view, long j);

        void setInterpolator(View view, Interpolator interpolator);

        void setListener(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener);

        void setStartDelay(View view, long j);

        void start(View view);

        void translationX(View view, float f);

        void translationXBy(View view, float f);

        void translationY(View view, float f);

        void translationYBy(View view, float f);

        void withEndAction(View view, Runnable runnable);

        void withLayer(View view);

        void withStartAction(View view, Runnable runnable);

        void m118x(View view, float f);

        void xBy(View view, float f);

        void m119y(View view, float f);

        void yBy(View view, float f);
    }

    class BaseViewPropertyAnimatorCompatImpl implements ViewPropertyAnimatorCompatImpl {
        BaseViewPropertyAnimatorCompatImpl() {
        }

        public void alpha(View view, float f) {
        }

        public void alphaBy(View view, float f) {
        }

        public void cancel(View view) {
        }

        public long getDuration(View view) {
            return 0;
        }

        public Interpolator getInterpolator(View view) {
            return null;
        }

        public long getStartDelay(View view) {
            return 0;
        }

        public void rotation(View view, float f) {
        }

        public void rotationBy(View view, float f) {
        }

        public void rotationX(View view, float f) {
        }

        public void rotationXBy(View view, float f) {
        }

        public void rotationY(View view, float f) {
        }

        public void rotationYBy(View view, float f) {
        }

        public void scaleX(View view, float f) {
        }

        public void scaleXBy(View view, float f) {
        }

        public void scaleY(View view, float f) {
        }

        public void scaleYBy(View view, float f) {
        }

        public void setDuration(View view, long j) {
        }

        public void setInterpolator(View view, Interpolator interpolator) {
        }

        public void setListener(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        }

        public void setStartDelay(View view, long j) {
        }

        public void start(View view) {
        }

        public void translationX(View view, float f) {
        }

        public void translationXBy(View view, float f) {
        }

        public void translationY(View view, float f) {
        }

        public void translationYBy(View view, float f) {
        }

        public void withEndAction(View view, Runnable runnable) {
            runnable.run();
        }

        public void withLayer(View view) {
        }

        public void withStartAction(View view, Runnable runnable) {
            runnable.run();
        }

        public void m120x(View view, float f) {
        }

        public void xBy(View view, float f) {
        }

        public void m121y(View view, float f) {
        }

        public void yBy(View view, float f) {
        }
    }

    class ICSViewPropertyAnimatorCompatImpl extends BaseViewPropertyAnimatorCompatImpl {

        /* renamed from: android.support.v4.view.ViewPropertyAnimatorCompat.ICSViewPropertyAnimatorCompatImpl.1 */
        class C00841 implements ViewPropertyAnimatorListener {
            final /* synthetic */ Runnable val$runnable;

            C00841(Runnable runnable) {
                this.val$runnable = runnable;
            }

            public void onAnimationCancel(View view) {
            }

            public void onAnimationEnd(View view) {
                this.val$runnable.run();
                ICSViewPropertyAnimatorCompatImpl.this.setListener(view, null);
            }

            public void onAnimationStart(View view) {
            }
        }

        /* renamed from: android.support.v4.view.ViewPropertyAnimatorCompat.ICSViewPropertyAnimatorCompatImpl.2 */
        class C00852 implements ViewPropertyAnimatorListener {
            final /* synthetic */ Runnable val$runnable;

            C00852(Runnable runnable) {
                this.val$runnable = runnable;
            }

            public void onAnimationCancel(View view) {
            }

            public void onAnimationEnd(View view) {
            }

            public void onAnimationStart(View view) {
                this.val$runnable.run();
                ICSViewPropertyAnimatorCompatImpl.this.setListener(view, null);
            }
        }

        /* renamed from: android.support.v4.view.ViewPropertyAnimatorCompat.ICSViewPropertyAnimatorCompatImpl.3 */
        class C00863 implements ViewPropertyAnimatorListener {
            final /* synthetic */ int val$currentLayerType;

            C00863(int i) {
                this.val$currentLayerType = i;
            }

            public void onAnimationCancel(View view) {
            }

            public void onAnimationEnd(View view) {
                ViewCompat.setLayerType(view, this.val$currentLayerType, null);
                ICSViewPropertyAnimatorCompatImpl.this.setListener(view, null);
            }

            public void onAnimationStart(View view) {
                ViewCompat.setLayerType(view, 2, null);
            }
        }

        ICSViewPropertyAnimatorCompatImpl() {
        }

        public void alpha(View view, float f) {
            ViewPropertyAnimatorCompatICS.alpha(view, f);
        }

        public void alphaBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.alphaBy(view, f);
        }

        public void cancel(View view) {
            ViewPropertyAnimatorCompatICS.cancel(view);
        }

        public long getDuration(View view) {
            return ViewPropertyAnimatorCompatICS.getDuration(view);
        }

        public long getStartDelay(View view) {
            return ViewPropertyAnimatorCompatICS.getStartDelay(view);
        }

        public void rotation(View view, float f) {
            ViewPropertyAnimatorCompatICS.rotation(view, f);
        }

        public void rotationBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.rotationBy(view, f);
        }

        public void rotationX(View view, float f) {
            ViewPropertyAnimatorCompatICS.rotationX(view, f);
        }

        public void rotationXBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.rotationXBy(view, f);
        }

        public void rotationY(View view, float f) {
            ViewPropertyAnimatorCompatICS.rotationY(view, f);
        }

        public void rotationYBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.rotationYBy(view, f);
        }

        public void scaleX(View view, float f) {
            ViewPropertyAnimatorCompatICS.scaleX(view, f);
        }

        public void scaleXBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.scaleXBy(view, f);
        }

        public void scaleY(View view, float f) {
            ViewPropertyAnimatorCompatICS.scaleY(view, f);
        }

        public void scaleYBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.scaleYBy(view, f);
        }

        public void setDuration(View view, long j) {
            ViewPropertyAnimatorCompatICS.setDuration(view, j);
        }

        public void setInterpolator(View view, Interpolator interpolator) {
            ViewPropertyAnimatorCompatICS.setInterpolator(view, interpolator);
        }

        public void setListener(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            ViewPropertyAnimatorCompatICS.setListener(view, viewPropertyAnimatorListener);
        }

        public void setStartDelay(View view, long j) {
            ViewPropertyAnimatorCompatICS.setStartDelay(view, j);
        }

        public void start(View view) {
            ViewPropertyAnimatorCompatICS.start(view);
        }

        public void translationX(View view, float f) {
            ViewPropertyAnimatorCompatICS.translationX(view, f);
        }

        public void translationXBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.translationXBy(view, f);
        }

        public void translationY(View view, float f) {
            ViewPropertyAnimatorCompatICS.translationY(view, f);
        }

        public void translationYBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.translationYBy(view, f);
        }

        public void withEndAction(View view, Runnable runnable) {
            setListener(view, new C00841(runnable));
        }

        public void withLayer(View view) {
            setListener(view, new C00863(ViewCompat.getLayerType(view)));
        }

        public void withStartAction(View view, Runnable runnable) {
            setListener(view, new C00852(runnable));
        }

        public void m122x(View view, float f) {
            ViewPropertyAnimatorCompatICS.m126x(view, f);
        }

        public void xBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.xBy(view, f);
        }

        public void m123y(View view, float f) {
            ViewPropertyAnimatorCompatICS.m127y(view, f);
        }

        public void yBy(View view, float f) {
            ViewPropertyAnimatorCompatICS.yBy(view, f);
        }
    }

    class JBViewPropertyAnimatorCompatImpl extends ICSViewPropertyAnimatorCompatImpl {
        JBViewPropertyAnimatorCompatImpl() {
        }

        public void withEndAction(View view, Runnable runnable) {
            ViewPropertyAnimatorCompatJB.withEndAction(view, runnable);
        }

        public void withLayer(View view) {
            ViewPropertyAnimatorCompatJB.withLayer(view);
        }

        public void withStartAction(View view, Runnable runnable) {
            ViewPropertyAnimatorCompatJB.withStartAction(view, runnable);
        }
    }

    class JBMr2ViewPropertyAnimatorCompatImpl extends JBViewPropertyAnimatorCompatImpl {
        JBMr2ViewPropertyAnimatorCompatImpl() {
        }

        public Interpolator getInterpolator(View view) {
            return ViewPropertyAnimatorCompatJellybeanMr2.getInterpolator(view);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 18) {
            IMPL = new JBMr2ViewPropertyAnimatorCompatImpl();
        } else if (i >= 16) {
            IMPL = new JBViewPropertyAnimatorCompatImpl();
        } else if (i >= 14) {
            IMPL = new ICSViewPropertyAnimatorCompatImpl();
        } else {
            IMPL = new BaseViewPropertyAnimatorCompatImpl();
        }
    }

    ViewPropertyAnimatorCompat(View view) {
        this.mView = new WeakReference(view);
    }

    public ViewPropertyAnimatorCompat alpha(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.alpha(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.alphaBy(view, f);
        }
        return this;
    }

    public void cancel() {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.cancel(view);
        }
    }

    public long getDuration() {
        View view = (View) this.mView.get();
        return view != null ? IMPL.getDuration(view) : 0;
    }

    public Interpolator getInterpolator() {
        View view = (View) this.mView.get();
        return view != null ? IMPL.getInterpolator(view) : null;
    }

    public long getStartDelay() {
        View view = (View) this.mView.get();
        return view != null ? IMPL.getStartDelay(view) : 0;
    }

    public ViewPropertyAnimatorCompat rotation(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.rotation(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.rotationBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.rotationX(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.rotationXBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.rotationY(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.rotationYBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.scaleX(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.scaleXBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.scaleY(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.scaleYBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setDuration(long j) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setDuration(view, j);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setInterpolator(view, interpolator);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setListener(view, viewPropertyAnimatorListener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setStartDelay(long j) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setStartDelay(view, j);
        }
        return this;
    }

    public void start() {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.start(view);
        }
    }

    public ViewPropertyAnimatorCompat translationX(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.translationX(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.translationXBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.translationY(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.translationYBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.withEndAction(view, runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withLayer() {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.withLayer(view);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.withStartAction(view, runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat m124x(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.m118x(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.xBy(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat m125y(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.m119y(view, f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.yBy(view, f);
        }
        return this;
    }
}
