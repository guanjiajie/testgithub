package test;

import demo.DemoApplication;
import demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import demo.utils.InsertInfo;
import demo.utils.WebFileUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Component
@TestPropertySource("classpath:application.yml")
@Slf4j
public class SpringTest {

    @Resource
    private WebFileUtils webFileUtils;

    @Resource
    private InsertInfo insertInfo;

    @Resource
    private RedisUtils redisUtils;

    @Test
    public void testDownload1(){
        String path = "E:/IDEATest";
        String url = "http://cdn2.ime.sogou.com/9ec656cb14413fb1d3537ca785b0727a/5dd26144/dl/index/1571199498/sogou_pinyin_95a.exe";
        Map<String, String> params = new HashMap<>();
        params.put("id","001");
        webFileUtils.downloadLittleFileToPath(url,path,params);
    }

    @Test
    public void testDownload2(){
        String path = "E:/IDEATest";
        String url = "https://download.jetbrains.8686c.com/idea/ideaIU-2019.1.exe";
        Map<String, String> params = new HashMap<>();
        webFileUtils.downloadBigFileToPath(url,path,params);
    }
    @Test
    public void testInsert(){
        String tableName="user_list";
        insertInfo.insertUser(tableName);
    }

    @Test
    public void testUpdate(){
        String tableName="user_list";
        insertInfo.updateUser(tableName);
    }

    @Test
    public void testInsertStu(){
        insertInfo.insertStu();
    }

    @Test
    public void testInsertStatic(){
        insertInfo.insertStatic();
    }

    @Test
    public void testInsertCno(){
        insertInfo.insertCno();
    }

    @Test
    public void test1(){
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class);
        //装载监听
        context.addApplicationListener(new MyListener1());
        context.publishEvent("测试事件");
    }

    /**
     * 插入缓存数据
     */
    @Test
    public void set(){
        redisUtils.set("redis_key","redis_value");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get(){
        String value = redisUtils.get("redis_key");
        System.out.println(value);
    }

    @Test
    public void getAndSet(){
        redisUtils.getAndSet("redis_key","value_redis");
    }

    @Test
    public void delete(){
        redisUtils.delete("redis_key");
    }
}
