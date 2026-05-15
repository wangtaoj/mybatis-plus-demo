# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 构建与运行命令

- **构建**: `mvn clean package`
- **运行**: `mvn spring-boot:run`
- **运行全部测试**: `mvn test`
- **运行单个测试类**: `mvn test -Dtest=ApplicationTest`
- **运行单个测试方法**: `mvn test -Dtest=ApplicationTest#testSelectListWithoutPage`

注意：运行测试需要本地 MySQL 服务（`127.0.0.1:3306`），数据库名为 `test`。

## 项目架构

Spring Boot 2.7.18 + MyBatis-Plus 3.5.15 单模块项目，基础包名：`com.wangtao.mybatisplus`。

**包结构**：
- `Application.java` — Spring Boot 启动类
- `config/` — MyBatis-Plus 插件配置（`MybatisPlusConfig`）和自动填充处理器（`FillMetaObjectHandler`）
- `dao/` — Mapper 接口，继承 `BaseMapper<T>`，无 XML 映射文件
- `po/` — 实体类，`BaseModel` 提供公共审计字段

**核心约定**：
- **实体继承**：新增实体应继承 `BaseModel`，自动获得 `createTime`（仅 INSERT 填充，不会覆盖）和 `updateTime`（INSERT + UPDATE 填充）。
- **逻辑删除**：全局配置字段 `delFlg`（0=有效，1=已删除），通过 `@TableLogic` 注解实现，查询时自动过滤已删除记录。
- **分页**：已配置 `PaginationInnerInterceptor`（MySQL 方言），使用 `Page` 对象进行分页查询。
- **注解式 SQL**：简单SQL可使用 `@Update`/`@Select` 注解写在 Mapper 接口上，复杂SQL使用 XML。
- **实体更新**: 实体参数的更新方法，若涉及逻辑删除，使用 `@Param(Constants.ENTITY)`。
- **service**: 只有一个实现的service不要定义接口，不要使用Mybatis-Plus的IService、ServiceImpl。
- **连接池**：使用阿里巴巴 Druid（非 HikariCP），开启慢 SQL 检测（超过 1000ms 记录 WARN 日志）。