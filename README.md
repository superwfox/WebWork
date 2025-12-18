# Web 前端开发实践课结课作业

**题目：实现三列式网页（含章节目录跳转）**
**运行方式：Java 本地 HTTP 服务器（8080）+ HTML/CSS 静态页面**

---

## 1. 项目简介

本项目实现一个基础的三列式网页：**左侧章节目录**、**中间正文内容**、**右侧信息栏**。用户点击左侧目录即可在中间栏跳转至对应章节（锚点跳转）。页面样式与结构分离：HTML 负责结构，CSS 负责布局与美化，Java 负责通过 HTTP 提供静态资源。

---

## 2. 功能点

* 三列布局展示：左（目录）/中（内容）/右（速览）
* 章节跳转：目录点击 → 定位到中间对应标题（`href="#id"`）
* HTML/CSS 分离：`index.html` 引入 `/style.css`
* 本地 HTTP 服务：访问 `http://127.0.0.1:8080/` 即可查看页面
* 基础响应式：窄屏自动切换为单列（可选/按你代码实际保留）

---

## 3. 技术栈

* Java：JDK 自带 `com.sun.net.httpserver.HttpServer`
* 前端：HTML + CSS（Grid 三列布局、基础样式）
* 运行环境：本地浏览器（Chrome/Edge）

---

## 4. 目录结构（示例）

> 以你的实际工程为准，关键是保证 Java 读取的路径与文件位置一致。

```text
project-root/
  src/...
    ThreeColumnPageServer.java
  webContext/
    index.html
    style.css
```

---

## 5. 运行说明

### 5.1 启动服务

1. 确认已安装 JDK（建议 17+）
2. 运行 `ThreeColumnPageServer.java`（IDEA 直接 Run）
3. 控制台看到类似输出：
   `http://127.0.0.1:8080/`

### 5.2 访问页面

* 打开浏览器访问：
  `http://127.0.0.1:8080/`

---

## 6. 使用说明

* 左侧点击“第 1 章 / 第 2 章 / 第 3 章”等目录项
  → 页面滚动定位到中间栏对应章节标题
* 页面样式来自 `/style.css`，如需修改布局（列宽、间距、字体等），直接编辑 `style.css`

---

## 7. 实现要点（对照课程要求）

### Java（提供静态资源）

* 监听 8080
* 注册路由：

  * `/` → 返回 `index.html`
  * `/style.css` → 返回 `style.css`
* 设置正确响应头：

  * HTML：`Content-Type: text/html`
  * CSS：`Content-Type: text/css`

### HTML（结构 + 跳转）

* 引入 CSS：`<link rel="stylesheet" href="/style.css">`
* 章节跳转：`<a href="#ch1">` ↔ `<h2 id="ch1">`

### CSS（三列布局）

* Grid 三列：`grid-template-columns: 220px 1fr 260px`
* 间距/卡片化样式（提升可读性）
* 可选：响应式媒体查询、小屏单列

---

## 8. 测试结果（简述）

* `http://127.0.0.1:8080/` 可正常返回页面
* `/style.css` 可被浏览器加载，样式生效（三列布局可见）
* 目录点击可跳转到对应章节位置
* 窗口缩放时布局保持可用（若启用响应式）

---

## 9. 不足与改进方向

* 静态资源路由目前只覆盖固定文件，可扩展为通用静态资源处理（自动 MIME、404）
* 目录目前手写，后续可加 `app.js` 自动扫描 `h2/h3` 生成目录并做滚动高亮
* 可迁移到 Spring Boot 静态资源目录或文档站方案（VitePress/Docusaurus）提升工程化与部署便利

---

## 10. 交付物清单

* `ThreeColumnPageServer.java`
* `webContext/index.html`
* `webContext/style.css`
