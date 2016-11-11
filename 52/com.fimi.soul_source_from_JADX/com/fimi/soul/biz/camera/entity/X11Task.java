package com.fimi.soul.biz.camera.entity;

import com.fimi.soul.biz.camera.C1313t;

public abstract class X11Task {
    private C1313t context;
    private String currentKey;
    private int index;
    private String[] optionKeys;

    public X11Task(C1313t c1313t) {
        this.index = 0;
        this.context = c1313t;
    }

    protected abstract void doNext();

    protected C1313t getContext() {
        return this.context;
    }

    public String getCurrentKey() {
        return this.currentKey;
    }

    public int getIndex() {
        return this.index;
    }

    public String[] getOptionKeys() {
        return this.optionKeys;
    }

    public boolean isNext() {
        return this.optionKeys != null && this.index < this.optionKeys.length;
    }

    public boolean next() {
        if (!isNext()) {
            return false;
        }
        this.currentKey = this.optionKeys[this.index];
        this.index++;
        doNext();
        return true;
    }

    public void setCurrentKey(String str) {
        this.currentKey = str;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setOptionKeys(String[] strArr) {
        this.optionKeys = strArr;
    }
}
