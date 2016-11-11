package com.baidu.tts.p049k.p050a;

import com.baidu.tts.client.model.Conditions;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.database.C0782a;
import java.util.List;
import java.util.concurrent.Callable;

/* renamed from: com.baidu.tts.k.a.c */
public class C0816c implements Callable<ModelBags> {
    private Conditions f4579a;
    private C0782a f4580b;

    public C0816c(C0782a c0782a, Conditions conditions) {
        this.f4579a = conditions;
        this.f4580b = c0782a;
    }

    public ModelBags m6803a() {
        List a = this.f4580b.m6720a(this.f4579a);
        ModelBags modelBags = new ModelBags();
        modelBags.setList(a);
        return modelBags;
    }

    public /* synthetic */ Object call() {
        return m6803a();
    }
}
