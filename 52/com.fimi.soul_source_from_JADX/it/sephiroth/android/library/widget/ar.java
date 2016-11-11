package it.sephiroth.android.library.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class ar implements Filterable, WrapperListAdapter {
    static final ArrayList<ap> f14485c;
    ArrayList<ap> f14486a;
    ArrayList<ap> f14487b;
    boolean f14488d;
    private final ListAdapter f14489e;
    private final boolean f14490f;

    static {
        f14485c = new ArrayList();
    }

    public ar(ArrayList<ap> arrayList, ArrayList<ap> arrayList2, ListAdapter listAdapter) {
        this.f14489e = listAdapter;
        this.f14490f = listAdapter instanceof Filterable;
        if (arrayList == null) {
            this.f14486a = f14485c;
        } else {
            this.f14486a = arrayList;
        }
        if (arrayList2 == null) {
            this.f14487b = f14485c;
        } else {
            this.f14487b = arrayList2;
        }
        boolean z = m16307a(this.f14486a) && m16307a(this.f14487b);
        this.f14488d = z;
    }

    private boolean m16307a(ArrayList<ap> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!((ap) it.next()).f14481c) {
                    return false;
                }
            }
        }
        return true;
    }

    public int m16308a() {
        return this.f14486a.size();
    }

    public boolean m16309a(View view) {
        boolean z = false;
        for (int i = 0; i < this.f14486a.size(); i++) {
            if (((ap) this.f14486a.get(i)).f14479a == view) {
                this.f14486a.remove(i);
                if (m16307a(this.f14486a) && m16307a(this.f14487b)) {
                    z = true;
                }
                this.f14488d = z;
                return true;
            }
        }
        return false;
    }

    public boolean areAllItemsEnabled() {
        return this.f14489e != null ? this.f14488d && this.f14489e.areAllItemsEnabled() : true;
    }

    public int m16310b() {
        return this.f14487b.size();
    }

    public boolean m16311b(View view) {
        boolean z = false;
        for (int i = 0; i < this.f14487b.size(); i++) {
            if (((ap) this.f14487b.get(i)).f14479a == view) {
                this.f14487b.remove(i);
                if (m16307a(this.f14486a) && m16307a(this.f14487b)) {
                    z = true;
                }
                this.f14488d = z;
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return this.f14489e != null ? (m16310b() + m16308a()) + this.f14489e.getCount() : m16310b() + m16308a();
    }

    public Filter getFilter() {
        return this.f14490f ? ((Filterable) this.f14489e).getFilter() : null;
    }

    public Object getItem(int i) {
        int a = m16308a();
        if (i < a) {
            return ((ap) this.f14486a.get(i)).f14480b;
        }
        int i2 = i - a;
        a = 0;
        if (this.f14489e != null) {
            a = this.f14489e.getCount();
            if (i2 < a) {
                return this.f14489e.getItem(i2);
            }
        }
        return ((ap) this.f14487b.get(i2 - a)).f14480b;
    }

    public long getItemId(int i) {
        int a = m16308a();
        if (this.f14489e != null && i >= a) {
            a = i - a;
            if (a < this.f14489e.getCount()) {
                return this.f14489e.getItemId(a);
            }
        }
        return -1;
    }

    public int getItemViewType(int i) {
        int a = m16308a();
        if (this.f14489e != null && i >= a) {
            a = i - a;
            if (a < this.f14489e.getCount()) {
                return this.f14489e.getItemViewType(a);
            }
        }
        return -2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int a = m16308a();
        if (i < a) {
            return ((ap) this.f14486a.get(i)).f14479a;
        }
        int i2 = i - a;
        a = 0;
        if (this.f14489e != null) {
            a = this.f14489e.getCount();
            if (i2 < a) {
                return this.f14489e.getView(i2, view, viewGroup);
            }
        }
        return ((ap) this.f14487b.get(i2 - a)).f14479a;
    }

    public int getViewTypeCount() {
        return this.f14489e != null ? this.f14489e.getViewTypeCount() : 1;
    }

    public ListAdapter getWrappedAdapter() {
        return this.f14489e;
    }

    public boolean hasStableIds() {
        return this.f14489e != null ? this.f14489e.hasStableIds() : false;
    }

    public boolean isEmpty() {
        return this.f14489e == null || this.f14489e.isEmpty();
    }

    public boolean isEnabled(int i) {
        int a = m16308a();
        if (i < a) {
            return ((ap) this.f14486a.get(i)).f14481c;
        }
        int i2 = i - a;
        a = 0;
        if (this.f14489e != null) {
            a = this.f14489e.getCount();
            if (i2 < a) {
                return this.f14489e.isEnabled(i2);
            }
        }
        return ((ap) this.f14487b.get(i2 - a)).f14481c;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.f14489e != null) {
            this.f14489e.registerDataSetObserver(dataSetObserver);
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.f14489e != null) {
            this.f14489e.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
