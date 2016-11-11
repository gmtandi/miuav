package com.xiaomi.xmpush.thrift;

import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.xiaomi.xmpush.thrift.a */
public enum C2729a {
    Registration(1),
    UnRegistration(2),
    Subscription(3),
    UnSubscription(4),
    SendMessage(5),
    AckMessage(6),
    SetConfig(7),
    ReportFeedback(8),
    Notification(9),
    Command(10),
    MultiConnectionBroadcast(11),
    MultiConnectionResult(12),
    ConnectionKick(13);
    
    private final int f13478n;

    private C2729a(int i) {
        this.f13478n = i;
    }

    public static C2729a m15399a(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return Registration;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return UnRegistration;
            case Type.BYTE /*3*/:
                return Subscription;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return UnSubscription;
            case Type.INT /*5*/:
                return SendMessage;
            case Type.FLOAT /*6*/:
                return AckMessage;
            case Type.LONG /*7*/:
                return SetConfig;
            case Type.DOUBLE /*8*/:
                return ReportFeedback;
            case Type.ARRAY /*9*/:
                return Notification;
            case Type.OBJECT /*10*/:
                return Command;
            case Opcodes.T_LONG /*11*/:
                return MultiConnectionBroadcast;
            case Opcodes.FCONST_1 /*12*/:
                return MultiConnectionResult;
            case Opcodes.FCONST_2 /*13*/:
                return ConnectionKick;
            default:
                return null;
        }
    }

    public int m15400a() {
        return this.f13478n;
    }
}
