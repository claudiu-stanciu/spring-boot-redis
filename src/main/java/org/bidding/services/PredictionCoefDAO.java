package org.bidding.services;

public interface PredictionCoefDAO {

  void setCoef(String field, Double val);
  
  Double getCoef(String field);

  void delCoef(String field);
}