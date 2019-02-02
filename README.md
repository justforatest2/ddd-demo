# ddd-demo

DDD学习示例，循序渐进，每天一课。主要是技术层面（战术设计），以实践为主，理论为辅

## 第一天

讲解一些基础的东西，先有一些粗浅的认识

### 基本概念

实体（Entity）

* 有唯一标识（ID）
* 有部分Getter
* 没有Setter（例外：createBy）

值对象（Value Object）

* final类
* final属性
* 不可变！

### 设计思想

* 分而治之，将操作分散到实体、值对象、领域服务等
* 分离技术复杂度和业务复杂度。技术层面对应基础设施层，业务层面对应领域层
* 高内聚，数据和操作放在同一个地方（同一个类）
* 忘掉数据库，数据不一定存储在数据库，通过技术手段（ORM映射），可以使数据表结构和实体/值对象并不一一对应，因此领域建模时不要考虑数据库

### 具体技术

`MyBatis`配置自动将下划线字段映射成驼峰

```xml
<settings>
    <setting name="mapUnderscoreToCamelCase" value="true" />
</settings>
```

## 第二、三天

在第一天的基础上，进行一些知识的强化，并主要讲解了`聚合`的概念

### 基本概念

聚合（Aggregate）

* 选取一个实体充当聚合根
* 所有操作都通过聚合根，外部不能绕过聚合根去操作聚合里的元素
* 以关联的强弱来决定哪些元素要放到聚合中，强关联的同一聚合，弱关联的不同聚合
* 聚合要尽可能小，不要去创建一个大而全的聚合，那样往往得不偿失
* 聚合应看作一个整体，其中的元素一定是一起加载（不能部分加载）
* 聚合整体应一起保存
* 聚合之内强一致，聚合之间弱一致（可以视具体情况酌情处理）

### 设计思想

* 不要以数据的角度去思考，以领域模型的角度去思考
* 忘掉数据库，设想内存无限大，系统永不宕机
* `信息专家模式`（`Information Expert`）：谁拥有信息（数据），就让谁做操作。数据和操作应放同一个地方
* 值对象上添加操作，如`DateTimeSpan`的日期`diff`操作，更内聚，更易于测试，也体现`分而治之`的思想
* 分层，六边形架构，尽可能保持domain层的纯粹（不涉及技术层，有时需要妥协，如空的构造方法、依赖注入等）
* 其他层可以调用domain层，domain层绝对不能调用/依赖其他层

