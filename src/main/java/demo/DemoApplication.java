package demo;

import demo.listener.MyEvent;
import demo.listener.MyListener1;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("demo.*")
@EnableTransactionManagement(order = 10)
@MapperScan(value = {"demo.dao"})
public class DemoApplication {

   /* public static void main1(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        //装载监听
        context.addApplicationListener(new MyListener1());
        context.publishEvent(new MyEvent("测试事件"));
    }*/

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class,args);
        //装载监听
        context.addApplicationListener(new MyListener1());
        //context.publishEvent(new MyEvent("测试事件."));
    }

}
