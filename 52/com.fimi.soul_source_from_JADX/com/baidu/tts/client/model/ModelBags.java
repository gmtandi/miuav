package com.baidu.tts.client.model;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.tools.DataTool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelBags {
    private TtsError f4239a;
    private List<ModelInfo> f4240b;

    public void addModelInfo(ModelInfo modelInfo) {
        if (this.f4240b == null) {
            this.f4240b = new ArrayList();
        }
        this.f4240b.add(modelInfo);
    }

    public List<ModelInfo> getModelInfos() {
        return this.f4240b;
    }

    public TtsError getTtsError() {
        return this.f4239a;
    }

    public boolean isEmpty() {
        return DataTool.isListEmpty(this.f4240b);
    }

    public void parseJson(JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            ModelInfo modelInfo = new ModelInfo();
            modelInfo.parseJson(optJSONObject);
            addModelInfo(modelInfo);
        }
    }

    public void setList(List<Map<String, String>> list) {
        if (list != null) {
            List arrayList = new ArrayList();
            for (Map map : list) {
                ModelInfo modelInfo = new ModelInfo();
                modelInfo.setMap(map);
                arrayList.add(modelInfo);
            }
            this.f4240b = arrayList;
        }
    }

    public void setModelInfos(List<ModelInfo> list) {
        this.f4240b = list;
    }

    public void setTtsError(TtsError ttsError) {
        this.f4239a = ttsError;
    }

    public JSONArray toJson() {
        JSONArray jSONArray = new JSONArray();
        if (!isEmpty()) {
            for (ModelInfo toJson : this.f4240b) {
                jSONArray.put(toJson.toJson());
            }
        }
        return jSONArray;
    }
}
