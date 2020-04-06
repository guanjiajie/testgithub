package demo.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;

public class MyListener3 implements ApplicationListener<MyEvent> {
    //Logger logger = LoggerFactory.getLogger(MyListener3.class);
    org.apache.log4j.Logger logger = Logger.getLogger(MyListener3.class);
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        logger.info(String.format("%s 监听到事件源: %s",MyListener3.class.getName(),myEvent.getSource()));

    }
}
