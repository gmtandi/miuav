package com.fimi.soul.module.droneFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p090b.C1247f;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.C1564g;
import com.fimi.soul.drone.p117h.C1565h;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.aq;
import com.fimi.soul.view.photodraweeview.C2020f;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.BufferRecycler;

public class ShowDroneControlFragment extends Fragment implements OnClickListener, C1234i {
    private ImageView f7986A;
    private ImageView f7987B;
    private aq f7988C;
    private PercentRelativeLayout f7989a;
    private PercentRelativeLayout f7990b;
    private PercentRelativeLayout f7991c;
    private PercentRelativeLayout f7992d;
    private PercentRelativeLayout f7993e;
    private PercentRelativeLayout f7994f;
    private PercentRelativeLayout f7995g;
    private ImageButton f7996h;
    private Context f7997i;
    private DroidPlannerApp f7998j;
    private C1433a f7999k;
    private C1664h f8000l;
    private TextView f8001m;
    private TextView f8002n;
    private TextView f8003o;
    private TextView f8004p;
    private TextView f8005q;
    private TextView f8006r;
    private TextView f8007s;
    private int f8008t;
    private int f8009u;
    private ImageView f8010v;
    private ImageView f8011w;
    private ImageView f8012x;
    private ImageView f8013y;
    private ImageView f8014z;

    public ShowDroneControlFragment() {
        this.f8009u = 20;
    }

    private void m10910a(int i, int i2) {
        if (!this.f7999k.m9569P().m9995a() || !this.f7999k.m9570Q()) {
            return;
        }
        if (this.f7999k.aa()) {
            m10912a(0.3f, false, this.f8013y, this.f8001m);
            m10912a(C2020f.f10933c, false, this.f7989a);
            if (this.f7999k.ah().m10172g().isLightStream() || this.f7999k.ah().m10172g().isEnforcementAtti()) {
                if (this.f7999k.ah().m10172g().isEnforcementAtti()) {
                    m10912a(0.3f, false, this.f8014z, this.f8002n);
                    m10912a(C2020f.f10933c, false, this.f7990b);
                } else {
                    m10912a(C2020f.f10933c, true, this.f7990b, this.f8014z, this.f8002n);
                }
                m10912a(0.3f, false, this.f8011w, this.f8005q, this.f8012x, this.f8006r, this.f8010v, this.f8004p, this.f7986A, this.f8003o, this.f8007s, this.f7987B);
                m10912a(C2020f.f10933c, false, this.f7994f, this.f7992d, this.f7993e, this.f7994f, this.f7991c, this.f7995g);
                return;
            }
            m10912a(C2020f.f10933c, true, this.f7986A, this.f8003o, this.f7991c, this.f7990b, this.f8014z, this.f8002n);
            if (!this.f7999k.ah().m10172g().judgeNoAction() || this.f7999k.aj().m10314b()) {
                m10912a(0.3f, false, this.f8011w, this.f8012x, this.f8005q, this.f8006r, this.f8004p, this.f8010v, this.f8007s, this.f7987B);
                m10912a(C2020f.f10933c, false, this.f7992d, this.f7993e, this.f7994f, this.f7995g);
                return;
            }
            m10912a(C2020f.f10933c, true, this.f7992d, this.f8010v, this.f8004p, this.f8011w, this.f7993e, this.f8005q, this.f8006r, this.f8012x, this.f7994f, this.f7995g, this.f8007s, this.f7987B);
            return;
        }
        if (this.f7999k.ah().m10172g().isEnforcementAtti()) {
            m10912a(C2020f.f10933c, false, this.f7989a);
            m10912a(0.3f, false, this.f8013y, this.f8001m);
        } else {
            m10912a(C2020f.f10933c, true, this.f7989a, this.f8013y, this.f8001m);
        }
        m10912a(0.3f, false, this.f8014z, this.f7986A, this.f8003o, this.f8002n, this.f8011w, this.f8012x, this.f8005q, this.f8006r, this.f8010v, this.f8004p, this.f8007s, this.f7987B);
        m10912a(C2020f.f10933c, false, this.f7990b, this.f7991c, this.f7993e, this.f7994f, this.f7992d, this.f7995g);
    }

    public void m10911a() {
        m10912a(C2020f.f10933c, false, this.f7989a, this.f7990b, this.f7991c, this.f7992d, this.f7993e, this.f7994f, this.f7995g);
        m10912a(0.3f, false, this.f8014z, this.f8013y, this.f7986A, this.f8001m, this.f8003o, this.f8002n, this.f8007s);
        m10912a(0.3f, false, this.f8010v, this.f8004p, this.f8011w, this.f8012x, this.f8005q, this.f8006r, this.f7987B);
    }

