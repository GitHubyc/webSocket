package com.xakj.service.impl;

import com.xakj.service.IRedisService;
import com.xakj.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 */
@Service
public class RedisServiceImpl implements IRedisService {

	private static Logger logger = Logger.getLogger(RedisServiceImpl.class);

	@Autowired
	private JedisPool jedisPool;

	@Override
	public Jedis getResource() {
		return jedisPool.getResource();
	}

	@Override
	public Response set(String key, String value) {
		HttpStatus status = HttpStatus.OK;
		String message = "操作成功！";
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			logger.info("Redis set success - " + key + ", value:" + value);
			result = jedis.set(key, value);
			return new Response(status.value(), message, result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + value);
			message = "操作失败！";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			jedis.close();
		}
		return new Response(status.value(), message);
	}

	@Override
	public Response get(String key) {
		HttpStatus status = HttpStatus.OK;
		String message = "操作成功！";
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.get(key);
			logger.info("Redis get success - " + key + ", value:" + result);
			return new Response(status.value(), message, result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Redis set error: " + e.getMessage() + " - " + key + ", value:" + result);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			jedis.close();
		}
		return new Response(status.value(), message);
	}

	@Override
	public Response del(String key) {
		HttpStatus status = HttpStatus.OK;
		String message = "操作成功！";
		String result = "";
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.del(key) + "";
			return new Response(status.value(), message, result);
		} catch (Exception e) {
			e.printStackTrace();
			message = "操作失败！";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			jedis.close();
		}
		return new Response(status.value(), message);
	}

	@Override
	public Response expire(String key, int seconds) {
		HttpStatus status = HttpStatus.OK;
		String message = "操作成功！";
		String result = "";
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.expire(key, seconds) + "";
			return new Response(status.value(), message, result);
		} catch (Exception e) {
			e.printStackTrace();
			message = "操作失败！";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} finally {
			jedis.close();
		}
		return new Response(status.value(), message);
	}

}
