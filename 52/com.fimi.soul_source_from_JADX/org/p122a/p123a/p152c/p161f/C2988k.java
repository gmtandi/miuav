package org.p122a.p123a.p152c.p161f;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import org.apache.http.HttpHost;
import org.p122a.p123a.C2915a;
import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3239f;

@C2912b
/* renamed from: org.a.a.c.f.k */
public class C2988k {
    private C2988k() {
    }

    @Deprecated
    public static URI m16967a(String str, String str2, int i, String str3, String str4, String str5) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str2 != null) {
            if (str != null) {
                stringBuilder.append(str);
                stringBuilder.append("://");
            }
            stringBuilder.append(str2);
            if (i > 0) {
                stringBuilder.append(':');
                stringBuilder.append(i);
            }
        }
        if (str3 == null || !str3.startsWith("/")) {
            stringBuilder.append('/');
        }
        if (str3 != null) {
            stringBuilder.append(str3);
        }
        if (str4 != null) {
            stringBuilder.append('?');
            stringBuilder.append(str4);
        }
        if (str5 != null) {
            stringBuilder.append('#');
            stringBuilder.append(str5);
        }
        return new URI(stringBuilder.toString());
    }

    public static URI m16968a(URI uri) {
        C3234a.m17886a((Object) uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        C2987j c2987j = new C2987j(uri);
        if (c2987j.m16960g() != null) {
            c2987j.m16948b(null);
        }
        if (C3239f.m17910a(c2987j.m16964j())) {
            c2987j.m16954d("/");
        }
        if (c2987j.m16962h() != null) {
            c2987j.m16952c(c2987j.m16962h().toLowerCase(Locale.ENGLISH));
        }
        c2987j.m16961g(null);
        return c2987j.m16941a();
    }

    public static URI m16969a(URI uri, String str) {
        return C2988k.m16970a(uri, URI.create(str));
    }

    public static URI m16970a(URI uri, URI uri2) {
        C3234a.m17886a((Object) uri, "Base URI");
        C3234a.m17886a((Object) uri2, "Reference URI");
        String uri3 = uri2.toString();
        if (uri3.startsWith("?")) {
            return C2988k.m16974b(uri, uri2);
        }
        int i = uri3.length() == 0 ? 1 : 0;
        if (i != 0) {
            uri2 = URI.create("#");
        }
        URI resolve = uri.resolve(uri2);
        if (i != 0) {
            uri3 = resolve.toString();
            resolve = URI.create(uri3.substring(0, uri3.indexOf(35)));
        }
        return C2988k.m16976c(resolve);
    }

    public static URI m16971a(URI uri, HttpHost httpHost) {
        return C2988k.m16973a(uri, httpHost, false);
    }

    public static URI m16972a(URI uri, HttpHost httpHost, List<URI> list) {
        C2987j c2987j;
        C3234a.m17886a((Object) uri, "Request URI");
        if (list == null || list.isEmpty()) {
            c2987j = new C2987j(uri);
        } else {
            C2987j c2987j2 = new C2987j((URI) list.get(list.size() - 1));
            String l = c2987j2.m16966l();
            int size = list.size() - 1;
            while (l == null && size >= 0) {
                String fragment = ((URI) list.get(size)).getFragment();
                size--;
                l = fragment;
            }
            c2987j2.m16961g(l);
            c2987j = c2987j2;
        }
        if (c2987j.m16966l() == null) {
            c2987j.m16961g(uri.getFragment());
        }
        if (!(httpHost == null || c2987j.m16955d())) {
            c2987j.m16943a(httpHost.getSchemeName());
            c2987j.m16952c(httpHost.getHostName());
            c2987j.m16942a(httpHost.getPort());
        }
        return c2987j.m16941a();
    }

    public static URI m16973a(URI uri, HttpHost httpHost, boolean z) {
        C3234a.m17886a((Object) uri, "URI");
        if (uri.isOpaque()) {
            return uri;
        }
        C2987j c2987j = new C2987j(uri);
        if (httpHost != null) {
            c2987j.m16943a(httpHost.getSchemeName());
            c2987j.m16952c(httpHost.getHostName());
            c2987j.m16942a(httpHost.getPort());
        } else {
            c2987j.m16943a(null);
            c2987j.m16952c(null);
            c2987j.m16942a(-1);
        }
        if (z) {
            c2987j.m16961g(null);
        }
        if (C3239f.m17910a(c2987j.m16964j())) {
            c2987j.m16954d("/");
        }
        return c2987j.m16941a();
    }

    private static URI m16974b(URI uri, URI uri2) {
        String uri3 = uri.toString();
        if (uri3.indexOf(63) > -1) {
            uri3 = uri3.substring(0, uri3.indexOf(63));
        }
        return URI.create(uri3 + uri2.toString());
    }

    public static HttpHost m16975b(URI uri) {
        if (uri == null) {
            return null;
        }
        HttpHost httpHost;
        if (uri.isAbsolute()) {
            int port = uri.getPort();
            Object host = uri.getHost();
            if (host == null) {
                String authority = uri.getAuthority();
                if (authority != null) {
                    int indexOf = authority.indexOf(64);
                    String substring = indexOf >= 0 ? authority.length() > indexOf + 1 ? authority.substring(indexOf + 1) : null : authority;
                    if (substring != null) {
                        int indexOf2 = substring.indexOf(58);
                        if (indexOf2 >= 0) {
                            indexOf = indexOf2 + 1;
                            int i = indexOf;
                            int i2 = 0;
                            while (i < substring.length() && Character.isDigit(substring.charAt(i))) {
                                i2++;
                                i++;
                            }
                            if (i2 > 0) {
                                try {
                                    i = Integer.parseInt(substring.substring(indexOf, indexOf + i2));
                                } catch (NumberFormatException e) {
                                    i = port;
                                }
                            } else {
                                i = port;
                            }
                            port = i;
                            host = substring.substring(0, indexOf2);
                        }
                    }
                    authority = substring;
                }
            }
            String scheme = uri.getScheme();
            if (!C3239f.m17911b(host)) {
                httpHost = new HttpHost(host, port, scheme);
                return httpHost;
            }
        }
        httpHost = null;
        return httpHost;
    }

    private static URI m16976c(URI uri) {
        if (uri.isOpaque() || uri.getAuthority() == null) {
            return uri;
        }
        C3234a.m17888a(uri.isAbsolute(), "Base URI must be absolute");
        String path = uri.getPath() == null ? C2915a.f14760f : uri.getPath();
        String[] split = path.split("/");
        Stack stack = new Stack();
        for (String str : split) {
            if (!(str.length() == 0 || ".".equals(str))) {
                if (!"..".equals(str)) {
                    stack.push(str);
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            stringBuilder.append('/').append((String) it.next());
        }
        if (path.lastIndexOf(47) == path.length() - 1) {
            stringBuilder.append('/');
        }
        try {
            URI uri2 = new URI(uri.getScheme().toLowerCase(Locale.ENGLISH), uri.getAuthority().toLowerCase(Locale.ENGLISH), stringBuilder.toString(), null, null);
            if (uri.getQuery() == null && uri.getFragment() == null) {
                return uri2;
            }
            StringBuilder stringBuilder2 = new StringBuilder(uri2.toASCIIString());
            if (uri.getQuery() != null) {
                stringBuilder2.append('?').append(uri.getRawQuery());
            }
            if (uri.getFragment() != null) {
                stringBuilder2.append('#').append(uri.getRawFragment());
            }
            return URI.create(stringBuilder2.toString());
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }
}
