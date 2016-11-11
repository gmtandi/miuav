package com.facebook.rebound;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpringChain implements SpringListener {
    private static final int DEFAULT_ATTACHMENT_FRICTION = 10;
    private static final int DEFAULT_ATTACHMENT_TENSION = 70;
    private static final int DEFAULT_MAIN_FRICTION = 6;
    private static final int DEFAULT_MAIN_TENSION = 40;
    private static int id;
    private static final SpringConfigRegistry registry;
    private final SpringConfig mAttachmentSpringConfig;
    private int mControlSpringIndex;
    private final CopyOnWriteArrayList<SpringListener> mListeners;
    private final SpringConfig mMainSpringConfig;
    private final SpringSystem mSpringSystem;
    private final CopyOnWriteArrayList<Spring> mSprings;

    static {
        registry = SpringConfigRegistry.getInstance();
        id = 0;
    }

    private SpringChain() {
        this(DEFAULT_MAIN_TENSION, DEFAULT_MAIN_FRICTION, DEFAULT_ATTACHMENT_TENSION, DEFAULT_ATTACHMENT_FRICTION);
    }

    private SpringChain(int i, int i2, int i3, int i4) {
        this.mSpringSystem = SpringSystem.create();
        this.mListeners = new CopyOnWriteArrayList();
        this.mSprings = new CopyOnWriteArrayList();
        this.mControlSpringIndex = -1;
        this.mMainSpringConfig = SpringConfig.fromOrigamiTensionAndFriction((double) i, (double) i2);
        this.mAttachmentSpringConfig = SpringConfig.fromOrigamiTensionAndFriction((double) i3, (double) i4);
        SpringConfigRegistry springConfigRegistry = registry;
        SpringConfig springConfig = this.mMainSpringConfig;
        StringBuilder append = new StringBuilder().append("main spring ");
        int i5 = id;
        id = i5 + 1;
        springConfigRegistry.addSpringConfig(springConfig, append.append(i5).toString());
        springConfigRegistry = registry;
        springConfig = this.mAttachmentSpringConfig;
        append = new StringBuilder().append("attachment spring ");
        i5 = id;
        id = i5 + 1;
        springConfigRegistry.addSpringConfig(springConfig, append.append(i5).toString());
    }

    public static SpringChain create() {
        return new SpringChain();
    }

    public static SpringChain create(int i, int i2, int i3, int i4) {
        return new SpringChain(i, i2, i3, i4);
    }

    public SpringChain addSpring(SpringListener springListener) {
        this.mSprings.add(this.mSpringSystem.createSpring().addListener(this).setSpringConfig(this.mAttachmentSpringConfig));
        this.mListeners.add(springListener);
        return this;
    }

    public List<Spring> getAllSprings() {
        return this.mSprings;
    }

    public SpringConfig getAttachmentSpringConfig() {
        return this.mAttachmentSpringConfig;
    }

    public Spring getControlSpring() {
        return (Spring) this.mSprings.get(this.mControlSpringIndex);
    }

    public SpringConfig getMainSpringConfig() {
        return this.mMainSpringConfig;
    }

    public void onSpringActivate(Spring spring) {
        ((SpringListener) this.mListeners.get(this.mSprings.indexOf(spring))).onSpringActivate(spring);
    }

    public void onSpringAtRest(Spring spring) {
        ((SpringListener) this.mListeners.get(this.mSprings.indexOf(spring))).onSpringAtRest(spring);
    }

    public void onSpringEndStateChange(Spring spring) {
        ((SpringListener) this.mListeners.get(this.mSprings.indexOf(spring))).onSpringEndStateChange(spring);
    }

    public void onSpringUpdate(Spring spring) {
        int i;
        int indexOf = this.mSprings.indexOf(spring);
        SpringListener springListener = (SpringListener) this.mListeners.get(indexOf);
        if (indexOf == this.mControlSpringIndex) {
            int i2 = indexOf - 1;
            i = indexOf + 1;
            indexOf = i2;
        } else if (indexOf < this.mControlSpringIndex) {
            indexOf--;
            i = -1;
        } else if (indexOf > this.mControlSpringIndex) {
            i = indexOf + 1;
            indexOf = -1;
        } else {
            indexOf = -1;
            i = -1;
        }
        if (i > -1 && i < this.mSprings.size()) {
            ((Spring) this.mSprings.get(i)).setEndValue(spring.getCurrentValue());
        }
        if (indexOf > -1 && indexOf < this.mSprings.size()) {
            ((Spring) this.mSprings.get(indexOf)).setEndValue(spring.getCurrentValue());
        }
        springListener.onSpringUpdate(spring);
    }

    public SpringChain setControlSpringIndex(int i) {
        this.mControlSpringIndex = i;
        if (((Spring) this.mSprings.get(this.mControlSpringIndex)) == null) {
            return null;
        }
        for (Spring springConfig : this.mSpringSystem.getAllSprings()) {
            springConfig.setSpringConfig(this.mAttachmentSpringConfig);
        }
        getControlSpring().setSpringConfig(this.mMainSpringConfig);
        return this;
    }
}
