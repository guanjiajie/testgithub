package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class MyListener1 implements ApplicationListener<MyEvent> {
    Logger logger = LoggerFactory.getLogger(MyListener1.class);
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        logger.debug(String.format("%s监听到事件源: %s",MyListener1.class.getName(),myEvent.getSource()));
        System.out.println("事件监听");
    }

}
