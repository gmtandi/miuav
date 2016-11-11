package com.baidu.tts.p049k.p050a;

import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.database.C0782a;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* renamed from: com.baidu.tts.k.a.b */
public class C0815b implements Callable<ModelFileBags> {
    private Set<String> f4577a;
    private C0782a f4578b;

    public C0815b(C0782a c0782a, Set<String> set) {
        this.f4577a = set;
        this.f4578b = c0782a;
    }

    public ModelFileBags m6802a() {
        List a = this.f4578b.m6721a(this.f4577a);
        ModelFileBags modelFileBags = new ModelFileBags();
        modelFileBags.setList(a);
        return modelFileBags;
    }

    public /* synthetic */ Object call() {
        return m6802a();
    }
}
