package com.xiaomi.account;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.xiaomi.account.IXiaomiAuthResponse.Stub;
import com.xiaomi.auth.AuthConstants;

public class XiaomiOAuthResponse implements Parcelable {
    public static final Creator<XiaomiOAuthResponse> CREATOR;
    private static final String TAG;
    private IXiaomiAuthResponse mResponse;

    /* renamed from: com.xiaomi.account.XiaomiOAuthResponse.1 */
    final class C24451 implements Creator<XiaomiOAuthResponse> {
        C24451() {
        }

        public XiaomiOAuthResponse createFromParcel(Parcel parcel) {
            return new XiaomiOAuthResponse(parcel);
        }

        public XiaomiOAuthResponse[] newArray(int i) {
            return new XiaomiOAuthResponse[i];
        }
    }

    static {
        TAG = XiaomiOAuthResponse.class.getName();
        CREATOR = new C24451();
    }

    public XiaomiOAuthResponse(Parcel parcel) {
        this.mResponse = Stub.asInterface(parcel.readStrongBinder());
    }

    public XiaomiOAuthResponse(IXiaomiAuthResponse iXiaomiAuthResponse) {
        this.mResponse = iXiaomiAuthResponse;
    }

    public static void setIXiaomiAuthResponseCancel(IXiaomiAuthResponse iXiaomiAuthResponse) {
        if (iXiaomiAuthResponse != null) {
            try {
                iXiaomiAuthResponse.onCancel();
            } catch (Throwable e) {
                Log.e(TAG, "RuntimeException", e);
            } catch (Throwable e2) {
                Log.e(TAG, "RemoteException", e2);
            }
        }
    }

    public static void setIXiaomiAuthResponseResult(IXiaomiAuthResponse iXiaomiAuthResponse, Bundle bundle) {
        if (iXiaomiAuthResponse != null && bundle != null) {
            try {
                iXiaomiAuthResponse.onResult(bundle);
            } catch (Throwable e) {
                Log.e(TAG, "RemoteException", e);
                Bundle bundle2 = new Bundle();
                bundle2.putInt(AuthConstants.EXTRA_ERROR_CODE, -1);
                bundle2.putString(AuthConstants.EXTRA_ERROR_DESCRIPTION, e.getMessage());
                try {
                    iXiaomiAuthResponse.onResult(bundle2);
                } catch (Throwable e2) {
                    Log.e(TAG, "RuntimeException", e2);
                } catch (Throwable e22) {
                    Log.e(TAG, "RemoteException", e22);
                }
            } catch (Throwable e222) {
                Log.e(TAG, "RemoteException", e222);
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void onCancel() {
        setIXiaomiAuthResponseCancel(this.mResponse);
    }

    public void onError(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt(AuthConstants.EXTRA_ERROR_CODE, i);
        bundle.putString(AuthConstants.EXTRA_ERROR_DESCRIPTION, str);
        setIXiaomiAuthResponseResult(this.mResponse, bundle);
    }

    public void onResult(Bundle bundle) {
        setIXiaomiAuthResponseResult(this.mResponse, bundle);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.mResponse.asBinder());
    }
}
