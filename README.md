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


