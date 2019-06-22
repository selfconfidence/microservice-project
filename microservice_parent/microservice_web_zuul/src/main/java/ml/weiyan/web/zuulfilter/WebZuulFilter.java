package ml.weiyan.web.zuulfilter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author misterWei
 * @create 2019年06月22号:13点18分
 * @mailbox mynameisweiyan@gmail.com
 */

/**
 * 网关过滤器配置
 */
@Component
public class WebZuulFilter extends com.netflix.zuul.ZuulFilter {
    /**
     * pre 代表执行前去经过过滤器
     * post 代表执行后去经过过滤器
     * @return
     */
    @Override
    public String filterType() {

        return "pre";
    }

    /**
     * 假设有多个过滤器,配置n多个 int就是用来进行排序的 0代表第一个执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * true  开启此过滤器
     * false 关闭此过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤那些内容 返回null代表执行
     *  setSendZuulResponse(false); 代表不执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
       //进行业务头信息的数据处理
        HttpServletRequest request = currentContext.getRequest();
        //获取到业务数据信息
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization)){
            //对业务数据信息进行转发到微服务的时候做出保存操作把头数据信息也转发抽出去.
            currentContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
