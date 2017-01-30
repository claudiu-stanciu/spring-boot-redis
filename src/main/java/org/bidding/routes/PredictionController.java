package org.bidding.routes;

import org.bidding.device.DeviceTarget;
import org.bidding.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {

  PredictionService predService;

  @Autowired
  PredictionController(PredictionService predService) {
    this.predService = predService;

  }

  @RequestMapping(method = RequestMethod.POST, value = "/", consumes="application/json")
  public @ResponseBody ResponseCRT predictCRT(@RequestBody DeviceTarget device) {
    ResponseCRT res = new ResponseCRT();
    res.setCRT(predService.predictCRT(device).toString());
    return res;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/init")
  public @ResponseBody ResponseInit initiDB() {
    predService.initializePred();
    ResponseInit res = new ResponseInit();
    String responseMSg = "Initialized prediction DB";
    res.setStatus(responseMSg);
    return res;
  }
}
