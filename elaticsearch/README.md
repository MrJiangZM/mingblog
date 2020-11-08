1. 配置问题
    cluster-name
    cluster-nodes
    
2. 使用问题
    https://my.oschina.net/chuibilong/blog/1830382
    https://blog.csdn.net/a_lllk/article/details/83409880
    
    1. 复杂查询  组合查询 父子查询 权重查询 dismax查询 模糊查询 全文搜索
    2. 


3. elasticsearch的安装问题
    下一步是使用集群，搭建集群使用集群，研究集群的配置和稳定性问题
    
        启动elasticsearch-head使用
        grunt server &
    
4. logstash 的安装与使用
    这个好弄， 不涉及到集群之类的
    
    腾讯服务器内存不足，logstash 使用本地logstash操作

5. logstash的配置
    这个需要多试试学习


    
    
    input {
    	file{
    		path => "D:/test/application2.log"
    		start_position => beginning
    	}
    }
        
        
    output {
    	elasticsearch {
    		hosts => ["106.52.88.16:9200"]
    		index => "diledemo"
    		document_type => "diledemo"
    	}
    	stdout{
    		codec => rubydebug
    	}
    }

6. kibana 的安装与配置使用