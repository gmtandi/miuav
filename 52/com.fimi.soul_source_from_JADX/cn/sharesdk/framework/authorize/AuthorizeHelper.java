package cn.sharesdk.framework.authorize;

import cn.sharesdk.framework.Platform;

public interface AuthorizeHelper {
    AuthorizeListener getAuthorizeListener();

    String getAuthorizeUrl();

    C0155b getAuthorizeWebviewClient(C0161g c0161g);

    Platform getPlatform();

    String getRedirectUri();

    SSOListener getSSOListener();

    C0159f getSSOProcessor(C0158e c0158e);
}
