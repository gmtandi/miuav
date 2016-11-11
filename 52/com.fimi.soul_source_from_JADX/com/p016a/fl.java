package com.p016a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: com.a.fl */
class fl implements fj {
    private IBinder f1209a;

    fl(IBinder iBinder) {
        this.f1209a = iBinder;
    }

    public int m1831a(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.amap.api.service.locationprovider.ILocationProviderService");
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.f1209a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            int readInt = obtain2.readInt();
            if (obtain2.readInt() != 0) {
                bundle.readFromParcel(obtain2);
            }
            obtain2.recycle();
            obtain.recycle();
            return readInt;
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.f1209a;
    }
}
