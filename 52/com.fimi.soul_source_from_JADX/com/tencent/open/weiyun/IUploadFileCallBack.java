package com.tencent.open.weiyun;

import com.tencent.tauth.UiError;

public interface IUploadFileCallBack {
    void onError(UiError uiError);

    void onPrepareStart();

    void onUploadProgress(int i);

    void onUploadStart();

    void onUploadSuccess();
}
