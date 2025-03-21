package com.inyaw.file.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class QniUploadService implements BaseUploadService {


    @Override
    public String uploadFile(MultipartFile file, Integer type, Map<String, Object> configMap) {
        return null;
    }

    @Override
    public void deleteFile(String url, Map<String, Object> configMap) {

    }

    @Override
    public List<String> readFileList(Map<String, Object> configMap, String start) {
        return null;
    }
}
