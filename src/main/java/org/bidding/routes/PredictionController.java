package org.bidding.routes;

import org.bidding.device.DeviceTarget;
import org.bidding.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {

  PredictionService predService;

  @Autowired
  PredictionController(PredictionService iPredService) {
    this.predService = iPredService;

  }

  @RequestMapping(method = RequestMethod.POST, value = "/")
  public Double predictCRT(@RequestBody DeviceTarget device) {
    return predService.predictCRT(device);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/init")
  public String initiDB() {
    predService.initializePred();
    return "Initialized prediction DB";
  }
}
