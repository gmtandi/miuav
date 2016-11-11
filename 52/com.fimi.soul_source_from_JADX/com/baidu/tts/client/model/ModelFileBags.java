package com.baidu.tts.client.model;

import android.content.Context;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.tools.DataTool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ModelFileBags {
    private TtsError f4241a;
    private List<ModelFileInfo> f4242b;

    public void addFileInfo(ModelFileInfo modelFileInfo) {
        if (this.f4242b == null) {
            this.f4242b = new ArrayList();
        }
        this.f4242b.add(modelFileInfo);
    }

    public void generateAbsPath(Context context) {
        if (this.f4242b != null) {
            for (ModelFileInfo generateAbsPath : this.f4242b) {
                generateAbsPath.generateAbsPath(context);
            }
        }
    }

    public ModelFileInfo getModelFileInfo(int i) {
        return this.f4242b != null ? (ModelFileInfo) this.f4242b.get(i) : null;
    }

    public List<ModelFileInfo> getModelFileInfos() {
        return this.f4242b;
    }

    public TtsError getTtsError() {
        return this.f4241a;
    }

    public String getUrl(int i) {
        ModelFileInfo modelFileInfo = getModelFileInfo(i);
        return modelFileInfo != null ? modelFileInfo.getUrl() : null;
    }

    public boolean isEmpty() {
        return DataTool.isListEmpty(this.f4242b);
    }

    public void parseJson(JSONArray jSONArray) {
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            ModelFileInfo modelFileInfo = new ModelFileInfo();
            modelFileInfo.parseJson(optJSONObject);
            addFileInfo(modelFileInfo);
        }
    }

    public void setList(List<Map<String, String>> list) {
        if (list != null && !list.isEmpty()) {
            List arrayList = new ArrayList();
            for (Map map : list) {
                ModelFileInfo modelFileInfo = new ModelFileInfo();
                modelFileInfo.setMap(map);
                arrayList.add(modelFileInfo);
            }
            this.f4242b = arrayList;
        }
    }

    public void setModelFileInfos(List<ModelFileInfo> list) {
        this.f4242b = list;
    }

    public void setTtsError(TtsError ttsError) {
        this.f4241a = ttsError;
    }

    public JSONArray toJson() {
        JSONArray jSONArray = new JSONArray();
        if (!isEmpty()) {
            for (ModelFileInfo toJson : this.f4242b) {
                jSONArray.put(toJson.toJson());
            }
        }
        return jSONArray;
    }
}
