package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public abstract class TransportPerformer {
    static final int AUDIOFOCUS_GAIN = 1;
    static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    static final int AUDIOFOCUS_LOSS = -1;
    static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;

    public void onAudioFocusChange(int i) {
        int i2 = 0;
        switch (i) {
            case AUDIOFOCUS_LOSS /*-1*/:
                i2 = Opcodes.LAND;
                break;
        }
        if (i2 != 0) {
            long uptimeMillis = SystemClock.uptimeMillis();
            onMediaButtonDown(i2, new KeyEvent(uptimeMillis, uptimeMillis, 0, i2, 0));
            onMediaButtonUp(i2, new KeyEvent(uptimeMillis, uptimeMillis, AUDIOFOCUS_GAIN, i2, 0));
        }
    }

    public int onGetBufferPercentage() {
        return 100;
    }

    public abstract long onGetCurrentPosition();

    public abstract long onGetDuration();

    public int onGetTransportControlFlags() {
        return 60;
    }

    public abstract boolean onIsPlaying();

    public boolean onMediaButtonDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case Opcodes.IASTORE /*79*/:
            case Opcodes.CASTORE /*85*/:
                if (!onIsPlaying()) {
                    onStart();
                    break;
                }
                onPause();
                break;
            case Opcodes.SASTORE /*86*/:
                onStop();
                break;
            case Opcodes.IAND /*126*/:
                onStart();
                break;
            case Opcodes.LAND /*127*/:
                onPause();
                break;
        }
        return true;
    }

    public boolean onMediaButtonUp(int i, KeyEvent keyEvent) {
        return true;
    }

    public abstract void onPause();

    public abstract void onSeekTo(long j);

    public abstract void onStart();

    public abstract void onStop();
}
