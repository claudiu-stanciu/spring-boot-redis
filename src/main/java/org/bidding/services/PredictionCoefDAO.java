package org.bidding.services;

public interface PredictionCoefDAO {

  void setCoef(String iKey, Double iVal);
  
  Double getCoef(String iKey);

  void delCoef(String iKey);
}