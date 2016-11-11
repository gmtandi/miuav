package p147m.framework.ui.widget.slidingmenu;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: m.framework.ui.widget.slidingmenu.f */
class C2900f implements OnTouchListener {
    final /* synthetic */ SlidingMenu f14728a;

    C2900f(SlidingMenu slidingMenu) {
        this.f14728a = slidingMenu;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                view.setBackgroundResource(this.f14728a.f14692a.f14714a);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case Type.BYTE /*3*/:
                view.setBackgroundResource(this.f14728a.f14692a.f14715b);
                break;
        }
        return false;
    }
}
