package de.antoniusstrauch.mpc.core.usecase;

import de.antoniusstrauch.mpc.core.entity.Event;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class PrepareJSONDictionaryUC {

  public PrepareJSONDictionaryUC() {}

  public String runUsecase(AddEventToAdtech addEvent) throws JSONException {

    JSONArray jsonArray = new JSONArray();

    for (Event event : addEvent.getEventArray()) {
      JSONObject thisObject = new JSONObject();
      thisObject.put("type", event.getType());
      thisObject.put("matchKey", event.getMatchKey());
      thisObject.put("timestamp", event.getTimestamp());
      jsonArray.put(thisObject.toString());
    }

    return jsonArray.toString();
  }
}
