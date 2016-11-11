package com.facebook.rebound.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.WeightedLatLng;
import com.facebook.rebound.OrigamiValueConverter;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringConfigRegistry;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.yyb.TitleBar;
import it.p074a.p075a.C2799f;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class SpringConfiguratorView extends FrameLayout {
    private static final DecimalFormat DECIMAL_FORMAT;
    private static final float MAX_FRICTION = 50.0f;
    private static final int MAX_SEEKBAR_VAL = 100000;
    private static final float MAX_TENSION = 200.0f;
    private static final float MIN_FRICTION = 0.0f;
    private static final float MIN_TENSION = 0.0f;
    private TextView mFrictionLabel;
    private SeekBar mFrictionSeekBar;
    private final float mRevealPx;
    private final Spring mRevealerSpring;
    private SpringConfig mSelectedSpringConfig;
    private final List<SpringConfig> mSpringConfigs;
    private Spinner mSpringSelectorSpinner;
    private final float mStashPx;
    private TextView mTensionLabel;
    private SeekBar mTensionSeekBar;
    private final int mTextColor;
    private final SpinnerAdapter spinnerAdapter;
    private final SpringConfigRegistry springConfigRegistry;

    class OnNubTouchListener implements OnTouchListener {
        private OnNubTouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                SpringConfiguratorView.this.togglePosition();
            }
            return true;
        }
    }

    class RevealerSpringListener implements SpringListener {
        private RevealerSpringListener() {
        }

        public void onSpringActivate(Spring spring) {
        }

        public void onSpringAtRest(Spring spring) {
        }

        public void onSpringEndStateChange(Spring spring) {
        }

        public void onSpringUpdate(Spring spring) {
            float currentValue = (float) spring.getCurrentValue();
            float access$1300 = SpringConfiguratorView.this.mRevealPx;
            SpringConfiguratorView.this.setTranslationY((currentValue * (SpringConfiguratorView.this.mStashPx - access$1300)) + access$1300);
        }
    }

    class SeekbarListener implements OnSeekBarChangeListener {
        private SeekbarListener() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (seekBar == SpringConfiguratorView.this.mTensionSeekBar) {
                float f = ((SpringConfiguratorView.MAX_TENSION * ((float) i)) / 100000.0f) + 0.0f;
                SpringConfiguratorView.this.mSelectedSpringConfig.tension = OrigamiValueConverter.tensionFromOrigamiValue((double) f);
                SpringConfiguratorView.this.mTensionLabel.setText("T:" + SpringConfiguratorView.DECIMAL_FORMAT.format((double) f));
            }
            if (seekBar == SpringConfiguratorView.this.mFrictionSeekBar) {
                f = ((((float) i) * SpringConfiguratorView.MAX_FRICTION) / 100000.0f) + 0.0f;
                SpringConfiguratorView.this.mSelectedSpringConfig.friction = OrigamiValueConverter.frictionFromOrigamiValue((double) f);
                SpringConfiguratorView.this.mFrictionLabel.setText("F:" + SpringConfiguratorView.DECIMAL_FORMAT.format((double) f));
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class SpinnerAdapter extends BaseAdapter {
        private final Context mContext;
        private final List<String> mStrings;

        public SpinnerAdapter(Context context) {
            this.mContext = context;
            this.mStrings = new ArrayList();
        }

        public void add(String str) {
            this.mStrings.add(str);
            notifyDataSetChanged();
        }

        public void clear() {
            this.mStrings.clear();
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.mStrings.size();
        }

        public Object getItem(int i) {
            return this.mStrings.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = new TextView(this.mContext);
                view.setLayoutParams(new LayoutParams(-1, -1));
                int dpToPx = Util.dpToPx(12.0f, SpringConfiguratorView.this.getResources());
                view.setPadding(dpToPx, dpToPx, dpToPx, dpToPx);
                view.setTextColor(SpringConfiguratorView.this.mTextColor);
            } else {
                TextView textView = (TextView) view;
            }
            view.setText((CharSequence) this.mStrings.get(i));
            return view;
        }
    }

    class SpringSelectedListener implements OnItemSelectedListener {
        private SpringSelectedListener() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SpringConfiguratorView.this.mSelectedSpringConfig = (SpringConfig) SpringConfiguratorView.this.mSpringConfigs.get(i);
            SpringConfiguratorView.this.updateSeekBarsForSpringConfig(SpringConfiguratorView.this.mSelectedSpringConfig);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    static {
        DECIMAL_FORMAT = new DecimalFormat("#.#");
    }

    public SpringConfiguratorView(Context context) {
        this(context, null);
    }

    public SpringConfiguratorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @TargetApi(11)
    public SpringConfiguratorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSpringConfigs = new ArrayList();
        this.mTextColor = Color.argb(Util.MASK_8BIT, C2799f.f14253C, C2799f.f14253C, C2799f.f14253C);
        SpringSystem create = SpringSystem.create();
        this.springConfigRegistry = SpringConfigRegistry.getInstance();
        this.spinnerAdapter = new SpinnerAdapter(context);
        Resources resources = getResources();
        this.mRevealPx = (float) Util.dpToPx(40.0f, resources);
        this.mStashPx = (float) Util.dpToPx(280.0f, resources);
        this.mRevealerSpring = create.createSpring();
        this.mRevealerSpring.setCurrentValue(WeightedLatLng.DEFAULT_INTENSITY).setEndValue(WeightedLatLng.DEFAULT_INTENSITY).addListener(new RevealerSpringListener());
        addView(generateHierarchy(context));
        OnSeekBarChangeListener seekbarListener = new SeekbarListener();
        this.mTensionSeekBar.setMax(MAX_SEEKBAR_VAL);
        this.mTensionSeekBar.setOnSeekBarChangeListener(seekbarListener);
        this.mFrictionSeekBar.setMax(MAX_SEEKBAR_VAL);
        this.mFrictionSeekBar.setOnSeekBarChangeListener(seekbarListener);
        this.mSpringSelectorSpinner.setAdapter(this.spinnerAdapter);
        this.mSpringSelectorSpinner.setOnItemSelectedListener(new SpringSelectedListener());
        refreshSpringConfigurations();
        setTranslationY(this.mStashPx);
    }

    private View generateHierarchy(Context context) {
        Resources resources = getResources();
        int dpToPx = Util.dpToPx(5.0f, resources);
        int dpToPx2 = Util.dpToPx(TitleBar.SHAREBTN_RIGHT_MARGIN, resources);
        int dpToPx3 = Util.dpToPx(TitleBar.BACKBTN_LEFT_MARGIN, resources);
        ViewGroup.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -2, C2020f.f10933c);
        layoutParams.setMargins(0, 0, dpToPx, 0);
        View frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(Util.createLayoutParams(-1, Util.dpToPx(BitmapDescriptorFactory.HUE_MAGENTA, resources)));
        View frameLayout2 = new FrameLayout(context);
        ViewGroup.LayoutParams createMatchParams = Util.createMatchParams();
        createMatchParams.setMargins(0, dpToPx3, 0, 0);
        frameLayout2.setLayoutParams(createMatchParams);
        frameLayout2.setBackgroundColor(Color.argb(100, 0, 0, 0));
        frameLayout.addView(frameLayout2);
        this.mSpringSelectorSpinner = new Spinner(context, 0);
        createMatchParams = Util.createMatchWrapParams();
        createMatchParams.gravity = 48;
        createMatchParams.setMargins(dpToPx2, dpToPx2, dpToPx2, 0);
        this.mSpringSelectorSpinner.setLayoutParams(createMatchParams);
        frameLayout2.addView(this.mSpringSelectorSpinner);
        View linearLayout = new LinearLayout(context);
        ViewGroup.LayoutParams createMatchWrapParams = Util.createMatchWrapParams();
        createMatchWrapParams.setMargins(0, 0, 0, Util.dpToPx(80.0f, resources));
        createMatchWrapParams.gravity = 80;
        linearLayout.setLayoutParams(createMatchWrapParams);
        linearLayout.setOrientation(1);
        frameLayout2.addView(linearLayout);
        frameLayout2 = new LinearLayout(context);
        createMatchWrapParams = Util.createMatchWrapParams();
        createMatchWrapParams.setMargins(dpToPx2, dpToPx2, dpToPx2, dpToPx3);
        frameLayout2.setPadding(dpToPx2, dpToPx2, dpToPx2, dpToPx2);
        frameLayout2.setLayoutParams(createMatchWrapParams);
        frameLayout2.setOrientation(0);
        linearLayout.addView(frameLayout2);
        this.mTensionSeekBar = new SeekBar(context);
        this.mTensionSeekBar.setLayoutParams(layoutParams);
        frameLayout2.addView(this.mTensionSeekBar);
        this.mTensionLabel = new TextView(getContext());
        this.mTensionLabel.setTextColor(this.mTextColor);
        createMatchWrapParams = Util.createLayoutParams(Util.dpToPx(MAX_FRICTION, resources), -1);
        this.mTensionLabel.setGravity(19);
        this.mTensionLabel.setLayoutParams(createMatchWrapParams);
        this.mTensionLabel.setMaxLines(1);
        frameLayout2.addView(this.mTensionLabel);
        frameLayout2 = new LinearLayout(context);
        createMatchWrapParams = Util.createMatchWrapParams();
        createMatchWrapParams.setMargins(dpToPx2, dpToPx2, dpToPx2, dpToPx3);
        frameLayout2.setPadding(dpToPx2, dpToPx2, dpToPx2, dpToPx2);
        frameLayout2.setLayoutParams(createMatchWrapParams);
        frameLayout2.setOrientation(0);
        linearLayout.addView(frameLayout2);
        this.mFrictionSeekBar = new SeekBar(context);
        this.mFrictionSeekBar.setLayoutParams(layoutParams);
        frameLayout2.addView(this.mFrictionSeekBar);
        this.mFrictionLabel = new TextView(getContext());
        this.mFrictionLabel.setTextColor(this.mTextColor);
        ViewGroup.LayoutParams createLayoutParams = Util.createLayoutParams(Util.dpToPx(MAX_FRICTION, resources), -1);
        this.mFrictionLabel.setGravity(19);
        this.mFrictionLabel.setLayoutParams(createLayoutParams);
        this.mFrictionLabel.setMaxLines(1);
        frameLayout2.addView(this.mFrictionLabel);
        View view = new View(context);
        ViewGroup.LayoutParams createLayoutParams2 = Util.createLayoutParams(Util.dpToPx(BitmapDescriptorFactory.HUE_YELLOW, resources), Util.dpToPx(40.0f, resources));
        createLayoutParams2.gravity = 49;
        view.setLayoutParams(createLayoutParams2);
        view.setOnTouchListener(new OnNubTouchListener());
        view.setBackgroundColor(Color.argb(Util.MASK_8BIT, 0, Opcodes.IF_ICMPLE, 209));
        frameLayout.addView(view);
        return frameLayout;
    }

    private void togglePosition() {
        double d = WeightedLatLng.DEFAULT_INTENSITY;
        double endValue = this.mRevealerSpring.getEndValue();
        Spring spring = this.mRevealerSpring;
        if (endValue == WeightedLatLng.DEFAULT_INTENSITY) {
            d = 0.0d;
        }
        spring.setEndValue(d);
    }

    private void updateSeekBarsForSpringConfig(SpringConfig springConfig) {
        int round = Math.round(((((float) OrigamiValueConverter.origamiValueFromTension(springConfig.tension)) - 0.0f) * 100000.0f) / MAX_TENSION);
        int round2 = Math.round(((((float) OrigamiValueConverter.origamiValueFromFriction(springConfig.friction)) - 0.0f) * 100000.0f) / MAX_FRICTION);
        this.mTensionSeekBar.setProgress(round);
        this.mFrictionSeekBar.setProgress(round2);
    }

    public void destroy() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
        this.mRevealerSpring.destroy();
    }

    public void refreshSpringConfigurations() {
        Map allSpringConfig = this.springConfigRegistry.getAllSpringConfig();
        this.spinnerAdapter.clear();
        this.mSpringConfigs.clear();
        for (Entry entry : allSpringConfig.entrySet()) {
            if (entry.getKey() != SpringConfig.defaultConfig) {
                this.mSpringConfigs.add(entry.getKey());
                this.spinnerAdapter.add((String) entry.getValue());
            }
        }
        this.mSpringConfigs.add(SpringConfig.defaultConfig);
        this.spinnerAdapter.add((String) allSpringConfig.get(SpringConfig.defaultConfig));
        this.spinnerAdapter.notifyDataSetChanged();
        if (this.mSpringConfigs.size() > 0) {
            this.mSpringSelectorSpinner.setSelection(0);
        }
    }
}
