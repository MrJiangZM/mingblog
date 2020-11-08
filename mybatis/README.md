1. 多数据源分包配置很简单，见代码就可以   
    ok

2. 有一个大坑!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Bean的注解中如果指定名称千万不能有空格啥的，否则不能注入到IOC容器中
    ！！！！！！  因为这个问题查了好久好久，妹的很坑，这种问题也不容易找
    ok

3. 配置的时候要严格按照对应的字符串，url,否则不行
    ok

4. druid的连接池监控的UI界面
    druid 连接池的配置
        https://www.cnblogs.com/shyroke/p/8045077.html
        https://blog.csdn.net/Shangshan_Ruohe/article/details/82113872
        https://blog.csdn.net/yzh_1346983557/article/details/88547234
    log4j 的使用
        https://blog.csdn.net/evalsys/article/details/81031657
    log4j 日志打印配置
        https://blog.csdn.net/qq_36370294/article/details/81535532
        
        ok
    需要注意的是每个数据源都要指定stat,wall,log4j
    
5. 注解分包下次实现
    配置上，给每个线程去设置数据源，然后通过反射得到每个的数据源
    
    注解使用的数据源配置
    事务配置  ！！！！！！！！！！！！！  
        这个是大坑    联合事务根本不管用   第一个事务时惯用的，第二个就不行了，可能和线程有关，明天再看看！！！！！！！！！！
    threadLocal的使用
    配置mapper.xml
    aop的使用
  
6. jpa 完成多数据源配置与事务管理
    github地址
    https://github.com/MrCoderStack/SpringBootDemo/blob/master/sb-jpa-multidb/src/main/java/com/mrcoder/sbjpamultidb/config/MasterConfig.java

    https://www.cnblogs.com/eagle6688/p/9557620.html   springboot2 整合druid多数据源
    https://blog.csdn.net/u014229652/article/details/81781921
    
    springboot 2.0 以前和springboot2.1以后方式不同
    
    @id注解
    类不能写错，具体的DataSourceConfig见代码
    
    Spring Data Redis - Could not safely identify store assignment for repositor
    关闭对应的redis检查    需要弄明白这个具体是干嘛的
        spring:
            data:
              redis:
                repositories:
                  enabled: false
    
    
      

6. jta事务完成
    jta的事务控制    在mybatis中使用
    
    https://www.cnblogs.com/lfm601508022/p/InvalidBoundStatement.html  // xml文件没有编译成功
    
    Cannot use both: sqlSessionTemplate and sqlSessionFactory together. sqlSessionFactory is ignored.
    
    https://www.cnblogs.com/tusheng/p/9077309.html   // 带有druid监控
    https://blog.csdn.net/ccllcaochong1/article/details/79686539 
    
    https://blog.csdn.net/m0_37809146/article/details/86673372  // 看这个吧
    使用jta时数据库执行时卡住，得不到结果，也看不到进程是否死了
        这个问题解决，应该是配置文件中的数据库的配置信息没有加载到代码中，尤其是连接之类的没有注入进去！！！！！！！！！！！！
    https://blog.csdn.net/jy02268879/article/details/84398657
    
    https://github.com/MrJiangZM/spring-samples-for-all/tree/master/spring-boot
    事务还是不行，所以还要重新弄一下，暂时放置这里，往前走
    
7. 通过aop注解完成事务控制
    https://www.cnblogs.com/xiaofengfeng/p/9049363.html