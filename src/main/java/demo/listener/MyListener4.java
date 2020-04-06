package demo.listener;

import org.apache.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener4 {
    //Logger logger = LoggerFactory.getLogger(MyListener4.class);
    org.apache.log4j.Logger logger = Logger.getLogger(MyListener4.class);
    @EventListener
    public void listener(MyEvent myEvent){
        logger.info(String.format("%s 监听到事件源: %s",MyListener4.class.getName(),myEvent.getSource()));
    }
}
