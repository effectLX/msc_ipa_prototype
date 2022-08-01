package de.antoniusstrauch.mpc.core.usecase;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class PrepareJSONAttributionUC {

  public PrepareJSONAttributionUC() {}

  public String runUsecase(int attribution) throws JSONException {

    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", 1); // TODO: fixed value
    jsonObject.put("value", attribution);
    jsonArray.put(jsonObject.toString());

    return jsonArray.toString();
  }
}
