package com.xiaomi.mipush.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.PushMessageHandler.C2563a;
import java.util.HashMap;
import java.util.Map;

public class MiPushMessage implements C2563a {
    private static final String KEY_ALIAS = "alias";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DESC = "description";
    private static final String KEY_EXTRA = "extra";
    private static final String KEY_MESSAGE_ID = "messageId";
    private static final String KEY_MESSAGE_TYPE = "messageType";
    private static final String KEY_NOTIFIED = "isNotified";
    private static final String KEY_NOTIFY_ID = "notifyId";
    private static final String KEY_NOTIFY_TYPE = "notifyType";
    private static final String KEY_PASS_THROUGH = "passThrough";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TOPIC = "topic";
    public static final int MESSAGE_TYPE_ALIAS = 1;
    public static final int MESSAGE_TYPE_REG = 0;
    public static final int MESSAGE_TYPE_TOPIC = 2;
    private static final long serialVersionUID = 1;
    private String alias;
    private String category;
    private String content;
    private String description;
    private HashMap<String, String> extra;
    private boolean isNotified;
    private String messageId;
    private int messageType;
    private int notifyId;
    private int notifyType;
    private int passThrough;
    private String title;
    private String topic;

    public MiPushMessage() {
        this.extra = new HashMap();
    }

    public static MiPushMessage fromBundle(Bundle bundle) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.messageId = bundle.getString(KEY_MESSAGE_ID);
        miPushMessage.messageType = bundle.getInt(KEY_MESSAGE_TYPE);
        miPushMessage.passThrough = bundle.getInt(KEY_PASS_THROUGH);
        miPushMessage.alias = bundle.getString(KEY_ALIAS);
        miPushMessage.topic = bundle.getString(KEY_TOPIC);
        miPushMessage.content = bundle.getString(KEY_CONTENT);
        miPushMessage.description = bundle.getString(KEY_DESC);
        miPushMessage.title = bundle.getString(KEY_TITLE);
        miPushMessage.isNotified = bundle.getBoolean(KEY_NOTIFIED);
        miPushMessage.notifyId = bundle.getInt(KEY_NOTIFY_ID);
        miPushMessage.notifyType = bundle.getInt(KEY_NOTIFY_TYPE);
        miPushMessage.category = bundle.getString(KEY_CATEGORY);
        miPushMessage.extra = (HashMap) bundle.getSerializable(KEY_EXTRA);
        return miPushMessage;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getCategory() {
        return this.category;
    }

    public String getContent() {
        return this.content;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public int getNotifyId() {
        return this.notifyId;
    }

    public int getNotifyType() {
        return this.notifyType;
    }

    public int getPassThrough() {
        return this.passThrough;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTopic() {
        return this.topic;
    }

    public boolean isNotified() {
        return this.isNotified;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setExtra(Map<String, String> map) {
        this.extra.clear();
        if (map != null) {
            this.extra.putAll(map);
        }
    }

    public void setMessageId(String str) {
        this.messageId = str;
    }

    public void setMessageType(int i) {
        this.messageType = i;
    }

    public void setNotified(boolean z) {
        this.isNotified = z;
    }

    public void setNotifyId(int i) {
        this.notifyId = i;
    }

    public void setNotifyType(int i) {
        this.notifyType = i;
    }

    public void setPassThrough(int i) {
        this.passThrough = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE_ID, this.messageId);
        bundle.putInt(KEY_PASS_THROUGH, this.passThrough);
        bundle.putInt(KEY_MESSAGE_TYPE, this.messageType);
        if (!TextUtils.isEmpty(this.alias)) {
            bundle.putString(KEY_ALIAS, this.alias);
        }
        if (!TextUtils.isEmpty(this.topic)) {
            bundle.putString(KEY_TOPIC, this.topic);
        }
        bundle.putString(KEY_CONTENT, this.content);
        if (!TextUtils.isEmpty(this.description)) {
            bundle.putString(KEY_DESC, this.description);
        }
        if (!TextUtils.isEmpty(this.title)) {
            bundle.putString(KEY_TITLE, this.title);
        }
        bundle.putBoolean(KEY_NOTIFIED, this.isNotified);
        bundle.putInt(KEY_NOTIFY_ID, this.notifyId);
        bundle.putInt(KEY_NOTIFY_TYPE, this.notifyType);
        if (!TextUtils.isEmpty(this.category)) {
            bundle.putString(KEY_CATEGORY, this.category);
        }
        if (this.extra != null) {
            bundle.putSerializable(KEY_EXTRA, this.extra);
        }
        return bundle;
    }

    public String toString() {
        return "messageId={" + this.messageId + "},passThrough={" + this.passThrough + "},alias={" + this.alias + "},topic={" + this.topic + "},content={" + this.content + "},description={" + this.description + "},title={" + this.title + "},isNotified={" + this.isNotified + "},notifyId={" + this.notifyId + "},notifyType={" + this.notifyType + "}, category={" + this.category + "}, extra={" + this.extra + "}";
    }
}
