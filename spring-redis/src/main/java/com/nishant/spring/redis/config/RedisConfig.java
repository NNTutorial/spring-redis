package com.nishant.spring.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishant.spring.redis.property.RedisProperty;

@Configuration
@Profile(value= {"redis"})
public class RedisConfig {

	@Autowired
	private RedisProperty redisProperty; 

	@Bean
	public JedisConnectionFactory jedisConnFactory() {
		JedisConnectionFactory jd=new JedisConnectionFactory();
		jd.setUsePool(true);
		jd.setHostName(redisProperty.getRedis().getHost());
		jd.setPort(Integer.parseInt(redisProperty.getRedis().getPort()));
		return jd;
	}

	@Bean
	public RedisTemplate<String, String> stringRedisTemplate() {
		RedisTemplate<String, String> rt=new RedisTemplate<>();
		rt.setConnectionFactory(jedisConnFactory());
		rt.setDefaultSerializer(new StringRedisSerializer());
		return rt;
	}

	@Bean
	public ObjectMapper ojectMapper() {
		return new ObjectMapper();
	}

}
