package it.p074a.p075a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* renamed from: it.a.a.m */
class C2795m implements C2794j, Runnable {
    private ServerSocket f14242a;
    private Socket f14243b;
    private IOException f14244c;
    private Thread f14245d;

    public C2795m() {
        int parseInt;
        int parseInt2;
        Object obj;
        Object obj2 = null;
        this.f14242a = null;
        String property = System.getProperty(C2806q.f14304a);
        if (property != null) {
            Object obj3;
            int i;
            StringTokenizer stringTokenizer = new StringTokenizer(property, "-");
            if (stringTokenizer.countTokens() == 2) {
                String nextToken = stringTokenizer.nextToken();
                String nextToken2 = stringTokenizer.nextToken();
                try {
                    parseInt = Integer.parseInt(nextToken);
                } catch (NumberFormatException e) {
                    parseInt = 0;
                }
                try {
                    parseInt2 = Integer.parseInt(nextToken2);
                } catch (NumberFormatException e2) {
                    parseInt2 = 0;
                }
                if (parseInt > 0 && r0 > 0 && r0 >= parseInt) {
                    obj3 = 1;
                    obj = 1;
                    if (obj3 == null) {
                        System.err.println(new StringBuffer().append("WARNING: invalid value \"").append(property).append("\" for the ").append(C2806q.f14304a).append(" system property. The value should ").append("be in the start-stop form, with ").append("start > 0, stop > 0 and start <= stop.").toString());
                    }
                    i = parseInt2;
                    parseInt2 = parseInt;
                    parseInt = i;
                }
            }
            obj3 = null;
            parseInt2 = 0;
            parseInt = 0;
            obj = null;
            if (obj3 == null) {
                System.err.println(new StringBuffer().append("WARNING: invalid value \"").append(property).append("\" for the ").append(C2806q.f14304a).append(" system property. The value should ").append("be in the start-stop form, with ").append("start > 0, stop > 0 and start <= stop.").toString());
            }
            i = parseInt2;
            parseInt2 = parseInt;
            parseInt = i;
        } else {
            parseInt = 0;
            parseInt2 = 0;
            obj = null;
        }
        if (obj != null) {
            ArrayList arrayList = new ArrayList();
            for (parseInt2 = 
            /* Method generation error in method: it.a.a.m.<init>():void
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_11 'parseInt2' int) = (r0_9 'parseInt2' int), (r0_30 'parseInt2' int) binds: {(r0_9 'parseInt2' int)=B:17:0x006e, (r0_30 'parseInt2' int)=B:47:0x0116} in method: it.a.a.m.<init>():void
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:225)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:177)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:324)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:263)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:226)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:116)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:81)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
Caused by: jadx.core.utils.exceptions.CodegenException: Unknown instruction: PHI in method: it.a.a.m.<init>():void
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:512)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:219)
	... 22 more
 */

            public Socket m15969a() {
                if (this.f14243b == null && this.f14244c == null) {
                    try {
                        this.f14245d.join();
                    } catch (Exception e) {
                    }
                }
                if (this.f14244c != null) {
                    throw new C2802k("Cannot receive the incoming connection", this.f14244c);
                } else if (this.f14243b != null) {
                    return this.f14243b;
                } else {
                    throw new C2802k("No socket available");
                }
            }

            public void m15970b() {
                if (this.f14242a != null) {
                    try {
                        this.f14242a.close();
                    } catch (IOException e) {
                    }
                }
            }

            public int m15971c() {
                return this.f14242a.getLocalPort();
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r5 = this;
                r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
                r1 = "ftp4j.activeDataTransfer.acceptTimeout";
                r3 = java.lang.System.getProperty(r1);
                if (r3 == 0) goto L_0x0046;
            L_0x000a:
                r1 = 0;
                r2 = java.lang.Integer.parseInt(r3);	 Catch:{ NumberFormatException -> 0x0060 }
            L_0x000f:
                if (r2 < 0) goto L_0x0014;
            L_0x0011:
                r0 = 1;
                r1 = r0;
                r0 = r2;
            L_0x0014:
                if (r1 != 0) goto L_0x0046;
            L_0x0016:
                r1 = java.lang.System.err;
                r2 = new java.lang.StringBuffer;
                r2.<init>();
                r4 = "WARNING: invalid value \"";
                r2 = r2.append(r4);
                r2 = r2.append(r3);
                r3 = "\" for the ";
                r2 = r2.append(r3);
                r3 = "ftp4j.activeDataTransfer.acceptTimeout";
                r2 = r2.append(r3);
                r3 = " system property. The value should ";
                r2 = r2.append(r3);
                r3 = "be an integer greater or equal to 0.";
                r2 = r2.append(r3);
                r2 = r2.toString();
                r1.println(r2);
            L_0x0046:
                r1 = r5.f14242a;	 Catch:{ IOException -> 0x0063 }
                r1.setSoTimeout(r0);	 Catch:{ IOException -> 0x0063 }
                r0 = r5.f14242a;	 Catch:{ IOException -> 0x0063 }
                r0 = r0.accept();	 Catch:{ IOException -> 0x0063 }
                r5.f14243b = r0;	 Catch:{ IOException -> 0x0063 }
                r0 = r5.f14243b;	 Catch:{ IOException -> 0x0063 }
                r1 = 524288; // 0x80000 float:7.34684E-40 double:2.590327E-318;
                r0.setSendBufferSize(r1);	 Catch:{ IOException -> 0x0063 }
                r0 = r5.f14242a;	 Catch:{ IOException -> 0x0077 }
                r0.close();	 Catch:{ IOException -> 0x0077 }
            L_0x005f:
                return;
            L_0x0060:
                r2 = move-exception;
                r2 = -1;
                goto L_0x000f;
            L_0x0063:
                r0 = move-exception;
                r5.f14244c = r0;	 Catch:{ all -> 0x006e }
                r0 = r5.f14242a;	 Catch:{ IOException -> 0x006c }
                r0.close();	 Catch:{ IOException -> 0x006c }
                goto L_0x005f;
            L_0x006c:
                r0 = move-exception;
                goto L_0x005f;
            L_0x006e:
                r0 = move-exception;
                r1 = r5.f14242a;	 Catch:{ IOException -> 0x0075 }
                r1.close();	 Catch:{ IOException -> 0x0075 }
            L_0x0074:
                throw r0;
            L_0x0075:
                r1 = move-exception;
                goto L_0x0074;
            L_0x0077:
                r0 = move-exception;
                goto L_0x005f;
                */
                throw new UnsupportedOperationException("Method not decompiled: it.a.a.m.run():void");
            }
        }
