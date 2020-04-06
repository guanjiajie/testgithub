package demo.config;

import org.apache.log4j.Logger;

/**
 * 这里切换读/写模式
 * 利用ThreadLocal保存当前线程是否处于读模式(通过开始READ_ONLY注解在开始操作前设置模式为读模式
 * 操作结束后清除该数据，避免内存泄漏，同时也为了后续在该线程进行写操作时仍然为读模式)
 */

public class DbContextHolder {
    //private static Logger logger = LoggerFactory.getLogger(DbContextHolder.class);
    private static org.apache.log4j.Logger logger = Logger.getLogger(DbContextHolder.class);
    public static final String WRITE = "write";
    public static final String READ = "read";

    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDbType(String dbType){
        if (dbType == null){
            logger.error("dbType为空");
            throw new NullPointerException();
        }
        logger.info("设置dbType为: "+dbType);
        contextHolder.set(dbType);
    }

    public static String getDbType(){
        return contextHolder.get() == null ? "write":contextHolder.get();
    }

    public static void clearDbType(){
        contextHolder.remove();
    }
}
