package com.fimi.soul.entity;

import java.io.Serializable;

public class BroadcastMode implements Serializable {
    private static final long serialVersionUID = 1;
    private String broadcastID;
    private String content;
    private String createtime;
    private String discuss_num;
    private String fieldNames;
    private String imgUrl;
    private String nickName;
    private String objectName;
    private String praise_num;
    private String userCommentNum;
    private String userID;
    private String userImageUrl;
    private String userPraiseNum;

    public String getBroadcastID() {
        return this.broadcastID;
    }

    public String getContent() {
        return this.content;
    }

    public String getCreatetime() {
        return this.createtime;
    }

    public String getDiscuss_num() {
        return this.discuss_num;
    }

    public String getFieldNames() {
        return this.fieldNames;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public String getPraise_num() {
        return this.praise_num;
    }

    public String getUserCommentNum() {
        return this.userCommentNum;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserImageUrl() {
        return this.userImageUrl;
    }

    public String getUserPraiseNum() {
        return this.userPraiseNum;
    }

    public void setBroadcastID(String str) {
        this.broadcastID = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setCreatetime(String str) {
        this.createtime = str;
    }

    public void setDiscuss_num(String str) {
        this.discuss_num = str;
    }

    public void setFieldNames(String str) {
        this.fieldNames = str;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public void setPraise_num(String str) {
        this.praise_num = str;
    }

    public void setUserCommentNum(String str) {
        this.userCommentNum = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setUserImageUrl(String str) {
        this.userImageUrl = str;
    }

    public void setUserPraiseNum(String str) {
        this.userPraiseNum = str;
    }
}
