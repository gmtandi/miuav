package it.sephiroth.android.library.widget;

import android.database.DataSetObserver;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.HeterogeneousExpandableList;
import java.util.ArrayList;
import java.util.Collections;

class ExpandableHListConnector extends BaseAdapter implements Filterable {
    private ExpandableListAdapter f14398a;
    private ArrayList<GroupMetadata> f14399b;
    private int f14400c;
    private int f14401d;
    private final DataSetObserver f14402e;

    class GroupMetadata implements Parcelable, Comparable<GroupMetadata> {
        public static final Creator<GroupMetadata> CREATOR;
        static final int f14393a = -1;
        int f14394b;
        int f14395c;
        int f14396d;
        long f14397e;

        static {
            CREATOR = new ac();
        }

        private GroupMetadata() {
        }

        static GroupMetadata m16168a(int i, int i2, int i3, long j) {
            GroupMetadata groupMetadata = new GroupMetadata();
            groupMetadata.f14394b = i;
            groupMetadata.f14395c = i2;
            groupMetadata.f14396d = i3;
            groupMetadata.f14397e = j;
            return groupMetadata;
        }

        public int m16169a(GroupMetadata groupMetadata) {
            if (groupMetadata != null) {
                return this.f14396d - groupMetadata.f14396d;
            }
            throw new IllegalArgumentException();
        }

