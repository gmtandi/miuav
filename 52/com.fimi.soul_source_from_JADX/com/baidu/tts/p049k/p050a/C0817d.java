package com.baidu.tts.p049k.p050a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.loopj.JsonHttpResponseHandler;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.tts.k.a.d */
public class C0817d extends JsonHttpResponseHandler {
    private TtsError f4581a;
    private ModelFileBags f4582b;

    public ModelFileBags m6804a() {
        return this.f4582b;
    }

    public void onFailure(int i, Header[] headerArr, String str, Throwable th) {
        LoggerProxy.m6515d("GetModelFileInfosHttpHandler", "onFailure1");
        this.f4581a = C0807c.m6758a().m6761a(C0802n.MODEL_REQUEST_ERROR, i, str, th);
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONObject jSONObject) {
        LoggerProxy.m6515d("GetModelFileInfosHttpHandler", "onFailure2");
        String str = null;
        if (jSONObject != null) {
            str = jSONObject.toString();
        }
        this.f4581a = C0807c.m6758a().m6761a(C0802n.MODEL_REQUEST_ERROR, i, str, th);
    }

    public void onSuccess(int i, Header[] headerArr, JSONObject jSONObject) {
        LoggerProxy.m6515d("GetModelFileInfosHttpHandler", "onSuccess response=" + jSONObject);
        int optInt = jSONObject.optInt(C0794g.ERROR_NUMBER.m6741a());
        String optString = jSONObject.optString(C0794g.ERROR_MESSAGE.m6741a());
        if (optInt == 0 || optInt == -4005) {
            JSONArray optJSONArray = jSONObject.optJSONArray(C0794g.DATA.m6742b());
            this.f4582b = new ModelFileBags();
            this.f4582b.parseJson(optJSONArray);
            return;
        }
        this.f4581a = C0807c.m6758a().m6760a(C0802n.MODEL_SERVER_ERROR, optInt, optString);
    }
}
