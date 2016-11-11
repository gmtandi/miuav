package com.tencent.mm.sdk.contact;

import android.content.ContentValues;
import android.database.Cursor;
import com.tencent.mm.sdk.platformtools.LVBuffer;
import com.tencent.mm.sdk.platformtools.Log;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.mm.sdk.storage.MAutoDBItem;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

public class RContact extends MAutoDBItem {
    public static final String[] COLUMNS;
    public static final String COL_ALIAS = "alias";
    public static final String COL_CONREMARK = "conRemark";
    public static final String COL_CONREMARK_PYFULL = "conRemarkPYFull";
    public static final String COL_CONREMARK_PYSHORT = "conRemarkPYShort";
    public static final String COL_DOMAINLIST = "domainList";
    public static final int COL_ID_INVALID_VALUE = -1;
    public static final String COL_NICKNAME = "nickname";
    public static final String COL_PYINITIAL = "pyInitial";
    public static final String COL_QUANPIN = "quanPin";
    public static final String COL_SHOWHEAD = "showHead";
    public static final String COL_TYPE = "type";
    public static final String COL_USERNAME = "username";
    public static final String COL_VERIFY_FLAG = "verifyFlag";
    public static final String COL_WEIBOFLAG = "weiboFlag";
    public static final String COL_WEIBONICKNAME = "weiboNickname";
    public static final int DEL_CONTACT_MSG = -1;
    public static final String FAVOUR_CONTACT_SHOW_HEAD_CHAR = "$";
    public static final int FAVOUR_CONTACT_SHOW_HEAD_CODE = 32;
    private static Map<String, String> f11762M = null;
    public static final int MM_CONTACTFLAG_ALL = 127;
    public static final int MM_CONTACTFLAG_BLACKLISTCONTACT = 8;
    public static final int MM_CONTACTFLAG_CHATCONTACT = 2;
    public static final int MM_CONTACTFLAG_CHATROOMCONTACT = 4;
    public static final int MM_CONTACTFLAG_CONTACT = 1;
    public static final int MM_CONTACTFLAG_DOMAINCONTACT = 16;
    public static final int MM_CONTACTFLAG_FAVOURCONTACT = 64;
    public static final int MM_CONTACTFLAG_HIDECONTACT = 32;
    public static final int MM_CONTACTFLAG_NULL = 0;
    public static final int MM_CONTACTIMGFLAG_HAS_HEADIMG = 3;
    public static final int MM_CONTACTIMGFLAG_HAS_NO_HEADIMG = 4;
    public static final int MM_CONTACTIMGFLAG_LOCAL_EXIST = 153;
    public static final int MM_CONTACTIMGFLAG_MODIFY = 2;
    public static final int MM_CONTACTIMGFLAG_NOTMODIFY = 1;
    public static final int MM_CONTACT_BOTTLE = 5;
    public static final int MM_CONTACT_CHATROOM = 2;
    public static final int MM_CONTACT_EMAIL = 3;
    public static final int MM_CONTACT_QQ = 4;
    public static final int MM_CONTACT_QQMICROBLOG = 1;
    public static final int MM_CONTACT_WEIXIN = 0;
    public static final int MM_SEX_FEMALE = 2;
    public static final int MM_SEX_MALE = 1;
    public static final int MM_SEX_UNKNOWN = 0;
    private static Map<String, String> f11763N;
    public static final int NOT_IN_CHAT_LIST = 0;
    protected static Field[] f11764p;
    private String f11765A;
    private int f11766B;
    private int f11767C;
    private String f11768D;
    private String f11769E;
    private String f11770F;
    private String f11771G;
    private int f11772H;
    private int f11773I;
    private String f11774J;
    private String f11775K;
    private String f11776L;
    public long contactId;
    public String field_alias;
    public String field_conRemark;
    public String field_conRemarkPYFull;
    public String field_conRemarkPYShort;
    public String field_domainList;
    public byte[] field_lvbuff;
    public String field_nickname;
    public String field_pyInitial;
    public String field_quanPin;
    public int field_showHead;
    public int field_type;
    public String field_username;
    public int field_verifyFlag;
    public int field_weiboFlag;
    public String field_weiboNickname;
    private int f11777h;
    private int f11778q;
    private int f11779r;
    private String f11780s;
    private long f11781u;
    private String f11782v;
    private String f11783w;
    private int f11784x;
    private int f11785y;
    private String f11786z;

