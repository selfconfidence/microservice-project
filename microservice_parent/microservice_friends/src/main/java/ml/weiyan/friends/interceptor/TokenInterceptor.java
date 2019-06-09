package ml.weiyan.friends.interceptor;

import io.jsonwebtoken.Claims;
import ml.weiyan.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author misterWei
 * @create 2019年05月25号:17点33分
 * @mailbox mynameisweiyan@gmail.com
 */
//对token解析的拦截器
@Component
public class TokenInterceptor implements HandlerInterceptor{
    @Autowired
    private JwtUtil jwtUtil;

    //在请求来的时候拦截  true放行，否则拦截
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         //业务操作，把内部业务简易化，从拦截器取出token并解析放到request域对象中
         String authorization = request.getHeader("Authorization");
         if (authorization != null) {
             //为了防止恶意操作，约定好的标示
             if (authorization.startsWith("Bearer ")) {
                 String token = authorization.substring(7);
                 try {
                     //如果设置过期时间，到时间之后就会报错。
                     Claims claims = jwtUtil.parseJWT(token);
                     //查询权限，是否有权限删除用户
                     String role = (String) claims.get("roles");
                     //如果当前role包含了admin就放到域对象中
                     if (!(!(role != null && role.equals("admin")))){
                            request.setAttribute("claims_admin",claims);
                     }
                     if (!(!(role != null && role.equals("user")))){
                         request.setAttribute("claims_user",claims);
                     }
                 }catch (Exception e){
                     throw new RuntimeException("令牌失效");

                 }
             }
         }


         return true;
    }
}
