package de.antoniusstrauch.mpc.core.entity;

import lombok.Getter;
import java.util.Random;

@Getter
public class Encryption {

  private final int publicKey;
  private final int decrypt1;
  private final int decrypt2;

  private final int randRange = 1000;

  public Encryption(){
    this.decrypt1 = new Random().nextInt(randRange);
    this.decrypt2 = new Random().nextInt(randRange);
    this.publicKey = decrypt1 * decrypt2;
  }
}
