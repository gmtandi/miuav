package com.facebook.common.soloader;

public class SoLoaderShim {
    private static volatile Handler sHandler;

    public interface Handler {
        void loadLibrary(String str);
    }

    /* renamed from: com.facebook.common.soloader.SoLoaderShim.1 */
    final class C09731 implements Handler {
        C09731() {
        }

        public void loadLibrary(String str) {
        }
    }

    public class DefaultHandler implements Handler {
        public void loadLibrary(String str) {
            System.loadLibrary(str);
        }
    }

    static {
        sHandler = new DefaultHandler();
    }

    public static void loadLibrary(String str) {
        sHandler.loadLibrary(str);
    }

    public static void setHandler(Handler handler) {
        if (handler == null) {
            throw new NullPointerException("Handler cannot be null");
        }
        sHandler = handler;
    }

    public static void setInTestMode() {
        setHandler(new C09731());
    }
}
