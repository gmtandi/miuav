package com.fimi.soul.biz.update;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fimi.soul.C1205R;

public class MyDialogFragment extends DialogFragment {
    private String f6307a;

    public void m9374a(String str) {
        this.f6307a = str;
    }

    public boolean isCancelable() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return new Builder(getActivity()).setMessage(this.f6307a).setPositiveButton(C1205R.string.finish, new ag(this)).create();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().requestWindowFeature(1);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void setShowsDialog(boolean z) {
        super.setShowsDialog(z);
    }
}
