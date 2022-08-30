//package de.antoniusstrauch.mpc;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import de.antoniusstrauch.mpc.core.entity.AttributionResult;
//import de.antoniusstrauch.mpc.core.entity.EventBatch;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.configurationprocessor.json.JSONArray;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Map;
//import java.util.UUID;
//
//@SpringBootApplication
//public class MPCSimulation {
//
//  private static final String domain = "http://localhost:8080";
//  private static final Gson gson = new Gson();
//  private long publicKey;
//  private long encryptionFactor;
//
//  public static void main(String[] args) throws IOException {
//    SpringApplication.run(MultiPartyComputingEntityApplication.class, args);
//    MPCSimulation mpc = new MPCSimulation();
//
//    JsonObject rawEvents = gson.fromJson(new FileReader("rawEvents.json"), JsonObject.class);
//    JsonObject conformEvents = createConformEvents(rawEvents);
//    EventBatch eventBatch = createEventBatch(conformEvents);
//    postRequestToMPC(eventBatch);
//  }
//
//  public MPCSimulation() throws IOException {
//    this.publicKey = getRequestTOMPC("/requestPublicKey");
//    this.encryptionFactor = getRequestTOMPC("/requestPublicEncryptionFactor");
//  }
//
//  private static long  getRequestTOMPC(String endpoint) throws IOException {
//    URL url = new URL(domain + endpoint);
//    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//    connection.setRequestMethod("GET");
//    String result = String.valueOf(connection.getInputStream());
//    return Long.parseLong(result);
//  }
//
//  private static void postRequestToMPC(EventBatch eventBatch) {
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//    HttpEntity<String> request = new HttpEntity<>(gson.toJson(eventBatch), headers);
////    this.attributionResult = template.postForObject(
////            config.getMpc().getServerURL() + "/requestAttribution", request, AttributionResult.class);
////    if (attributionResult != null) {
////      log.info("Attribution result: " + attributionResult.getResult());
////    } else {
////      log.warn("Attribution result null");
////    }
//  }
//
//  private long  postRequestTOMPC(String endpoint) throws IOException {
//    URL url = new URL(domain + endpoint);
//    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//    connection.setRequestMethod("GET");
//    String result = String.valueOf(connection.getInputStream());
//    return Long.parseLong(result);
//  }
//
//  private static JsonObject createConformEvents(JsonObject rawEvents) {
//
//    for (Map.Entry<String, JsonElement> entry : rawEvents.entrySet()) {
//      // loop through events in JSON
//          // if type, keep as is
//          // if matchkey, encrypt and create client key
//          // if timestamp, convert to LocalDateTime
//     // create new event including clientKey
//     // create new JSON with all new events
//    }
//    return eventBatch;
//  }
//
//  private static EventBatch createEventBatch(JsonObject conformEvents) {
//     // create linkedlist 'events' from JsonObject
//    return EventBatch.builder().eventBatchPairId(UUID.randomUUID()).events(events)
//            .build();
//  }
//}
//
