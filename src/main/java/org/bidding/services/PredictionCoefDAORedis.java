package org.bidding.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.*;

@Configuration
public class PredictionCoefDAORedis implements PredictionCoefDAO {

  JedisPool pool;
  static final String KEY = "model";
  static Logger logger = Logger.getLogger(PredictionCoefDAORedis.class.getName());

  public PredictionCoefDAORedis() throws IOException {
    String propFileName = "application.properties";
    InputStream is = getClass().getClassLoader().getResourceAsStream(propFileName);
    Properties prop = new Properties();
    if (is != null) {
      prop.load(is);
    } else {
      throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
    }

    pool = new JedisPool(new JedisPoolConfig(), prop.getProperty("redis.host"),
        Integer.parseInt(prop.getProperty("redis.port")));
  }

  @Override
  public void setCoef(String iKey, Double iVal) {
    Long reply = Long.valueOf(0L);
    try (Jedis jedis = pool.getResource()) {
      jedis.hset(KEY, iKey, String.valueOf(iVal));
    } catch (Exception ex) {
      logger.log(Level.WARNING, ex.getMessage());
    }
  }

  @Override
  public Double getCoef(String iKey) {
    Double coef = Double.valueOf(0);
    try (Jedis jedis = pool.getResource()) {
      coef = Double.valueOf(jedis.hget(KEY, iKey));
    } catch (Exception ex) {
      logger.log(Level.WARNING, ex.getMessage());
    }
    return coef;
  }

  @Override
  public void delCoef(String iKey) {
    try (Jedis jedis = pool.getResource()) {
      jedis.hdel(KEY, iKey);
    } catch (Exception ex) {
      logger.log(Level.WARNING, ex.getMessage());
    }
  }

}
