quartz 问题

    单点高可用  需要使用分布式定时任务   几种实现方式暂不不去考虑
    动态配置
    
    
    {
          "jobName": "test01",
          "jobGroup": "test",
          "description": "测试任务",
          "jobClassName": "com.itstyle.quartz.job.ChickenJob",
          "cronExpression": "*/1 * * * * ?",
          "triggerName": "triggertest01",
          "triggerState": "ACQUIRED",
          "oldJobName": "test01",  com.blog.quartz.job.ChickenJob
          "oldJobGroup": "test"
        }