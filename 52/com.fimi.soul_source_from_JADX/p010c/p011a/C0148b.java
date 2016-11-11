package p010c.p011a;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: c.a.b */
public abstract class C0148b extends Binder implements C0147a {
    static final int f173a = 1;
    static final int f174b = 2;
    static final int f175c = 3;
    static final int f176d = 4;
    private static final String f177e = "miui.net.IXiaomiAuthService";

    public C0148b() {
        attachInterface(this, f177e);
    }

    public static C0147a m389a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(f177e);
        return (queryLocalInterface == null || !(queryLocalInterface instanceof C0147a)) ? new C0149c(iBinder) : (C0147a) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        Bundle a;
        switch (i) {
            case f173a /*1*/:
                parcel.enforceInterface(f177e);
                a = m385a(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(f173a);
                    a.writeToParcel(parcel2, f173a);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case f174b /*2*/:
                parcel.enforceInterface(f177e);
                a = m386b(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(f173a);
                    a.writeToParcel(parcel2, f173a);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case f175c /*3*/:
                parcel.enforceInterface(f177e);
                a = m387c(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (a != null) {
                    parcel2.writeInt(f173a);
                    a.writeToParcel(parcel2, f173a);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            case f176d /*4*/:
                parcel.enforceInterface(f177e);
                m388d(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString(f177e);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
