# halo-log
## 介绍

halo-log用于记录监控用户行为的日志服务。

## 开始

### 基本配置

```yaml
server:
  port: 8899
spring:
  application:
    name: example-app
  zipkin:
    base-url: 10.10.71.173:9411
    sender:
      type: kafka
    service:
      name: ${spring.application.name}
  sleuth:
    sampler:
      probability: 1.0
  kafka:
    bootstrap-servers: 10.10.71.173:9092
  main:
    allow-bean-definition-overriding: true
  profiles:
    active: dev

halo:
  log:
    enable: true

```

### 编写日志拦截器

只能存在一个，请勿在容器中多个AbstractLogInterceptor的子类

```java
@Component("logInterceptor")
public class LogInterceptor extends AbstractLogInterceptor {

   @Override
   public String getCurrentUserName(HttpServletRequest httpServletRequest) {
      //因为是模拟请求，所以固定是用户张三发起的请求
      return "张三";
   }

   @Override
   public String getCurrentUserNo(HttpServletRequest httpServletRequest) {
      //因为是模拟请求，所以固定是用户张三发起的请求
      return "zhangsanno";
   }

}
```

### 编写日志监听器

可以存在多个

```java
@Slf4j
@Component
public class ElasticSearchLogListener extends AbstractLogListener {

   public ElasticSearchLogListener() {
      super(new LogHandler() {
         @Override
         public void accept(LogEvent logEvent) {
            //模拟向ElasticSearch投递日志
            log.info("elasticSearch日志处理器接收到日志: LogEvent[{}]", logEvent);
         }
      });
   }

}
```

```java
@Slf4j
@Component
public class KafkaLogListener extends AbstractLogListener {

   public KafkaLogListener() {
      super(new LogHandler() {
         @Override
         public void accept(LogEvent logEvent) {
            //模拟向kafka指定topic投递日志
            log.info("kafka日志处理器接收到日志: LogEvent[{}]", logEvent);
         }
      });
   }

}
```

### 编写service

```java
@Slf4j
@Component
public class LogServiceImpl implements LogService {

   @HaloLog(action = "登录动作")
   @Override
   public String login(String param) {

      return "响应登录: param[ " + param + "]";
   }

   @HaloLog(action = "登出动作")
   @Override
   public String logout() {

      return "响应登出";
   }

   @HaloLog(action = "动作一")
   @Override
   public String actionOne() {
      return "响应动作1";
   }

   @HaloLog(action = "动作二")
   @Override
   public String actionTwo(String param1, String param2, String param3) {
      return "响应动作2: " + param1 + "," + param2 + "," + param3;
   }

   @HaloLog(action = "动作三")
   @Override
   public void actionThree() {

   }

   @HaloLog(action = "动作四")
   @Override
   public void actionFour(String param1, String param2) {

   }

   @HaloLog(action = "动作五")
   @Override
   public String actionFive(String param) {
      throw new RuntimeException("模拟发生异常情况");
   }

}
```

### 编写controller

```java
@SpringBootApplication
@RestController
@RequestMapping("/example")
@Slf4j
public class App {

   public static void main(String[] args) {
      SpringApplication.run(App.class, args);
   }

   @Autowired
   private LogService logService;

   @GetMapping("/get/1")
   public void get1() {
      logService.login("aaaaaa");
   }

   @GetMapping("/get/2")
   public void get2() {
      logService.logout();
   }

   @GetMapping("/get/3")
   public void get3() {
      logService.actionOne();
   }

   @GetMapping("/get/4")
   public void get4() {
      logService.actionTwo("1", "2", "3");
   }

   @GetMapping("/get/5")
   public void get5() {
      logService.actionThree();
   }

   @GetMapping("/get/6")
   public void get6() {
      logService.actionFour("a", "b");
   }

   @GetMapping("/get/7")
   public void get7() {
      logService.actionFive("xxxxx");
   }

}
```

### 测试是否正常运行

#### 1、运行请求方法一

![image-20210328214459919](https://raw.githubusercontent.com/halomzh/pic/master/20210328214501.png)

#### 2、查看请求链路获取，traceId

![image-20210414103543313](https://raw.githubusercontent.com/halomzh/pic/master/20210414103551.png)

![image-20210414103804336](https://raw.githubusercontent.com/halomzh/pic/master/20210414103806.png)

#### 3、根据traceId，到kibana查看存储在elasticsearch的日志

![image-20210414104018653](https://raw.githubusercontent.com/halomzh/pic/master/20210414104021.png)