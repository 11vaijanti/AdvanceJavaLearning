package com.project.jwtexample.classroomredis;
import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentRedisService {

    private final StringRedisTemplate redisTemplate;

    public StudentRedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveStudent(String id, String name) {
        String key = "student:" + id;

        redisTemplate.opsForValue()
                .set(key, name, Duration.ofMinutes(10));
    }

    public String getStudent(String id) {
        String key = "student:" + id;

        return redisTemplate.opsForValue().get(key);
    }

    public void deleteStudent(String id) {
        String key = "student:" + id;

        redisTemplate.delete(key);
    }
}

