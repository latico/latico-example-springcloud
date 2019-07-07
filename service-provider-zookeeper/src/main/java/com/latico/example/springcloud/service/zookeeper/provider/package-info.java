/**
 * <PRE>
 * 连接zookeeper注册中心方式的服务提供者
 *
 1、如果发生以下错误，那么就是因为springcloud集成的zookeeper客户端版本过高导致的，两种解决方案，一种是升级zookeeper版本，二是排除掉springcloud自带的zookeeper客户端，使用我们自己依赖进来的客户端。
 当前使用springcloud的版本是：Greenwich.RELEASE，集成的是zookeeper-3.5.4-beta，经过测试，目前使用第二种方式才行；
 Could not re-register instances after reconnection
 org.apache.zookeeper.KeeperException$UnimplementedException: KeeperErrorCode = Unimplemented for /services/service-provider-zookeeper/dc395ae6-12c7-4883-938b-a16c3946dcd3

 <dependency>
 <groupId>org.springframework.cloud</groupId>
 <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
 <!-- 排除掉自带的Zookeeper客户端 --
 <exclusions>
 <exclusion>
 <groupId>org.apache.zookeeper</groupId>
 <artifactId>zookeeper</artifactId>
 </exclusion>
 </exclusions>
 </dependency>
 <dependency>
 <groupId>org.apache.zookeeper</groupId>
 <artifactId>zookeeper</artifactId>
 <!-- 与服务器安装的Zookeeper版本相同 -->
 <version>3.4.10</version>
 <exclusions>
 <exclusion>
 <groupId>org.slf4j</groupId>
 <artifactId>slf4j-log4j12</artifactId>
 </exclusion>
 </exclusions>
 </dependency>

 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-14 18:05
 * @Version: 1.0
 */
package com.latico.example.springcloud.service.zookeeper.provider;