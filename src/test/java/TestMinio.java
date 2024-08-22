import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class TestMinio {
    public static void main(String[] args) throws  Exception{
        // 初始化客户端
        MinioClient minioClient =
                MinioClient.builder()
                        // minio服务端地址URL
                        .endpoint("http://172.30.154.202:9002")
                        // 用户名及密码（访问密钥/密钥）
                        .credentials("2TjAk2MJOXRpWeZhr8SQ", "aK9RY8JtHiCgBsmrDfjH73jrBe0Hun0e2WYNNJmk")
                        .build();
//        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("public").build());
//        if (found) {
//            System.out.println("my-bucketname exists");
//            List<Bucket> bucketList = minioClient.listBuckets();
//            for (Bucket bucket : bucketList) {
//                System.out.println(bucket.creationDate() + ", " + bucket.name());
//            }
//        } else {
//            System.out.println("my-bucketname does not exist");
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket("public").build());
//            System.out.println("my-bucketname is created successfully");
//        }
        String objectName = "test";
        File file = new File("D:\\Program Files (x86)\\Tencent\\WeChat\\file\\WeChat Files\\lj838865121\\FileStorage\\File\\2024-08\\升级问题.doc");
        FileInputStream fileInputStream = new FileInputStream(file);
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("public")
                        .object(objectName)
                        .stream(fileInputStream, file.length(), -1)
                        .build()
        );

    }
}
