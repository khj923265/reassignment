package com.rb.rbassignment.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
@PropertySource("classpath:application.yml")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;


    // redisConnectionFactory -> redis 와 connection 을 생성해주는 객체
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        //TODO EC2 Redis 성공시 redisStandaloneConfiguration 를 LettuceConnectionFactory 파라미터에 적용
        // AWS ElastiCache  적용시 집어넣자... EC2 안되는 이유를 못찾음
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        return new LettuceConnectionFactory();
    }

    // redisTemplate 을 사용하는 방식 repository 를 사용하는 방식 (일단 redisTemplate 도 추가는 해놓음)
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
