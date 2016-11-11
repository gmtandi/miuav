package com.fimi.soul.biz.p102i;

import android.content.Context;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p099f.C1333a;
import com.fimi.soul.entity.FdsMsg;
import com.fimi.soul.entity.PlaneMsg;
import com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration;
import com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClient;
import com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl;
import com.xiaomi.infra.galaxy.fds.android.auth.OAuthCredential;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.android.model.ExpiresParam;
import com.xiaomi.infra.galaxy.fds.android.model.FDSObject;
import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult;
import com.xiaomi.infra.galaxy.fds.android.model.UserParam;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.biz.i.d */
public class C1357d {
    private Context f6000a;
    private GalaxyFDSClient f6001b;
    private String f6002c;
    private String f6003d;
    private List<UserParam> f6004e;

    public C1357d(Context context) {
        this.f6002c = null;
        this.f6003d = null;
        this.f6004e = null;
        this.f6004e = new ArrayList();
        this.f6000a = context;
    }

    public PlaneMsg m9064a() {
        boolean z;
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            this.f6002c = C1333a.m8958c(this.f6000a, 0);
            this.f6003d = C1333a.m8956b(this.f6000a, 0);
            this.f6001b = new GalaxyFDSClientImpl(new FDSClientConfiguration().withCredential(new OAuthCredential(C1236a.f5616n, C1236a.f5628z, C1236a.f5628z, this.f6002c, C1236a.f5627y, this.f6003d, C1236a.f5618p)).withFdsServiceBaseUri(C1236a.f5616n));
            this.f6004e.add(new ExpiresParam(System.currentTimeMillis() + 3153600000000L));
            z = true;
        } catch (GalaxyFDSClientException e) {
            e.printStackTrace();
            planeMsg.setErrorMessage(e.getMessage());
            z = false;
        }
        planeMsg.setSuccess(z);
        return planeMsg;
    }

    public PlaneMsg m9065a(File file) {
        boolean z = false;
        FdsMsg fdsMsg = new FdsMsg();
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            if (this.f6001b == null) {
                m9064a();
            }
            if (this.f6001b != null) {
                PutObjectResult putObject = this.f6001b.putObject(C1236a.f5617o, file, this.f6004e);
                fdsMsg.setBucketName(C1236a.f5617o);
                fdsMsg.setObjectName(putObject.getObjectName());
                fdsMsg.setUrl(putObject.getAbsolutePresignedUri());
                z = true;
            }
        } catch (GalaxyFDSClientException e) {
            e.printStackTrace();
            planeMsg.setErrorMessage(e.getMessage());
        }
        planeMsg.setSuccess(z);
        planeMsg.setData(fdsMsg);
        return planeMsg;
    }

    public PlaneMsg m9066a(File file, ProgressListener progressListener) {
        boolean z = false;
        FdsMsg fdsMsg = new FdsMsg();
        PlaneMsg planeMsg = new PlaneMsg();
        try {
            if (this.f6001b == null) {
                m9064a();
            }
            if (this.f6001b != null) {
                PutObjectResult putObject = this.f6001b.putObject(C1236a.f5617o, file, this.f6004e, progressListener);
                fdsMsg.setBucketName(C1236a.f5617o);
                fdsMsg.setObjectName(putObject.getObjectName());
                fdsMsg.setUrl(putObject.getAbsolutePresignedUri());
                z = true;
            }
        } catch (GalaxyFDSClientException e) {
            e.printStackTrace();
            planeMsg.setErrorMessage(e.getMessage());
        }
        planeMsg.setSuccess(z);
        planeMsg.setData(fdsMsg);
        return planeMsg;
    }

    public PlaneMsg m9067a(InputStream inputStream, ObjectMetadata objectMetadata) {
        PlaneMsg planeMsg = new PlaneMsg();
        FdsMsg fdsMsg = new FdsMsg();
        boolean z = false;
        try {
            if (this.f6001b == null) {
                m9064a();
            }
            if (this.f6001b != null) {
                PutObjectResult putObject = this.f6001b.putObject(C1236a.f5617o, inputStream, objectMetadata, this.f6004e);
                fdsMsg.setBucketName(C1236a.f5617o);
                fdsMsg.setObjectName(putObject.getObjectName());
                fdsMsg.setUrl(putObject.getAbsolutePresignedUri());
                z = true;
            }
        } catch (GalaxyFDSClientException e) {
            e.printStackTrace();
            planeMsg.setErrorMessage(e.getMessage());
        }
        planeMsg.setSuccess(z);
        planeMsg.setData(fdsMsg);
        return planeMsg;
    }

    public PlaneMsg m9068a(InputStream inputStream, ObjectMetadata objectMetadata, ProgressListener progressListener) {
        boolean z;
        PlaneMsg planeMsg = new PlaneMsg();
        FdsMsg fdsMsg = new FdsMsg();
        try {
            if (this.f6001b == null) {
                m9064a();
            }
            if (this.f6001b != null) {
                PutObjectResult putObject = this.f6001b.putObject(C1236a.f5617o, inputStream, objectMetadata, this.f6004e, progressListener);
                fdsMsg.setBucketName(C1236a.f5617o);
                fdsMsg.setObjectName(putObject.getObjectName());
                fdsMsg.setUrl(putObject.getAbsolutePresignedUri());
                z = true;
                planeMsg.setSuccess(z);
                planeMsg.setData(fdsMsg);
                return planeMsg;
            }
        } catch (GalaxyFDSClientException e) {
            e.printStackTrace();
            planeMsg.setErrorMessage(e.getMessage());
        }
        z = false;
        planeMsg.setSuccess(z);
        planeMsg.setData(fdsMsg);
        return planeMsg;
    }

    public File m9069a(String str) {
        try {
            if (this.f6001b == null) {
                m9064a();
            }
            this.f6001b.getObject(C1236a.f5617o, str, null);
        } catch (GalaxyFDSClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputStream m9070b(String str) {
        FDSObject object;
        try {
            if (this.f6001b == null) {
                m9064a();
            }
            object = this.f6001b.getObject(C1236a.f5617o, str);
        } catch (GalaxyFDSClientException e) {
            e.printStackTrace();
            object = null;
        }
        return object != null ? object.getObjectContent() : null;
    }
}
