package de.antoniusstrauch.mpc.core;

public abstract class AUsecase<Input, Output> {

  public abstract Output runUsecase(Input input) throws MPCExecption;

}
