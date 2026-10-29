
package com.example.demo;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

    private final StringRedisTemplate redisTemplate;

    public RedisTestController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/student")
    public String saveStudent(
            @RequestParam String id,
            @RequestParam String name) {

        redisTemplate.opsForValue()
                .set("student:" + id, name);

        return "Student saved in Redis";
    }

    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable String id) {

        String name = redisTemplate.opsForValue()
                .get("student:" + id);

        return name != null ? name : "Student not found";
    }
}
