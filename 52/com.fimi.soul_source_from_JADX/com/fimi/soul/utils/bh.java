package com.fimi.soul.utils;

final class bh implements Runnable {
    bh() {
    }

    public void run() {
        if (!be.f10088e.isPlaying()) {
            be.f10088e.start();
        }
    }
}
