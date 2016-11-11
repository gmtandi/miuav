package com.xiaomi.account;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface IXiaomiAuthService extends IInterface {

    public abstract class Stub extends Binder implements IXiaomiAuthService {
        private static final String DESCRIPTOR = "com.xiaomi.account.IXiaomiAuthService";
        static final int TRANSACTION_getAccessTokenInResponse = 6;
        static final int TRANSACTION_getMiCloudAccessToken = 2;
        static final int TRANSACTION_getMiCloudUserInfo = 1;
        static final int TRANSACTION_getSnsAccessToken = 3;
        static final int TRANSACTION_invalidateAccessToken = 4;
        static final int TRANSACTION_supportResponseWay = 5;

        class Proxy implements IXiaomiAuthService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void getAccessTokenInResponse(IXiaomiAuthResponse iXiaomiAuthResponse, Bundle bundle, int i, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXiaomiAuthResponse != null ? iXiaomiAuthResponse.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_getAccessTokenInResponse, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Bundle getMiCloudAccessToken(Account account, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getMiCloudAccessToken, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getMiCloudUserInfo(Account account, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getMiCloudUserInfo, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSnsAccessToken(Account account, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getSnsAccessToken, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void invalidateAccessToken(Account account, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(Stub.TRANSACTION_getMiCloudUserInfo);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_invalidateAccessToken, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean supportResponseWay() {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_supportResponseWay, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXiaomiAuthService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IXiaomiAuthService)) ? new Proxy(iBinder) : (IXiaomiAuthService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            Bundle miCloudUserInfo;
            switch (i) {
                case TRANSACTION_getMiCloudUserInfo /*1*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    miCloudUserInfo = getMiCloudUserInfo(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (miCloudUserInfo != null) {
                        parcel2.writeInt(TRANSACTION_getMiCloudUserInfo);
                        miCloudUserInfo.writeToParcel(parcel2, TRANSACTION_getMiCloudUserInfo);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_getMiCloudAccessToken /*2*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    miCloudUserInfo = getMiCloudAccessToken(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (miCloudUserInfo != null) {
                        parcel2.writeInt(TRANSACTION_getMiCloudUserInfo);
                        miCloudUserInfo.writeToParcel(parcel2, TRANSACTION_getMiCloudUserInfo);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_getSnsAccessToken /*3*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    miCloudUserInfo = getSnsAccessToken(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (miCloudUserInfo != null) {
                        parcel2.writeInt(TRANSACTION_getMiCloudUserInfo);
                        miCloudUserInfo.writeToParcel(parcel2, TRANSACTION_getMiCloudUserInfo);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case TRANSACTION_invalidateAccessToken /*4*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    invalidateAccessToken(parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_supportResponseWay /*5*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean supportResponseWay = supportResponseWay();
                    parcel2.writeNoException();
                    parcel2.writeInt(supportResponseWay ? TRANSACTION_getMiCloudUserInfo : 0);
                    return true;
                case TRANSACTION_getAccessTokenInResponse /*6*/:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAccessTokenInResponse(com.xiaomi.account.IXiaomiAuthResponse.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void getAccessTokenInResponse(IXiaomiAuthResponse iXiaomiAuthResponse, Bundle bundle, int i, int i2);

    Bundle getMiCloudAccessToken(Account account, Bundle bundle);

    Bundle getMiCloudUserInfo(Account account, Bundle bundle);

    Bundle getSnsAccessToken(Account account, Bundle bundle);

    void invalidateAccessToken(Account account, Bundle bundle);

    boolean supportResponseWay();
}
