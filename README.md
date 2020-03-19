# lyscms-prevent-duplicate-submit

#### 介绍
基于SpringBoot2+实现的一个防止重复提交组件，支持多策略模式：ALL、POST、GET、NONE及兼容注解指定接口拦截或者不拦截，防止用户提交时重复点击导致重复数据。

#### 软件架构
1.基于SpringBoot2.0+上的组件  
2.基于拦截器Interceptor实现  
3.支持多种策略运行模式：ALL、POST、GET、NONE以及@Annotation自定义方式  
4.内置本地内存锁加锁，支持自定义其它锁实现，实现LockHandler接口并交给Spring管理即可，如：分布式锁  
5.内置默认拦截返回信息，支持自定义返回handler  
5.内置默认幂等标识(请求唯一标识): sessionId, 支持自定义，实现IdempotentUniquenessHandler接口交给Spring管理即可  

#### 安装教程

1.  下载源码
2.  编译安装(install)到本地仓库或者发布(deploy)到私服
3.  在项目中增加maven依赖
    ```xml
         <dependency>
            <groupId>info.lyscms.assembly</groupId>
            <artifactId>lyscms-prevent-duplicate-submit</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
    ```
4.  在启动类上增加注解 @EnableLyscmsPreventRepeated(strategy = PreventStrategy.POST)   
    默认为POST，可设置：ALL、POST、GET、NONE

#### 使用说明

1.  策略模式说明:  
    a.  ALL：所有类型的请求均会进行放重复拦截  
    b.  POST：包括post、delete、put、patch  
    c.  GET：包括get、head、options  
    d.  NONE：不做任何放重复拦截
2.  自定义幂等标识(请求id)、自定义锁实现、自定义拦截响应与下类似
    ```java
        @Configuration
        public class PreventRepeatConfig {
        
            @Bean
            public IdempotentUniquenessHandler idempotentUniquenessHandler() {
                return new IdempotentUniquenessHandler() {
                    /**
                     * 幂等唯一id
                     *
                     * @param request
                     * @param method
                     * @return
                     * @author sunkl
                     * @date 2020/3/18 17:23
                     */
                    @Override
                    public String idempotentId(HttpServletRequest request, HandlerMethod method) {
                        return request.getSession().getId() + "_" + request.getMethod() + "_" + request.getQueryString();
                    }
                };
            }
        }
    ```
3.  注意项目中不要存在其它继承WebMvcConfigurationSupport的配置，若有请修改为实现WebMvcConfigurer即可，否则拦截器可能不会生效

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 亮月特技
1.  Power by [www.lyscms.info](https://www.lyscms.info)
2.  官方博客 [xzpdskll](https://blog.csdn.net/xzpdskll)
