package com.fimi.soul.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.Handler;
import android.os.IBinder;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.receiver.NetworkStateReceiver;
import com.fimi.soul.utils.C1962b;
import com.fimi.soul.utils.C1969i;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;
import org.codehaus.jackson.smile.SmileConstants;

public class CameraSocketService extends Service {
    public static final int f9881a = 26112;
    public static final int f9882b = 1200;
    public static final int f9883c = 32;
    public static final int f9884d = 1001;
    public static final int f9885e = 2001;
    public static final int f9886f = 0;
    public static final int f9887g = 1;
    public static final int f9888h = 2;
    public static final int f9889i = 3;
    public static final int f9890j = 4;
    public static final int f9891k = 5;
    public static final int f9892l = 7;
    public static final int f9893m = 8;
    public static final String f9894n = "/uDiskFile.txt";
    private static final int f9895s = 3000;
    private static Stack<Thread> f9896y;
    private DataInputStream f9897A;
    private DataOutputStream f9898B;
    private ServerSocket f9899C;
    private Handler f9900D;
    private C1939f f9901E;
    private C1945l f9902F;
    private MulticastLock f9903G;
    private BroadcastReceiver f9904H;
    private final byte[] f9905I;
    private final byte[] f9906J;
    private final byte[] f9907K;
    private final byte[] f9908L;
    private final byte[] f9909M;
    private final byte[] f9910N;
    public C1940g f9911o;
    private MulticastSocket f9912p;
    private String f9913q;
    private InetAddress f9914r;
    private String f9915t;
    private boolean f9916u;
    private boolean f9917v;
    private boolean f9918w;
    private String f9919x;
    private Socket f9920z;

    static {
        f9896y = new Stack();
    }

