//package com.ming.blog.mq.event.processor;
//
//import com.alibaba.fastjson.JSONObject;
//import com...Const;
//import com...enums.EventTypeEnum;
//import com...event.base.BaseFunctionProcessor;
//import com...event.function.EventType;
//import com...event.param.ServiceAlertPublishParam;
//import com...pojo.CompanyShops;
//import com...pojo.alert.UrgentServiceAlertPo;
//import com...service.CompanyShopService;
//import com...service.UserRoleService;
//import com...service.alert.ServiceAlertSendService;
//import com...util.DateUtil;
//import com...util.PageHelper;
//import com...util.Utils;
//import com...websocket.AmrMapWS;
//import com...websocket.AmrUrgentListWS;
//import com...websocket.CheckAlertWS;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import javax.websocket.Session;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.stream.Collectors;
//
///**
// * @author jiangzaiming
// */
//@Slf4j
//@Component
//public class AmrProcessor extends BaseFunctionProcessor {
//    @Resource
//    private CompanyShopService shopService;
//    @Resource
//    private UserRoleService userRoleService;
//    @Resource
//    private ServiceAlertSendService serviceAlertSendService;
//
//    @EventType(type = EventTypeEnum.SERVICE_ALERT_PUBLISH_SHOP, timeout = 60 * 60 * 1000L)
//    public void map(ServiceAlertPublishParam param) {
//        List<Long> users = userRoleService.queryAmrUserIdByShopId(param.getShopId());
//        if (!CollectionUtils.isEmpty(users)) {
//            ConcurrentHashMap<Long, Session> maps = AmrMapWS.sessionMap;
//            if (!CollectionUtils.isEmpty(maps)) {
//                for (Long user : users) {
//                    Session postman = maps.get(user);
//                    if (postman != null) {
//                        try {
//                            List<CompanyShops> shops =
//                                    shopService.findByIdIn(userRoleService.getAmrShop(user)).stream().filter(s -> s.getLatitude() != null && s.getLongitude() != null).collect(Collectors.toList());
//                            long now = System.currentTimeMillis();
//                            long zero = DateUtil.getZero(now);
//                            Map<String, ArrayList<HashMap<String, Object>>> anchor = new HashMap<>(Utils.initMapCapacity(shops.size()));
//                            for (CompanyShops shop : shops) {
//                                String key = shop.getLatitude() + Const.COMMA + shop.getLongitude();
//                                HashMap<String, Object> map = new HashMap<String, Object>(Utils.initMapCapacity(3)) {{
//                                    put("id", shop.getId());
//                                    put("name", shop.getName());
//                                    put("count", serviceAlertSendService.count(shop.getId(), null, zero, zero + DateUtil.DAY - 1));
//                                }};
//                                if (anchor.containsKey(key)) {
//                                    ArrayList<HashMap<String, Object>> list = anchor.get(key);
//                                    list.add(map);
//                                    anchor.put(key, list);
//                                } else {
//                                    anchor.put(key, new ArrayList<HashMap<String, Object>>() {{
//                                        add(map);
//                                    }});
//                                }
//                            }
//                            log.info("push message user:{}, sessionId:{}", user, postman.getId());
//                            postman.getBasicRemote().sendText(JSONObject.toJSONString(new HashMap<String, Object>(Utils.initMapCapacity(3)) {{
//                                put("shop", shops.size());
//                                put("alert", serviceAlertSendService.countAllAlert(shops.stream().map(CompanyShops::getId).collect(Collectors.toList()), 0L, now, null));
//                                put("map", anchor);
//                            }}));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @EventType(type = EventTypeEnum.SERVICE_ALERT_PUBLISH, timeout = 60 * 60 * 1000L)
//    public void servicePublish(ServiceAlertPublishParam param) {
//        log.info("收到报警下发事件，param{}", param);
//        List<Long> users = userRoleService.queryAmrUserIdByShopId(param.getShopId());
//        if (!CollectionUtils.isEmpty(users)) {
//            ConcurrentHashMap<Long, AmrUrgentListWS> sessionMap = AmrUrgentListWS.sessionMap;
//            if (!CollectionUtils.isEmpty(sessionMap)) {
//                for (Long user : users) {
//                    AmrUrgentListWS session = sessionMap.get(user);
//                    if (session != null) {
//                        try {
//                            PageHelper<UrgentServiceAlertPo> pageHelper = serviceAlertSendService.queryCurrentDayUrgentAlertListWithPage(user, 1, 1000);
//                            session.sendMessage(JSONObject.toJSONString(pageHelper));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @EventType(type = EventTypeEnum.CHECK_ALERT, timeout = 60 * 60 * 1000L)
//    public void checkAlertMsg(Integer alertType) {
//        log.info("收到审核报警下发事件");
//        ConcurrentHashMap<Long, CheckAlertWS> sessionMap = CheckAlertWS.sessionMap;
//        if (!CollectionUtils.isEmpty(sessionMap)) {
//            sessionMap.values().stream().forEach(n -> {
//                try {
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("alertType", alertType);
//                    n.sendMessage(jsonObject);
//                } catch (IOException e) {
//                    log.error("{}", e);
//                }
//            });
//
//        }
//    }
//}
