package com.facebook.imagepipeline.nativecode;

import com.facebook.common.soloader.SoLoaderShim;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImagePipelineNativeLoader {
    public static final List<String> DEPENDENCIES;
    public static final String DSO_NAME = "imagepipeline";

    static {
        List arrayList = new ArrayList();
        arrayList.add("webp");
        DEPENDENCIES = Collections.unmodifiableList(arrayList);
    }

    public static void load() {
        for (int i = 0; i < DEPENDENCIES.size(); i++) {
            SoLoaderShim.loadLibrary((String) DEPENDENCIES.get(i));
        }
        SoLoaderShim.loadLibrary(DSO_NAME);
    }
}