    public CameraSocketService() {
        this.f9913q = "224.0.0.1";
        this.f9915t = null;
        this.f9916u = false;
        this.f9917v = false;
        this.f9918w = false;
        this.f9919x = null;
        this.f9920z = null;
        this.f9897A = null;
        this.f9898B = null;
        this.f9899C = null;
        this.f9900D = new Handler();
        this.f9901E = C1939f.CAMERA;
        this.f9902F = null;
        this.f9911o = new C1940g(this);
        this.f9904H = new C1937d(this);
        this.f9905I = new byte[]{(byte) 0, (byte) 102, (byte) 0, (byte) 0, (byte) -47, (byte) 7, (byte) 2, (byte) 0, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.f9906J = new byte[65];
        this.f9907K = new byte[]{(byte) 0, (byte) 102, (byte) 1, (byte) 0, (byte) -47, (byte) 7, (byte) 2, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.f9908L = new byte[]{(byte) 0, (byte) 102, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.f9909M = new byte[]{(byte) 0, (byte) 102, (byte) 1, (byte) 0, SmileConstants.TOKEN_LITERAL_EMPTY_STRING, (byte) 0, SmileConstants.TOKEN_LITERAL_EMPTY_STRING, (byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.f9910N = new byte[]{(byte) 0, (byte) 102, (byte) 0, (byte) 0, (byte) -47, (byte) 7, (byte) 7, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    }

    private void m12105a(DataInputStream dataInputStream) {
        boolean z = false;
        byte[] bArr = new byte[68];
        try {
            if (dataInputStream.read(bArr) != -1) {
                long b = (long) C1962b.m12312b(bArr, 64);
                if (b > 0) {
                    File file = new File(C1969i.m12487j());
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String str = C1969i.m12489l() + this.f9915t;
                    if (f9894n.equalsIgnoreCase(this.f9915t)) {
                        str = C1969i.m12487j() + this.f9915t;
                    } else {
                        z = true;
                    }
                    try {
                        C1962b.m12308a(this.f9898B, this.f9907K);
                        this.f9916u = true;
                        C1962b.m12309a(str, b, dataInputStream, this, z);
                        this.f9916u = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void m12106a(Socket socket) {
        int i = f9886f;
        byte[] bArr = new byte[SmileConstants.MAX_SHARED_STRING_VALUES];
        int i2 = f9886f;
        int i3 = f9886f;
        while (this.f9897A != null && this.f9897A.read(bArr) != -1) {
            this.f9917v = true;
            if (f9881a == C1962b.m12304a(bArr, (int) f9886f)) {
                i = C1962b.m12304a(bArr, (int) f9888h);
                i3 = C1962b.m12304a(bArr, (int) f9890j);
                i2 = C1962b.m12304a(bArr, 6);
                C1236a.m8532a("mCurMsgType =" + i3, getClass());
            }
            if (i3 == f9882b) {
                m12105a(this.f9897A);
            } else if (i3 == f9885e) {
                continue;
            } else if (i3 == f9884d) {
                if (i != f9887g) {
                    try {
                        m12127a(f9888h, false, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (socket != null) {
                            try {
                                socket.close();
                                return;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th) {
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                } else if (i2 == f9892l) {
                    this.f9917v = false;
                    break;
                } else if (i2 == f9888h) {
                    m12127a(f9888h, true, this.f9915t);
                    this.f9915t = null;
                } else {
                    continue;
                }
            } else if (i3 == f9883c) {
                m12127a(i3, true, "connected");
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e22) {
                e22.printStackTrace();
            }
        }
    }

    private void m12118i() {
        try {
            this.f9912p = new MulticastSocket(C1962b.f10058a);
            this.f9914r = InetAddress.getByName(this.f9913q);
            this.f9912p.joinGroup(this.f9914r);
            Thread thread = new Thread(new C1944k(this));
            thread.start();
            f9896y.add(thread);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m12120j() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkStateReceiver.f9876a);
        registerReceiver(this.f9904H, intentFilter);
    }

    private void m12122k() {
        if (!this.f9918w) {
            try {
                this.f9899C = new ServerSocket(C1962b.f10059b);
                new Thread(new C1938e(this)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        C1236a.m8532a("startSocketServer " + this.f9918w, CameraSocketService.class);
    }

    private void m12124l() {
        C1962b.m12308a(this.f9898B, this.f9910N);
    }

    public void m12126a() {
        if (this.f9917v) {
            this.f9915t = f9894n;
            C1962b.m12308a(this.f9898B, this.f9905I);
            this.f9906J[f9886f] = (byte) 0;
            C1962b.m12308a(this.f9898B, this.f9906J);
            this.f9901E = C1939f.U_DISK;
            return;
        }
        m12127a(f9892l, this.f9917v, "disConnect..");
    }

    public void m12127a(int i, boolean z, String str) {
        this.f9900D.post(new C1936c(this, i, z, str));
    }

    public void m12128a(C1945l c1945l) {
        this.f9902F = c1945l;
        this.f9902F.m12180a(f9893m, m12135g(), "chanager state");
    }

    public void m12129a(String str, String str2) {
        int i = f9886f;
        if (this.f9901E == C1939f.CAMERA) {
            m12127a(f9891k, this.f9917v, "error mode..");
        } else if (this.f9917v) {
            this.f9915t = str;
            C1962b.m12308a(this.f9898B, this.f9905I);
            byte[] bArr = new byte[65];
            bArr[f9886f] = (byte) 2;
            byte[] bytes = str2.getBytes();
            while (i < bytes.length) {
                if (i + f9887g < bArr.length) {
                    bArr[i + f9887g] = bytes[i];
                }
                i += f9887g;
            }
            C1962b.m12308a(this.f9898B, bArr);
        } else {
            m12127a(f9892l, this.f9917v, "disConnect..");
        }
    }

    public void m12130b() {
        if (this.f9917v && this.f9901E != C1939f.CAMERA) {
            C1962b.m12308a(this.f9898B, this.f9905I);
            this.f9906J[f9886f] = (byte) 1;
            C1962b.m12308a(this.f9898B, this.f9906J);
            this.f9901E = C1939f.CAMERA;
        }
        if (!this.f9917v) {
            m12127a(f9892l, this.f9917v, "disConnect..");
        } else if (this.f9901E == C1939f.CAMERA) {
            m12127a(f9886f, this.f9917v, "Connected..");
        }
    }

    public C1939f m12131c() {
        return this.f9901E;
    }

    public void m12132d() {
        int i = f9886f;
        C1236a.m8532a("stopSocketServer", CameraSocketService.class);
        if (this.f9918w && this.f9899C != null) {
            try {
                this.f9918w = false;
                if (!(this.f9920z == null || this.f9920z.isClosed())) {
                    this.f9920z.close();
                    this.f9920z = null;
                }
                if (this.f9898B != null) {
                    this.f9898B.close();
                    this.f9898B = null;
                }
                this.f9899C.close();
                this.f9899C = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int size = f9896y.size();
        while (i < size) {
            if (!(f9896y.get(i) == null || ((Thread) f9896y.get(i)).isInterrupted())) {
                ((Thread) f9896y.get(i)).interrupt();
            }
            i += f9887g;
        }
        f9896y.clear();
        if (m12135g()) {
            m12124l();
        }
    }

    public void m12133e() {
        Thread thread = new Thread(new C1942i(this));
        thread.start();
        f9896y.add(thread);
    }

    public String m12134f() {
        return this.f9919x;
    }

    public boolean m12135g() {
        return this.f9917v;
    }

    public IBinder onBind(Intent intent) {
        return this.f9911o;
    }

    public void onCreate() {
        super.onCreate();
        C1236a.m8532a("onCreate", CameraSocketService.class);
        this.f9903G = ((WifiManager) getSystemService("wifi")).createMulticastLock("multicastLock");
        m12122k();
        m12118i();
        m12120j();
    }

    public void onDestroy() {
        super.onDestroy();
        m12132d();
        unregisterReceiver(this.f9904H);
    }
}
