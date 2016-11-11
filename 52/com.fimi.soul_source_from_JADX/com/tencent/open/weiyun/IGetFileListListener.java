package com.tencent.open.weiyun;

import com.tencent.tauth.UiError;
import java.util.List;

public interface IGetFileListListener {
    void onComplete(List<WeiyunFile> list);

    void onError(UiError uiError);
}
