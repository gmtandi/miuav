package it.p074a.p075a.p140a;

import com.fimi.kernel.p076b.p080d.C1142e;
import it.p074a.p075a.C2777i;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: it.a.a.a.g */
public class C2781g extends C2777i {
    private String f14176d;
    private int f14177e;
    private String f14178f;

    public C2781g(String str, int i) {
        this(str, i, null);
    }

    public C2781g(String str, int i, String str2) {
        this.f14176d = str;
        this.f14177e = i;
        this.f14178f = str2;
    }

    private int m15850a(InputStream inputStream) {
        int read = inputStream.read();
        if (read >= 0) {
            return read;
        }
        throw new IOException("SOCKS4Connector: connection closed by the proxy");
    }

    private Socket m15851a(String str, int i, boolean z) {
        byte[] address;
        Socket b;
        IOException e;
        InputStream inputStream;
        OutputStream outputStream;
        InputStream inputStream2 = null;
        Object obj = null;
        try {
            address = InetAddress.getByName(str).getAddress();
        } catch (Exception e2) {
            address = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
            int i2 = 1;
        }
        if (z) {
            try {
                b = m15836b(this.f14176d, this.f14177e);
            } catch (IOException e3) {
                e = e3;
                OutputStream outputStream2 = null;
                Socket socket = null;
                try {
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStream2;
                    b = socket;
                    outputStream = outputStream2;
                }
            } catch (Throwable th2) {
                Throwable th3;
                th3 = th2;
                outputStream = null;
                inputStream = null;
                b = null;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th5) {
                    }
                }
                if (b != null) {
                    try {
                        b.close();
                    } catch (Throwable th6) {
                    }
                }
                throw th3;
            }
        }
        b = m15832a(this.f14176d, this.f14177e);
        try {
            inputStream = b.getInputStream();
            try {
                outputStream = b.getOutputStream();
                try {
                    outputStream.write(4);
                    outputStream.write(1);
                    outputStream.write(i >> 8);
                    outputStream.write(i);
                    outputStream.write(address);
                    if (this.f14178f != null) {
                        outputStream.write(this.f14178f.getBytes(C1142e.f5201a));
                    }
                    outputStream.write(0);
                    if (obj != null) {
                        outputStream.write(str.getBytes(C1142e.f5201a));
                        outputStream.write(0);
                    }
                    if (m15850a(inputStream) != 0) {
                        throw new IOException("SOCKS4Connector: invalid proxy response");
                    }
                    switch (m15850a(inputStream)) {
                        case Opcodes.DUP_X1 /*90*/:
                            inputStream.skip(6);
                            return b;
                        case Opcodes.DUP_X2 /*91*/:
                            throw new IOException("SOCKS4Connector: connection refused/failed");
                        case Opcodes.DUP2 /*92*/:
                            throw new IOException("SOCKS4Connector: cannot validate the user");
                        case Opcodes.DUP2_X1 /*93*/:
                            throw new IOException("SOCKS4Connector: invalid user");
                        default:
                            throw new IOException("SOCKS4Connector: invalid proxy response");
                    }
                } catch (IOException e4) {
                    e = e4;
                    outputStream2 = outputStream;
                    inputStream2 = inputStream;
                    socket = b;
                    throw e;
                } catch (Throwable th7) {
                    th3 = th7;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (b != null) {
                        b.close();
                    }
                    throw th3;
                }
            } catch (IOException e5) {
                e = e5;
                outputStream2 = null;
                socket = b;
                inputStream2 = inputStream;
                throw e;
            } catch (Throwable th8) {
                th3 = th8;
                outputStream = null;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (b != null) {
                    b.close();
                }
                throw th3;
            }
        } catch (IOException e6) {
            e = e6;
            outputStream2 = null;
            socket = b;
            throw e;
        } catch (Throwable th9) {
            th3 = th9;
            outputStream = null;
            inputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (b != null) {
                b.close();
            }
            throw th3;
        }
    }

    public Socket m15852c(String str, int i) {
        return m15851a(str, i, false);
    }

    public Socket m15853d(String str, int i) {
        return m15851a(str, i, true);
    }
}
