package demo.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener2 implements ApplicationListener<MyEvent> {
    //Logger logger = LoggerFactory.getLogger(MyListener2.class);
    Logger logger = Logger.getLogger(MyListener2.class);
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        logger.info(String.format("%s 监听到事件源: %s",MyListener2.class.getName(),myEvent.getSource()));

    }
}
