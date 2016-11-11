package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import javax.annotation.Nullable;

public class SimpleDraweeView extends GenericDraweeView {
    private static Supplier<? extends SimpleDraweeControllerBuilder> sDraweeControllerBuilderSupplier;
    private SimpleDraweeControllerBuilder mSimpleDraweeControllerBuilder;

    public SimpleDraweeView(Context context) {
        super(context);
        init();
    }

    public SimpleDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SimpleDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @TargetApi(21)
    public SimpleDraweeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    public SimpleDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Preconditions.checkNotNull(sDraweeControllerBuilderSupplier, "SimpleDraweeView was not initialized!");
            this.mSimpleDraweeControllerBuilder = (SimpleDraweeControllerBuilder) sDraweeControllerBuilderSupplier.get();
        }
    }

    public static void initialize(Supplier<? extends SimpleDraweeControllerBuilder> supplier) {
        sDraweeControllerBuilderSupplier = supplier;
    }

    public static void shutDown() {
        sDraweeControllerBuilderSupplier = null;
    }

    protected SimpleDraweeControllerBuilder getControllerBuilder() {
        return this.mSimpleDraweeControllerBuilder;
    }

    public void setImageURI(Uri uri) {
        setImageURI(uri, null);
    }

    public void setImageURI(Uri uri, @Nullable Object obj) {
        setController(this.mSimpleDraweeControllerBuilder.setCallerContext(obj).setUri(uri).setOldController(getController()).build());
    }
}
