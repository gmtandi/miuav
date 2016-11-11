package com.fimi.soul.module.calibcompass;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p103k.C1394s;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.entity.CheckCampssBeann;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.be;
import java.util.List;

public class CaliCompassFourFragment extends BaseCaliCompassFragment {
    private TextView f7912f;
    private TextView f7913g;
    private TextView f7914h;
    private Button f7915i;
    private boolean f7916j;
    private Handler f7917k;
    private Runnable f7918l;

    public CaliCompassFourFragment() {
        this.f7917k = new Handler();
        this.f7918l = new C1680n(this);
    }

    private void m10877a(View view) {
        this.f7912f = (TextView) view.findViewById(C1205R.id.title);
        this.f7913g = (TextView) view.findViewById(C1205R.id.descompass);
        this.f7914h = (TextView) view.findViewById(C1205R.id.descompasscom);
        this.f7915i = (Button) view.findViewById(C1205R.id.endcalicompass);
        this.f7915i.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f7912f, this.f7913g, this.f7914h, this.f7915i);
    }

    public void m10879a() {
        C1664h.m10813a(this.b).m10816a((byte) 1, (byte) 1, (byte) 4);
        getActivity().finish();
    }

    public void m10880a(boolean z) {
        this.f7916j = z;
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == C1205R.id.endcalicompass) {
            this.f7917k.postDelayed(this.f7918l, 300);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.cali_compass_four, null);
        m10877a(inflate);
        return inflate;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && isVisible()) {
            Object obj;
            List listLatlng;
            CheckCampssBeann checkCampssBeann = (CheckCampssBeann) C1157c.m7938a().m7939a(C1543c.f7225Z, CheckCampssBeann.class);
            if (checkCampssBeann != null) {
                obj = checkCampssBeann;
                listLatlng = checkCampssBeann.getListLatlng();
            } else {
                obj = new CheckCampssBeann();
                listLatlng = obj.getListLatlng();
            }
            Location d = C1394s.m9340a(getActivity()).m9352d();
            if (d != null) {
                listLatlng.add(new LatLng(d.getLatitude(), d.getLongitude()));
                obj.setListLatlng(listLatlng);
                C1157c.m7938a().m7943a(C1543c.f7225Z, obj);
            }
        }
    }

    public void onResume() {
        super.onResume();
    }
}
