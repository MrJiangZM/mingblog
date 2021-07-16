package com.ming.blog.service;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.alibaba.fastjson.JSON;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author MrJiangZM
 * @since <pre>2021/7/12</pre>
 */
@Service
@Slf4j
public class FileService {

    @Resource
    private MinioClient minioClient;

    public ObjectWriteResponse storeFile(String bucketName, String category, String objectName, InputStream stream, Long size) {
//        PutObjectArgs.builder().contentType("jpg").stream(stream, size, 0L);
        ObjectWriteResponse jpg = null;
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .contentType("multipart/form-data")
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(stream, size, 0L).build();
            jpg = minioClient.putObject(putObjectArgs);
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
        catch (InvalidBucketNameException e) {
            e.printStackTrace();
        }
        log.info("get response:{}", JSON.toJSONString(jpg));
        return jpg;
    }

}
