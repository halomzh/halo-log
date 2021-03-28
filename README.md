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

halo:
  log:
    enable: true #是否开启日志服务
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

![image-20210328214459919](https://raw.githubusercontent.com/halomzh/pic/master/20210328214501.png)![image-20210328214542662](https://raw.githubusercontent.com/halomzh/pic/master/20210328214544.png)![image-20210328214617079](https://raw.githubusercontent.com/halomzh/pic/master/20210328214618.png)![image-20210328214643676](https://raw.githubusercontent.com/halomzh/pic/master/20210328214645.png)

![image-20210328214719887](https://raw.githubusercontent.com/halomzh/pic/master/20210328214721.png)

![image-20210328214742722](https://raw.githubusercontent.com/halomzh/pic/master/20210328214744.png)

![image-20210328214832993](https://raw.githubusercontent.com/halomzh/pic/master/20210328214833.png)

![image-20210328214912966](https://raw.githubusercontent.com/halomzh/pic/master/20210328214915.png)

![image-20210328214943113](https://raw.githubusercontent.com/halomzh/pic/master/20210328214943.png)

![image-20210328215009961](https://raw.githubusercontent.com/halomzh/pic/master/20210328215011.png)

![image-20210328215040059](https://raw.githubusercontent.com/halomzh/pic/master/20210328215041.png)

![image-20210328215107269](https://raw.githubusercontent.com/halomzh/pic/master/20210328215114.png)

![image-20210328215140803](https://raw.githubusercontent.com/halomzh/pic/master/20210328215141.png)

![image-20210328215211096](https://raw.githubusercontent.com/halomzh/pic/master/20210328215212.png)