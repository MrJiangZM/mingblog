package sdk.aa;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jiang Zaiming
 * @date 2020/6/18 11:22 上午
 */
public interface VoiceService {

    String getVoice(String voiceId, MultipartFile file);

}
