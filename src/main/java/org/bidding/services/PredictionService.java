package org.bidding.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.bidding.device.DeviceTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PredictionService {

  PredictionCoefDAO predCoefDAO;

  @Autowired
  public PredictionService(PredictionCoefDAO iPredCoefDAO) {
    predCoefDAO = iPredCoefDAO;
  }

  public Double predictCRT(DeviceTarget device) {
    Double chance = Double.valueOf(0);

    chance += predCoefDAO.getCoef("bias");

    // TODO refactor with reflection and iterate
    chance += predCoefDAO.getCoef(device.getBannerExtSizeKey());
    chance += predCoefDAO.getCoef(device.getDeviceExtBrowserKey());
    chance += predCoefDAO.getCoef(device.getDeviceExtTypeKey());
    chance += predCoefDAO.getCoef(device.getDeviceLanguageKey());

    // TODO apply logistic function to chance sum

    return chance;
  }

  public void initializePred() {
    String predList = "predictionCoefList.csv";
    try (BufferedReader br = new BufferedReader(
        new FileReader(getClass().getClassLoader().getResource(predList).getPath()))) {
      String line;
      String[] splitLine;
      while ((line = br.readLine()) != null) {
        splitLine = line.split(",");
        predCoefDAO.setCoef(splitLine[0], Double.valueOf(splitLine[1]));
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      System.out.println("Path to CSV: " + getClass().getClassLoader().getResource(predList).getPath());
    }

  }
}
