package com.xiaomi.infra.galaxy.fds.android;

import com.xiaomi.infra.galaxy.fds.android.model.FDSObject;
import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult;
import com.xiaomi.infra.galaxy.fds.android.model.UserParam;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface GalaxyFDSClient {
    boolean doesObjectExist(String str, String str2);

    FDSObject getObject(String str, long j, List<UserParam> list, ProgressListener progressListener);

    FDSObject getObject(String str, String str2);

    FDSObject getObject(String str, String str2, long j, List<UserParam> list);

    FDSObject getObject(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener);

    FDSObject getObject(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener, boolean z);

    ObjectMetadata getObject(String str, File file, List<UserParam> list, ProgressListener progressListener);

    ObjectMetadata getObject(String str, String str2, File file);

    ObjectMetadata getObject(String str, String str2, File file, List<UserParam> list);

    ObjectMetadata getObject(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener);

    ObjectMetadata getObject(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener, boolean z);

    PutObjectResult putObject(String str, File file);

    PutObjectResult putObject(String str, File file, List<UserParam> list);

    PutObjectResult putObject(String str, File file, List<UserParam> list, ProgressListener progressListener);

    PutObjectResult putObject(String str, InputStream inputStream, ObjectMetadata objectMetadata);

    PutObjectResult putObject(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list);

    PutObjectResult putObject(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list, ProgressListener progressListener);

    PutObjectResult putObject(String str, String str2, File file);

    PutObjectResult putObject(String str, String str2, File file, List<UserParam> list);

    PutObjectResult putObject(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener);

    PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata);

    PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list);

    PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list, ProgressListener progressListener);
}
