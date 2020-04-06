package demo.config.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

/**
 * Created by Another on 2017/7/26.
 */
@Aspect
@Component
public class AspectLog {

    private static Logger logger =LoggerFactory.getLogger(AspectLog.class);

    @Pointcut("execution(* demo.controller..*.*(..))")
    public void log() {
    }

    /**
     * 进入Controller获取参数
     *
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        logger.info("==============================start==============================");

        //url
        logger.info("url=" + request.getRequestURL());

        //methon
        logger.info("method=" + request.getMethod());

        //ip
        logger.info("ip=" + request.getRemoteHost());

        //类方法
        logger.info("class_method=" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        Object[] objs = joinPoint.getArgs();
        List args = new ArrayList();
        for (Object obj : objs) {
            if ((obj instanceof HttpServletRequest) || (obj instanceof HttpServletResponse) || obj instanceof BindingResult) {
                continue;
            }
            args.add(obj);
        }
        if (args == null || "".equals(args)) {
            logger.info("args=" + JSON.toJSONString(args));
        }

    }

    @After("log()")
    public void doAfter() {

    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void adAfterReturning(Object object) {
       /* ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletContainer.getRequest();
        HttpServletResponse response = servletContainer.getResponse();
        logger.info(JSON.toJSONString(response));*/
        logger.info("resopnse=" + JSON.toJSONString(object));
        logger.info("==============================end==============================");
    }

    /*@Around("log()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        Gson gson = new Gson();
        logger.info("请求结束，controller的返回值是 " + gson.toJson(result));
        return result;
    }*/
}
