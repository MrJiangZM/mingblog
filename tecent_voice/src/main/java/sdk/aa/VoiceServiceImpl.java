package sdk.aa;

import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author Jiang Zaiming
 * @date 2020/6/18 11:22 上午
 */
@Service
public class VoiceServiceImpl implements VoiceService {

    @Override
    public String getVoice(String voiceId, MultipartFile file1) {
        try{
            Credential cred = new EmbeddedLdapProperties.Credential("AKID4SZVS97x0nMyZ4xHyQHrfdLzGL9FZeW7", "YpycJktkGXWFyLRJzQaU7lH4dR7art65");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("asr.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            clientProfile.setSignMethod("TC3-HMAC-SHA256");
            AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);
            String params = "{\"ProjectId\":0,\"SubServiceType\":2,\"EngSerViceType\":\"16k_zh\",\"SourceType\":1,\"Url\":\"\",\"VoiceFormat\":\"wav\",\"UsrAudioKey\":\"session-123\"}";
            SentenceRecognitionRequest req = SentenceRecognitionRequest.fromJsonString(params, SentenceRecognitionRequest.class);
            //这里直接给出一个音频文件的本地路径
//            File file = new File("C:/Users/zhao/Desktop/ws.wav");
//            FileInputStream inputFile = new FileInputStream(file);
            InputStream inputStream = file1.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
//            byte[] buffer = new byte[(int)inputStream.];
//            req.setDataLen(file.length());
//            inputFile.read(buffer);
//            inputFile.close();
            String encodeData = Base64.getEncoder().encodeToString(bytes);
            req.setData(encodeData);

            SentenceRecognitionResponse resp = client.SentenceRecognition(req);
            String result = resp.getResult();
            return result;
        } catch (TencentCloudSDKException | IOException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void main(String[] args) {
        try{
            Credential cred = new Credential("AKID4SZVS97x0nMyZ4xHyQHrfdLzGL9FZeW7", "YpycJktkGXWFyLRJzQaU7lH4dR7art65");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("asr.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            clientProfile.setSignMethod("TC3-HMAC-SHA256");
            AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);
            String params = "{\"ProjectId\":0,\"SubServiceType\":2,\"EngSerViceType\":\"16k_zh\",\"SourceType\":1,\"Url\":\"\",\"VoiceFormat\":\"wav\",\"UsrAudioKey\":\"session-123\"}";
            SentenceRecognitionRequest req = SentenceRecognitionRequest.fromJsonString(params, SentenceRecognitionRequest.class);
            //这里直接给出一个音频文件的本地路径
            File file = new File("/Users/jiangzaiming/project/java_realtime_asr_sdk/java_realtime_asr_sdk/test_wav/16k/16k_30s..wav");
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            req.setDataLen(file.length());
            inputFile.read(buffer);
            inputFile.close();
            String encodeData = Base64.getEncoder().encodeToString(buffer);
            req.setData(encodeData);

            SentenceRecognitionResponse resp = client.SentenceRecognition(req);
            String result = resp.getResult();
            System.out.println(result);
        } catch (TencentCloudSDKException | IOException e) {
            System.out.println(e.toString());
        }
    }

}
