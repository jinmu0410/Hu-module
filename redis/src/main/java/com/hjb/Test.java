package com.hjb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
public class Test {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test")
    public void test(){
        redisTemplate.opsForValue().set("test","hello world!");

        System.out.println( redisTemplate.opsForValue().get("test"));
       /* Map<String, Object> hashmap = new HashMap<>();
        hashmap.put("name","zhs");
        hashmap.put("age",12);
        hashmap.put("address","杭州西湖");
        redisTemplate.opsForHash().putAll("user",hashmap);*/

        /*redisTemplate.opsForList().leftPush("test-list",123);
        redisTemplate.opsForList().leftPush("test-list",456);*/

      /*  System.out.println("两个集合的交集" + redisTemplate.opsForSet().intersect("set","set1"));

        System.out.println("差值"+ redisTemplate.opsForSet().difference("set","set1"));
        System.out.println("合集"+ redisTemplate.opsForSet().union("set","set1"));
        System.out.println("set长度"+ redisTemplate.opsForSet().size("set"));
        System.out.println("set=" + redisTemplate.opsForSet().members("set"));*/

       /* redisTemplate.opsForZSet().add("zset",123,31);
        redisTemplate.opsForZSet().add("zset",456,34);
        redisTemplate.opsForZSet().add("zset1",332,54);
        redisTemplate.opsForZSet().add("zset",335,24);*/
       /* System.out.println(redisTemplate.opsForZSet().range("zset",30,100));
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt(100);
        range.lt(350);
        System.out.println(redisTemplate.opsForZSet().rangeByLex("zset",range));

        System.out.println("335的排名="+redisTemplate.opsForZSet().rank("zset",335));

        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset",0,500));
*/

    }
}
