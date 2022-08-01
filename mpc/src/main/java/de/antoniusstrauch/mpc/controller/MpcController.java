package de.antoniusstrauch.mpc.controller;

import de.antoniusstrauch.mpc.core.entity.AttributionResult;
import de.antoniusstrauch.mpc.core.usecase.mpc.RequestAttribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MpcController {

  private final RequestAttribution requestAttribution;

  @Autowired
  public MpcController(RequestAttribution requestAttribution) {
    this.requestAttribution = requestAttribution;
  }


  @PostMapping("/requestAttribution")
  AttributionResult requestAttribution(@RequestBody RequestAttributionCommand command) {
    return requestAttribution.runUsecase(command);
  }

}
