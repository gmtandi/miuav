package com.baidu.tts.p049k.p050a;

import com.baidu.tts.client.model.BasicHandler;
import com.baidu.tts.client.model.Conditions;
import com.baidu.tts.client.model.LibEngineParams;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.jni.EmbeddedSynthesizerEngine;
import com.baidu.tts.p049k.C0822a;
import java.util.Set;
import java.util.concurrent.FutureTask;

/* renamed from: com.baidu.tts.k.a.h */
public class C0821h {
    private C0822a f4588a;

    public C0821h(C0822a c0822a) {
        this.f4588a = c0822a;
    }

    public BasicHandler<ModelBags> m6811a(Conditions conditions) {
        BasicHandler<ModelBags> basicHandler = new BasicHandler(new FutureTask(new C0820g(conditions)));
        basicHandler.start();
        return basicHandler;
    }

    public BasicHandler<ModelFileBags> m6812a(Set<String> set) {
        BasicHandler<ModelFileBags> basicHandler = new BasicHandler(new FutureTask(new C0819f(set)));
        basicHandler.start();
        return basicHandler;
    }

    public LibEngineParams m6813a() {
        return new LibEngineParams(EmbeddedSynthesizerEngine.bdTTSGetEngineParam());
    }

    public BasicHandler<ModelBags> m6814b() {
        BasicHandler<ModelBags> basicHandler = new BasicHandler(new FutureTask(new C0818e()));
        basicHandler.start();
        return basicHandler;
    }

    public BasicHandler<ModelBags> m6815b(Conditions conditions) {
        BasicHandler<ModelBags> basicHandler = new BasicHandler(new FutureTask(new C0816c(this.f4588a.m6830e(), conditions)));
        basicHandler.start();
        return basicHandler;
    }

    public BasicHandler<ModelFileBags> m6816b(Set<String> set) {
        BasicHandler<ModelFileBags> basicHandler = new BasicHandler(new FutureTask(new C0815b(this.f4588a.m6830e(), set)));
        basicHandler.start();
        return basicHandler;
    }
}
