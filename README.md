# _springBoot 高级实战_ 


**企业级开发主要内容** 
1.  企业级开发
2.  开发和测试
3.	应用监控
4.	分布式系统开发

**SpringBoot的开发部署和测试主要内容** 
1.	开发热部署
2.	常规部署
3.	云部署-基于Docker的部署
4.	SpringBoot的测试	



**参考书籍**

1.	JavaEE开发的颠覆者 Spring Boot实战  完整版
	
## 企业级开发

### 安全控制Spring Security(注：此功能还在修复中，目前存在问题)


#### 实战（功能在修复中）
---
实战内容：<br/>
&nbsp;&nbsp;&nbsp;&nbsp;1.	认证授权：springboot + Spring Security <br/>
&nbsp;&nbsp;&nbsp;&nbsp;2.	获取数据：Spring Data JPA <br/>
&nbsp;&nbsp;&nbsp;&nbsp;3.	页面标签：Thymeleaf 对spring Security的支持
---

1.	引入依赖

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
	<dependency>
		<groupId>com.oracle</groupId>
		<artifactId>ojdbc6</artifactId>
		<version>11.2.0.1.0</version>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
		<optional>true</optional>
	</dependency>
	<dependency>
		<groupId>org.thymeleaf.extras</groupId>
		<artifactId>thymeleaf-extras-springsecurity4</artifactId>
	</dependency>
```



### 批处理Spring Batch


#### 实战

1.	依赖

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-batch</artifactId>
		<exclusions>
			<exclusion>
				<groupId>org.hsqldb</groupId>
				<artifactId>hsqldb</artifactId>
			</exclusion>
		</exclusions>
	</dependency>
	<!-- hibernate-validator:数据校验 -->
	<dependency>
	    <groupId>org.hibernate</groupId>
	    <artifactId>hibernate-validator</artifactId>
	    <version>6.0.1.Final</version>
	</dependency>
```

2. 实战结果：
Caused by: java.sql.SQLSyntaxErrorException: ORA-00942: 表或视图不存在

目前还不能解决，正在解决中！


### 异步消息


#### ActiveMq(JMS)实战

1.	安装activeMq

	访问：http://localhost:8161  用户名/密码  admin/admin
	
2.	内嵌activemQ ---依赖

```
	<!-- jms -->
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-jms</artifactId>
	</dependency>
	<!-- activeMQ -->
	<dependency>
		<groupId>org.apache.activemq</groupId>
		<artifactId>activemq-client</artifactId>
	</dependency>
```

**注意** 
	实际开发中，消息的发送和接受收拾分开的，此处为了演示方便，将其放在一个程序中。
	
#### RabbitMQ(AMQP)实战

1.	依赖

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-amqp</artifactId>
	</dependency>
```

2.	测试结果
	启动打印：Receive RabbitMQ <来自RabbitMQ的消息>

### 系统集成 Spring Integration


#### 实战

---

**实战内容**：读取消息通过消息分类，将消息发送到不同的消息通道，将分类的release和engineering的消息写入磁盘文件，将分类的消息通过邮件发送

---

1.	依赖

```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-integration</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-mail</artifactId>
	</dependency>
```

2. 结论

**注意**

此功能暂时没有实现，如果需要请自行研究实现


## SpringBoot的开发部署和测试


### 开发的热部署


### 常规部署


### 云部署--基于docker的部署



### Springboot的测试


#### 实战














