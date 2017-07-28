# template
模板项目学习
Get Time：2017/7/28

整个项目分为七个模型：
template-base, template-http, template-domain
, template-service, template-admin, template-mobile, template-war

template-base:
  项目的基础、公共模型，如：包含ResponseBean 响应类的定义

template-http:
  定义了在请求和响应中，http使用到的常量 和 错误的处理

template-domain:
  定义 实体Bean ，service 的interface， Ps：由于本项目的dao层采用的是Mango操作的因此，不需要在domain中写dao的接口了
  
template-service:
  主要 包含了对service的 实现， 以及对dao层的操作
 
template-mobile: 
  暂时还没有用到，应该是关于终端的控制层

template-war:
  这个是用户操作的静态界面的入口
 
