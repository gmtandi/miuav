package com.autonavi.amap.mapcore;

import android.content.Context;
import com.amap.api.mapcore.AMapDelegateImp;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager.RetStyleIconsFile;
import org.p122a.p123a.C2915a;

public class GLMapResManager {
    private static final int AM_DATA_FORMAT_TYPE_GZIP = 1;
    public static final int AM_STYLEDATA_INDOOR = 1;
    private static final int AM_STYLE_DATA_TYPE_BASE_MAP = 0;
    public static final int TEXTURE_BACKGROUND = 1;
    public static final int TEXTURE_BIG_ICON = 20;
    public static final int TEXTURE_CHANGDUAN = 10;
    public static final int TEXTURE_GJ_GAOSU_GUIDE_BOARD = 12;
    public static final int TEXTURE_GUODAO_GUIDE_BOARD = 14;
    public static final int TEXTURE_HK_GAOSU_GUIDE_BOARD = 11;
    public static final int TEXTURE_ICON = 0;
    public static final int TEXTURE_INDOORICON = 31;
    public static final int TEXTURE_RAILWAY = 8;
    public static final int TEXTURE_ROADARROW = 2;
    public static final int TEXTURE_ROADROUND = 3;
    public static final int TEXTURE_SHENGDAO_GUIDE_BOARD = 15;
    public static final int TEXTURE_SHENG_GAOSU_GUIDE_BOARD = 13;
    public static final int TEXTURE_TIANQIAO = 9;
    public static final int TEXTURE_TMC_BLACK = 7;
    public static final int TEXTURE_TMC_GRAY = 18;
    public static final int TEXTURE_TMC_GREEN = 6;
    public static final int TEXTURE_TMC_RED = 4;
    public static final int TEXTURE_TMC_YELLOW = 5;
    public static final int TEXTURE_TOP_COVER = 41;
    public static final int TEXTURE_XIANDAO_GUIDE_BOARD = 16;
    public static final int TEXTURE_XIANGDAO_GUIDE_BOARD = 17;
    private static final String iconName1 = "icons_1_7_1444880368.data";
    private static final String iconName2 = "icons_2_7_1445580283.data";
    private static final String iconName3 = "icons_3_7_1444880372.data";
    private static final String iconName4 = "icons_4_6_1437480571.data";
    private static final String iconName50 = "icons_50_7_1444880375.data";
    private static final String styleName1 = "style_1_7_1445219169.data";
    private static final String styleName2 = "style_1_7_1445219169.data";
    private static final String styleName3 = "style_3_7_1445827513.data";
    private static final String styleName4 = "style_4_7_1445391691.data";
    private static final String styleName5 = "style_5_7_1445391719.data";
    private static final String styleName50 = "style_50_7_1445670996.data";
    private static final String styleName6 = "style_6_7_1445325996.data";
    private static final String styleName7 = "style_6_7_1445325996.data";
    private static final String styleName8 = "style_8_7_1445391734.data";
    private static final String styleName9 = "style_9_7_1445327958.data";
    public boolean isBigIcon;
    private Context mContext;
    private MapCore mMapCore;
    private AMapDelegateImp mapDelegateImp;

    /* renamed from: com.autonavi.amap.mapcore.GLMapResManager.1 */
    class C06051 implements Runnable {
        final /* synthetic */ byte[] f3695a;
        final /* synthetic */ byte[] f3696b;
        final /* synthetic */ byte[] f3697c;
        final /* synthetic */ GLMapResManager f3698d;

        C06051(GLMapResManager gLMapResManager, byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.f3698d = gLMapResManager;
            this.f3695a = bArr;
            this.f3696b = bArr2;
            this.f3697c = bArr3;
        }

