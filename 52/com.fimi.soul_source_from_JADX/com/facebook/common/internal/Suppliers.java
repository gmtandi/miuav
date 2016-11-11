package com.facebook.common.internal;

public class Suppliers {

    /* renamed from: com.facebook.common.internal.Suppliers.1 */
    final class C09711 implements Supplier<T> {
        final /* synthetic */ Object val$instance;

        C09711(Object obj) {
            this.val$instance = obj;
        }

        public T get() {
            return this.val$instance;
        }
    }

    public static <T> Supplier<T> of(T t) {
        return new C09711(t);
    }
}
