package jmu.lsk.service.impl;

import jmu.lsk.domain.ResponseResult;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.exception.SystemException;
import jmu.lsk.service.FileStorageService;
import jmu.lsk.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    FileStorageService fileStorageService;

    @Override
    public ResponseResult uploadHeadImg(MultipartFile img) {
        try {
            InputStream inputStream = img.getInputStream();
            String url = fileStorageService.uploadImgFile("head", img.getOriginalFilename(), inputStream);
            return ResponseResult.okResult(url);
        } catch (IOException e) {
            throw new SystemException(AppHttpCodeEnum.UPLOAD_ERROR);
        }
    }

    @Override
    public ResponseResult uploadArticleImg(MultipartFile img) {
        try {
            InputStream inputStream = img.getInputStream();
            String url = fileStorageService.uploadImgFile("article", img.getOriginalFilename(), inputStream);
            return ResponseResult.okResult(url);
        } catch (IOException e) {
            throw new SystemException(AppHttpCodeEnum.UPLOAD_ERROR);
        }
    }
}
