package com.hjy.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul过滤，如果所有接口通过zuul访问，就可以通过这样的过滤器过滤数据
 * Created by hjy on 2018/2/1.
 */
@Component
public class MyFilter extends ZuulFilter {
    /**
     * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
     *
     *  pre：路由之前调用
        routing：路由之时调用
        post： 路由之后调用
        error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     * 优先级为0，数字越大，优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * 是否执行该过滤器，此处为true，说明需要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     *  run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * 过滤器的具体逻辑
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");//通过每次访问的token来过滤
        if(accessToken==null){
            ctx.setSendZuulResponse(false);//false令zuul过滤该请求，true令zuul不过滤该请求
            ctx.setResponseStatusCode(401);//设置了其返回的错误码
            //返回数据到页面
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){

            }

        }

        return null;
    }
}
