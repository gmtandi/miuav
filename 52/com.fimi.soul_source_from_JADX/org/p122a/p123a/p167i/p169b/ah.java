package org.p122a.p123a.p167i.p169b;

import java.net.URI;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.i.b.ah */
public class ah extends AbstractList<Object> {
    private final Set<URI> f15264a;
    private final List<URI> f15265b;

    public ah() {
        this.f15264a = new HashSet();
        this.f15265b = new ArrayList();
    }

    public URI m17348a(int i) {
        return (URI) this.f15265b.get(i);
    }

    public List<URI> m17349a() {
        return new ArrayList(this.f15265b);
    }

    public boolean m17350a(URI uri) {
        return this.f15264a.contains(uri);
    }

    public void add(int i, Object obj) {
        this.f15265b.add(i, (URI) obj);
        this.f15264a.add((URI) obj);
    }

    public URI m17351b(int i) {
        URI uri = (URI) this.f15265b.remove(i);
        this.f15264a.remove(uri);
        if (this.f15265b.size() != this.f15264a.size()) {
            this.f15264a.addAll(this.f15265b);
        }
        return uri;
    }

    public void m17352b(URI uri) {
        this.f15264a.add(uri);
        this.f15265b.add(uri);
    }

    public boolean m17353c(URI uri) {
        boolean remove = this.f15264a.remove(uri);
        if (remove) {
            Iterator it = this.f15265b.iterator();
            while (it.hasNext()) {
                if (((URI) it.next()).equals(uri)) {
                    it.remove();
                }
            }
        }
        return remove;
    }

    public boolean contains(Object obj) {
        return this.f15264a.contains(obj);
    }

    public /* synthetic */ Object get(int i) {
        return m17348a(i);
    }

    public /* synthetic */ Object remove(int i) {
        return m17351b(i);
    }

    public Object set(int i, Object obj) {
        URI uri = (URI) this.f15265b.set(i, (URI) obj);
        this.f15264a.remove(uri);
        this.f15264a.add((URI) obj);
        if (this.f15265b.size() != this.f15264a.size()) {
            this.f15264a.addAll(this.f15265b);
        }
        return uri;
    }

    public int size() {
        return this.f15265b.size();
    }
}
