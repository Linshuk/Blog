package jmu.lsk.service;

import jmu.lsk.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult uploadHeadImg(MultipartFile img);

    ResponseResult uploadArticleImg(MultipartFile img);
}