    static {
        Field[] validFields = MAutoDBItem.getValidFields(RContact.class);
        f11764p = validFields;
        COLUMNS = MAutoDBItem.getFullColumns(validFields);
        f11762M = new HashMap();
        f11763N = new HashMap();
    }

    public RContact() {
        reset();
    }

    public RContact(String str) {
        this();
        if (str == null) {
            str = C2915a.f14760f;
        }
        this.field_username = str;
    }

    private byte[] m13520a() {
        try {
            LVBuffer lVBuffer = new LVBuffer();
            lVBuffer.initBuild();
            lVBuffer.putInt(this.f11778q);
            lVBuffer.putInt(this.f11779r);
            lVBuffer.putString(this.f11780s);
            lVBuffer.putLong(this.f11781u);
            lVBuffer.putInt(this.f11777h);
            lVBuffer.putString(this.f11782v);
            lVBuffer.putString(this.f11783w);
            lVBuffer.putInt(this.f11784x);
            lVBuffer.putInt(this.f11785y);
            lVBuffer.putString(this.f11786z);
            lVBuffer.putString(this.f11765A);
            lVBuffer.putInt(this.f11766B);
            lVBuffer.putInt(this.f11767C);
            lVBuffer.putString(this.f11768D);
            lVBuffer.putString(this.f11769E);
            lVBuffer.putString(this.f11770F);
            lVBuffer.putString(this.f11771G);
            lVBuffer.putInt(this.f11772H);
            lVBuffer.putInt(this.f11773I);
            lVBuffer.putString(this.f11774J);
            lVBuffer.putInt(this.field_verifyFlag);
            lVBuffer.putString(this.f11775K);
            lVBuffer.putString(this.f11776L);
            return lVBuffer.buildFinish();
        } catch (Exception e) {
            Log.m13541e("MicroMsg.Contact", "get value failed");
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDisplayNick(String str) {
        return str == null ? null : str.toLowerCase().endsWith("@t.qq.com") ? "@" + str.replace("@t.qq.com", C2915a.f14760f) : str.toLowerCase().endsWith("@qqim") ? str.replace("@qqim", C2915a.f14760f) : str;
    }

    public static int getBlackListContactBit() {
        return MM_CONTACTFLAG_BLACKLISTCONTACT;
    }

    public static int getContactBit() {
        return MM_SEX_MALE;
    }

    public static int getDomainContactBit() {
        return MM_CONTACTFLAG_DOMAINCONTACT;
    }

    public static int getHiddenContactBit() {
        return MM_CONTACTFLAG_HIDECONTACT;
    }

    public static boolean isContact(int i) {
        return (i & MM_SEX_MALE) != 0;
    }

    private static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static void setHardCodeAliasMaps(Map<String, String> map) {
        f11763N = map;
    }

    public static void setHardCodeNickMaps(Map<String, String> map) {
        f11762M = map;
    }

    public int calculateShowHead() {
        int i = MM_CONTACTFLAG_HIDECONTACT;
        if (this.field_conRemarkPYShort != null && !this.field_conRemarkPYShort.equals(C2915a.f14760f)) {
            i = this.field_conRemarkPYShort.charAt(MM_CONTACT_WEIXIN);
        } else if (this.field_conRemarkPYFull != null && !this.field_conRemarkPYFull.equals(C2915a.f14760f)) {
            i = this.field_conRemarkPYFull.charAt(MM_CONTACT_WEIXIN);
        } else if (this.field_pyInitial != null && !this.field_pyInitial.equals(C2915a.f14760f)) {
            i = this.field_pyInitial.charAt(MM_CONTACT_WEIXIN);
        } else if (this.field_quanPin != null && !this.field_quanPin.equals(C2915a.f14760f)) {
            i = this.field_quanPin.charAt(MM_CONTACT_WEIXIN);
        } else if (this.field_nickname != null && !this.field_nickname.equals(C2915a.f14760f) && isLetter(this.field_nickname.charAt(MM_CONTACT_WEIXIN))) {
            i = this.field_nickname.charAt(MM_CONTACT_WEIXIN);
        } else if (!(this.field_username == null || this.field_username.equals(C2915a.f14760f) || !isLetter(this.field_username.charAt(MM_CONTACT_WEIXIN)))) {
            i = this.field_username.charAt(MM_CONTACT_WEIXIN);
        }
        return (i < 97 || i > Opcodes.ISHR) ? (i < 65 || i > 90) ? Opcodes.LSHR : i : (char) (i - 32);
    }

    public void convertFrom(Cursor cursor) {
        super.convertFrom(cursor);
        this.contactId = (long) ((int) cursor.getLong(cursor.getColumnCount() + DEL_CONTACT_MSG));
        byte[] bArr = this.field_lvbuff;
        try {
            LVBuffer lVBuffer = new LVBuffer();
            int initParse = lVBuffer.initParse(bArr);
            if (initParse != 0) {
                Log.m13541e("MicroMsg.Contact", "parse LVBuffer error:" + initParse);
                return;
            }
            this.f11778q = lVBuffer.getInt();
            this.f11779r = lVBuffer.getInt();
            this.f11780s = lVBuffer.getString();
            this.f11781u = lVBuffer.getLong();
            this.f11777h = lVBuffer.getInt();
            this.f11782v = lVBuffer.getString();
            this.f11783w = lVBuffer.getString();
            this.f11784x = lVBuffer.getInt();
            this.f11785y = lVBuffer.getInt();
            this.f11786z = lVBuffer.getString();
            this.f11765A = lVBuffer.getString();
            this.f11766B = lVBuffer.getInt();
            this.f11767C = lVBuffer.getInt();
            this.f11768D = lVBuffer.getString();
            this.f11769E = lVBuffer.getString();
            this.f11770F = lVBuffer.getString();
            this.f11771G = lVBuffer.getString();
            this.f11772H = lVBuffer.getInt();
            this.f11773I = lVBuffer.getInt();
            this.f11774J = lVBuffer.getString();
            this.field_verifyFlag = lVBuffer.getInt();
            this.f11775K = lVBuffer.getString();
            if (!lVBuffer.checkGetFinish()) {
                this.f11776L = lVBuffer.getString();
            }
        } catch (Exception e) {
            Log.m13541e("MicroMsg.Contact", "get value failed");
            e.printStackTrace();
        }
    }

    public ContentValues convertTo() {
        this.field_lvbuff = m13520a();
        return super.convertTo();
    }

    public Field[] fields() {
        return f11764p;
    }

    public String getAlias() {
        String str = (String) f11763N.get(this.field_username);
        return str == null ? this.field_alias : str;
    }

    public int getChatroomNotify() {
        return this.f11766B;
    }

    public String getCity() {
        return this.f11770F;
    }

    public String getConQQMBlog() {
        return this.f11765A;
    }

    public String getConRemark() {
        return this.field_conRemark;
    }

    public String getConRemarkPYFull() {
        return this.field_conRemarkPYFull;
    }

    public String getConRemarkPYShort() {
        return this.field_conRemarkPYShort;
    }

    public String getConSMBlog() {
        return this.f11786z;
    }

    public int getConType() {
        return this.f11785y;
    }

    public int getContactID() {
        return (int) this.contactId;
    }

    public String getDisplayNick() {
        String str = (String) f11762M.get(this.field_username);
        return str != null ? str : (this.field_nickname == null || this.field_nickname.length() <= 0) ? getDisplayUser() : this.field_nickname;
    }

    public String getDisplayRemark() {
        return (this.field_conRemark == null || this.field_conRemark.trim().equals(C2915a.f14760f)) ? getDisplayNick() : this.field_conRemark;
    }

    public String getDisplayUser() {
        String alias = getAlias();
        if (!Util.isNullOrNil(alias)) {
            return alias;
        }
        alias = formatDisplayNick(this.field_username);
        return (alias == null || alias.length() == 0) ? this.field_username : alias;
    }

    public String getDistance() {
        return this.f11771G;
    }

    public String getDomainList() {
        return this.field_domainList;
    }

    public String getEmail() {
        return this.f11782v;
    }

    public long getFaceBookId() {
        return this.f11781u;
    }

    public String getFaceBookUsername() {
        return this.f11780s;
    }

    public int getFromType() {
        return this.f11772H;
    }

    public int getImgFlag() {
        return this.f11778q;
    }

    public String getMobile() {
        return this.f11783w;
    }

    public String getNickname() {
        return this.field_nickname;
    }

    public int getPersonalCard() {
        return this.f11767C;
    }

    public String getProvince() {
        return this.f11769E;
    }

    public String getPyInitial() {
        return (this.field_pyInitial == null || this.field_pyInitial.length() < 0) ? getQuanPin() : this.field_pyInitial;
    }

    public String getQuanPin() {
        return (this.field_quanPin == null || this.field_quanPin.length() < 0) ? getNickname() : this.field_quanPin;
    }

    public String getRegionCode() {
        return this.f11776L;
    }

    public int getSex() {
        return this.f11779r;
    }

    public int getShowFlag() {
        return this.f11784x;
    }

    public int getShowHead() {
        return this.field_showHead;
    }

    public String getSignature() {
        return this.f11768D;
    }

    public int getSource() {
        return this.f11773I;
    }

    public int getType() {
        return this.field_type;
    }

    public int getUin() {
        return this.f11777h;
    }

    public String getUsername() {
        return this.field_username;
    }

    public int getVerifyFlag() {
        return this.field_verifyFlag;
    }

    public String getVerifyInfo() {
        return this.f11775K;
    }

    public String getWeibo() {
        return this.f11774J;
    }

    public int getWeiboFlag() {
        return this.field_weiboFlag;
    }

    public String getWeiboNickName() {
        return this.field_weiboNickname;
    }

    public boolean isBlackListContact() {
        return (this.field_type & MM_CONTACTFLAG_BLACKLISTCONTACT) != 0;
    }

    public boolean isChatContact() {
        return (this.field_type & MM_SEX_FEMALE) != 0;
    }

    public boolean isChatRoomContact() {
        return (this.field_type & MM_CONTACT_QQ) != 0;
    }

    public boolean isContact() {
        return isContact(this.field_type);
    }

    public boolean isDomainContact() {
        return (this.field_type & MM_CONTACTFLAG_DOMAINCONTACT) != 0;
    }

    public boolean isFavourContact() {
        return (this.field_type & MM_CONTACTFLAG_FAVOURCONTACT) != 0;
    }

    public boolean isHidden() {
        return (this.field_type & MM_CONTACTFLAG_HIDECONTACT) != 0;
    }

    public boolean isImgLocalExist() {
        return MM_CONTACTIMGFLAG_LOCAL_EXIST == this.f11778q;
    }

    public void reset() {
        this.field_username = C2915a.f14760f;
        this.field_nickname = C2915a.f14760f;
        this.field_pyInitial = C2915a.f14760f;
        this.field_quanPin = C2915a.f14760f;
        this.field_alias = C2915a.f14760f;
        this.field_conRemark = C2915a.f14760f;
        this.field_conRemarkPYShort = C2915a.f14760f;
        this.field_conRemarkPYFull = C2915a.f14760f;
        this.field_domainList = C2915a.f14760f;
        this.field_weiboFlag = MM_CONTACT_WEIXIN;
        this.field_weiboNickname = C2915a.f14760f;
        this.field_showHead = MM_CONTACT_WEIXIN;
        this.field_type = MM_CONTACT_WEIXIN;
        this.field_verifyFlag = MM_CONTACT_WEIXIN;
        this.f11779r = MM_CONTACT_WEIXIN;
        this.f11771G = C2915a.f14760f;
        this.f11772H = MM_CONTACT_WEIXIN;
        this.f11777h = MM_CONTACT_WEIXIN;
        this.f11782v = C2915a.f14760f;
        this.f11783w = C2915a.f14760f;
        this.f11784x = MM_CONTACT_WEIXIN;
        this.f11785y = MM_CONTACT_WEIXIN;
        this.f11786z = C2915a.f14760f;
        this.f11765A = C2915a.f14760f;
        this.f11766B = MM_SEX_MALE;
        this.f11778q = MM_CONTACT_WEIXIN;
        this.f11767C = MM_CONTACT_WEIXIN;
        this.f11768D = C2915a.f14760f;
        this.f11769E = C2915a.f14760f;
        this.f11770F = C2915a.f14760f;
        this.f11773I = MM_CONTACT_WEIXIN;
        this.f11775K = C2915a.f14760f;
        this.f11774J = C2915a.f14760f;
        this.f11781u = 0;
        this.f11780s = C2915a.f14760f;
        this.f11776L = C2915a.f14760f;
    }

    public void setAlias(String str) {
        this.field_alias = str;
    }

    public void setBlackList() {
        this.field_type |= MM_CONTACTFLAG_BLACKLISTCONTACT;
    }

    public void setChatContact() {
        this.field_type |= MM_SEX_FEMALE;
    }

    public void setChatroomContact() {
        this.field_type |= MM_CONTACT_QQ;
    }

    public void setChatroomNotify(int i) {
        this.f11766B = i;
    }

    public void setCity(String str) {
        this.f11770F = str;
    }

    public void setConQQMBlog(String str) {
        this.f11765A = str;
    }

    public void setConRemark(String str) {
        this.field_conRemark = str;
    }

    public void setConRemarkPYFull(String str) {
        this.field_conRemarkPYFull = str;
    }

    public void setConRemarkPYShort(String str) {
        this.field_conRemarkPYShort = str;
    }

    public void setConSMBlog(String str) {
        this.f11786z = str;
    }

    public void setConType(int i) {
        this.f11785y = i;
    }

    public void setContact() {
        this.field_type |= MM_SEX_MALE;
    }

    public void setDistance(String str) {
        this.f11771G = str;
    }

    public void setDomainList(String str) {
        this.field_domainList = str;
    }

    public void setEmail(String str) {
        this.f11782v = str;
    }

    public void setFaceBookId(long j) {
        this.f11781u = j;
    }

    public void setFaceBookUsername(String str) {
        this.f11780s = str;
    }

    public void setFavour() {
        this.field_type |= MM_CONTACTFLAG_FAVOURCONTACT;
    }

    public void setFromType(int i) {
        this.f11772H = i;
    }

    public void setHidden() {
        this.field_type |= MM_CONTACTFLAG_HIDECONTACT;
    }

    public void setImgFlag(int i) {
        this.f11778q = i;
    }

    public void setMobile(String str) {
        this.f11783w = str;
    }

    public void setNickname(String str) {
        this.field_nickname = str;
    }

    public void setNullContact() {
        this.field_type = MM_CONTACT_WEIXIN;
    }

    public void setPersonalCard(int i) {
        this.f11767C = i;
    }

    public void setProvince(String str) {
        this.f11769E = str;
    }

    public void setPyInitial(String str) {
        this.field_pyInitial = str;
    }

    public void setQuanPin(String str) {
        this.field_quanPin = str;
    }

    public void setRegionCode(String str) {
        this.f11776L = str;
    }

    public void setSex(int i) {
        this.f11779r = i;
    }

    public void setShowFlag(int i) {
        this.f11784x = i;
    }

    public void setShowHead(int i) {
        this.field_showHead = i;
    }

    public void setSignature(String str) {
        this.f11768D = str;
    }

    public void setSource(int i) {
        this.f11773I = i;
    }

    public void setType(int i) {
        this.field_type = i;
    }

    public void setUin(int i) {
        this.f11777h = i;
    }

    public void setUsername(String str) {
        this.field_username = str;
    }

    public void setVerifyFlag(int i) {
        this.field_verifyFlag = i;
    }

    public void setVerifyInfo(String str) {
        this.f11775K = str;
    }

    public void setWeibo(String str) {
        this.f11774J = str;
    }

    public void setWeiboFlag(int i) {
        this.field_weiboFlag = i;
    }

    public void setWeiboNickName(String str) {
        this.field_weiboNickname = str;
    }

    public void unSetBlackList() {
        this.field_type &= -9;
    }

    public void unSetChatContact() {
        this.field_type &= -3;
    }

    public void unSetContact() {
        this.field_type &= -2;
    }

    public void unSetFavour() {
        this.field_type &= -65;
    }

    public void unSetHidden() {
        this.field_type &= -33;
    }
}
