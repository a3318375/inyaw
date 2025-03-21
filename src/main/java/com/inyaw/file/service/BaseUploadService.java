package com.inyaw.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BaseUploadService {

    String uploadFile(MultipartFile file, Integer type, Map<String, Object> configMap) throws IOException;

    void deleteFile(String url, Map<String, Object> configMap);

    List<String> readFileList(Map<String, Object> configMap, String start);
}
