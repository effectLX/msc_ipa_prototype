package de.antoniusstrauch.mpc.controller;

import de.antoniusstrauch.mpc.core.entity.Event;
import de.antoniusstrauch.mpc.core.usecase.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

// TODO: HTTPS (?)
// TODO: temporary state of the MPC

@RestController
public class ExampleController {

  private final RunMPCUC runMPCUC;
  private final GetPublicKeyUC getPublicKeyUC;
  private final PrepareJSONAttributionUC getJSON;
  private final AddEventToAdtechUC addEvent;
  private final PrepareJSONDictionaryUC prepareDict;
  private final PostEventsToMPCUC postEvents;

  @Autowired
  public ExampleController(RunMPCUC runMPCUC, GetPublicKeyUC getPublicKeyUC, PrepareJSONAttributionUC getJSON,
                           AddEventToAdtechUC addEvent, PrepareJSONDictionaryUC prepareDict, PostEventsToMPCUC postEvents) {
    this.runMPCUC = runMPCUC;
    this.getPublicKeyUC = getPublicKeyUC;
    this.getJSON = getJSON;
    this.addEvent = addEvent;
    this.prepareDict = prepareDict;
    this.postEvents = postEvents;
  }

  @GetMapping("/info")
  public String getInfo() {
    return "This is an example endpoint.";
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/test")
  public String getTest() throws JSONException {

    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", 1);
    jsonObject.put("value", 2);
    jsonArray.put(jsonObject.toString());

    return jsonArray.toString();
  }

  @GetMapping("/publicKey/")
  public String getPublicKey() {return getPublicKeyUC.runUsecase();}

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(
          value = "/attribution",
          consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> postController(
      @RequestBody LinkedList<Event> events) throws JSONException {
    int attribution = runMPCUC.runUsecase(events);
    String result = getJSON.runUsecase(attribution);

    return new ResponseEntity(
        result,
        HttpStatus.OK);
  }

  // Adtech Server
  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(
          value = "/registerEvent",
          consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity addEvent(
          @RequestBody Event newEvent) throws JSONException {
    String result = "No attribution report created.";
    if(addEvent.runUsecase(newEvent)){ //TODO: Die Benennung des Usecases ist nicht verständlich Adding wozu?
      String jsonDict = prepareDict.runUsecase(addEvent); //TODO: Warum konvertierst du hier das schöne Event Object zu einem json String ?
      result = postEvents.runUsecase(jsonDict);
    }
    return new ResponseEntity(
            result,
            HttpStatus.OK);
  }
}