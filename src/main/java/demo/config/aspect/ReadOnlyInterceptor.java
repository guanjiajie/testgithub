package demo.config.aspect;

import demo.config.DbContextHolder;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReadOnlyInterceptor implements Ordered {

    //private static final Logger logger = LoggerFactory.getLogger(ReadOnlyInterceptor.class);
    private static final Logger logger = Logger.getLogger(ReadOnlyInterceptor.class);

    @Around("@annotation(readOnly)")
    public Object setRead(ProceedingJoinPoint joinPoint,ReadOnly readOnly) throws Throwable{
        try {
            DbContextHolder.setDbType(DbContextHolder.READ);
            return joinPoint.proceed();
        } finally {
            //DbType一方面为了避免内存泄漏，更重要的是避免对后续再本线程上执行的操作产生影响
            DbContextHolder.clearDbType();
            logger.info("清除threadLocal");
            logger.error("发生错误");
            logger.warn("发生警告");
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
