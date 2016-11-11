package com.baidu.tts.p049k.p050a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.loopj.JsonHttpResponseHandler;
import com.baidu.tts.p041e.C0794g;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.fimi.soul.media.player.IMediaPlayer;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.tts.k.a.a */
public class C0814a extends JsonHttpResponseHandler {
    private TtsError f4575a;
    private ModelBags f4576b;

    public ModelBags m6801a() {
        return this.f4576b;
    }

    public void onFailure(int i, Header[] headerArr, String str, Throwable th) {
        LoggerProxy.m6515d("GetListHttpHandler", "onFailure1");
        this.f4575a = C0807c.m6758a().m6761a(C0802n.MODEL_REQUEST_ERROR, i, str, th);
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONObject jSONObject) {
        LoggerProxy.m6515d("GetListHttpHandler", "onFailure2");
        String str = null;
        if (jSONObject != null) {
            str = jSONObject.toString();
        }
        this.f4575a = C0807c.m6758a().m6761a(C0802n.MODEL_REQUEST_ERROR, i, str, th);
    }

    public void onSuccess(int i, Header[] headerArr, JSONObject jSONObject) {
        LoggerProxy.m6515d("GetListHttpHandler", "onSuccess response=" + jSONObject);
        int optInt = jSONObject.optInt(C0794g.ERROR_NUMBER.m6741a());
        String optString = jSONObject.optString(C0794g.ERROR_MESSAGE.m6741a());
        if (optInt == 0 || optInt == IMediaPlayer.MEDIA_ERROR_IO) {
            JSONArray optJSONArray = jSONObject.optJSONArray(C0794g.DATA_LIST.m6742b());
            this.f4576b = new ModelBags();
            this.f4576b.parseJson(optJSONArray);
            return;
        }
        this.f4575a = C0807c.m6758a().m6760a(C0802n.MODEL_SERVER_ERROR, optInt, optString);
    }
}
