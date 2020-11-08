package sdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sdk.http.synchronize.RequestSender;
import sdk.model.response.VoiceResponse;
import sdk.utils.ByteUtils;
import sdk.utils.StringTools;

import java.io.*;

/**
 * @author Jiang Zaiming
 * @date 2020/6/16 4:29 下午
 */
@RestController
@RequestMapping("/test")
public class VoiceController {

    @Autowired
    private RasrRequestSample rasrRequestSample;

    @GetMapping("/test1")
    public String getVoiceId() {
        return StringTools.getRandomString(16);
    }


    @PostMapping("/test2")
    public void test(@RequestParam("voiceId") String voiceId,
                     @RequestParam("file") MultipartFile file) throws IOException {
        RequestSender requestSender = new RequestSender();

//        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test_wav/8k/8k.wav");
//        InputStream inputStream = this.getClass().getResourceAsStream("/test_wav/8k/8k.wav");
//        ClassPathResource classPathResource = new ClassPathResource("/test_wav/8k/8k.wav");
//        InputStream inputStream =classPathResource.getInputStream();
//        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test_wav/8k/8k.wav");

//        byte[] content = ByteUtils.inputStream2ByteArray(inputStream);
//        File file = ResourceUtils.getFile("classpath:test_wav/8k/8k.wav");
//        InputStream inputStream = new FileInputStream(file);

//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/Users/jiangzaiming/project/java_realtime_asr_sdk/java_realtime_asr_sdk/test_wav/8k/a.txt");
//        File file = new File("/Users/jiangzaiming/project/java_realtime_asr_sdk/java_realtime_asr_sdk/test_wav/8k/a.txt");

//        File file = new File("/Users/jiangzaiming/project/java_realtime_asr_sdk/java_realtime_asr_sdk/test_wav/8k/8k_19s.wav");
//        System.out.println(file.getName());
//        FileInputStream inputStream = new FileInputStream(file);
        long size = file.getSize();
        System.out.println("大小为：" + size);
        InputStream inputStream = file.getInputStream();
        byte[] content = ByteUtils.toByteArray(inputStream);
        VoiceResponse voiceResponse = requestSender.sendFromBytes(voiceId, content);
        System.out.println(voiceResponse);
//        rasrRequestSample.printReponse(voiceResponse);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/jiangzaiming/project/java_realtime_asr_sdk/java_realtime_asr_sdk/test_wav/8k/8k.wav");
        System.out.println(file.getName());
        FileInputStream fileInputStream = new FileInputStream(file);

        System.out.println(fileInputStream);
    }


}
