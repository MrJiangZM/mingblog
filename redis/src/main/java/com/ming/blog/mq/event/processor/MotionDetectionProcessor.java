//package com.ming.blog.mq.event.processor;
//
//import com...enums.EventTypeEnum;
//import com...event.base.BaseFunctionProcessor;
//import com...event.function.EventType;
//import com...event.param.MotionDetectionTaskParam;
//import com...pojo.smart_device.SmartDeviceMotionDetection;
//import com...service.smart_device.SmartDeviceMotionDetectionService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Slf4j
//@Component
//public class MotionDetectionProcessor extends BaseFunctionProcessor {
//
//    @Resource
//    private SmartDeviceMotionDetectionService smartDeviceMotionDetectionService;
//
//    @EventType(type = EventTypeEnum.MOTION_DETECTION_TASK)
//    public void store(MotionDetectionTaskParam param) {
//        SmartDeviceMotionDetection motionDetection = SmartDeviceMotionDetection.builder()
//                .taskId(param.getTaskId())
//                .channelNo(param.getChannelNo())
//                .sn(param.getSn())
//                .url(param.getUrl())
//                .startAt(param.getStartAt())
//                .endAt(param.getEndAt())
//                .build();
//        smartDeviceMotionDetectionService.add(motionDetection);
//    }
//}
