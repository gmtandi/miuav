package com.p016a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.fk */
public abstract class fk extends Binder implements fj {
    public fk() {
        attachInterface(this, "com.amap.api.service.locationprovider.ILocationProviderService");
    }

    public static fj m1830a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.amap.api.service.locationprovider.ILocationProviderService");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof fj)) ? new fl(iBinder) : (fj) queryLocalInterface;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                parcel.enforceInterface("com.amap.api.service.locationprovider.ILocationProviderService");
                Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                int a = m1829a(bundle);
                parcel2.writeNoException();
                parcel2.writeInt(a);
                if (bundle != null) {
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case 1598968902:
                parcel2.writeString("com.amap.api.service.locationprovider.ILocationProviderService");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
