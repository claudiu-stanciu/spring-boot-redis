package org.bidding.test;

import redis.clients.jedis.*;

import org.bidding.device.DeviceTarget;
import org.bidding.services.PredictionCoefDAO;
import org.bidding.services.PredictionCoefDAORedis;
import org.bidding.services.PredictionService;
import org.junit.Assert;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestPredDAO {

  @Test
  public void testDataAccess() {
    try {
      PredictionCoefDAO predDAO = new PredictionCoefDAORedis();
      Double coef = Double.valueOf(1);
      String key = "myKey";
      predDAO.setCoef(key, coef);
      Assert.assertEquals(coef, predDAO.getCoef(key));
      predDAO.delCoef(key);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

  @Test
  public void testPredictionWithRedisInputs() {

    try {
      PredictionCoefDAO predDAO = new PredictionCoefDAORedis();
      PredictionService predService = new PredictionService(predDAO);

      DeviceTarget mockDevice = new DeviceTarget();

      String bannerExtSize = "bannerExtSize";
      String deviceExtBrowser = "deviceExtBrowser";
      String deviceExtType = "deviceExtType";
      String deviceLanguage = "deviceLanguage";

      mockDevice.setBannerExtSize(bannerExtSize);
      mockDevice.setDeviceExtBrowser(deviceExtBrowser);
      mockDevice.setDeviceExtType(deviceExtType);
      mockDevice.setDeviceLanguage(deviceLanguage);

      predDAO.setCoef(mockDevice.getBannerExtSizeKey(), Double.valueOf(1));
      predDAO.setCoef(mockDevice.getDeviceExtBrowserKey(), Double.valueOf(1));
      predDAO.setCoef(mockDevice.getDeviceExtTypeKey(), Double.valueOf(1));
      predDAO.setCoef(mockDevice.getDeviceLanguageKey(), Double.valueOf(1));
      predDAO.setCoef("bias", Double.valueOf(1));

      Assert.assertEquals(predService.predictCRT(mockDevice), Double.valueOf(5));

      predDAO.delCoef(mockDevice.getBannerExtSizeKey());
      predDAO.delCoef(mockDevice.getDeviceExtBrowserKey());
      predDAO.delCoef(mockDevice.getDeviceExtTypeKey());
      predDAO.delCoef(mockDevice.getDeviceLanguageKey());
      predDAO.delCoef("bias");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }
}
