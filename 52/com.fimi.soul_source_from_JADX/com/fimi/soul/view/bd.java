package com.fimi.soul.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import java.util.List;

public class bd extends Drawable {
    private final List<Drawable> f10723a;

    public bd(List<Drawable> list) {
        this.f10723a = list;
    }

    public void draw(Canvas canvas) {
        if (this.f10723a.size() == 1) {
            ((Drawable) this.f10723a.get(0)).draw(canvas);
            return;
        }
        int width = getBounds().width();
        int height = getBounds().height();
        canvas.save();
        canvas.clipRect(0, 0, width, height);
        if (this.f10723a.size() == 2 || this.f10723a.size() == 3) {
            canvas.save();
            canvas.clipRect(0, 0, width / 2, height);
            canvas.translate((float) ((-width) / 4), 0.0f);
            ((Drawable) this.f10723a.get(0)).draw(canvas);
            canvas.restore();
        }
        if (this.f10723a.size() == 2) {
            canvas.save();
            canvas.clipRect(width / 2, 0, width, height);
            canvas.translate((float) (width / 4), 0.0f);
            ((Drawable) this.f10723a.get(1)).draw(canvas);
            canvas.restore();
        } else {
            canvas.save();
            canvas.scale(0.5f, 0.5f);
            canvas.translate((float) width, 0.0f);
            ((Drawable) this.f10723a.get(1)).draw(canvas);
            canvas.translate(0.0f, (float) height);
            ((Drawable) this.f10723a.get(2)).draw(canvas);
            canvas.restore();
        }
        if (this.f10723a.size() >= 4) {
            canvas.save();
            canvas.scale(0.5f, 0.5f);
            ((Drawable) this.f10723a.get(0)).draw(canvas);
            canvas.translate(0.0f, (float) height);
            ((Drawable) this.f10723a.get(3)).draw(canvas);
            canvas.restore();
        }
        canvas.restore();
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
