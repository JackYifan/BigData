# BigData
BigData learning code 
## 搭建集群
> 注意事项
- 在服务器上的`hosts`文件使用的是服务器的私有ip
```shell script
0.0.0.0         ecs-bigdatapro  ecs-bigdatapro
192.168.0.230   ecs-bigdatapro
```
**注意不能使用127.0.0.1,否则显示connect refuse**
- 在本机的`hosts`文件设置公网ip
> 常见问题的解决方案
- datanode没有启动

需要删除`hadoop`的`tmp/dfs`文件夹，重启`hadoop`服务
- 检查网络连接问题的方法
```shell script
[root@ecs-bigdatapro hadoop]# netstat -lntp
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 192.168.0.230:8020      0.0.0.0:*               LISTEN      7059/java           
```
