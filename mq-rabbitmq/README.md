rabbitmq  在springboot中的几种方式

rabbitmq 设计默认是让每个消息只有一个消费者消费，具体保证可以查阅相关资料

直接通过queue发送消息

通过topic exchange 指定routeKey发送数据

通过direct发送数据

通过fanout发送消息