        public /* synthetic */ int compareTo(Object obj) {
            return m16169a((GroupMetadata) obj);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f14394b);
            parcel.writeInt(this.f14395c);
            parcel.writeInt(this.f14396d);
            parcel.writeLong(this.f14397e);
        }
    }

    public ExpandableHListConnector(ExpandableListAdapter expandableListAdapter) {
        this.f14401d = Integer.MAX_VALUE;
        this.f14402e = new ad(this);
        this.f14399b = new ArrayList();
        m16176a(expandableListAdapter);
    }

    private void m16171a(boolean z, boolean z2) {
        int i;
        int i2;
        int a;
        int i3 = 0;
        Object obj = this.f14399b;
        int size = obj.size();
        this.f14400c = 0;
        if (z2) {
            i = size - 1;
            i2 = 0;
            while (i >= 0) {
                GroupMetadata groupMetadata;
                int i4;
                groupMetadata = (GroupMetadata) obj.get(i);
                a = m16172a(groupMetadata.f14397e, groupMetadata.f14396d);
                if (a != groupMetadata.f14396d) {
                    if (a == -1) {
                        obj.remove(i);
                        size--;
                    }
                    groupMetadata.f14396d = a;
                    if (i2 == 0) {
                        i4 = 1;
                        i--;
                        i2 = i4;
                    }
                }
                i4 = i2;
                i--;
                i2 = i4;
            }
            if (i2 != 0) {
                Collections.sort(obj);
            }
        }
        i = 0;
        a = 0;
        while (i3 < size) {
            groupMetadata = (GroupMetadata) obj.get(i3);
            i2 = (groupMetadata.f14395c == -1 || z) ? this.f14398a.getChildrenCount(groupMetadata.f14396d) : groupMetadata.f14395c - groupMetadata.f14394b;
            this.f14400c += i2;
            a += groupMetadata.f14396d - i;
            i = groupMetadata.f14396d;
            groupMetadata.f14394b = a;
            i2 += a;
            groupMetadata.f14395c = i2;
            i3++;
            a = i2;
        }
    }

    int m16172a(long j, int i) {
        int groupCount = this.f14398a.getGroupCount();
        if (groupCount == 0) {
            return -1;
        }
        if (j == Long.MIN_VALUE) {
            return -1;
        }
        int min = Math.min(groupCount - 1, Math.max(0, i));
        long uptimeMillis = SystemClock.uptimeMillis() + 100;
        Object obj = null;
        ExpandableListAdapter a = m16173a();
        if (a == null) {
            return -1;
        }
        int i2 = min;
        int i3 = min;
        while (SystemClock.uptimeMillis() <= uptimeMillis) {
            if (a.getGroupId(i3) != j) {
                Object obj2 = min == groupCount + -1 ? 1 : null;
                Object obj3 = i2 == 0 ? 1 : null;
                if (obj2 != null && obj3 != null) {
                    break;
                } else if (obj3 != null || (r0 != null && obj2 == null)) {
                    min++;
                    obj = null;
                    i3 = min;
                } else if (obj2 != null || (r0 == null && obj3 == null)) {
                    i2--;
                    obj = 1;
                    i3 = i2;
                }
            } else {
                return i3;
            }
        }
        return -1;
    }

    ExpandableListAdapter m16173a() {
        return this.f14398a;
    }

    ae m16174a(int i) {
        int i2 = 0;
        ArrayList arrayList = this.f14399b;
        int size = arrayList.size();
        int i3 = size - 1;
        if (size == 0) {
            return ae.m16284a(i, 2, i, -1, null, 0);
        }
        size = i3;
        int i4 = 0;
        while (i4 <= size) {
            i2 = ((size - i4) / 2) + i4;
            GroupMetadata groupMetadata = (GroupMetadata) arrayList.get(i2);
            if (i > groupMetadata.f14395c) {
                i4 = i2 + 1;
            } else if (i < groupMetadata.f14394b) {
                size = i2 - 1;
            } else if (i == groupMetadata.f14394b) {
                return ae.m16284a(i, 2, groupMetadata.f14396d, -1, groupMetadata, i2);
            } else if (i <= groupMetadata.f14395c) {
                return ae.m16284a(i, 1, groupMetadata.f14396d, i - (groupMetadata.f14394b + 1), groupMetadata, i2);
            }
        }
        GroupMetadata groupMetadata2;
        if (i4 > i2) {
            groupMetadata2 = (GroupMetadata) arrayList.get(i4 - 1);
            size = (i - groupMetadata2.f14395c) + groupMetadata2.f14396d;
            i2 = i4;
        } else if (size < i2) {
            i2 = size + 1;
            groupMetadata2 = (GroupMetadata) arrayList.get(i2);
            size = groupMetadata2.f14396d - (groupMetadata2.f14394b - i);
        } else {
            throw new RuntimeException("Unknown state");
        }
        return ae.m16284a(i, 2, size, -1, null, i2);
    }

    ae m16175a(af afVar) {
        int i = 0;
        ArrayList arrayList = this.f14399b;
        int size = arrayList.size();
        int i2 = size - 1;
        if (size == 0) {
            return ae.m16284a(afVar.f14470c, afVar.f14473f, afVar.f14470c, afVar.f14471d, null, 0);
        }
        size = i2;
        int i3 = 0;
        while (i3 <= size) {
            i = ((size - i3) / 2) + i3;
            GroupMetadata groupMetadata = (GroupMetadata) arrayList.get(i);
            if (afVar.f14470c > groupMetadata.f14396d) {
                i3 = i + 1;
            } else if (afVar.f14470c < groupMetadata.f14396d) {
                size = i - 1;
            } else if (afVar.f14470c == groupMetadata.f14396d) {
                return afVar.f14473f == 2 ? ae.m16284a(groupMetadata.f14394b, afVar.f14473f, afVar.f14470c, afVar.f14471d, groupMetadata, i) : afVar.f14473f == 1 ? ae.m16284a((groupMetadata.f14394b + afVar.f14471d) + 1, afVar.f14473f, afVar.f14470c, afVar.f14471d, groupMetadata, i) : null;
            }
        }
        if (afVar.f14473f != 2) {
            return null;
        }
        GroupMetadata groupMetadata2;
        if (i3 > i) {
            groupMetadata2 = (GroupMetadata) arrayList.get(i3 - 1);
            return ae.m16284a((afVar.f14470c - groupMetadata2.f14396d) + groupMetadata2.f14395c, afVar.f14473f, afVar.f14470c, afVar.f14471d, null, i3);
        } else if (size >= i) {
            return null;
        } else {
            i = size + 1;
            groupMetadata2 = (GroupMetadata) arrayList.get(i);
            return ae.m16284a(groupMetadata2.f14394b - (groupMetadata2.f14396d - afVar.f14470c), afVar.f14473f, afVar.f14470c, afVar.f14471d, null, i);
        }
    }

    public void m16176a(ExpandableListAdapter expandableListAdapter) {
        if (this.f14398a != null) {
            this.f14398a.unregisterDataSetObserver(this.f14402e);
        }
        this.f14398a = expandableListAdapter;
        expandableListAdapter.registerDataSetObserver(this.f14402e);
    }

    void m16177a(ArrayList<GroupMetadata> arrayList) {
        if (arrayList != null && this.f14398a != null) {
            int groupCount = this.f14398a.getGroupCount();
            int size = arrayList.size() - 1;
            while (size >= 0) {
                if (((GroupMetadata) arrayList.get(size)).f14396d < groupCount) {
                    size--;
                } else {
                    return;
                }
            }
            this.f14399b = arrayList;
            m16171a(true, false);
        }
    }

    boolean m16178a(ae aeVar) {
        if (aeVar.f14464b == null) {
            return false;
        }
        this.f14399b.remove(aeVar.f14464b);
        m16171a(false, false);
        notifyDataSetChanged();
        this.f14398a.onGroupCollapsed(aeVar.f14464b.f14396d);
        return true;
    }

    public boolean areAllItemsEnabled() {
        return this.f14398a.areAllItemsEnabled();
    }

    ArrayList<GroupMetadata> m16179b() {
        return this.f14399b;
    }

    boolean m16180b(int i) {
        af a = af.m16291a(2, i, -1, -1);
        ae a2 = m16175a(a);
        a.m16296b();
        if (a2 == null) {
            return false;
        }
        boolean a3 = m16178a(a2);
        a2.m16287a();
        return a3;
    }

    boolean m16181b(ae aeVar) {
        if (aeVar.f14463a.f14470c < 0) {
            throw new RuntimeException("Need group");
        } else if (this.f14401d == 0) {
            return false;
        } else {
            if (aeVar.f14464b != null) {
                return false;
            }
            GroupMetadata groupMetadata;
            if (this.f14399b.size() >= this.f14401d) {
                groupMetadata = (GroupMetadata) this.f14399b.get(0);
                int indexOf = this.f14399b.indexOf(groupMetadata);
                m16180b(groupMetadata.f14396d);
                if (aeVar.f14465c > indexOf) {
                    aeVar.f14465c--;
                }
            }
            groupMetadata = GroupMetadata.m16168a(-1, -1, aeVar.f14463a.f14470c, this.f14398a.getGroupId(aeVar.f14463a.f14470c));
            this.f14399b.add(aeVar.f14465c, groupMetadata);
            m16171a(false, false);
            notifyDataSetChanged();
            this.f14398a.onGroupExpanded(groupMetadata.f14396d);
            return true;
        }
    }

    boolean m16182c(int i) {
        af a = af.m16291a(2, i, -1, -1);
        ae a2 = m16175a(a);
        a.m16296b();
        boolean b = m16181b(a2);
        a2.m16287a();
        return b;
    }

    public boolean m16183d(int i) {
        for (int size = this.f14399b.size() - 1; size >= 0; size--) {
            if (((GroupMetadata) this.f14399b.get(size)).f14396d == i) {
                return true;
            }
        }
        return false;
    }

    public void m16184e(int i) {
        this.f14401d = i;
    }

    public int getCount() {
        return this.f14398a.getGroupCount() + this.f14400c;
    }

    public Filter getFilter() {
        ExpandableListAdapter a = m16173a();
        return a instanceof Filterable ? ((Filterable) a).getFilter() : null;
    }

    public Object getItem(int i) {
        Object group;
        ae a = m16174a(i);
        if (a.f14463a.f14473f == 2) {
            group = this.f14398a.getGroup(a.f14463a.f14470c);
        } else if (a.f14463a.f14473f == 1) {
            group = this.f14398a.getChild(a.f14463a.f14470c, a.f14463a.f14471d);
        } else {
            throw new RuntimeException("Flat list position is of unknown type");
        }
        a.m16287a();
        return group;
    }

    public long getItemId(int i) {
        ae a = m16174a(i);
        long groupId = this.f14398a.getGroupId(a.f14463a.f14470c);
        if (a.f14463a.f14473f == 2) {
            groupId = this.f14398a.getCombinedGroupId(groupId);
        } else if (a.f14463a.f14473f == 1) {
            groupId = this.f14398a.getCombinedChildId(groupId, this.f14398a.getChildId(a.f14463a.f14470c, a.f14463a.f14471d));
        } else {
            throw new RuntimeException("Flat list position is of unknown type");
        }
        a.m16287a();
        return groupId;
    }

    public int getItemViewType(int i) {
        int groupType;
        ae a = m16174a(i);
        af afVar = a.f14463a;
        if (this.f14398a instanceof HeterogeneousExpandableList) {
            HeterogeneousExpandableList heterogeneousExpandableList = (HeterogeneousExpandableList) this.f14398a;
            groupType = afVar.f14473f == 2 ? heterogeneousExpandableList.getGroupType(afVar.f14470c) : heterogeneousExpandableList.getGroupTypeCount() + heterogeneousExpandableList.getChildType(afVar.f14470c, afVar.f14471d);
        } else {
            groupType = afVar.f14473f == 2 ? 0 : 1;
        }
        a.m16287a();
        return groupType;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View groupView;
        boolean z = true;
        ae a = m16174a(i);
        if (a.f14463a.f14473f == 2) {
            groupView = this.f14398a.getGroupView(a.f14463a.f14470c, a.m16288b(), view, viewGroup);
        } else if (a.f14463a.f14473f == 1) {
            if (a.f14464b.f14395c != i) {
                z = false;
            }
            groupView = this.f14398a.getChildView(a.f14463a.f14470c, a.f14463a.f14471d, z, view, viewGroup);
        } else {
            throw new RuntimeException("Flat list position is of unknown type");
        }
        a.m16287a();
        return groupView;
    }

    public int getViewTypeCount() {
        if (!(this.f14398a instanceof HeterogeneousExpandableList)) {
            return 2;
        }
        HeterogeneousExpandableList heterogeneousExpandableList = (HeterogeneousExpandableList) this.f14398a;
        return heterogeneousExpandableList.getChildTypeCount() + heterogeneousExpandableList.getGroupTypeCount();
    }

    public boolean hasStableIds() {
        return this.f14398a.hasStableIds();
    }

    public boolean isEmpty() {
        ExpandableListAdapter a = m16173a();
        return a != null ? a.isEmpty() : true;
    }

    public boolean isEnabled(int i) {
        boolean z = true;
        ae a = m16174a(i);
        af afVar = a.f14463a;
        if (afVar.f14473f == 1) {
            z = this.f14398a.isChildSelectable(afVar.f14470c, afVar.f14471d);
        }
        a.m16287a();
        return z;
    }
}
