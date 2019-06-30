一、windows下安装启动
下载地址：https://nginx.org/en/download.html

1、最简单的配置，只需要在下面节点，加上proxy_pass  http://127.0.0.1:8300;  把服务转向springcloud的网关。
location / {
            root   html;
            index  index.html index.htm;
			
			#真正服务器地址
			proxy_pass  http://127.0.0.1:8300;
        }
        
 负载均衡方式：
 #注意：经过测试，windows中upstream的名称使用下划线会导致不生效，中划线可以
    upstream server-list{
        server 127.0.0.1:9001;
        server 127.0.0.1:9002;
        server 127.0.0.1:9003;
    }
    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
        
    
        location / {
            proxy_pass http://server-list;
            root   html;
            index  index.html index.htm;						
    			
    }
        
  在
  启动nginx.exe目录，执行如下命令
  nginx
  停止/重启
  nginx -s stop(quit、reload)
  命令帮助
  nginx -h
  验证配置文件
  nginx -t
  配置文件
  nginx/conf/nginx.conf
        
二、linux下安装启动

[root@localhost src]# wget http://nginx.org/download/nginx-1.10.2.tar.gz
省略安装内容...

//ssh已安装，可以跳过
[root@localhost src]# wget http://www.openssl.org/source/openssl-fips-2.0.10.tar.gz

省略安装内容...
[root@localhost src]# wget http://zlib.net/zlib-1.2.11.tar.gz

省略安装内容...
[root@localhost src]# wget ftp://ftp.csx.cam.ac.uk/pub/software/programming/pcre/pcre-8.40.tar.gz
省略安装内容...

安装c++编译环境，如已安装可略过

[root@localhost src]# yum install gcc-c++
省略安装内容...
期间会有确认提示输入y回车
Is this ok [y/N]:y
省略安装内容...

openssl安装（已安装，跳过）

[root@localhost src]# tar zxvf openssl-fips-2.0.10.tar.gz
省略安装内容...
[root@localhost src]# cd openssl-fips-2.0.10
[root@localhost openssl-fips-2.0.10]# ./config && make && make install
省略安装内容...
pcre安装

[root@localhost src]# tar zxvf pcre-8.40.tar.gz
省略安装内容...
[root@localhost src]# cd pcre-8.40
[root@localhost pcre-8.40]# ./configure && make && make install
省略安装内容...
zlib安装

[root@localhost src]# tar zxvf zlib-1.2.11.tar.gz
省略安装内容...
[root@localhost src]# cd zlib-1.2.11
[root@localhost zlib-1.2.11]# ./configure && make && make install
省略安装内容...
nginx安装

[root@localhost src]# tar zxvf nginx-1.10.2.tar.gz
省略安装内容...
[root@localhost src]# cd nginx-1.10.2
[root@localhost nginx-1.10.2]# ./configure && make && make install
省略安装内容...

先找一下nginx安装到什么位置上了
[root@localhost nginx]# whereis nginx
nginx: /usr/local/nginx
[root@localhost nginx]# 

启动Nginx
[root@localhost nginx]# sbin/nginx 
sbin/nginx: error while loading shared libraries: libpcre.so.1: cannot open shared object file: No such file or directory
[root@localhost nginx]# 

报上面的错误是因为没有快捷连接，执行如下命令：

1.用whereis libpcre.so.1命令找到libpcre.so.1在哪里
2.用ln -s /usr/local/lib/libpcre.so.1 /lib64 命令做个软连接就可以了
3.用sbin/nginx启动Nginx
4.用ps -aux | grep nginx查看状态

[root@localhost nginx]# whereis libpcre.so.1
[root@localhost nginx]# ln -s /usr/local/lib/libpcre.so.1 /lib64
32位系统执行
[root@localhost nginx]# ln -s /usr/local/lib/libpcre.so.1 /lib
64位系统
[root@localhost nginx]# ln -s /usr/local/lib/libpcre.so.1 /lib64

[root@localhost nginx]# sbin/nginx
查看进程
[root@localhost nginx]# ps -aux | grep nginx

启动
[root@localhost ~]# /usr/local/nginx/sbin/nginx
停止/重启
[root@localhost ~]# /usr/local/nginx/sbin/nginx -s stop(quit、reload)
命令帮助
[root@localhost ~]# /usr/local/nginx/sbin/nginx -h
验证配置文件
[root@localhost ~]# /usr/local/nginx/sbin/nginx -t
配置文件
[root@localhost ~]# vim /usr/local/nginx/conf/nginx.conf

配置代理转发的目标地址，跟windows的一样；
也可以配置负载均衡；


三、配置详解

upstream xxx{};upstream模块是命名一个后端服务器组，组名必须为后端服务器站点域名，内部可以写多台服务器ip和port，还可以设置跳转规则及权重等等
ip_hash;代表使用ip地址方式分配跳转后端服务器，同一ip请求每次都会访问同一台后端服务器
server;代表后端服务器地址

server{};server模块依然是接收外部请求的部分
server_name;代表外网访问域名
location / {};同样代表过滤器，用于制定不同请求的不同操作
proxy_pass;代表后端服务器组名，此组名必须为后端服务器站点域名

server_name和upstream{}的组名可以不一致，server_name是外网访问接收请求的域名，upstream{}的组名是跳转后端服务器时站点访问的域名