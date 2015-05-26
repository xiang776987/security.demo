# security.demo
<h1>security.demo:后台管理系统-权限控制(rbac)</h1>
<h2>Maven Java EE Web Project</h2>
<h3>项目计划</h3>
<ul>
<li>第一阶段：
		通用后台管理框架组合：
		spring+spring mvc+hibernate jap+spring jdbc+mybatis+jquery-easyui
</li>
<li>第三阶段：
		前台Javascript编码，增加MVC编码模式，使用angularjs等框架进行前端开发。

</li>
<li>第四阶段：
 	项目结构改造，使用maven jetty技术合并web resource资源

</li>
</ul>
	

<h4>运行项目</h4>
<span>
1. 启动项目 mvn jetty:run
</span>

<span>
2. 系统初始化，资源-角色-用户数据初始化： 运行单元测试：com.jsrush.security.rbac.InitRootUserTest
</span>

<h4>功能更新</h4>
<ul>
<li>1.增加静态资源版本控制，JSP文件使用${resourceRoot}引用静态文件；web-aap.properties配置app.resourceVersion</li>
</ul>
