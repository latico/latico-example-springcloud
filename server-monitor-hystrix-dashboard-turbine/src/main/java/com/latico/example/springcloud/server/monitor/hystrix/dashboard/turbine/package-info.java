/**
 * <PRE>
 *     查看是否监控成功：http://localhost:8400/turbine.stream
 *
 *     用界面图形化查看：
 *     在页面http://localhost:8400/hystrix
 *     然后输入监控流，点击
 *     http://localhost:8400/turbine.stream
 *
 turbine.appConfig定义了要监控的服务，这里是我们在前面章节创建的service-feign和sercice-ribbon; aggregator.clusterConfig定义了聚合方式， 此处为default.

 turbine.appConfig ：配置Eureka中的serviceId列表，表明监控哪些服务

 turbine.aggregator.clusterConfig ：指定聚合哪些集群，多个使用”,”分割，默认为default。可使用http://.../turbine.stream?cluster={clusterConfig之一}访问

 turbine.clusterNameExpression ：指定集群名称，可以是三种类型的值

 - 默认表达式为appName；此时turbine.aggregator.clusterConfig需要配置想要监控的应用名称；

 - 当为default时，turbine.aggregator.clusterConfig可以不写，因为默认就是default；

 - 当为metadata[‘cluster’]时，假设想要监控的应用配置了eureka.instance.metadata-map.cluster: ABC，则需要配置，同时turbine.aggregator.clusterConfig: ABC

 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-17 16:46
 * @Version: 1.0
 */
package com.latico.example.springcloud.server.monitor.hystrix.dashboard.turbine;