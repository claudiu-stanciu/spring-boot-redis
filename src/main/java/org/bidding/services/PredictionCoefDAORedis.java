package org.bidding.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

@Configuration
public class PredictionCoefDAORedis implements PredictionCoefDAO {

  JedisPool pool;
  static final String KEY = "model";

  //@Bean
  public PredictionCoefDAORedis() throws IOException{
    String propFileName = "application.properties";
    InputStream is = getClass().getClassLoader().getResourceAsStream(propFileName);
    Properties prop = new Properties();
    
    if (is != null) {
      prop.load(is);
    } else {
      throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
    }

    pool = new JedisPool(new JedisPoolConfig(), prop.getProperty("redis.host"), Integer.parseInt(prop.getProperty("redis.port")));
  }

  @Override
  public void setCoef(String iKey, Double iVal) {
    System.out.println("SET REDIS key: " + iKey + " val " + iVal);
    try (Jedis jedis = pool.getResource()) {
      Long reply = jedis.hset(KEY, iKey, String.valueOf(iVal));
      System.out.println("SET REDIS reply: " + reply);
    } catch (JedisException ex) {
      System.out.println(ex.getMessage());
    }
  }

  @Override
  public Double getCoef(String iKey) {

    Double coef = Double.valueOf(0);
    System.out.println("GET REDIS key: " + iKey);

    try (Jedis jedis = pool.getResource()) {
      System.out.println("GET REDIS val: " + jedis.hget("predCoef", iKey));
      coef = Double.valueOf(jedis.hget(KEY, iKey));
    } catch (JedisException ex) {
      System.out.println(ex.getMessage());
    }
    return coef;
  }

  @Override
  public void delCoef(String iKey) {
    try (Jedis jedis = pool.getResource()){
      Long reply = jedis.hdel(KEY, iKey);
      System.out.println("SET REDIS reply: " + reply);
    } catch (JedisException ex) {
      System.out.println(ex.getMessage());
    }
  }

}
