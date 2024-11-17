package com.brunomurakami.softplan.config;

import com.brunomurakami.softplan.model.Pessoa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host:localhost}")
    private String host;

    @Value("${spring.data.redis.port:6379}")
    private Integer port;

    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(host, port));
    }

    @Bean
    RedisTemplate<String, Pessoa> redisTemplate (RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Pessoa> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }


}
