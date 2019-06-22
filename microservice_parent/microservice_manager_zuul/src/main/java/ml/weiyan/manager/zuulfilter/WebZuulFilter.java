package ml.weiyan.manager.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import ml.weiyan.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author misterWei
 * @create 2019年06月22号:14点22分
 * @mailbox mynameisweiyan@gmail.com
 */
@Component
public class WebZuulFilter extends ZuulFilter{
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //对后台网关路由进行验证处理
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //防止第一次握手被拦截
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        //如果是管理员登陆也是要放行的
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf("/admin/login") > 0) {
            return null;
        }
        //具体业务操作
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization)) {
            if (authorization.startsWith("Bearer ")) {
                try {
                    String substring = authorization.substring(7);
                    Claims claims = jwtUtil.parseJWT(substring);
                    String flag = (String) claims.get("roles");
                    if (flag.equals("admin")){
                        return null;
                    }

                }catch (Exception e){
                    //如果该方法设置为fase 代表不再路由不再执行
                    currentContext.setSendZuulResponse(false);
                    currentContext.setResponseBody(e.getMessage()+"~");
                    currentContext.setResponseStatusCode(403);
                    currentContext.getResponse().setContentType("text/html;charset=utf-8");
                }
            }
        }
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseBody("权限不足~");
        currentContext.setResponseStatusCode(403);
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
