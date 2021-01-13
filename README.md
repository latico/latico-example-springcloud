### 一、springcloud是以注册中心为核心，想连接springcloud的注册中心就是使用注册中心客户端，其余的各个组件都是按需使用
======================================================================
======================================================================
### 二、项目模块的命名规则
    如果是以server-开头，那么就是springcloud的服务器端，跟业务无关，比如注册中心、配置中心等
    如果是以service-开头，那么就是业务服务提供者或者服务调用者，比如：rest接口服务提供者，rest接口服务调用者，配置服务调用者；
======================================================================
======================================================================
### 三、springcloud的角色分类（角色只是作用，一个项目模块可以使用所有角色）：

### 1、注册中心（服务注册与发现）：
    所有服务提供者的接口都可以把接口挂靠在这里，支持集群的高可用，当有客户端连接进来，注册中心会根据服务名称提供所有的接口，客户端根据服务名称加接口名称去拿所有的接口，然后可以根据负载均衡选择其中一个服务提供者去连接；
    实现组件：
    可以使用包括：
        1、Eureka(目前最流行，但是官方说2.0后不维护)；
        2、Consul(目前最强)；
        3、zookeeper(最通用，适合其他类型的项目公共的中间件)；
        
        目前建议Eureka。

### 2、服务提供者：
    注册接口到注册中心，让服务消费者使用，也可以不注册到注册中心（如果这样就失去了springcloud的核心意义）；
    实现组件：
        1、eureka；
        2、zookeeper；

### 3、服务调用者（服务消费）：
    从注册中心那里调用接口，也可以直接从服务提供者那里调用接口（如果这样就失去了springcloud的核心意义）；
    实现组件：
        1、eureka；
        2、zookeeper；

### 4、配置中心（配置管理）：
    存放和管理配置文件，目前支持：本地存储、git、svn，三种存放文件读取方式，注册到注册中心，也可以不注册到注册中心（如果这样就失去了springcloud的核心意义）；
    实现组件：
    Spring Cloud 配置中心的主流实现方式
    1、Spring cloud config
    2、Spring cloud zookeeper config
    3、apollo
   
    以下是这两者的简介
    Srping Cloud Config
    Spring cloud config就是和git（svn）集成来实现配置中心。Spring cloud config分服务端、客户端和git(svn)三部分，服务端负责将Git（SVN）中存储的配置文件发布成REST接口，客户端可以从服务端REST接口获取配置（但客户端并不能主动感知到配置的变化，从而主动去获取新的配置，这需要每个客户端通过POST方法触发各自的/refresh）。其中通过git本身的属性可以达到配置版本控制的目的。有缓存形式，先把config下载到服务端本地再提供给客户端，提高可靠性。
    Srping Cloud Zookeeper Config
    该项目通过自动配置并绑定到Spring环境，为Spring Boot应用程序提供Zookeeper集成。Zookeeper提供了一个分层命名空间，允许客户端存储任意数据，如配置数据。Spring Cloud Zookeeper Config是Config Server和Client的替代方案。
   
    两者的比较 
    Spring Cloud Config 通过文件系统，git/svn仓库来管理配置文件。包含客户端、服务端和git/svn仓库。通过git/svn特性可以达到版本控制
    
### 5、配置调用者：
    根据配置服务名称和指定要获取的配置文件，连接到注册中心拿配置文件，就跟拿本地配置文件一样，也可以连接配置中心拿（如果这样就失去了springcloud的核心意义）；
    需要使用到bootstrap.yaml配置文件，该配置文件是会影响到程序启动的配置。
    实现组件：
        1、eureka；
        2、zookeeper；
