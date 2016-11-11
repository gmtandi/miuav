package com.mob.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.mob.tools.utils.ReflectHelper;
import java.util.HashMap;
import org.codehaus.jackson.smile.SmileConstants;

public class MobUIShell extends Activity {
    private static HashMap<String, FakeActivity> executors;
    public static int forceTheme;
    private FakeActivity executor;

    static {
        executors = new HashMap();
        MobLog.getInstance().m737d("===============================", new Object[0]);
        MobLog.getInstance().m737d("MobTools " + "2016-04-26".replace("-0", "-").replace("-", "."), new Object[0]);
        MobLog.getInstance().m737d("===============================", new Object[0]);
    }

    protected static String registerExecutor(Object obj) {
        return registerExecutor(String.valueOf(System.currentTimeMillis()), obj);
    }

    protected static String registerExecutor(String str, Object obj) {
        executors.put(str, (FakeActivity) obj);
        return str;
    }

    public void finish() {
        if (this.executor == null || !this.executor.onFinish()) {
            super.finish();
        }
    }

    public FakeActivity getDefault() {
        try {
            String string = getPackageManager().getActivityInfo(getComponentName(), SmileConstants.TOKEN_PREFIX_TINY_UNICODE).metaData.getString("defaultActivity");
            if (!TextUtils.isEmpty(string)) {
                if (string.startsWith(".")) {
                    string = getPackageName() + string;
                }
                Object importClass = ReflectHelper.importClass(string);
                if (!TextUtils.isEmpty(importClass)) {
                    importClass = ReflectHelper.newInstance(importClass, new Object[0]);
                    if (importClass != null && (importClass instanceof FakeActivity)) {
                        return (FakeActivity) importClass;
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m750w(th);
        }
        return null;
    }

    public Object getExecutor() {
        return this.executor;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.executor != null) {
            this.executor.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.executor != null) {
            this.executor.onConfigurationChanged(configuration);
        }
    }

    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("launch_time");
        String stringExtra2 = intent.getStringExtra("executor_name");
        this.executor = (FakeActivity) executors.remove(stringExtra);
        if (this.executor == null) {
            this.executor = (FakeActivity) executors.remove(intent.getScheme());
            if (this.executor == null) {
                this.executor = getDefault();
                if (this.executor == null) {
                    MobLog.getInstance().m750w(new RuntimeException("Executor lost! launchTime = " + stringExtra + ", executorName: " + stringExtra2));
                    super.onCreate(bundle);
                    finish();
                    return;
                }
            }
        }
        MobLog.getInstance().m743i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
        this.executor.setActivity(this);
        super.onCreate(bundle);
        MobLog.getInstance().m737d(this.executor.getClass().getSimpleName() + " onCreate", new Object[0]);
        this.executor.onCreate();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return this.executor != null ? this.executor.onCreateOptionsMenu(menu) : super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        if (this.executor != null) {
            this.executor.sendResult();
            MobLog.getInstance().m737d(this.executor.getClass().getSimpleName() + " onDestroy", new Object[0]);
            this.executor.onDestroy();
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.executor != null) {
            z = this.executor.onKeyEvent(i, keyEvent);
        }
        return z ? true : super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.executor != null) {
            z = this.executor.onKeyEvent(i, keyEvent);
        }
        return z ? true : super.onKeyUp(i, keyEvent);
    }

    protected void onNewIntent(Intent intent) {
        if (this.executor == null) {
            super.onNewIntent(intent);
        } else {
            this.executor.onNewIntent(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.executor != null ? this.executor.onOptionsItemSelected(menuItem) : super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        if (this.executor != null) {
            MobLog.getInstance().m737d(this.executor.getClass().getSimpleName() + " onPause", new Object[0]);
            this.executor.onPause();
        }
        super.onPause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.executor != null) {
            this.executor.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    protected void onRestart() {
        if (this.executor != null) {
            MobLog.getInstance().m737d(this.executor.getClass().getSimpleName() + " onRestart", new Object[0]);
            this.executor.onRestart();
        }
        super.onRestart();
    }

    protected void onResume() {
        if (this.executor != null) {
            MobLog.getInstance().m737d(this.executor.getClass().getSimpleName() + " onResume", new Object[0]);
            this.executor.onResume();
        }
        super.onResume();
    }

    protected void onStart() {
        if (this.executor != null) {
            MobLog.getInstance().m737d(this.executor.getClass().getSimpleName() + " onStart", new Object[0]);
            this.executor.onStart();
        }
        super.onStart();
    }

    protected void onStop() {
        if (this.executor != null) {
            MobLog.getInstance().m737d(this.executor.getClass().getSimpleName() + " onStop", new Object[0]);
            this.executor.onStop();
        }
        super.onStop();
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(this).inflate(i, null));
    }

    public void setContentView(View view) {
        if (view != null) {
            super.setContentView(view);
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        if (view != null) {
            if (layoutParams == null) {
                super.setContentView(view);
            } else {
                super.setContentView(view, layoutParams);
            }
            if (this.executor != null) {
                this.executor.setContentView(view);
            }
        }
    }

    public void setTheme(int i) {
        if (forceTheme > 0) {
            super.setTheme(forceTheme);
        } else {
            super.setTheme(i);
        }
    }
}
