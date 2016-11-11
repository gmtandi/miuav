package com.xiaomi.account.openauth;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class AuthorizeActivity extends AuthorizeActivityBase {
    @Deprecated
    public static int RESULT_CANCEL;
    @Deprecated
    public static int RESULT_FAIL;
    @Deprecated
    public static int RESULT_SUCCESS;
    private ProgressBar mProgressBar;
    private MenuItem mRefreshItem;

    /* renamed from: com.xiaomi.account.openauth.AuthorizeActivity.1 */
    class C24461 implements OnMenuItemClickListener {
        C24461() {
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            AuthorizeActivity.this.refreshWebView();
            return true;
        }
    }

    static {
        RESULT_SUCCESS = AuthorizeActivityBase.RESULT_SUCCESS;
        RESULT_FAIL = AuthorizeActivityBase.RESULT_FAIL;
        RESULT_CANCEL = AuthorizeActivityBase.RESULT_CANCEL;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isFinishing() && !super.isMiddleActivityMode()) {
            View webView = super.getWebView();
            View relativeLayout = new RelativeLayout(this);
            relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
            relativeLayout.addView(webView, new LayoutParams(-1, -1));
            this.mProgressBar = new ProgressBar(this, null, 16842872);
            relativeLayout.addView(this.mProgressBar, new LayoutParams(-1, -2));
            setContentView(relativeLayout);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (!(isFinishing() || super.isMiddleActivityMode())) {
            this.mRefreshItem = menu.add("refresh");
            this.mRefreshItem.setIcon(17301629);
            this.mRefreshItem.setShowAsActionFlags(2);
            this.mRefreshItem.setOnMenuItemClickListener(new C24461());
            this.mRefreshItem.setVisible(false);
        }
        return true;
    }

    protected void onHideErrorUI() {
        if (this.mRefreshItem != null) {
            this.mRefreshItem.setVisible(false);
        }
    }

    protected void onHideProgress() {
        if (this.mProgressBar != null) {
            this.mProgressBar.setVisibility(8);
        }
    }

    protected void onShowErrorUI() {
        if (this.mRefreshItem != null) {
            this.mRefreshItem.setVisible(true);
        }
    }

    protected void onShowProgress() {
        if (this.mProgressBar != null) {
            this.mProgressBar.setVisibility(0);
        }
    }

    protected void onUpdateProgress(int i) {
        if (this.mProgressBar != null) {
            this.mProgressBar.setProgress(i);
        }
    }
}
