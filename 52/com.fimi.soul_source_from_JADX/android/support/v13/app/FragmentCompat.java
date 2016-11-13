package android.support.v13.app;

import android.app.Fragment;
import android.os.Build.VERSION;

public class FragmentCompat {
    static final FragmentCompatImpl IMPL;

    interface FragmentCompatImpl {
        void setMenuVisibility(Fragment fragment, boolean z);

        void setUserVisibleHint(Fragment fragment, boolean z);
    }

    class BaseFragmentCompatImpl implements FragmentCompatImpl {
        BaseFragmentCompatImpl() {
        }

        public void setMenuVisibility(Fragment fragment, boolean z) {
        }

        public void setUserVisibleHint(Fragment fragment, boolean z) {
        }
    }

    class ICSFragmentCompatImpl extends BaseFragmentCompatImpl {
        ICSFragmentCompatImpl() {
        }

        public void setMenuVisibility(Fragment fragment, boolean z) {
            FragmentCompatICS.setMenuVisibility(fragment, z);
        }
    }

    class ICSMR1FragmentCompatImpl extends ICSFragmentCompatImpl {
        ICSMR1FragmentCompatImpl() {
        }

        public void setUserVisibleHint(Fragment fragment, boolean z) {
            FragmentCompatICSMR1.setUserVisibleHint(fragment, z);
        }
    }

    static {
        if (VERSION.SDK_INT >= 15) {
            IMPL = new ICSMR1FragmentCompatImpl();
        } else if (VERSION.SDK_INT >= 14) {
            IMPL = new ICSFragmentCompatImpl();
        } else {
            IMPL = new BaseFragmentCompatImpl();
        }
    }

    public static void setMenuVisibility(Fragment fragment, boolean z) {
        IMPL.setMenuVisibility(fragment, z);
    }

    public static void setUserVisibleHint(Fragment fragment, boolean z) {
        IMPL.setUserVisibleHint(fragment, z);
    }
}
