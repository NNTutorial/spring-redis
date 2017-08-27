package com.nishant.spring.redis.controller;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nishant.spring.redis.vo.RedisVO;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisTemplate<String, String> template;

	@Autowired
	private ObjectMapper objectMapper;


	@RequestMapping(value="/set",method=RequestMethod.POST)
	public RedisVO set(@RequestBody RedisVO redisVO) throws JsonProcessingException{
		template.opsForValue().set(redisVO.getKeyname(), objectMapper.writeValueAsString(redisVO));
		return redisVO;
	}

	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public Set<String> getAll() {
		return template.keys("*");
	}

	@RequestMapping(value="/get/{userId}",method=RequestMethod.GET)
	public RedisVO get(@PathVariable String userId) throws IOException {
		String getval=template.opsForValue().get(userId);
		return objectMapper.readValue(getval, RedisVO.class);
	}

	@RequestMapping(value="/del/{key}",method=RequestMethod.DELETE)
	public String delKey(@PathVariable String key) {
		template.delete(key);
		return "deleted key:"+key;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String invalidRequest(Exception e) {
		return "invalid request";
	}

}
