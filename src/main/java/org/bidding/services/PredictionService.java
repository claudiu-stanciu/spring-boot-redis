package org.bidding.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bidding.device.DeviceTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PredictionService {

  PredictionCoefDAO predCoefDAO;

  static Logger logger = Logger.getLogger(PredictionService.class.getName());
  
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

    logger.log(Level.INFO, "Computed prediction: " + chance);
    
    return chance;
  }

  public void initializePred() {
    String predList = "predictionCoefList.csv";
    try (BufferedReader br = new BufferedReader(
        new FileReader(getClass().getClassLoader().getResource(predList).getPath()))) {
      String coefInputLine;
      String[] coefInputLineSplit;
      while ((coefInputLine = br.readLine()) != null) {
	  coefInputLineSplit = coefInputLine.split(",");
        predCoefDAO.setCoef(coefInputLineSplit[0], Double.valueOf(coefInputLineSplit[1]));
      }
      logger.log(Level.INFO, "Initialized prediction coefficients DB");
    } catch (Exception ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }

  }
}
