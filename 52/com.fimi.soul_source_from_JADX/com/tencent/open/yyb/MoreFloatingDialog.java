package com.tencent.open.yyb;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.open.p133a.C2333f;
import java.util.HashMap;

public class MoreFloatingDialog extends Dialog {
    private static final float NINE_PATCH_TOP = 9.0f;
    private static final float SHARE_ICON_SIZE = 28.0f;
    private static final String SOURCE_FLOATING_BG = "yyb_appdetail_bg_floatingwindow.9.png";
    private static final String SOURCE_FRIENDS_BG = "yyb_friends.png";
    private static final String SOURCE_QQ_BG = "yyb_qq.png";
    private static final String SOURCE_QZONE_BG = "yyb_qzone.png";
    private static final String SOURCE_WEIXIN_BG = "yyb_weixin.png";
    private float density;
    private RelativeLayout mContentView;
    private LinearLayout mRootView;
    private HashMap<String, TextView> mShareItems;
    private Rect ninePatchRect;

    public MoreFloatingDialog(Context context) {
        super(context, 16973840);
        this.mShareItems = new HashMap(4);
        this.ninePatchRect = new Rect(0, dip2px(NINE_PATCH_TOP), 0, 0);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.density = displayMetrics.density;
        C2333f.m13754b(C2333f.f12014d, "-->(MoreFloatingDialog) : density = " + this.density);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.mContentView = new RelativeLayout(context);
        this.mContentView.setLayoutParams(layoutParams);
        this.mContentView.setBackgroundDrawable(C2391a.m13880a(SOURCE_FLOATING_BG, context, this.ninePatchRect));
        this.mRootView = new LinearLayout(context);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.mRootView.setLayoutParams(layoutParams);
        this.mShareItems.put(SOURCE_QQ_BG, addShareItemText(C2391a.m13880a(SOURCE_QQ_BG, getContext(), this.ninePatchRect), "QQ\u5206\u4eab"));
        this.mShareItems.put(SOURCE_QZONE_BG, addShareItemText(C2391a.m13880a(SOURCE_QZONE_BG, getContext(), this.ninePatchRect), "\u7a7a\u95f4\u5206\u4eab"));
        this.mContentView.addView(this.mRootView, layoutParams);
        setContentView(this.mContentView);
    }

    private void addHorizontalDivider() {
        View imageView = new ImageView(getContext());
        imageView.setBackgroundColor(Color.parseColor("#33ffffff"));
        this.mRootView.addView(imageView, new LinearLayout.LayoutParams(dip2px(C2020f.f10933c), -1));
    }

    private TextView addShareItemText(Drawable drawable, String str) {
        if (drawable != null) {
            drawable.setBounds(0, 0, dip2px(SHARE_ICON_SIZE), dip2px(SHARE_ICON_SIZE));
        }
        View textView = new TextView(getContext());
        textView.setTextColor(-1);
        textView.setCompoundDrawables(null, drawable, null, null);
        textView.setTextSize(13.0f);
        textView.setCompoundDrawablePadding(dip2px(8.0f));
        textView.setPadding(0, dip2px(19.0f), 0, dip2px(19.0f));
        textView.setGravity(1);
        textView.setText(str);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = C2020f.f10933c;
        layoutParams.gravity = 1;
        this.mRootView.addView(textView, layoutParams);
        addHorizontalDivider();
        return textView;
    }

    public int dip2px(float f) {
        return (int) ((this.density * f) + 0.5f);
    }

    public int getContentViewHeight() {
        return this.mContentView.getHeight();
    }

    public int getHeight() {
        return this.mContentView.getHeight();
    }

    public View getQQItem() {
        return (View) this.mShareItems.get(SOURCE_QQ_BG);
    }

    public View getQzoneItem() {
        return (View) this.mShareItems.get(SOURCE_QZONE_BG);
    }

    public View getTimelineItem() {
        return (View) this.mShareItems.get(SOURCE_FRIENDS_BG);
    }

    public View getWXItem() {
        return (View) this.mShareItems.get(SOURCE_WEIXIN_BG);
    }
}
