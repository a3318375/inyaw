package com.inyaw.file.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class CostUploadService implements BaseUploadService {

    private final SysFileService sysFileService;

    private COSClient getInitClientBean(Map<String, Object> configMap) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = String.valueOf(configMap.get("UPLOAD_COS_SECRETID"));
        String secretKey = String.valueOf(configMap.get("UPLOAD_COS_SECRETKEY"));
        String regionName = String.valueOf(configMap.get("UPLOAD_COS_REGION"));
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    @Override
    public String uploadFile(MultipartFile file, Integer type, Map<String, Object> configMap) throws IOException {
        //指定文件将要存放的存储桶名称
        String bucket = String.valueOf(configMap.get("UPLOAD_COS_BUCKET"));
        //上传到cos的文件夹
        String path = "";
        if (type == 10) {
            path = String.valueOf(configMap.get("UPLOAD_COS_ARTICLE_PATH"));
        } else {
            path = String.valueOf(configMap.get("UPLOAD_COS_COVER_PATH"));
        }

        //文件主域名
        String adminUrl = String.valueOf(configMap.get("UPLOAD_ADMIN_URL"));

        COSClient cosClient = getInitClientBean(configMap);

        String filename = file.getOriginalFilename();
        //path是指存储桶的域名
        String key = path + "/" + getUUID32() + filename.substring(filename.indexOf("."));

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, file.getInputStream(), objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        log.info("腾讯云cos返回结果：" + putObjectResult);
        String url = adminUrl + key;
        int uploadType = Integer.parseInt(String.valueOf(configMap.get("UPLOAD_TYPE")));
        sysFileService.save(url, uploadType, type);
        return url;
    }

    @Override
    public void deleteFile(String url, Map<String, Object> configMap) {

    }

    @Override
    public List<String> readFileList(Map<String, Object> configMap, String start) {
        return null;
    }

    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
