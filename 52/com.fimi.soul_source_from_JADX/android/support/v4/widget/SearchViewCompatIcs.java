package android.support.v4.widget;

import android.content.Context;
import android.view.View;
import android.widget.SearchView;
import org.p122a.p123a.C2915a;

class SearchViewCompatIcs {

    public class MySearchView extends SearchView {
        public MySearchView(Context context) {
            super(context);
        }

        public void onActionViewCollapsed() {
            setQuery(C2915a.f14760f, false);
            super.onActionViewCollapsed();
        }
    }

    SearchViewCompatIcs() {
    }

    public static View newSearchView(Context context) {
        return new MySearchView(context);
    }

    public static void setImeOptions(View view, int i) {
        ((SearchView) view).setImeOptions(i);
    }

    public static void setInputType(View view, int i) {
        ((SearchView) view).setInputType(i);
    }
}
