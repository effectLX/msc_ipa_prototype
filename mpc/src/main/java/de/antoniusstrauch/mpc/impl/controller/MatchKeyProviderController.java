package de.antoniusstrauch.mpc.impl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchKeyProviderController {

  private final Map<String, Integer> matchKeys;

  public MatchKeyProviderController() {
    this.matchKeys = new HashMap<>();
  }

  @CrossOrigin(origins = "http://localhost:3000/")
  @PostMapping("/getMatchKey")
  public String getMatchKey(@RequestBody String email) {
    if (matchKeys.containsKey(email)) {
      return matchKeys.get(email).toString();
    } else {
      Integer newMatchkey = (int) (Math.random() * 100);
      matchKeys.put(email, newMatchkey);
      return newMatchkey.toString();
    }
  }

}
