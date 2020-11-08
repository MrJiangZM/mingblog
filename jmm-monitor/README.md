jvm 的监控
jconsole
    https://blog.csdn.net/liqi_q/article/details/80420854

jps
    https://blog.csdn.net/zjcjava/article/details/78784641
    
    
    
jinfo(Configuration Info for Java)：JVM配置信息工具
    可以输出并修改运行时的java 进程的opts。用处比较简单，用于输出JAVA系统参数及命令行参数
    
    
stack(Stack Trace for Java)：JVM堆栈跟踪工具


jstat(JVM statistics Monitoriing Tool)：JVM统计信息监视工具
    对Java应用程序的资源和性能进行实时的命令行的监控，包括了对Heap size和垃圾回收状况的监控


jmap( Memory Map for Java)：JVM内存映像工具
    打印出某个java进程（使用pid）内存内的所有‘对象’的情况（如：产生那些对象，及其数量）
    生成的文件可以使用jhat工具进行分析，在OOM（内存溢出）时，分析大对象，非常有用
    通过使用如下参数启动JVM，也可以获取到dump文件：
    -XX:+HeapDumpOnOutOfMemoryError
    -XX:HeapDumpPath=./java_pid.hprof
    在jvm发生内存溢出时生成内存映像文件

jhat(JVM Heap Analysis Tool)：JVM堆转储快照分析工具

MAT(Memory Analyzer Tool)：一个基于Eclipse的内存分析工

图形化监控工具
在JDK安装目录bin下面有两个可视化监控工具
    1. JConsole(Java Monitoring and Management Console) 基于JMX的可视化管理工具。
    2. VisualVM(All-in-one Java Troubleshooting Tool)随JDK发布的最强大的运行监视和故障处理程序。
        推荐使用VisualVM，他有很多插件，可以更方便的监控运行时JVM

VisualVM  使用
    https://blog.csdn.net/lijie1010/article/details/78805837
    
    
