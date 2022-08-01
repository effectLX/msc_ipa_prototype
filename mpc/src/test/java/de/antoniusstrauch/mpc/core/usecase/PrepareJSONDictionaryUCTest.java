package de.antoniusstrauch.mpc.core.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.entity.EventType;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;


class PrepareJSONDictionaryUCTest {

  private PrepareJSONDictionaryUC prepareJSON;
  private AddEventToAdtechUC addEvent;

  @BeforeEach
  void initTest() {
    prepareJSON = new PrepareJSONDictionaryUC();
    addEvent = new AddEventToAdtechUC(new CheckReportTriggerUC());

    for (int i = 0; i < 5; i++){
      Event event = new Event(EventType.SOURCE, Integer.toString(i), Integer.toString(i));
      addEvent.runUsecase(event);
    }
  }

  @Test
  void runUsecase() throws JSONException {
    String response = prepareJSON.runUsecase(addEvent);

    JSONArray array = new JSONArray(response);
    for(int n = 0; n < array.length(); n++) {
      JSONObject object = array.getJSONObject(n); // TODO: why does it break here?
    }

    for (int i = 0; i < array.length(); i++){
      JSONObject obj = array.getJSONObject(i);
      Assertions.assertEquals("SOURCE", obj.getJSONObject("type"));
      Assertions.assertEquals(Integer.toString(i), obj.getJSONObject("matchKey"));
      Assertions.assertEquals(Integer.toString(i), obj.getJSONObject("timestamp"));
    }
  }
}