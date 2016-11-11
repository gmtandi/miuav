package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.mm.sdk.platformtools.PhoneUtil.CellInfo;
import java.util.LinkedList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

class PhoneUtil16Impl {
    private static int aK;
    private static int aL;
    private TelephonyManager aM;
    private PhoneStateListener aN;

    /* renamed from: com.tencent.mm.sdk.platformtools.PhoneUtil16Impl.1 */
    class C22711 extends PhoneStateListener {
        final /* synthetic */ PhoneUtil16Impl aO;

        C22711(PhoneUtil16Impl phoneUtil16Impl) {
            this.aO = phoneUtil16Impl;
        }

        public void onSignalStrengthChanged(int i) {
            super.onSignalStrengthChanged(i);
            PhoneUtil16Impl.aL = (i * 2) - 113;
            if (this.aO.aM != null) {
                this.aO.aM.listen(this.aO.aN, 0);
            }
        }
    }

    static {
        aK = C1873o.ak;
        aL = C1873o.ak;
    }

    PhoneUtil16Impl() {
        this.aN = new C22711(this);
    }

    public List<CellInfo> getCellInfoList(Context context) {
        List<CellInfo> linkedList = new LinkedList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String networkOperator = telephonyManager.getNetworkOperator();
        if (networkOperator == null || networkOperator.equals(C2915a.f14760f)) {
            return linkedList;
        }
        String str = "460";
        String str2 = C2915a.f14760f;
        try {
            str = networkOperator.substring(0, 3);
            str2 = networkOperator.substring(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            if (gsmCellLocation != null) {
                int cid = gsmCellLocation.getCid();
                int lac = gsmCellLocation.getLac();
                if (!(lac >= Util.MASK_16BIT || lac == -1 || cid == -1)) {
                    linkedList.add(new CellInfo(str, str2, String.valueOf(lac), String.valueOf(cid), aL == aK ? C2915a.f14760f : aL, PhoneUtil.CELL_GSM, C2915a.f14760f, C2915a.f14760f, C2915a.f14760f));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        List<NeighboringCellInfo> neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
        if (neighboringCellInfo != null && neighboringCellInfo.size() > 0) {
            for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                if (neighboringCellInfo2.getCid() != -1) {
                    linkedList.add(new CellInfo(str, str2, C2915a.f14760f, neighboringCellInfo2.getCid(), C2915a.f14760f, PhoneUtil.CELL_GSM, C2915a.f14760f, C2915a.f14760f, C2915a.f14760f));
                }
            }
        }
        return linkedList;
    }

    public void getSignalStrength(Context context) {
        this.aM = (TelephonyManager) context.getSystemService("phone");
        this.aM.listen(this.aN, Opcodes.ACC_NATIVE);
    }
}