    public void m10912a(float f, boolean z, View... viewArr) {
        for (int i = 0; i < viewArr.length; i++) {
            View view = viewArr[i];
            if (view.getAlpha() != f) {
                view.setAlpha(f);
            }
            if (view.isEnabled() != z) {
                viewArr[i].setEnabled(z);
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f7997i = activity.getApplicationContext();
        this.f7998j = (DroidPlannerApp) activity.getApplication();
        this.f7999k = this.f7998j.f5570a;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.closebutton:
                this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
            case C1205R.id.takeoffrl:
                C1253k.m8598a(getActivity().getApplicationContext()).m8600a(0);
                if (this.f7999k.aa()) {
                    ak.m8083a(getActivity(), (int) C1205R.string.wrongtakeoff, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                } else {
                    if (this.f8009u == 2) {
                        this.f7988C.m12748a(getString(C1205R.string.takeoffpromptlightstream));
                    } else if (this.f8009u == 1) {
                        this.f7988C.m12748a(getString(C1205R.string.takeoffpromptgps));
                    } else {
                        ak.m8083a(getActivity(), (int) C1205R.string.wrongtakeoff, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                        return;
                    }
                    this.f7988C.m12753b(getString(C1205R.string.ensure), new ae(this)).m12749a(getString(C1205R.string.cancel), new ad(this)).m12746a().show();
                }
                this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
            case C1205R.id.landingpl:
                C1253k.m8598a(getActivity().getApplicationContext()).m8600a(0);
                if (this.f7999k.aa()) {
                    this.f7988C.m12748a(getString(C1205R.string.landprompt));
                    this.f7988C.m12753b(getString(C1205R.string.ensure), new ag(this)).m12749a(getString(C1205R.string.cancel), new af(this)).m12746a().show();
                } else {
                    ak.m8083a(getActivity(), (int) C1205R.string.wrongLanding, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                }
                this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
            case C1205R.id.gohomepl:
                C1253k.m8598a(getActivity().getApplicationContext()).m8600a(0);
                boolean isLightStream = this.f7999k.ah().m10172g().isLightStream();
                double f = (this.f7999k.m9617t().m10362f() - 677216.0d) / 10.0d;
                if (this.f7999k.aa() && !isLightStream) {
                    this.f7988C.m12748a(getString(C1205R.string.gohomeprompt3));
                    this.f7988C.m12753b(getString(C1205R.string.ensure), new ai(this)).m12749a(getString(C1205R.string.cancel), new ah(this)).m12746a().show();
                } else if (isLightStream) {
                    ak.m8083a(getActivity(), (int) C1205R.string.lightstreamgohome, 3000);
                } else {
                    ak.m8083a(getActivity(), (int) C1205R.string.wrongbackhome, (int) BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN);
                }
                this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
            case C1205R.id.detoruingpointpl:
                if (this.f7999k.ah().m10172g().isGps()) {
                    C1247f.m8565k().m8573a(false);
                    C1253k.m8598a(getActivity().getApplicationContext()).m8600a(3);
                    this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
                    return;
                }
                ak.m8082a(getActivity(), (int) C1205R.string.cant_action_interest);
            case C1205R.id.takephonemyselfpl:
                if (!this.f7999k.ah().m10172g().isGps()) {
                    ak.m8082a(getActivity(), (int) C1205R.string.cant_action_tajephoto);
                } else if (this.f7999k.ab().m10225u()) {
                    ak.m8083a(getActivity(), (int) C1205R.string.discongc, 3000);
                } else if (this.f7999k.ab().m10224t()) {
                    ak.m8083a(getActivity(), (int) C1205R.string.gcunready, 3000);
                } else if (C1325k.m8930a().m8943h()) {
                    try {
                        C1564g c1564g = (C1564g) this.f7999k.f6506b;
                        if (c1564g.m10548f() == C1565h.NoSDCard) {
                            ak.m8083a(getActivity(), (int) C1205R.string.tf_remove_error, 3000);
                        } else if (c1564g.m10548f() == C1565h.FullSDCard) {
                            ak.m8083a(getActivity(), (int) C1205R.string.tf_full_error, 3000);
                        } else if (c1564g.m10548f() == C1565h.Error) {
                            ak.m8083a(getActivity(), (int) C1205R.string.tf_io_error, 3000);
                        } else {
                            if (c1564g.m10548f() == C1565h.LOW_SPEED_CARD) {
                                ak.m8083a(getActivity(), (int) C1205R.string.tf_low_speed_error, 3000);
                                return;
                            }
                            C1253k.m8598a(getActivity().getApplicationContext()).m8600a(4);
                            this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
                        }
                    } catch (Exception e) {
                    }
                } else {
                    ak.m8083a(getActivity(), (int) C1205R.string.no_connect_camera, 3000);
                }
            case C1205R.id.setwaypointpl:
                if (this.f7999k.ah().m10172g().isGps()) {
                    C1247f.m8565k().m8573a(false);
                    C1253k.m8598a(getActivity().getApplicationContext()).m8600a(1);
                    this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
                    return;
                }
                ak.m8082a(getActivity(), (int) C1205R.string.cant_action_waypoint);
            case C1205R.id.setdestination:
                if (this.f7999k.ah().m10172g().isGps()) {
                    C1247f.m8565k().m8573a(false);
                    C1253k.m8598a(getActivity().getApplicationContext()).m8600a(2);
                    this.f7999k.m9589a(C1584h.CLOSEDRAWCONTROL);
                    return;
                }
                ak.m8082a(getActivity(), (int) C1205R.string.cant_action_flyto);
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7988C = new aq(getActivity());
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.dronecontrol, null);
        this.f7995g = (PercentRelativeLayout) inflate.findViewById(C1205R.id.takephonemyselfpl);
        this.f8007s = (TextView) inflate.findViewById(C1205R.id.takephonetext);
        this.f7987B = (ImageView) inflate.findViewById(C1205R.id.takephoneicon);
        this.f7996h = (ImageButton) inflate.findViewById(C1205R.id.closebutton);
        this.f7989a = (PercentRelativeLayout) inflate.findViewById(C1205R.id.takeoffrl);
        this.f7990b = (PercentRelativeLayout) inflate.findViewById(C1205R.id.landingpl);
        this.f7991c = (PercentRelativeLayout) inflate.findViewById(C1205R.id.gohomepl);
        this.f7992d = (PercentRelativeLayout) inflate.findViewById(C1205R.id.detoruingpointpl);
        this.f7993e = (PercentRelativeLayout) inflate.findViewById(C1205R.id.setwaypointpl);
        this.f7994f = (PercentRelativeLayout) inflate.findViewById(C1205R.id.setdestination);
        for (View onClickListener : new View[]{this.f7995g, this.f7989a, this.f7990b, this.f7991c, this.f7992d, this.f7993e, this.f7994f, this.f7996h}) {
            onClickListener.setOnClickListener(this);
        }
        this.f8000l = C1664h.m10813a(this.f7999k);
        this.f8001m = (TextView) inflate.findViewById(C1205R.id.takeofftext);
        this.f8003o = (TextView) inflate.findViewById(C1205R.id.gohometext);
        this.f8002n = (TextView) inflate.findViewById(C1205R.id.landingtext);
        this.f8004p = (TextView) inflate.findViewById(C1205R.id.controlfourtext);
        this.f8005q = (TextView) inflate.findViewById(C1205R.id.waypointtext);
        this.f8006r = (TextView) inflate.findViewById(C1205R.id.targetplacetext);
        be.m12359a(getActivity().getAssets(), this.f8001m, this.f8003o, this.f8004p, this.f8005q, this.f8006r, this.f8007s);
        this.f8013y = (ImageView) inflate.findViewById(C1205R.id.takeofficon);
        this.f8014z = (ImageView) inflate.findViewById(C1205R.id.landingicon);
        this.f7986A = (ImageView) inflate.findViewById(C1205R.id.gohomeicon);
        this.f8010v = (ImageView) inflate.findViewById(C1205R.id.detoruingpointicon);
        this.f8011w = (ImageView) inflate.findViewById(C1205R.id.setwaypointicon);
        this.f8012x = (ImageView) inflate.findViewById(C1205R.id.setdestinationicon);
        m10911a();
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f7999k.m9594b((C1234i) this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f7999k.m9594b((C1234i) this);
        if (this.f7988C != null) {
            this.f7988C = null;
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (aj.f8178a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f8008t = c1433a.ah().m10170e();
                this.f8009u = c1433a.ah().m10169d();
                m10910a(this.f8008t, this.f8009u);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                m10911a();
            case Type.BYTE /*3*/:
                if (!c1433a.m9570Q()) {
                    m10911a();
                }
            default:
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        this.f7999k.m9590a((C1234i) this);
    }
}
