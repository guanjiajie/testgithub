package demo.controller;

import demo.common.ApiResult;
import demo.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 添加数据
     */
    @GetMapping(value = "/{id}")
    public ApiResult addData2Redis(@PathVariable("id") Integer id){
        redisTemplate.opsForValue().set("first",id);
        redisTemplate.opsForValue().set("second","hello world");
        redisTemplate.opsForValue().set("third",new Animal(100,200,"二狗子"));
        return ApiResult.builder()
                .status(ApiResult.STATUS_SUCCESS)
                .desc("添加成功")
                .build();
    }

    /**
     * 获取数据
     */
    @GetMapping("/redis-data")
    public ApiResult getRedisData(){
        Map<String, Object> result = new HashMap<>();
        result.put("first", redisTemplate.opsForValue().get("first"));
        result.put("second", redisTemplate.opsForValue().get("second"));
        result.put("third", redisTemplate.opsForValue().get("third"));

        return ApiResult.builder()
                .status(ApiResult.STATUS_SUCCESS)
                .desc("获取成功")
                .result(result)
                .build();
    }
}
