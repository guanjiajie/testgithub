package demo.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
    @Value("${mysql.datasource.num}")
    private int num;

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DbContextHolder.getDbType();
        if (typeKey == DbContextHolder.WRITE){
            logger.info("使用写库");
            return typeKey;
        }
        logger.info("使用读库");
        return DbContextHolder.READ;
    }
}
