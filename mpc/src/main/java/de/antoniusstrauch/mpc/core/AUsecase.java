package de.antoniusstrauch.mpc.core;

import org.springframework.boot.configurationprocessor.json.JSONException;

public abstract class AUsecase<Input, Output> {

  public abstract Output runUsecase(Input input) throws JSONException;

}
