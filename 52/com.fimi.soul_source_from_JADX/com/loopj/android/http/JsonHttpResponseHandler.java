package com.loopj.android.http;

import android.os.Message;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.p122a.p123a.C2915a;

public class JsonHttpResponseHandler extends AsyncHttpResponseHandler {
    protected static final int SUCCESS_JSON_MESSAGE = 100;

    protected void handleFailureMessage(Throwable th, String str) {
        if (str != null) {
            try {
                Object parseResponse = parseResponse(str);
                if (parseResponse instanceof JSONObject) {
                    onFailure(th, (JSONObject) parseResponse);
                    return;
                } else if (parseResponse instanceof JSONArray) {
                    onFailure(th, (JSONArray) parseResponse);
                    return;
                } else {
                    onFailure(th, str);
                    return;
                }
            } catch (JSONException e) {
                onFailure(th, str);
                return;
            }
        }
        onFailure(th, C2915a.f14760f);
    }

    protected void handleMessage(Message message) {
        switch (message.what) {
            case SUCCESS_JSON_MESSAGE /*100*/:
                Object[] objArr = (Object[]) message.obj;
                handleSuccessJsonMessage(((Integer) objArr[0]).intValue(), objArr[1]);
            default:
                super.handleMessage(message);
        }
    }

    protected void handleSuccessJsonMessage(int i, Object obj) {
        if (obj instanceof JSONObject) {
            onSuccess(i, (JSONObject) obj);
        } else if (obj instanceof JSONArray) {
            onSuccess(i, (JSONArray) obj);
        } else {
            onFailure(new JSONException("Unexpected type " + obj.getClass().getName()), (JSONObject) null);
        }
    }

    public void onFailure(Throwable th, JSONArray jSONArray) {
    }

    public void onFailure(Throwable th, JSONObject jSONObject) {
    }

    public void onSuccess(int i, JSONArray jSONArray) {
        onSuccess(jSONArray);
    }

    public void onSuccess(int i, JSONObject jSONObject) {
        onSuccess(jSONObject);
    }

    public void onSuccess(JSONArray jSONArray) {
    }

    public void onSuccess(JSONObject jSONObject) {
    }

    protected Object parseResponse(String str) {
        Object obj = null;
        String trim = str.trim();
        if (trim.startsWith("{") || trim.startsWith("[")) {
            obj = new JSONTokener(trim).nextValue();
        }
        return obj == null ? trim : obj;
    }

    protected void sendSuccessMessage(int i, String str) {
        if (i != C1458u.f6934b) {
            try {
                Object parseResponse = parseResponse(str);
                sendMessage(obtainMessage(SUCCESS_JSON_MESSAGE, new Object[]{Integer.valueOf(i), parseResponse}));
                return;
            } catch (Throwable e) {
                sendFailureMessage(e, str);
                return;
            }
        }
        sendMessage(obtainMessage(SUCCESS_JSON_MESSAGE, new Object[]{Integer.valueOf(i), new JSONObject()}));
    }
}
