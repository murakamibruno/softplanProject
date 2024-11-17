package com.brunomurakami.softplan.services;

import com.brunomurakami.softplan.model.Pessoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class RedisCacheService {

    @Autowired
    private RedisTemplate<String, Pessoa> redisTemplate;

    @Value("${spring.data.redis.ttl:60}")
    private Integer ttl;

    public void save(String key, Pessoa pessoa) {
        try {
            redisTemplate
                .opsForValue()
                .set(key, pessoa, Duration.ofSeconds(ttl));
        } catch (Exception ex) {
            log.error("Erro ao tentar salvar cache para chave {}", key, ex);
        }
    }

    public Pessoa get(String key) {
       try
       {
           return redisTemplate
               .opsForValue()
               .get(key);
       } catch (Exception ex) {
           log.error("Erro ao obter pessoa através da chave {}", key, ex);
           return null;
       }
    }

    public Boolean existsForKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception ex) {
            log.error("Erro ao obter se pessoa existe para a chave {}", key, ex);
            return false;
        }
    }

    public void remove(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception ex) {
            log.error("Erro ao obter se pessoa existe para a chave {}", key, ex);
        }
    }
}