### 6、智能路由：
    主要功能是路由和过滤器。路由功能是微服务的一部分，比如／api/user映射到user服务，/api/shop映射到shop服务。zuul实现了负载均衡。以下是微服务结构中，Zuul的基本流程。在接下来的步骤中，我们来创建一个zuul服务， 将/api-feign/**映射到我们之前创建feign-service, 将/api-ribbon/**映射到之前的ribbon-service服务。
    实现组件：
    1、springcloud 1.0一般使用zuul。
    2、而2.0版本以上，建议使用Spring Cloud Gateway；
    3、也可以使用 ngineureka + nginx 

### 7、API网关：API统一入口
    API统一入口
    作用，使用注册中心管理的服务接口，调用的时候，需要传递服务名称，正常是需要使用注册中心的客户端工具，
    但是大部分外部项目，并不是springboot项目，也不想集成注册中心客户端工具，所以需要一个API网关进行代理转发，
    这样的话，调用者并不关心调用时是怎么样连接注册中心里面的服务接口，这样变得跟连接普通的HTTP接口一样的效果，就像使用springmvc提供的rest接口。
    1、springcloud 1.0一般使用zuul，
    2、而2.0版本以上，建议使用Spring Cloud Gateway；
    3、也可以使用 ngineureka + nginx 

### 8、反向代理nginx
    由于springcloud后台程序都是内网程序，包括API网关，这里我们可以使用nginx代理访问API网关，外界公网通过访问nginx来访问接口，同时，nginx也支持负载均衡等高级配置功能；

### 9、断路器
    当执行调用远程接口时，发生了异常，断路器会返回指定的方法；
    实现组件：
        1、hystrix，可以结合feign或者单独使用
        
### 10、hystrix仪表盘
     断路器指标数据监控Hystrix Dashboard工具：     Hystrix一个很重要的功能是，可以通过HystrixCommand收集相关数据指标. Hystrix Dashboard可以很高效的现实每个断路器的健康状况。
     需要客户端启动Hystrix功能，仪表盘就能读取到它的监控信息
     只能监控一个应用

### 11、Turbine
    能同时显示和管理多个hystrix仪表盘；
    Turbine是hystrix Dashboard工具的多应用监控，一个界面同时监控多个；
    
### 12、消息总线
    对于配置服务，假如配置客户端要更新配置，每个客户端都要调用一次refresh接口去配置中心刷新并拉取最新配置，
    消息总线使用消息队列的方式，然后只需要调用配置中心的刷新配置接口，订阅了消息队列的客户端就会接收到通知；
    可以使用：rabbitmq、kafka等

### 13、Stream消息驱动
    基于Redis,Rabbit,Kafka实现的消息微服务
       应用程序通过 inputs 或者 outputs 来与 Spring Cloud Stream 中binder 交互，通过我们配置来 binding ，而 Spring Cloud Stream 的 binder 负责与消息中间件交互。所以，我们只需要搞清楚如何与 Spring Cloud Stream 交互就可以方便使用消息驱动的方式。
    通过使用Spring Integration来连接消息代理中间件以实现消息事件驱动。Spring Cloud Stream 为一些供应商的消息中间件产品提供了个性化的自动化配置实现，引用了发布-订阅、消费组、分区的三个核心概念。目前仅支持Redis、RabbitMQ、Kafka。
    包括的功能：
    1、消息发送；
    2、消息接收；
    3、消息中转（指定收到某种消息后进行中转处理再发出去）；
    4、消息反馈（收到消息后通知发送者）；
    5、消息分组（同一个组的消费者只会有一个接收到，不同的组是可以消费的，同一个组内会发生竞争关系，只有其中一个可以消费。）；
    6、消息分区（指定某个分区号的进行接收，一个或者多个生产者应用实例给多个消费者应用实例发送消息并确保相同特征的数据被同一消费者实例处理）；

### 14、分布式链路追踪（sleuth）
    随着分布式系统越来越复杂，你的一个请求发过发过去，各个微服务之间的跳转，有可能某个请求某一天压力太大了，一个请求过去没响应，一个请求下去依赖了三四个服务，但是你去不知道哪一个服务出来问题，这时候我是不是需要对微服务进行追踪呀？监控一个请求的发起，从服务之间传递之间的过程，我最好记录一下，记录每一个的耗时多久，一旦出了问题，我们就可以针对性的进行优化，是要增加节点，减轻压力，还是服务继续拆分，让逻辑更加简单点呢？这时候springcloud-sleuth集成zipkin能帮我们解决这些服务追踪问题。
    Spring Cloud Sleuth 在整合 Zipkin 时，不仅实现了以 HTTP 的方式收集跟踪信息，还实现了通过消息中间件来对跟踪信息进行异步收集的封装。在上一篇 Spring Cloud 应用篇 之 Spring Cloud Sleuth + Zipkin（一）链路监控 中，我们知道了 Spring Boot 2.0 之后 Zipkin 不再推荐我们来自定义 Server 端了
    具体实现组件：zipkin

### 15、安全认证
    Spring Cloud Security OAuth2 是 Spring 对 OAuth2 的开源实现，优点是能与Spring Cloud技术栈无缝集成，如果全部使用默认配置，开发者只需要添加注解就能完成 OAuth2 授权服务的搭建。
          
### 16、数据访问
    spring-data

                
======================================================================
======================================================================
### 四、各组件配置使用运行流程：
    1、请求统一通过API网关（Zuul）来访问内部服务.
    2、网关接收到请求后，从注册中心（Eureka）获取可用服务
    3、由Ribbon进行均衡负载后，分发到后端具体实例
    4、微服务之间通过Feign进行通信处理业务
    5、Hystrix负责处理服务超时熔断
    6、Turbine监控服务间的调用和熔断相关指标
======================================================================
======================================================================
### Spring Cloud共集成了19个子项目，里面都包含一个或者多个第三方的组件或者框架！

### Spring Cloud 工具框架

    1、Spring Cloud Config 配置中心，利用git集中管理程序的配置。
    2、Spring Cloud Netflix 集成众多Netflix的开源软件
    3、Spring Cloud Bus 消息总线，利用分布式消息将服务和服务实例连接在一起，用于在一个集群中传播状态的变化
    4、Spring Cloud for Cloud Foundry 利用Pivotal Cloudfoundry集成你的应用程序
    5、Spring Cloud Cloud Foundry Service Broker 为建立管理云托管服务的服务代理提供了一个起点。
    6、Spring Cloud Cluster 基于Zookeeper, Redis, Hazelcast, Consul实现的领导选举和平民状态模式的抽象和实现。
    7、Spring Cloud Consul 基于Hashicorp Consul实现的服务发现和配置管理。
    8、Spring Cloud Security 在Zuul代理中为OAuth2 rest客户端和认证头转发提供负载均衡
    9、Spring Cloud Sleuth SpringCloud应用的分布式追踪系统，和Zipkin，HTrace，ELK兼容。
    10、Spring Cloud Data Flow 一个云本地程序和操作模型，组成数据微服务在一个结构化的平台上。
    11、Spring Cloud Stream 基于Redis,Rabbit,Kafka实现的消息微服务，简单声明模型用以在Spring Cloud应用中收发消息。
    12、Spring Cloud Stream App Starters 基于Spring Boot为外部系统提供spring的集成
    13、Spring Cloud Task 短生命周期的微服务，为SpringBooot应用简单声明添加功能和非功能特性。
    14、Spring Cloud Task App Starters
    15、Spring Cloud Zookeeper 服务发现和配置管理基于Apache Zookeeper。
    16、Spring Cloud for Amazon Web Services 快速和亚马逊网络服务集成。
    17、Spring Cloud Connectors 便于PaaS应用在各种平台上连接到后端像数据库和消息经纪服务。
    18、Spring Cloud Starters （项目已经终止并且在Angel.SR2后的版本和其他项目合并）
    19、Spring Cloud CLI 插件用Groovy快速的创建Spring Cloud组件应用。


    Spring Cloud Gateway 作为API 网关（不是使用zuul作为网关），关于Spring Cloud Gateway和zuul的性能比较本文不再赘述，基本可以肯定Spring Cloud Finchley版本的gateway比zuul 1.x系列的性能和功能整体要好。
    
    特别提醒：Spring Cloud Finchley版本中，即使你引入了spring-cloud-starter-netflix-zuul，也不是2.0版本的zuul
    
    负载均衡ribbon
        一般跟RestTemplate结合使用，达到负载均衡；
        
        
        从Spring Cloud Edgware开始，@EnableDiscoveryClient 或@EnableEurekaClient 可省略。只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上。
        
        @EnableDiscoveryClient和@EnableEurekaClient共同点就是：都是能够让注册中心能够发现，扫描到改服务。
        
    不同点：@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心