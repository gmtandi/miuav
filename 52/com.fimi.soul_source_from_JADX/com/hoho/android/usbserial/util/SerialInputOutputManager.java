package com.hoho.android.usbserial.util;

import android.util.Log;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import java.nio.ByteBuffer;

public class SerialInputOutputManager implements Runnable {
    private static final int BUFSIZ = 4096;
    private static final boolean DEBUG = true;
    private static final int READ_WAIT_MILLIS = 200;
    private static final String TAG;
    private final UsbSerialDriver mDriver;
    private Listener mListener;
    private final ByteBuffer mReadBuffer;
    private State mState;
    private final ByteBuffer mWriteBuffer;

    public interface Listener {
        void onNewData(byte[] bArr);

        void onRunError(Exception exception);
    }

    enum State {
        STOPPED,
        RUNNING,
        STOPPING
    }

    static {
        TAG = SerialInputOutputManager.class.getSimpleName();
    }

    public SerialInputOutputManager(UsbSerialDriver usbSerialDriver) {
        this(usbSerialDriver, null);
    }

    public SerialInputOutputManager(UsbSerialDriver usbSerialDriver, Listener listener) {
        this.mReadBuffer = ByteBuffer.allocate(BUFSIZ);
        this.mWriteBuffer = ByteBuffer.allocate(BUFSIZ);
        this.mState = State.STOPPED;
        this.mDriver = usbSerialDriver;
        this.mListener = listener;
    }

    private synchronized State getState() {
        return this.mState;
    }

    private void step() {
        int read = this.mDriver.read(this.mReadBuffer.array(), READ_WAIT_MILLIS);
        if (read > 0) {
            Log.d(TAG, "Read data len=" + read);
            Listener listener = getListener();
            if (listener != null) {
                byte[] bArr = new byte[read];
                this.mReadBuffer.get(bArr, 0, read);
                listener.onNewData(bArr);
            }
            this.mReadBuffer.clear();
        }
        byte[] bArr2 = null;
        synchronized (this.mWriteBuffer) {
            if (this.mWriteBuffer.position() > 0) {
                read = this.mWriteBuffer.position();
                bArr2 = new byte[read];
                this.mWriteBuffer.rewind();
                this.mWriteBuffer.get(bArr2, 0, read);
                this.mWriteBuffer.clear();
            }
        }
        if (bArr2 != null) {
            Log.d(TAG, "Writing data len=" + read);
            this.mDriver.write(bArr2, READ_WAIT_MILLIS);
        }
    }

    public synchronized Listener getListener() {
        return this.mListener;
    }

    public void run() {
        synchronized (this) {
            if (getState() != State.STOPPED) {
                throw new IllegalStateException("Already running.");
            }
            this.mState = State.RUNNING;
        }
        Log.i(TAG, "Running ..");
        while (getState() == State.RUNNING) {
            try {
                step();
            } catch (Throwable e) {
                Log.w(TAG, "Run ending due to exception: " + e.getMessage(), e);
                r1 = getListener();
                Listener listener;
                if (listener != null) {
                    listener.onRunError(e);
                }
                synchronized (this) {
                }
                this.mState = State.STOPPED;
                Log.i(TAG, "Stopped.");
                return;
            } catch (Throwable th) {
                synchronized (this) {
                }
                this.mState = State.STOPPED;
                Log.i(TAG, "Stopped.");
            }
        }
        Log.i(TAG, "Stopping mState=" + getState());
        synchronized (this) {
            this.mState = State.STOPPED;
            Log.i(TAG, "Stopped.");
        }
    }

    public synchronized void setListener(Listener listener) {
        this.mListener = listener;
    }

    public synchronized void stop() {
        if (getState() == State.RUNNING) {
            Log.i(TAG, "Stop requested");
            this.mState = State.STOPPING;
        }
    }

    public void writeAsync(byte[] bArr) {
        synchronized (this.mWriteBuffer) {
            this.mWriteBuffer.put(bArr);
        }
    }
}
