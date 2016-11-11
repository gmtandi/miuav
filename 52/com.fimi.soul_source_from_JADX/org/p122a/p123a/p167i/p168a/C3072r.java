package org.p122a.p123a.p167i.p168a;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.NTCredentials;
import org.apache.http.impl.auth.NTLMEngine;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;

@C2913c
/* renamed from: org.a.a.i.a.r */
public class C3072r extends C3054a {
    private final NTLMEngine f15214a;
    private C3073s f15215b;
    private String f15216c;

    public C3072r() {
        this(new C3063i());
    }

    public C3072r(NTLMEngine nTLMEngine) {
        C3234a.m17886a((Object) nTLMEngine, "NTLM engine");
        this.f15214a = nTLMEngine;
        this.f15215b = C3073s.UNINITIATED;
        this.f15216c = null;
    }

    protected void m17292a(CharArrayBuffer charArrayBuffer, int i, int i2) {
        this.f15216c = charArrayBuffer.substringTrimmed(i, i2);
        if (this.f15216c.length() == 0) {
            if (this.f15215b == C3073s.UNINITIATED) {
                this.f15215b = C3073s.CHALLENGE_RECEIVED;
            } else {
                this.f15215b = C3073s.FAILED;
            }
        } else if (this.f15215b.compareTo(C3073s.MSG_TYPE1_GENERATED) < 0) {
            this.f15215b = C3073s.FAILED;
            throw new MalformedChallengeException("Out of sequence NTLM response message");
        } else if (this.f15215b == C3073s.MSG_TYPE1_GENERATED) {
            this.f15215b = C3073s.MSG_TYPE2_RECEVIED;
        }
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        try {
            NTCredentials nTCredentials = (NTCredentials) credentials;
            if (this.f15215b == C3073s.FAILED) {
                throw new AuthenticationException("NTLM authentication failed");
            }
            String generateType1Msg;
            if (this.f15215b == C3073s.CHALLENGE_RECEIVED) {
                generateType1Msg = this.f15214a.generateType1Msg(nTCredentials.getDomain(), nTCredentials.getWorkstation());
                this.f15215b = C3073s.MSG_TYPE1_GENERATED;
            } else if (this.f15215b == C3073s.MSG_TYPE2_RECEVIED) {
                generateType1Msg = this.f15214a.generateType3Msg(nTCredentials.getUserName(), nTCredentials.getPassword(), nTCredentials.getDomain(), nTCredentials.getWorkstation(), this.f15216c);
                this.f15215b = C3073s.MSG_TYPE3_GENERATED;
            } else {
                throw new AuthenticationException("Unexpected state: " + this.f15215b);
            }
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
            if (m17172a()) {
                charArrayBuffer.append(C3004e.f15002N);
            } else {
                charArrayBuffer.append(C3004e.f15022h);
            }
            charArrayBuffer.append(": NTLM ");
            charArrayBuffer.append(generateType1Msg);
            return new BufferedHeader(charArrayBuffer);
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
    }

    public String getParameter(String str) {
        return null;
    }

    public String getRealm() {
        return null;
    }

    public String getSchemeName() {
        return "ntlm";
    }

    public boolean isComplete() {
        return this.f15215b == C3073s.MSG_TYPE3_GENERATED || this.f15215b == C3073s.FAILED;
    }

    public boolean isConnectionBased() {
        return true;
    }
}
