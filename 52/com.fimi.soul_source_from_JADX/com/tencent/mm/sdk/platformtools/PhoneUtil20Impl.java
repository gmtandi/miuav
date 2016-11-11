package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.tencent.mm.sdk.platformtools.PhoneUtil.CellInfo;
import java.util.LinkedList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;

class PhoneUtil20Impl {
    private static int aK;
    private static int aL;
    private TelephonyManager aM;
    private PhoneStateListener aN;
    private int aP;

    /* renamed from: com.tencent.mm.sdk.platformtools.PhoneUtil20Impl.1 */
    class C22721 extends PhoneStateListener {
        final /* synthetic */ PhoneUtil20Impl aQ;

        C22721(PhoneUtil20Impl phoneUtil20Impl) {
            this.aQ = phoneUtil20Impl;
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (this.aQ.aP == 2) {
                PhoneUtil20Impl.aL = signalStrength.getCdmaDbm();
            }
            if (this.aQ.aP == 1) {
                PhoneUtil20Impl.aL = (signalStrength.getGsmSignalStrength() * 2) - 113;
            }
            if (this.aQ.aM != null) {
                this.aQ.aM.listen(this.aQ.aN, 0);
            }
        }
    }

    static {
        aK = C1873o.ak;
        aL = C1873o.ak;
    }

    PhoneUtil20Impl() {
        this.aN = new C22721(this);
    }

    public List<CellInfo> getCellInfoList(Context context) {
        GsmCellLocation gsmCellLocation;
        int cid;
        int lac;
        List<NeighboringCellInfo> neighboringCellInfo;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        List<CellInfo> linkedList = new LinkedList();
        String str = "460";
        String str2 = C2915a.f14760f;
        try {
            String networkOperator = telephonyManager.getNetworkOperator();
            if (networkOperator == null || networkOperator.equals(C2915a.f14760f)) {
                networkOperator = telephonyManager.getSimOperator();
                if (!(networkOperator == null || networkOperator.equals(C2915a.f14760f))) {
                    str = networkOperator.substring(0, 3);
                    str2 = networkOperator.substring(3, 5);
                }
                networkOperator = str2;
            } else {
                str = networkOperator.substring(0, 3);
                networkOperator = networkOperator.substring(3, 5);
            }
            String str3;
            if (telephonyManager.getPhoneType() == 2) {
                try {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                    if (cdmaCellLocation != null) {
                        str3 = aL == aK ? C2915a.f14760f : aL;
                        if (!(cdmaCellLocation.getBaseStationId() == -1 || cdmaCellLocation.getNetworkId() == -1 || cdmaCellLocation.getSystemId() == -1)) {
                            linkedList.add(new CellInfo(str, networkOperator, C2915a.f14760f, C2915a.f14760f, str3, PhoneUtil.CELL_CDMA, cdmaCellLocation.getBaseStationId(), cdmaCellLocation.getNetworkId(), cdmaCellLocation.getSystemId()));
                        }
                    }
                } catch (Exception e) {
                    try {
                        gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                        if (gsmCellLocation != null) {
                            cid = gsmCellLocation.getCid();
                            lac = gsmCellLocation.getLac();
                            if (!(lac >= Util.MASK_16BIT || lac == -1 || cid == -1)) {
                                linkedList.add(new CellInfo(str, networkOperator, String.valueOf(lac), String.valueOf(cid), C2915a.f14760f, PhoneUtil.CELL_GSM, C2915a.f14760f, C2915a.f14760f, C2915a.f14760f));
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
                    if (neighboringCellInfo != null && neighboringCellInfo.size() > 0) {
                        for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                            if (!(neighboringCellInfo2.getCid() == -1 || neighboringCellInfo2.getLac() > Util.MASK_16BIT || neighboringCellInfo2.getLac() == -1)) {
                                linkedList.add(new CellInfo(str, networkOperator, neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid(), ((neighboringCellInfo2.getRssi() * 2) - 113), PhoneUtil.CELL_GSM, C2915a.f14760f, C2915a.f14760f, C2915a.f14760f));
                            }
                        }
                    }
                }
            } else {
                try {
                    gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                    if (gsmCellLocation != null) {
                        cid = gsmCellLocation.getCid();
                        lac = gsmCellLocation.getLac();
                        if (!(lac >= Util.MASK_16BIT || lac == -1 || cid == -1)) {
                            linkedList.add(new CellInfo(str, networkOperator, String.valueOf(lac), String.valueOf(cid), aL == aK ? C2915a.f14760f : aL, PhoneUtil.CELL_GSM, C2915a.f14760f, C2915a.f14760f, C2915a.f14760f));
                        }
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
                neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
                if (neighboringCellInfo != null && neighboringCellInfo.size() > 0) {
                    for (NeighboringCellInfo neighboringCellInfo22 : neighboringCellInfo) {
                        if (neighboringCellInfo22.getCid() != -1 && neighboringCellInfo22.getLac() <= Util.MASK_16BIT) {
                            str3 = ((neighboringCellInfo22.getRssi() * 2) - 113);
                            Log.m13547v("checked", "lac:" + neighboringCellInfo22.getLac() + "  cid:" + neighboringCellInfo22.getCid() + " dbm:" + str3);
                            linkedList.add(new CellInfo(str, networkOperator, neighboringCellInfo22.getLac(), neighboringCellInfo22.getCid(), str3, PhoneUtil.CELL_GSM, C2915a.f14760f, C2915a.f14760f, C2915a.f14760f));
                        }
                    }
                }
            }
            return linkedList;
        } catch (Exception e222) {
            e222.printStackTrace();
            return linkedList;
        }
    }

    public void getSignalStrength(Context context) {
        this.aM = (TelephonyManager) context.getSystemService("phone");
        this.aM.listen(this.aN, Opcodes.ACC_NATIVE);
        this.aP = this.aM.getPhoneType();
    }
}
