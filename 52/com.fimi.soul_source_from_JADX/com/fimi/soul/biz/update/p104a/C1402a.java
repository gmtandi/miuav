package com.fimi.soul.biz.update.p104a;

import android.util.Xml;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.biz.update.af;
import com.fimi.soul.entity.FirmwareInfo;
import com.tencent.mm.sdk.message.RMsgInfo;
import com.xiaomi.market.sdk.C2537j;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

/* renamed from: com.fimi.soul.biz.update.a.a */
public class C1402a implements af {
    public String m9377a(List<FirmwareInfo> list) {
        XmlSerializer newSerializer = XmlPullParserFactory.newInstance().newSerializer();
        Writer stringWriter = new StringWriter();
        newSerializer.setOutput(stringWriter);
        newSerializer.startDocument(C1142e.f5201a, Boolean.valueOf(true));
        newSerializer.startTag(C2915a.f14760f, "firmwares");
        for (FirmwareInfo firmwareInfo : list) {
            newSerializer.startTag(C2915a.f14760f, "firmware");
            newSerializer.attribute(C2915a.f14760f, "sysId", firmwareInfo.getSysId() + C2915a.f14760f);
            newSerializer.startTag(C2915a.f14760f, "model");
            newSerializer.text(firmwareInfo.getModel() + C2915a.f14760f);
            newSerializer.endTag(C2915a.f14760f, "model");
            newSerializer.startTag(C2915a.f14760f, "sysName");
            newSerializer.text(firmwareInfo.getSysName());
            newSerializer.endTag(C2915a.f14760f, "sysName");
            newSerializer.startTag(C2915a.f14760f, C2537j.aq);
            newSerializer.text(firmwareInfo.getVersion() + C2915a.f14760f);
            newSerializer.endTag(C2915a.f14760f, C2537j.aq);
            newSerializer.startTag(C2915a.f14760f, "isLowVersion");
            newSerializer.text(firmwareInfo.getIsLowVersion() + C2915a.f14760f);
            newSerializer.endTag(C2915a.f14760f, "isLowVersion");
            newSerializer.startTag(C2915a.f14760f, "inputTime");
            newSerializer.text(firmwareInfo.getInputTime() + C2915a.f14760f);
            newSerializer.endTag(C2915a.f14760f, "inputTime");
            newSerializer.startTag(C2915a.f14760f, "sort");
            newSerializer.text(firmwareInfo.getSort() + C2915a.f14760f);
            newSerializer.endTag(C2915a.f14760f, "sort");
            newSerializer.endTag(C2915a.f14760f, "firmware");
        }
        newSerializer.endTag(C2915a.f14760f, "firmwares");
        newSerializer.endDocument();
        return stringWriter.toString();
    }

    public List<FirmwareInfo> m9378a(InputStream inputStream) {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, C1142e.f5201a);
        List<FirmwareInfo> list = null;
        FirmwareInfo firmwareInfo = null;
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    list = new ArrayList();
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (!newPullParser.getName().equals("firmware")) {
                        if (!newPullParser.getName().equals("sysId")) {
                            if (!newPullParser.getName().equals("model")) {
                                if (!newPullParser.getName().equals("sysName")) {
                                    if (!newPullParser.getName().equals(RMsgInfo.COL_STATUS)) {
                                        if (!newPullParser.getName().equals("isLowVersion")) {
                                            if (!newPullParser.getName().equals("inputTime")) {
                                                if (!newPullParser.getName().equals("sort")) {
                                                    if (!newPullParser.getName().equals(C2537j.aq)) {
                                                        if (!newPullParser.getName().equals("fileName")) {
                                                            break;
                                                        }
                                                        newPullParser.next();
                                                        firmwareInfo.setFileName(newPullParser.getText());
                                                        break;
                                                    }
                                                    newPullParser.next();
                                                    firmwareInfo.setVersion(newPullParser.getText());
                                                    break;
                                                }
                                                newPullParser.next();
                                                firmwareInfo.setSort(Integer.valueOf(newPullParser.getText()).intValue());
                                                break;
                                            }
                                            newPullParser.next();
                                            firmwareInfo.setInputTime(newPullParser.getText());
                                            break;
                                        }
                                        newPullParser.next();
                                        firmwareInfo.setIsLowVersion(Integer.valueOf(newPullParser.getText()).intValue());
                                        break;
                                    }
                                    newPullParser.next();
                                    firmwareInfo.setStatus(newPullParser.getText());
                                    break;
                                }
                                newPullParser.next();
                                firmwareInfo.setSysName(newPullParser.getText());
                                break;
                            }
                            newPullParser.next();
                            firmwareInfo.setModel(Integer.valueOf(newPullParser.getText()).intValue());
                            break;
                        }
                        newPullParser.next();
                        firmwareInfo.setSysId(Integer.valueOf(newPullParser.getText()).intValue());
                        break;
                    }
                    firmwareInfo = new FirmwareInfo();
                    break;
                case Type.BYTE /*3*/:
                    if (!newPullParser.getName().equals("firmware")) {
                        break;
                    }
                    list.add(firmwareInfo);
                    firmwareInfo = null;
                    break;
                default:
                    break;
            }
        }
        return list;
    }
}
