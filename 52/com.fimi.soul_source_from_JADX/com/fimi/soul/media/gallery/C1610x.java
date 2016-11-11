package com.fimi.soul.media.gallery;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.PhotoDraweeView;

/* renamed from: com.fimi.soul.media.gallery.x */
public class C1610x extends AlertDialog implements OnClickListener {
    private Bitmap f7832a;
    private ProgressBar f7833b;

    public C1610x(Context context, Bitmap bitmap, int i) {
        super(context, i);
        this.f7832a = bitmap;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.gallery:
                dismiss();
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.gallery_dialog);
        PhotoDraweeView photoDraweeView = (PhotoDraweeView) findViewById(C1205R.id.gallery);
        photoDraweeView.setOnPhotoTapListener(new C1611y(this));
        this.f7833b = (ProgressBar) findViewById(C1205R.id.progress);
        photoDraweeView.setOnClickListener(this);
        if (this.f7832a != null) {
            photoDraweeView.setImageBitmap(this.f7832a);
            this.f7833b.setVisibility(4);
            photoDraweeView.setVisibility(0);
        }
    }
}
