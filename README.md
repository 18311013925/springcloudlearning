这里只做服务的简单介绍
peer1 peer2 是注册中心，实现了服务的高可用 访问地址：http://peer1:8762/

provideruser 服务提供者，测试集群情况，需要先修改配置文件的端口，application.yml

服务消费者
    
    consumermovie 是服务消费者，使用ribbon 实现客户端负载均衡，是通过 RestTemplate 实现服务间的访问的
    consumermoviefeign 服务消费者，使用feign 实现客户端负载均衡

熔断器

    Hystrix:
        consumermovieribbonwithhystrix
        consumermoviefeignwithhystrixstream
    
    Hystrix Dashboard:
        hystrixdashboard
    Turbin:
        hystrixturbine
    
    
本地的host 文件配置

    ##
    # Host Database
    #
    # localhost is used to configure the loopback interface
    # when the system is booting.  Do not change this entry.
    ##
    127.0.0.1       localhost peer1 peer2 mac1 mac2 config-server gateway
    255.255.255.255 broadcasthost
    ::1             localhost
    0.0.0.0 account.jetbrains.com
    103.235.225.42 package.registry
    nexus.weibangong.site nexus.weibangong.me
    103.235.226.57 nexus.weibangong.me
    weibangong.site  weibangong.me
    0.0.0.0 account.jetbrains.com
    ~