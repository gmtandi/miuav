package com.facebook.rebound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BaseSpringSystem {
    private final Set<Spring> mActiveSprings;
    private boolean mIdle;
    private final CopyOnWriteArraySet<SpringSystemListener> mListeners;
    private final SpringLooper mSpringLooper;
    private final Map<String, Spring> mSpringRegistry;

    public BaseSpringSystem(SpringLooper springLooper) {
        this.mSpringRegistry = new HashMap();
        this.mActiveSprings = new CopyOnWriteArraySet();
        this.mListeners = new CopyOnWriteArraySet();
        this.mIdle = true;
        if (springLooper == null) {
            throw new IllegalArgumentException("springLooper is required");
        }
        this.mSpringLooper = springLooper;
        this.mSpringLooper.setSpringSystem(this);
    }

    void activateSpring(String str) {
        Spring spring = (Spring) this.mSpringRegistry.get(str);
        if (spring == null) {
            throw new IllegalArgumentException("springId " + str + " does not reference a registered spring");
        }
        this.mActiveSprings.add(spring);
        if (getIsIdle()) {
            this.mIdle = false;
            this.mSpringLooper.start();
        }
    }

    public void addListener(SpringSystemListener springSystemListener) {
        if (springSystemListener == null) {
            throw new IllegalArgumentException("newListener is required");
        }
        this.mListeners.add(springSystemListener);
    }

    void advance(double d) {
        for (Spring spring : this.mActiveSprings) {
            if (spring.systemShouldAdvance()) {
                spring.advance(d / 1000.0d);
            } else {
                this.mActiveSprings.remove(spring);
            }
        }
    }

    public Spring createSpring() {
        Spring spring = new Spring(this);
        registerSpring(spring);
        return spring;
    }

    void deregisterSpring(Spring spring) {
        if (spring == null) {
            throw new IllegalArgumentException("spring is required");
        }
        this.mActiveSprings.remove(spring);
        this.mSpringRegistry.remove(spring.getId());
    }

    public List<Spring> getAllSprings() {
        List list;
        Collection values = this.mSpringRegistry.values();
        if (values instanceof List) {
            list = (List) values;
        } else {
            Object arrayList = new ArrayList(values);
        }
        return Collections.unmodifiableList(list);
    }

    public boolean getIsIdle() {
        return this.mIdle;
    }

    public Spring getSpringById(String str) {
        if (str != null) {
            return (Spring) this.mSpringRegistry.get(str);
        }
        throw new IllegalArgumentException("id is required");
    }

    public void loop(double d) {
        Iterator it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((SpringSystemListener) it.next()).onBeforeIntegrate(this);
        }
        advance(d);
        if (this.mActiveSprings.isEmpty()) {
            this.mIdle = true;
        }
        it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((SpringSystemListener) it.next()).onAfterIntegrate(this);
        }
        if (this.mIdle) {
            this.mSpringLooper.stop();
        }
    }

    void registerSpring(Spring spring) {
        if (spring == null) {
            throw new IllegalArgumentException("spring is required");
        } else if (this.mSpringRegistry.containsKey(spring.getId())) {
            throw new IllegalArgumentException("spring is already registered");
        } else {
            this.mSpringRegistry.put(spring.getId(), spring);
        }
    }

    public void removeAllListeners() {
        this.mListeners.clear();
    }

    public void removeListener(SpringSystemListener springSystemListener) {
        if (springSystemListener == null) {
            throw new IllegalArgumentException("listenerToRemove is required");
        }
        this.mListeners.remove(springSystemListener);
    }
}
