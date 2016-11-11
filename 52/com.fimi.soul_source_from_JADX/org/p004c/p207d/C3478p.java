package org.p004c.p207d;

import java.io.File;
import java.io.IOException;
import org.p122a.p123a.C2915a;

/* renamed from: org.c.d.p */
public class C3478p extends C3468g {
    private final File f15995a;
    private File f15996b;

    public C3478p() {
        this(null);
    }

    public C3478p(File file) {
        this.f15995a = file;
    }

    private File m18988a(File file) {
        File createTempFile = File.createTempFile("junit", C2915a.f14760f, file);
        createTempFile.delete();
        createTempFile.mkdir();
        return createTempFile;
    }

    private boolean m18989a(int i, String[] strArr) {
        return i == strArr.length + -1;
    }

    private void m18990b(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File b : listFiles) {
                m18990b(b);
            }
        }
        file.delete();
    }

    private void m18991c(String str) {
        if (new File(str).getParent() != null) {
            throw new IOException("Folder name cannot consist of multiple path components separated by a file separator. Please use newFolder('MyParentFolder','MyFolder') to create hierarchies of folders");
        }
    }

    public File m18992a(String str) {
        File file = new File(m19000f(), str);
        if (file.createNewFile()) {
            return file;
        }
        throw new IOException("a file with the name '" + str + "' already exists in the test folder");
    }

    public File m18993a(String... strArr) {
        File f = m19000f();
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            m18991c(str);
            File file = new File(f, str);
            if (file.mkdir() || !m18989a(i, strArr)) {
                i++;
                f = file;
            } else {
                throw new IOException("a folder with the name '" + str + "' already exists");
            }
        }
        return f;
    }

    protected void m18994a() {
        m18997c();
    }

    public File m18995b(String str) {
        return m18993a(str);
    }

    protected void m18996b() {
        m19001g();
    }

    public void m18997c() {
        this.f15996b = m18988a(this.f15995a);
    }

    public File m18998d() {
        return File.createTempFile("junit", null, m19000f());
    }

    public File m18999e() {
        return m18988a(m19000f());
    }

    public File m19000f() {
        if (this.f15996b != null) {
            return this.f15996b;
        }
        throw new IllegalStateException("the temporary folder has not yet been created");
    }

    public void m19001g() {
        if (this.f15996b != null) {
            m18990b(this.f15996b);
        }
    }
}
