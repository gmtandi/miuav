package com.fimi.soul.entity;

public class BroadcastComment {
    private String broadcastCommentID;
    private String broadcastID;
    private String comment_num;
    private String content;
    private String createTime;
    private String name;
    private String nickName;
    private String userCommentID;
    private String userCommentName;
    private String userID;
    private String userImgUrl;

    public String getBroadcastCommentID() {
        return this.broadcastCommentID;
    }

    public String getBroadcastID() {
        return this.broadcastID;
    }

    public String getComment_num() {
        return this.comment_num;
    }

    public String getContent() {
        return this.content;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public String getName() {
        return this.name;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getUserCommentID() {
        return this.userCommentID;
    }

    public String getUserCommentName() {
        return this.userCommentName;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserImgUrl() {
        return this.userImgUrl;
    }

    public void setBroadcastCommentID(String str) {
        this.broadcastCommentID = str;
    }

    public void setBroadcastID(String str) {
        this.broadcastID = str;
    }

    public void setComment_num(String str) {
        this.comment_num = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public void setUserCommentID(String str) {
        this.userCommentID = str;
    }

    public void setUserCommentName(String str) {
        this.userCommentName = str;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public void setUserImgUrl(String str) {
        this.userImgUrl = str;
    }
}
