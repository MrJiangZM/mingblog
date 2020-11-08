package sdk.aa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sdk.controller.RasrAsynRequestSample;
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
@RequestMapping("/voice")
public class VoiceController {

    @Autowired
    private VoiceService voiceService;

    @GetMapping("/getVoiceId")
    public String getVoiceId() {
        String voiceId = StringTools.getRandomString(16);
        voiceCache.put(voiceId, 1 + "", StringUtils.EMPTY);
        return voiceId;
    }

    @GetMapping("/deleteVoiceId")
    public String deleteVoiceId(String voiceId) {
        voiceCache.drop(voiceId);
        return voiceId;
    }

    @PostMapping("/getTextData16k")
    public void getTextData16k(@RequestParam("voiceId") String voiceId,
                               @RequestParam("file") MultipartFile file) throws IOException {


        RasrAsynRequestSample rasrRequestSample = new RasrAsynRequestSample();
        String[] arg = new String[0];
        rasrRequestSample.setArguments(arg);
        rasrRequestSample.start();

//        sleepSomeTime(600000); // 10分钟后停止示例程序。
        rasrRequestSample.stop();
//        System.exit(0);
    }


    @PostMapping("/getTextData")
    public void test(@RequestParam("voiceId") String voiceId,
                     @RequestParam("file") MultipartFile file) throws IOException {
        RequestSender requestSender = new RequestSender();
        long size = file.getSize();
        System.out.println("大小为：" + size);
        InputStream inputStream = file.getInputStream();
        byte[] content = ByteUtils.toByteArray(inputStream);
        VoiceResponse voiceResponse = requestSender.sendFromBytes(voiceId, content);
        System.out.println(voiceResponse);


//        File file1 = new File("/Users/jiangzaiming/project/java_realtime_asr_sdk/java_realtime_asr_sdk/test_wav/8k/8k_19s.wav");
//        System.out.println(file1.getName());
//        FileInputStream inputStream = new FileInputStream(file1);
//        byte[] content = ByteUtils.toByteArray(inputStream);
//        VoiceResponse voiceResponse = requestSender.sendFromBytes(voiceId, content);
        // 获取到多次的数据文件后进行拼接成最后的一句话
//        voiceCache.
    }

    @PostMapping("/getVoice")
    public String getVoice(@RequestParam("voiceId") String voiceId,
                           @RequestParam("file") MultipartFile file) {
        return voiceService.getVoice(voiceId, file);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/jiangzaiming/project/java_realtime_asr_sdk/java_realtime_asr_sdk/test_wav/8k/8k.wav");
        System.out.println(file.getName());
        FileInputStream fileInputStream = new FileInputStream(file);

        System.out.println(fileInputStream);
    }


}
