package demo.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;

public class MyListener1 implements ApplicationListener<MyEvent> {
    //Logger logger = LoggerFactory.getLogger(MyListener1.class);
    Logger logger = Logger.getLogger(MyListener1.class);
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        logger.info(String.format("%s 监听到事件源: %s",MyListener1.class.getName(),myEvent.getSource()));

    }

}