![六边形架构](http://zhangyi.xyz/handling-complex-by-ddd/03.jpg)

### 具体技术

#### `MyBatis`一对N映射

* 一对多的映射
* 一对一`LEFT JOIN`映射
* 一对一2次查询映射


#### `MyBatis`配置自动参数映射（去除`@Param`注解）

##### `MyBatis`文件配置

`MyBatis`配置文件中，必须把`useActualParamName`置为`true`。虽然有说明这个参数默认就为`true`，但似乎不显式设置为`true`是不生效的。可能是因为兼容性的考虑，因为这个参数设置为`true`，会使以前那种`#{0}`、`#{param1}`这类引用参数的作法失效。不管如何，还是显式设置一下。

```xml
<settings>
    <setting name="useActualParamName" value="true" />
</settings>
```

##### `IntelliJ IDEA`配置

使用`IntelliJ IDEA`开发时，要进行配置，才能顺利运行无`@Parma`注解的`MyBatis`

`Preference` > `Build, Execution, Deployment` > `Compiler` > `Java Compiler` > `Additional command line parameters`

加上`-parameters`，然后最好用`mvn clean`，然后再`Build`一下项目。

##### Maven配置

要保证最后编译打包出来的`war`包也是正常的，需要在`pom.xml`中加入编译参数：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.3</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
                <compilerArgs>
                    <arg>-parameters</arg>
                </compilerArgs>
            </configuration>
        </plugin>
    </plugins>
</build>
```

通常这样应该没有问题了，如果不行，可以尝试在`configuration`块加入
`<forceJavacCompilerUse>true</forceJavacCompilerUse>`

##### 扩展思考

为什么`Spring MVC`的`Controller`可以不加注解就直接能映射参数

```java
public void updateRefStaffAdjustType(Long workOrderId, Integer adjustType,HttpServletResponse response) {
}
```

为什么可以不加`@RequestParam`？

#### `MyBatis`配置属性自动映射（不需要在`<resultMap>`中列出所有属性）

`autoMappingBehavior`：NONE, PARTIAL, FULL。

设置为`PARTIAL`时，有配置在`<resultMap>`中的属性才映射，`FULL`则没配置也映射（只要实体中存在这个属性就映射）

```xml
<settings>
    <setting name="autoMappingBehavior" value="FULL" />
</settings>
```

#### `MyBatis`根据字段值映射返回不同类型的对象

需要用到`discriminator`（鉴别器）

*以下纯粹是技术方面的示例，不要当作本课程的领域建模*

```java
public interface IDispatch {
	void dispatch();
}
```

`ServiceWorkOrderModel`和`TrainServiceWorkOrderModel`都实现了`IDispatch`接口

Mapper配置

```xml
<resultMap id="BaseResultMap" type="ServiceWorkOrderModel">
</resultMap>
<resultMap id="TrainBaseResultMap" type="TrainServiceWorkOrderModel">
</resultMap>

<resultMap id="DispatchBaseResultMap" type="domain.module.workorder.model.IDispatch">
    <discriminator javaType="int" column="type">
        <case value="0" resultMap="BaseResultMap" />
        <case value="2" resultMap="TrainBaseResultMap" />
    </discriminator>
</resultMap>

<select id="findDispatchById" resultMap="DispatchBaseResultMap">
    SELECT *
    FROM service_work_order
    WHERE id=#{id}
</select>
```

## 第四天

主要讲解`仓储`的概念及技术复杂度和业务复杂度分离的思想

### 基本概念

仓储（Repository）

* 用来获取已有的领域模型
* 与`DAO`的差别，`DAO`是面向数据的，仓储是面向集合（领域模型）的
* 不要写通用的查询，要写特定的查询
* 仓储的查询只会是为了写操作而做的查询
* 对于聚合来说，整个聚合对应一个仓储，聚合中的实体不应有独立的仓储（这也体现之前所说的，聚合同时加载，同时保存的思想）
* 聚合的仓储只能返回整个聚合，不能只返回聚合中的实体，如`WorkOrderRepository`不能存在返回`ServiceWorkOrderModel`的方法（如`ServiceWorkOrderModel findByServiceWorkOrderId(Long serviceWorkOrderId)`）

### 设计思想

技术复杂度和业务复杂度分离

* 领域层应保持纯粹，不依赖于具体的技术，如`Spring`、`MyBatis`、`Hibernate`，具体的技术由基础设施层去实现。这需要去做权衡，根据具体情况让领域层一定程度上依赖于某些技术
* 基础设施层决定数据的存储方式，如`MongoDB`、`MySQL`，领域层只依赖仓储接口，不依赖于具体的存储实现
* 领域层应只关注业务逻辑，不依赖其他层。如领域服务的方法应只接受领域内已有的对象，而不应接收不在领域层的`DTO`等
* 事务、缓存、权限等应放到应用服务层

## 第五天

主要讲解`领域服务`、`应用服务`的概念

### 基本概念

领域服务（Domain Service）

* 需要多个实体/聚合进行操作，或者某个操作单独放在某个实体不合适
* 每个服务只有一个方法，且方法应该是可以直接调用。明确依赖项，使业务逻辑的代码更清晰
* 方法参数只接收领域层的对象，如值对象、Java自带的对象等，不接收自定义的DTO
* 计算类的操作

应用服务（Application Service）

* 事务、缓存、权限
* 发邮件、发短信等非核心业务
* 参数转化，如把普通的DTO请求对象转化成领域所需的参数
* 可以看作是应用对外的接口(facade)
* 一个服务类可以有多个方法，方法按业务使用场景来创建

## 第六天

补充一些其他方面的知识

### 防腐层（Anticorruption Layer - ACL）

封装对外部系统的交互调用

[https://www.cnblogs.com/daxnet/archive/2012/08/30/2663092.html](https://www.cnblogs.com/daxnet/archive/2012/08/30/2663092.html)
	
* 库存调度
* http通信？RPC（DUBBO）？
* 参数格式？
* 接口会变？会切换？

### 模块

如何分包，组织代码，尽量同一个实体/聚合的领域代码放一起

### 实体的创建

* Builder
* 静态工厂方法
* 直接new（适用于简单的值对象）
* customer.createOrder() -> Order(uid=customer.id)
* 参考《Effective Java中文版（原书第3版）》第1、2条
* [http://www.importnew.com/6518.html](http://www.importnew.com/6518.html)

### CQRS（Command Query Responsibility Segregation）命令查询职责分离
	
更广义读写分离

* UI读与业务逻辑读分离（ReaderModel 和 WriterModel）
* 数据库的读写分离（甚至是读写异构）
* Event、Command
* 事件溯源

### 并发处理

乐观锁，版本号控制

