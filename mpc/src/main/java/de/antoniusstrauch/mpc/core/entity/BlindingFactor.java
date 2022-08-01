package de.antoniusstrauch.mpc.core.entity;

import lombok.Getter;

import java.util.Random;

@Getter
public class BlindingFactor {

  private final int blind1;
  private final int blind2;

  private final int randRange = 1000;

  public BlindingFactor(){
    this.blind1 = new Random().nextInt(randRange);
    this.blind2 = new Random().nextInt(randRange);
  }
}
