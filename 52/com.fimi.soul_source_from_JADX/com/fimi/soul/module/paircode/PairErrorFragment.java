package com.fimi.soul.module.paircode;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class PairErrorFragment extends Fragment implements OnClickListener {
    PairCodeActivity f8875a;
    Button f8876b;
    View f8877c;
    private TextView f8878d;
    private TextView f8879e;

    private void m11543a(View view) {
        this.f8878d = (TextView) view.findViewById(C1205R.id.connect_dis_title);
        this.f8879e = (TextView) view.findViewById(C1205R.id.connect_dis_tip);
        this.f8876b = (Button) view.findViewById(C1205R.id.dis_con_submit);
        be.m12359a(this.f8875a.getAssets(), this.f8879e, this.f8878d, this.f8876b);
        this.f8876b.setOnClickListener(this);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8875a = (PairCodeActivity) activity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.dis_con_submit:
                this.f8875a.finish();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f8877c = layoutInflater.inflate(C1205R.layout.pair_disconnect_layout, null);
        m11543a(this.f8877c);
        return this.f8877c;
    }
}
