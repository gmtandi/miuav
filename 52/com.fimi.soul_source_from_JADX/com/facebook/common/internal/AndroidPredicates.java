package com.facebook.common.internal;

import com.android.internal.util.Predicate;

public class AndroidPredicates {

    /* renamed from: com.facebook.common.internal.AndroidPredicates.1 */
    final class C09671 implements Predicate<T> {
        C09671() {
        }

        public boolean apply(T t) {
            return true;
        }
    }

    /* renamed from: com.facebook.common.internal.AndroidPredicates.2 */
    final class C09682 implements Predicate<T> {
        C09682() {
        }

        public boolean apply(T t) {
            return false;
        }
    }

    private AndroidPredicates() {
    }

    public static <T> Predicate<T> False() {
        return new C09682();
    }

    public static <T> Predicate<T> True() {
        return new C09671();
    }
}
