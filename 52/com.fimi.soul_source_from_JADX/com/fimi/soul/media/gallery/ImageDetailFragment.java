package com.fimi.soul.media.gallery;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.fimi.kernel.p084e.C1167f;
import com.fimi.kernel.p084e.aa;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.C1985y;
import com.fimi.soul.view.photodraweeview.PhotoDraweeView;
import com.tencent.open.SocialConstants;

public class ImageDetailFragment extends Fragment {
    private static boolean f7768e;
    Bitmap f7769a;
    private String f7770b;
    private PhotoDraweeView f7771c;
    private ProgressBar f7772d;

    public ImageDetailFragment() {
        this.f7769a = null;
    }

    public static ImageDetailFragment m10745a(String str, boolean z) {
        ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_URL, str);
        imageDetailFragment.setArguments(bundle);
        f7768e = z;
        return imageDetailFragment;
    }

    @SuppressLint({"NewApi"})
    private void m10747a(PhotoDraweeView photoDraweeView, String str, Bitmap bitmap) {
        if (bitmap == null) {
            photoDraweeView.setImageResource(C1205R.drawable.friends_sends_pictures_no);
        } else if (C1985y.m12536c(str, getActivity())) {
            photoDraweeView.setBackground(aa.m7991a(bitmap));
            photoDraweeView.setImageResource(C1205R.drawable.video_ic);
        } else {
            photoDraweeView.setImageBitmap(bitmap);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (f7768e) {
            this.f7770b = "file://" + this.f7770b;
        }
        this.f7771c.getLayoutParams().height = C1167f.m8115d(getActivity()).heightPixels;
        this.f7771c.getLayoutParams().width = C1167f.m8115d(getActivity()).widthPixels;
        com.fimi.soul.utils.aa.m12209a(this.f7771c, this.f7770b, new aj(this));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7770b = getArguments() != null ? getArguments().getString(SocialConstants.PARAM_URL) : null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.image_detail_fragment, viewGroup, false);
        this.f7771c = (PhotoDraweeView) inflate.findViewById(C1205R.id.image);
        this.f7771c.setOnPhotoTapListener(new ai(this));
        this.f7772d = (ProgressBar) inflate.findViewById(C1205R.id.loading);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f7769a = null;
        System.gc();
    }

    public void onStop() {
        super.onStop();
        if (!(this.f7769a == null || this.f7769a.isRecycled())) {
            this.f7769a.recycle();
        }
        System.gc();
    }
}
