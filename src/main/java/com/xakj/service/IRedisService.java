package com.xakj.service;

import com.xakj.util.Response;
import redis.clients.jedis.Jedis;

/**
 * @author 施先锋 缓存Redis
 */
public interface IRedisService {

	public Jedis getResource();

	public Response set(String key, String value);

	public Response get(String key);

	public Response del(String key);

	public Response expire(String key, int seconds);

}