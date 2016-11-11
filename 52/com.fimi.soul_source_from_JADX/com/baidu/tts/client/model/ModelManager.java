package com.baidu.tts.client.model;

import android.content.Context;
import com.baidu.tts.p038d.C0775b;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p049k.C0822a;
import java.util.Iterator;
import java.util.Set;

public class ModelManager {
    private C0822a f4260a;

    public ModelManager(Context context) {
        this.f4260a = new C0822a(context);
    }

    private Conditions m6554a() {
        Conditions conditions = new Conditions();
        LibEngineParams engineParams = getEngineParams();
        conditions.setVersion(engineParams.getVersion());
        conditions.setDomains(engineParams.getDomain());
        conditions.setLanguages(engineParams.getLanguage());
        conditions.setQualitys(engineParams.getQuality());
        return conditions;
    }

    private Conditions m6555a(AvailableConditions availableConditions) {
        Conditions a = m6554a();
        if (!(a == null || availableConditions == null)) {
            a.setSpeakers(availableConditions.getSpeakers());
            a.setGenders(availableConditions.getGenders());
        }
        return a;
    }

    public DownloadHandler download(String str, OnDownloadListener onDownloadListener) {
        C0775b c0775b = new C0775b();
        c0775b.m6680a(str);
        c0775b.m6679a(onDownloadListener);
        return this.f4260a.m6820a(c0775b);
    }

    public LibEngineParams getEngineParams() {
        return this.f4260a.m6821a();
    }

    public BasicHandler<ModelFileBags> getLocalModelFileInfos(Set<String> set) {
        return this.f4260a.m6826b((Set) set);
    }

    public BasicHandler<ModelBags> getLocalModels(Conditions conditions) {
        return this.f4260a.m6825b(conditions);
    }

    public BasicHandler<ModelBags> getLocalModelsAvailable(AvailableConditions availableConditions) {
        BasicHandler<ModelBags> localModels = getLocalModels(m6555a(availableConditions));
        ModelBags modelBags = (ModelBags) localModels.get();
        if (modelBags != null) {
            Iterator it = modelBags.getModelInfos().iterator();
            while (it.hasNext()) {
                if (!isModelValid(((ModelInfo) it.next()).getServerId())) {
                    it.remove();
                }
            }
        }
        return localModels;
    }

    public BasicHandler<ModelBags> getServerDefaultModels() {
        return this.f4260a.m6824b();
    }

    public BasicHandler<ModelFileBags> getServerModelFileInfos(Set<String> set) {
        return this.f4260a.m6819a((Set) set);
    }

    public BasicHandler<ModelBags> getServerModels(Conditions conditions) {
        return this.f4260a.m6818a(conditions);
    }

    public BasicHandler<ModelBags> getServerModelsAvailable(AvailableConditions availableConditions) {
        return getServerModels(m6555a(availableConditions));
    }

    public String getSpeechModelFileAbsPath(String str) {
        return this.f4260a.m6822a(C0794g.SPEECH_DATA_ID.m6742b(), str);
    }

    public String getTextModelFileAbsPath(String str) {
        return this.f4260a.m6822a(C0794g.TEXT_DATA_ID.m6742b(), str);
    }

    public boolean isModelFileValid(String str) {
        return this.f4260a.m6823a(str);
    }

    public boolean isModelValid(String str) {
        return this.f4260a.m6827b(str);
    }

    public int stop() {
        this.f4260a.m6828c();
        return 0;
    }
}
