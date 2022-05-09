//package com.ming.blog.canary;
//
//import com.netflix.loadbalancer.AbstractServerPredicate;
//import com.netflix.loadbalancer.PredicateKey;
//import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
//import org.apache.commons.lang3.StringUtils;
//import java.util.Map;
//
//public class MetadataDefaultPredicate extends AbstractServerPredicate {
//
//    @Override
//    public boolean apply(PredicateKey input) {
//        return input != null
//                && input.getServer() instanceof DiscoveryEnabledServer
//                && apply((DiscoveryEnabledServer) input.getServer());
//    }
//
//    /**
//     * 如果寻找对应灰度版本的服务没有找到，则寻找default
//     */
//    private boolean apply(DiscoveryEnabledServer server) {
//
//        // 服务端是否是灰度
//        Map<String, String> metadata = server.getInstanceInfo().getMetadata();
//        String currentVer = metadata.get("version");
//        return StringUtils.isBlank(currentVer) || StringUtils.equalsIgnoreCase(currentVer, "default");
//    }
//
//}
