package com.fimi.soul.module.setting.newhand;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class NewHandStartPagerFragment extends BaseNewHandFragment {
    private TextView f9442c;
    private TextView f9443d;
    private Button f9444e;

    protected View m11812a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_start, null);
    }

    protected void m11813a() {
    }

    protected void m11814a(View view) {
        this.f9442c = (TextView) view.findViewById(C1205R.id.newhand_title_tv);
        this.f9443d = (TextView) view.findViewById(C1205R.id.newhand_content_tv);
        this.f9444e = (Button) view.findViewById(C1205R.id.newhand_start_btn);
        this.f9444e.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f9442c, this.f9443d, this.f9444e);
    }

    protected String m11815b() {
        return null;
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.newhand_start_btn:
                Log.i("zhej", "current:" + ((NewHandActivity) getActivity()).m11784a().getCurrentItem());
                ((NewHandActivity) getActivity()).m11784a().setCurrentItem(((NewHandActivity) getActivity()).m11784a().getCurrentItem() + 1, true);
            default:
        }
    }
}
