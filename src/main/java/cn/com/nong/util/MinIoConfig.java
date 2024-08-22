package cn.com.nong.util;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MinIoConfig {
    /**
     * Minio 服务地址
     */
    @Value("${min.io.endpoint}")
    private String endpoint;
 
    /**
     * Minio 服务端口号
     */
    @Value("${min.io.point}")
    private Integer point;
 
    /**
     * Minio ACCESS_KEY
     */
    @Value("${min.io.accessKey}")
    private String accessKey;
 
    /**
     * Minio SECRET_KEY
     */
    @Value("${min.io.secretKey}")
    private String secretKey;
 
    /**
     * Minio 是否为https 请求，true 地址为https,false地址为http
     */
    @Value("${min.io.secure}")
    private boolean secure;
 
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
 
}