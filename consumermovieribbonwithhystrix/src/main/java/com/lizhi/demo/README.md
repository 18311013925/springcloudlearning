
HystrixCacheController 测试Hystrix 请求缓存
    
    在同一个请求中，多次调用服务消费者，是可以实现请求缓存的，但是从浏览器，
    每次刷新调用，调用多次，每次都是会请求服务消费者，没有起到缓存的作用，
    也没有搞清楚，
 
 
 RibbonHystrixContrixController 是hystrix 一种断路器实现服务容错保护的；服务熔断，服务降级，
    
    添加了几种实现服务降级的方式，注解方式@HystrixCommand，继承HystrixCommand 命令方式(UserCommand) 以及同步调用、异步调用的方式，