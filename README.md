此框架是基于SpringMVC+Spring+myBatis的封装，为了快速开发，以下做了一些改进以及增加的特点。

1，由于myBatis是半自动框架，对数据库的操作比较麻烦，为了集成hibernate的优点， 封装了myBatis的基本操作，基于实体类的注解，数据库默认用下划线规范，字段用驼峰命名规范。 也可以用实体的注解实现其它变换。

2，利用泛型的特点，对service和dao进行了泛型注入，在简单的增删改查条件查询更新，等操作，不用在 service和dao中写代码。控制层入口类示例，com.qk.core.module.user.web.UserController。

3,事务配置，默认命名开头会自动添加事务，特殊命名使用注解增加事务。  

4，加入代码代码生成器，更好的规范代码，和提高开发效率，地址 https://github.com/wangbing234/code_factory 同时也加入了文档生成器。

1)代码生成器主类路径：com.codefactory.CodeGenerator.java

2)代码生成器主类路径：com.doc.DB_Main.java

3)代码生成器主类路径：com.doc.IF_Main.java
5，2017年8月1日16:59:20，增加hibernate-validator 实体注解。
 
6，jwt集成


7，redis集成。

8，微信支付在业务中集成。

自动生成的输出文档示例： 接口文档(moke2017-08-01).docx 数据库设计(moke2017-08-01).docx

