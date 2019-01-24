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

在第一天的基础上，进行一些知识的强化

### 基本概念

聚合

* 选取一个实体充当聚合根
* 所有操作都通过聚合根，外部不能绕过聚合根去操作聚合里的元素
* 以关联的强弱来决定哪些元素要放到聚合中，强关联的同一聚合，弱关联的不同聚合
* 聚合要尽可能小，不要去创建一个大而全的聚合，那样往往得不偿失
* 聚合里的东西一定是一起加载（不能部分加载）
* 聚合之内强一致，聚合之间弱一致（可以视具体情况酌情处理）

### 设计思想

* 不要以数据的角度去思考，以领域模型的角度去思考
* `信息专家模式`（`Information Expert`）：谁拥有信息（数据），就让谁做操作。数据和操作应放同一个地方
* 值对象上添加加操作，如`DateTimeSpan`的日期`diff`操作，更内聚，更易于测试，也体现`分而治之`的思想
* 分层，六边形架构，尽可能保持domain层的纯粹（不涉及技术层，有时需要妥协，如空的构造方法、依赖注入等）
* 其他层可以调用domain层，domain层绝对不能调用/依赖其他层
* 忘掉数据库，设想内存无限大，系统永不宕机

![六边形架构](http://zhangyi.xyz/handling-complex-by-ddd/03.jpg)

### 具体技术

#### `MyBatis`一对N映射

* 一对多的映射
* 一对一`LEFT JOIN`映射
* 一对一2次查询映射


#### MyBatis配置自动参数映射（去除`@Param`注解）

`MyBatis`配置文件中，必须把`useActualParamName`置为`true`。虽然有说明这个参数默认就为`true`，但似乎不显式设置为`true`是不生效的。可能是因为兼容性的考虑，因为这个参数设置为`true`，会使以前那种`#{0}`、`#{param1}`这类引用参数的作法失效。不管如何，还是显式设置一下。

```xml
<settings>
    <setting name="useActualParamName" value="true" />
</settings>
```

##### IntelliJ IDEA配置

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

#### MyBatis配置属性自动映射（不需要在`<resultMap>`中列出所有属性）

`autoMappingBehavior`：NONE, PARTIAL, FULL。

`PARTIAL`则配置在`<resultMap>`中的属性才映射，`FULL`则没配置也映射（只要实体中存在这个属性就映射）

```xml
<settings>
    <setting name="autoMappingBehavior" value="FULL" />
</settings>
```

#### `MyBatis`根据字段值映射返回不同类型的对象

需要用到`discriminator`（鉴别器）

```java
public interface IDispatch {
	void dispatch();
}
```

Mapper配置

```xml
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
