package net.xdclass.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import net.xdclass.enums.PermissionEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenConfig {
    /**
     * SaToken全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude(
                        "/account-service/api/v1/account/login",
                        "/account-service/api/v1/account/register",
                        "/*/actuator/prometheus",
                        "/actuator/prometheus"
                )
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    SaRouter.match("/**", r -> StpUtil.checkLogin());
                    // 权限认证 -- 不同模块, 校验不同权限
                    //任何权限都可以
                    SaRouter.match("/*/api/v1/*/list","/*/api/v1/*/find","/*/api/v1/*/page","/*/api/v1/*/get_temp_url","/*/api/v1/*/findLoginAccountRole","/*/api/v1/*/export")
                            .check(r -> {
                                StpUtil.checkPermissionOr( PermissionEnum.PROJECT_READ_WRITE.name(), PermissionEnum.PROJECT_AUTH.name(), PermissionEnum.PROJECT_READ_ONLY.name());
                            });
                    //需要读写权限 或 授权权限 或 全部权限
                    SaRouter.match(
                                    "/*/api/v1/*/save",
                                    "/*/api/v1/*/update",
                                    "/*/api/v1/*/del",
                                    "/*/api/v1/*/execute",
                                    "/*/api/v1/*/upload"
                            )
                            .check(r -> {
                                StpUtil.checkPermissionOr(PermissionEnum.PROJECT_READ_WRITE.name(), PermissionEnum.PROJECT_AUTH.name());

                            });
                    //给别人授权，需要管理员
                    SaRouter.match("/*/api/permit/v1/**").check(r -> {StpUtil.checkPermissionOr(PermissionEnum.PROJECT_AUTH.name());});
                })
                // 异常处理方法：每次setAuth函数出现异常时进入
                .setError(e -> {
                    return SaResult.error(e.getMessage());
                }).setBeforeAuth(obj -> {
                    SaHolder.getResponse()

                            // ---------- 设置跨域响应头 ----------
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "*")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                    ;

                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .free(r -> System.out.println("--------OPTIONS预检请求，不做处理-------"))
                            .back();
                });
    }
}

