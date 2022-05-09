//package com.ming.blog.canary;
//
//import com.google.common.base.Optional;
//import com.haodf.service.medcorp.config.CanaryTraceHelper;
//import com.netflix.loadbalancer.AbstractServerPredicate;
//import com.netflix.loadbalancer.AvailabilityPredicate;
//import com.netflix.loadbalancer.ClientConfigEnabledRoundRobinRule;
//import com.netflix.loadbalancer.CompositePredicate;
//import com.netflix.loadbalancer.ILoadBalancer;
//import com.netflix.loadbalancer.Server;
//import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
//import org.apache.commons.lang3.StringUtils;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//public class MetadataCanaryRule extends ClientConfigEnabledRoundRobinRule {
//    private final CompositePredicate predicate;
//
//    public MetadataCanaryRule() {
//        super();
//        com.haodf.service.medcorp.config.predicate.MetadataCanaryPredicate metadataCanaryPredicate = new com.haodf.service.medcorp.config.predicate.MetadataCanaryPredicate();
//        com.haodf.service.medcorp.config.predicate.MetadataDefaultPredicate metadataDefaultPredicate = new com.haodf.service.medcorp.config.predicate.MetadataDefaultPredicate();
//        AvailabilityPredicate availabilityPredicate = new AvailabilityPredicate(this, null);
//        this.predicate = this.createCompositePredicate(metadataCanaryPredicate, availabilityPredicate, metadataDefaultPredicate);
//    }
//
//    @Override
//    public Server choose(Object key) {
//        ILoadBalancer lb = getLoadBalancer();
//        this.checkCanaryServer(lb);
//        Optional<Server> server = getPredicate().chooseRoundRobinAfterFiltering(lb.getAllServers(), key);
//        if (server.isPresent()) {
//            return server.get();
//        } else {
//            return null;
//        }
//    }
//
//    public AbstractServerPredicate getPredicate() {
//        return predicate;
//    }
//
//    private CompositePredicate createCompositePredicate(AbstractServerPredicate predicate,
//                                                        AvailabilityPredicate availabilityPredicate,
//                                                        com.haodf.service.medcorp.config.predicate.MetadataDefaultPredicate metadataDefaultPredicate) {
//        return CompositePredicate.withPredicates(predicate, availabilityPredicate)
//                .addFallbackPredicate(metadataDefaultPredicate)
//                .addFallbackPredicate(AbstractServerPredicate.alwaysTrue())
//                .build();
//    }
//
//    /**
//     * 链路保存一个所有灰度版本的实例
//     *
//     * @param lb 负载均衡
//     */
//    private void checkCanaryServer(ILoadBalancer lb) {
//        boolean canary = CanaryTraceHelper.isCanary();
//        if (!canary) {
//            return;
//        }
//
//        List<Server> allServers = lb.getAllServers();
//        Set<String> serverVersions = com.haodf.service.medcorp.config.predicate.CanaryServerHelper.getServerVersions() == null
//                ? new HashSet<>() : com.haodf.service.medcorp.config.predicate.CanaryServerHelper.getServerVersions();
//        for (Server server : allServers) {
//            if (server instanceof DiscoveryEnabledServer) {
//                Map<String, String> metaData = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();
//                String ver = metaData.get("version");
//                if (StringUtils.isNotEmpty(ver)) {
//                    serverVersions.add(ver);
//                }
//            }
//        }
//    }
//}