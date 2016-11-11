package com.p016a;

import com.autonavi.aps.amapapi.model.AmapLoc;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.market.sdk.C2537j;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: com.a.df */
public class df {
    public AmapLoc m1453a(String str) {
        InputStream inputStream;
        if (!str.contains("SuccessCode")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.reverse();
            try {
                str = new String(fy.m1905b(stringBuilder.toString()), C1142e.f5201a);
            } catch (Throwable e) {
                ev.m1777a(e, "parser", "ParserApsResp1");
            }
            stringBuilder.delete(0, stringBuilder.length());
        }
        if (str.contains("SuccessCode=\"0\"")) {
            try {
            } catch (Throwable e2) {
                ev.m1777a(e2, "parser", "ParserApsResp");
                inputStream = null;
            }
        }
        inputStream = new ByteArrayInputStream(str.getBytes(C1142e.f5201a));
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        DefaultHandler dgVar = new dg();
        try {
            SAXParser newSAXParser = newInstance.newSAXParser();
            if (inputStream != null) {
                newSAXParser.parse(inputStream, dgVar);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
            dgVar.f843a.m5328c("network");
            return dgVar.f843a;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
        }
    }

    public AmapLoc m1454b(String str) {
        AmapLoc amapLoc = new AmapLoc();
        amapLoc.m5321b(7);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!(jSONObject.has(RMsgInfo.COL_STATUS) && jSONObject.has(C2537j.ag))) {
                bs.f647c.append("json is error " + str);
            }
            String string = jSONObject.getString(RMsgInfo.COL_STATUS);
            String string2 = jSONObject.getString(C2537j.ag);
            if (string.equals(Constants.VIA_TO_TYPE_QQ_GROUP)) {
                bs.f647c.append("json is error " + str);
            }
            if (string.equals(Constants.VIA_RESULT_SUCCESS)) {
                bs.f647c.append("auth fail:" + string2);
            }
        } catch (Throwable th) {
            bs.f647c.append("json exception error:" + th.getMessage());
            ev.m1777a(th, "parser", "paseAuthFailurJson");
        }
        amapLoc.m5322b(bs.f647c.toString());
        return amapLoc;
    }
}
