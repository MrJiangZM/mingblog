//package com.ming.blog.canary;
//
//import com.haodf.service.medcorp.config.CanaryTraceHelper;
//import com.netflix.loadbalancer.AbstractServerPredicate;
//import com.netflix.loadbalancer.PredicateKey;
//import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
//import org.apache.commons.lang3.StringUtils;
//import java.util.Map;
//import java.util.Objects;
//
//public class MetadataCanaryPredicate extends AbstractServerPredicate {
//
//    @Override
//    public boolean apply(PredicateKey input) {
//        return input != null
//                && input.getServer() instanceof DiscoveryEnabledServer
//                && apply((DiscoveryEnabledServer) input.getServer());
//    }
//
//    /**
//     * 灰度情况, 请求版本命中当前版本则选中
//     * 非灰度情况, 当前版本和稳定版本一致则选中
//     */
//    private boolean apply(DiscoveryEnabledServer server) {
//
//        // 链路上的是否是灰度
//        boolean canary = CanaryTraceHelper.isCanary();
//        String traceVer = CanaryTraceHelper.getReleaseVer();
//
//        // 服务端是否是灰度
//        Map<String, String> metadata = server.getInstanceInfo().getMetadata();
//        String currentVer = metadata.get("version");
//
//        if (canary) {
//            // 判断当前实例是否匹配灰度
//            return StringUtils.equalsIgnoreCase(currentVer, traceVer);
//        } else {
//            // 无灰度信息
//            return StringUtils.isEmpty(currentVer) || "default".equals(currentVer) || Objects.equals(currentVer, traceVer);
//        }
//    }
//
//}