        public void run() {
            if (this.f3695a != null) {
                this.f3698d.mMapCore.setInternaltexture(this.f3695a, GLMapResManager.TEXTURE_ICON);
            }
            if (this.f3696b != null) {
                this.f3698d.mMapCore.setInternaltexture(this.f3696b, GLMapResManager.TEXTURE_INDOORICON);
            }
            if (this.f3698d.isBigIcon && this.f3697c != null) {
                this.f3698d.mMapCore.setInternaltexture(this.f3697c, GLMapResManager.TEXTURE_BIG_ICON);
            }
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.GLMapResManager.2 */
    class C06062 implements Runnable {
        final /* synthetic */ byte[] f3699a;
        final /* synthetic */ byte[] f3700b;
        final /* synthetic */ byte[] f3701c;
        final /* synthetic */ byte[] f3702d;
        final /* synthetic */ byte[] f3703e;
        final /* synthetic */ GLMapResManager f3704f;

        C06062(GLMapResManager gLMapResManager, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            this.f3704f = gLMapResManager;
            this.f3699a = bArr;
            this.f3700b = bArr2;
            this.f3701c = bArr3;
            this.f3702d = bArr4;
            this.f3703e = bArr5;
        }

        public void run() {
            this.f3704f.setInternalTexture(this.f3699a, this.f3700b, this.f3701c, this.f3702d, this.f3703e);
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.GLMapResManager.3 */
    class C06073 implements Runnable {
        final /* synthetic */ byte[] f3705a;
        final /* synthetic */ byte[] f3706b;
        final /* synthetic */ GLMapResManager f3707c;

        C06073(GLMapResManager gLMapResManager, byte[] bArr, byte[] bArr2) {
            this.f3707c = gLMapResManager;
            this.f3705a = bArr;
            this.f3706b = bArr2;
        }

        public void run() {
            this.f3707c.mMapCore.setInternaltexture(this.f3705a, GLMapResManager.TEXTURE_BACKGROUND);
            this.f3707c.mMapCore.setInternaltexture(this.f3706b, GLMapResManager.TEXTURE_TOP_COVER);
        }
    }

    /* renamed from: com.autonavi.amap.mapcore.GLMapResManager.4 */
    class C06084 implements Runnable {
        final /* synthetic */ byte[] f3708a;
        final /* synthetic */ byte[] f3709b;
        final /* synthetic */ byte[] f3710c;
        final /* synthetic */ byte[] f3711d;
        final /* synthetic */ byte[] f3712e;
        final /* synthetic */ GLMapResManager f3713f;

        C06084(GLMapResManager gLMapResManager, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
            this.f3713f = gLMapResManager;
            this.f3708a = bArr;
            this.f3709b = bArr2;
            this.f3710c = bArr3;
            this.f3711d = bArr4;
            this.f3712e = bArr5;
        }

        public void run() {
            this.f3713f.mMapCore.setInternaltexture(this.f3708a, GLMapResManager.TEXTURE_ROADARROW);
            this.f3713f.mMapCore.setInternaltexture(this.f3709b, GLMapResManager.TEXTURE_ROADROUND);
            this.f3713f.mMapCore.setInternaltexture(this.f3710c, GLMapResManager.TEXTURE_RAILWAY);
            this.f3713f.mMapCore.setInternaltexture(this.f3711d, GLMapResManager.TEXTURE_TIANQIAO);
            this.f3713f.mMapCore.setInternaltexture(this.f3712e, GLMapResManager.TEXTURE_CHANGDUAN);
        }
    }

    public enum MapViewMode {
        NORAML,
        SATELLITE,
        BUS
    }

    public enum MapViewModeState {
        NORMAL,
        PREVIEW_CAR,
        PREVIEW_BUS,
        PREVIEW_FOOT,
        NAVI_CAR,
        NAVI_BUS,
        NAVI_FOOT
    }

    public enum MapViewTime {
        DAY,
        NIGHT
    }

    public GLMapResManager(AMapDelegateImp aMapDelegateImp, Context context) {
        this.isBigIcon = true;
        this.mapDelegateImp = null;
        this.mContext = null;
        this.mMapCore = null;
        this.mapDelegateImp = aMapDelegateImp;
        this.mContext = context;
        this.mMapCore = this.mapDelegateImp.m2450a();
    }

    private String getBigIconName(String str) {
        if (str.equals(iconName1)) {
            this.isBigIcon = true;
            return iconName4;
        }
        this.isBigIcon = false;
        return null;
    }

    private void setInternalTexture(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        if (bArr != null) {
            this.mMapCore.setInternaltexture(bArr, TEXTURE_TMC_GREEN);
        }
        if (bArr2 != null) {
            this.mMapCore.setInternaltexture(bArr2, TEXTURE_TMC_RED);
        }
        if (bArr3 != null) {
            this.mMapCore.setInternaltexture(bArr3, TEXTURE_TMC_YELLOW);
        }
        if (bArr4 != null) {
            this.mMapCore.setInternaltexture(bArr4, TEXTURE_TMC_BLACK);
        }
        if (bArr5 != null) {
            this.mMapCore.setInternaltexture(bArr5, TEXTURE_TMC_GRAY);
        }
    }

    public String getIconName() {
        String str = C2915a.f14760f;
        if (this.mapDelegateImp == null) {
            return str;
        }
        MapViewTime Y = this.mapDelegateImp.m2438Y();
        MapViewMode Z = this.mapDelegateImp.m2439Z();
        return MapViewTime.DAY == Y ? MapViewMode.BUS == Z ? iconName3 : iconName1 : MapViewTime.NIGHT == Y ? MapViewMode.BUS == Z ? iconName3 : iconName2 : str;
    }

    public String getStyleName() {
        String str = C2915a.f14760f;
        if (this.mapDelegateImp == null) {
            return str;
        }
        MapViewTime Y = this.mapDelegateImp.m2438Y();
        MapViewMode Z = this.mapDelegateImp.m2439Z();
        MapViewModeState aa = this.mapDelegateImp.aa();
        return MapViewTime.DAY == Y ? MapViewMode.NORAML == Z ? MapViewModeState.NAVI_CAR == aa ? styleName4 : MapViewModeState.PREVIEW_BUS == aa ? styleName7 : MapViewModeState.PREVIEW_CAR == aa ? styleName8 : MapViewModeState.NAVI_BUS == aa ? styleName9 : styleName2 : MapViewMode.SATELLITE == Z ? MapViewModeState.NAVI_CAR == aa ? styleName4 : MapViewModeState.PREVIEW_BUS == aa ? styleName7 : styleName3 : MapViewMode.BUS == Z ? MapViewModeState.NAVI_CAR == aa ? styleName4 : MapViewModeState.PREVIEW_BUS == aa ? styleName7 : styleName7 : str : MapViewTime.NIGHT == Y ? MapViewMode.NORAML == Z ? MapViewModeState.NAVI_CAR == aa ? styleName5 : MapViewModeState.PREVIEW_BUS == aa ? styleName7 : styleName2 : MapViewMode.SATELLITE == Z ? MapViewModeState.NAVI_CAR == aa ? styleName5 : MapViewModeState.PREVIEW_BUS == aa ? styleName7 : styleName3 : MapViewMode.BUS == Z ? MapViewModeState.NAVI_CAR == aa ? styleName5 : MapViewModeState.PREVIEW_BUS == aa ? styleName7 : styleName7 : str : str;
    }

    public void setBkTexture(boolean z) {
        byte[] otherResData;
        byte[] otherResData2;
        if (this.mapDelegateImp.m2438Y() != MapViewTime.NIGHT) {
            otherResData = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("bktile.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("3d_sky_day.dat");
        } else {
            otherResData = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("bktile_n.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("3d_sky_night.dat");
        }
        if (z) {
            this.mMapCore.setInternaltexture(otherResData, TEXTURE_BACKGROUND);
            this.mMapCore.setInternaltexture(otherResData2, TEXTURE_TOP_COVER);
            return;
        }
        this.mapDelegateImp.m2491a(new C06073(this, otherResData, otherResData2));
    }

    public void setIconsData(boolean z) {
        if (this.mapDelegateImp != null) {
            byte[] bArr = null;
            RetStyleIconsFile retStyleIconsFile = new RetStyleIconsFile();
            String iconName = getIconName();
            String bigIconName = getBigIconName(iconName);
            byte[] iconsData = MapTilsCacheAndResManager.getInstance(this.mContext).getIconsData(iconName, retStyleIconsFile);
            if (this.isBigIcon) {
                bArr = MapTilsCacheAndResManager.getInstance(this.mContext).getIconsData(bigIconName, new RetStyleIconsFile());
            }
            byte[] iconsData2 = MapTilsCacheAndResManager.getInstance(this.mContext).getIconsData(iconName50, retStyleIconsFile);
            if (z) {
                if (iconsData != null) {
                    this.mMapCore.setInternaltexture(iconsData, TEXTURE_ICON);
                }
                if (iconsData2 != null) {
                    this.mMapCore.setInternaltexture(iconsData2, TEXTURE_INDOORICON);
                }
                if (this.isBigIcon && bArr != null) {
                    this.mMapCore.setInternaltexture(bArr, TEXTURE_BIG_ICON);
                    return;
                }
                return;
            }
            this.mapDelegateImp.m2491a(new C06051(this, iconsData, iconsData2, bArr));
        }
    }

    public void setOtherMapTexture(boolean z) {
        byte[] otherResData = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("roadarrow.data");
        byte[] otherResData2 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("lineround.data");
        byte[] otherResData3 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("dash.data");
        byte[] otherResData4 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("dash_tq.data");
        byte[] otherResData5 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("dash_cd.data");
        if (z) {
            this.mMapCore.setInternaltexture(otherResData, TEXTURE_ROADARROW);
            this.mMapCore.setInternaltexture(otherResData2, TEXTURE_ROADROUND);
            this.mMapCore.setInternaltexture(otherResData3, TEXTURE_RAILWAY);
            this.mMapCore.setInternaltexture(otherResData4, TEXTURE_TIANQIAO);
            this.mMapCore.setInternaltexture(otherResData5, TEXTURE_CHANGDUAN);
            return;
        }
        this.mapDelegateImp.m2491a(new C06084(this, otherResData, otherResData2, otherResData3, otherResData4, otherResData5));
    }

    public void setRoadGuideTexture(boolean z) {
    }

    public void setStyleData() {
        if (this.mapDelegateImp != null) {
            RetStyleIconsFile retStyleIconsFile = new RetStyleIconsFile();
            byte[] styleData = MapTilsCacheAndResManager.getInstance(this.mContext).getStyleData(getStyleName(), retStyleIconsFile);
            if (styleData != null) {
                this.mMapCore.setStyleData(styleData, TEXTURE_ICON, TEXTURE_BACKGROUND);
            }
            byte[] styleData2 = MapTilsCacheAndResManager.getInstance(this.mContext).getStyleData(styleName50, retStyleIconsFile);
            if (styleData2 != null) {
                this.mMapCore.setStyleData(styleData2, TEXTURE_BACKGROUND, TEXTURE_BACKGROUND);
            }
        }
    }

    public void setTrafficTexture(boolean z) {
        byte[] otherResData;
        byte[] otherResData2;
        byte[] otherResData3;
        byte[] otherResData4;
        byte[] otherResData5;
        if (this.mapDelegateImp.m2438Y() != MapViewTime.NIGHT) {
            otherResData = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tgl_l.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("trl_l.data");
            otherResData3 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tyl_l.data");
            otherResData4 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tbl_l.data");
            otherResData5 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tnl_l.data");
        } else {
            otherResData = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tgl_n.data");
            otherResData2 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("trl_n.data");
            otherResData3 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tyl_n.data");
            otherResData4 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tbl_n.data");
            otherResData5 = MapTilsCacheAndResManager.getInstance(this.mContext).getOtherResData("tnl_n.data");
        }
        if (z) {
            setInternalTexture(otherResData, otherResData2, otherResData3, otherResData4, otherResData5);
        } else {
            this.mapDelegateImp.m2491a(new C06062(this, otherResData, otherResData2, otherResData3, otherResData4, otherResData5));
        }
    }
}
