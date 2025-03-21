package com.inyaw.file.service;

import com.upyun.RestManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpaiUploadService implements BaseUploadService {

    private final SysFileService sysFileService;

    //inyaa   yuxhtest     JWCKAeRxzO4iRsylqCxOzrlbS3I21zGB
    private Response uploadByUpai(MultipartFile file, String newname, String username, String password, String bucket) {
        try {
            RestManager manager = new RestManager(bucket, username, password);
            return manager.writeFile(newname, file.getInputStream(), null);
        } catch (Exception e) {
            log.error("图片上传异常", e);
            return null;
        }
    }

    @Override
    public String uploadFile(MultipartFile file, Integer type, Map<String, Object> configMap) {
        String username = String.valueOf(configMap.get("UPLOAD_UPAI_USERNAME"));
        String password = String.valueOf(configMap.get("UPLOAD_UPAI_PASSWORD"));
        String bucket = String.valueOf(configMap.get("UPLOAD_UPAI_BUCKET"));
        String path = String.valueOf(configMap.get("UPLOAD_UPAI_PATH"));
        String adminUrl = String.valueOf(configMap.get("UPLOAD_ADMIN_URL"));

        String filename = file.getOriginalFilename();
        String saveName = path + "/" + getUUID32() + filename.substring(filename.indexOf("."));
        Response resp = uploadByUpai(file, saveName, username, password, bucket);
        String url = null;
        if (resp != null && resp.body() != null && resp.isSuccessful()) {
            url = adminUrl + saveName;
            int uploadType = Integer.parseInt(String.valueOf(configMap.get("UPLOAD_TYPE")));
            sysFileService.save(url, uploadType, type);
        }
        return url;
    }

    @Override
    public void deleteFile(String url, Map<String, Object> configMap) {
        String username = String.valueOf(configMap.get("UPLOAD_UPAI_USERNAME"));
        String password = String.valueOf(configMap.get("UPLOAD_UPAI_PASSWORD"));
        String bucket = String.valueOf(configMap.get("UPLOAD_UPAI_BUCKET"));
        try {
            RestManager manager = new RestManager(bucket, username, password);
            manager.deleteFile(url, null);
        } catch (Exception e) {
            log.error("图片上传异常", e);
        }
    }

    @Override
    public List<String> readFileList(Map<String, Object> configMap, String start) {
        String username = String.valueOf(configMap.get("UPLOAD_UPAI_USERNAME"));
        String password = String.valueOf(configMap.get("UPLOAD_UPAI_PASSWORD"));
        String bucket = String.valueOf(configMap.get("UPLOAD_UPAI_BUCKET"));
        RestManager manager = new RestManager(bucket, username, password);

        try {
            Map<String, String> params = new HashMap<>();
            if (StringUtils.isNotBlank(start)) {
                params.put("x-list-iter", start);
            }
            Response resp = manager.readDirIter("/up/tt/", params);
            if (resp != null && resp.body() != null && resp.isSuccessful()) {
            }
        } catch (Exception e) {
            log.error("读取图片目录异常", e);
        }
        return null;
    }

    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
