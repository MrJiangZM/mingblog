//package com.ming.blog.canary;
//
//import com.google.common.collect.Sets;
//import org.springframework.util.CollectionUtils;
//import java.util.HashSet;
//import java.util.Set;
//
//public final class CanaryServerHelper {
//
//    private CanaryServerHelper() {
//    }
//
//    private static ThreadLocal<CanaryServerInfo> threadLocal = new ThreadLocal<>();
//
//    public static Set<String> getServerVersions() {
//        CanaryServerInfo canaryServerInfo = threadLocal.get();
//        canaryServerInfo = canaryServerInfo == null ? new CanaryServerInfo(new HashSet<>()) : canaryServerInfo;
//        return canaryServerInfo.getVersion() == null ? new HashSet<>() : canaryServerInfo.getVersion();
//    }
//
//    public static void setServerVersions(Set<String> versions) {
//        threadLocal.set(new CanaryServerInfo(versions));
//    }
//
//    public static void addServerVersion(String version) {
//        CanaryServerInfo canaryServerInfo = threadLocal.get();
//        if (canaryServerInfo == null) {
//            canaryServerInfo = new CanaryServerInfo(Sets.newHashSet(version));
//            threadLocal.set(canaryServerInfo);
//            return;
//        }
//        Set<String> versions = CollectionUtils.isEmpty(canaryServerInfo.getVersion())
//                ? new HashSet<>() : canaryServerInfo.getVersion();
//        versions.add(version);
//        canaryServerInfo.setVersion(versions);
//    }
//
//    public static void removeAll() {
//        threadLocal.remove();
//    }
//
//}