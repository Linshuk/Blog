package jmu.lsk.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
 
@Data
@ConfigurationProperties("minio")
public class MinIOConfigProperties implements Serializable {


    private String accessKey;


    private String secretKey;


    private String bucket;


    private String endpoint;


    private String readPath;
}