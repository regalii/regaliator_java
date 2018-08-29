package com.regalii.regaliator;

import com.regalii.regaliator.api.Configuration;
import com.regalii.regaliator.api.Response;
import com.regalii.regaliator.api.Version;
import com.regalii.regaliator.v32.Client;
import com.regalii.regaliator.v32.Bill;
import com.regalii.regaliator.utils.JSON;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class RegaliatorXdataV32 {
  static private void print_error_message(Response response,
    Map<String, Object> body) {
    System.out.println("Code: " + response.httpCode());
    System.out.println("Error: " + body.get("message"));
  }

  static public void main(final String[] args) {
    String api_key = System.getenv("API_KEY");
    String secret_key = System.getenv("SECRET_KEY");

    final Configuration config = new Configuration(Version.v3_2,
      "apix.casiregalii.com", api_key, secret_key);
    config.setUseSSL(true);

    final Client client = (Client) config.buildClient();

    Bill bill_obj = client.getBill();
    Response response;
    Map<String, Object> body;
    String bill_id = "";

    System.out.println("--- CREATE BASIC BILL ---");

    Map<String, Object> map = new HashMap<String, Object>();

    map.put("login", "regalii");
    map.put("password", "12345");
    map.put("biller_id", "63b83a52-2749-49a5-8726-731590e973b6");

    response = bill_obj.create(map);
    body = response.body();

    if(response.isSuccess()){
      bill_id = (String) body.get("uuid");
      System.out.println(body);

      // Check how many bills there are by calling GET/bills
      System.out.println("--- PRINT # OF EXISTING BILLS ---");

      response = bill_obj.list(null);
      body = response.body();

      if(response.isSuccess()) {
        System.out.println("Total bills: " + response.getTotalEntries());
      } else {
        print_error_message(response, body);
      }
    } else {
      print_error_message(response, body);
    }

    if (!bill_id.equals("")) {
      System.out.println("--- GET SINGLE BILL DETAILS ---");

      response = bill_obj.show(bill_id);
      body = response.body();

      if(response.isSuccess()){
        System.out.println(body);
      } else {
        print_error_message(response, body);
      }

      System.out.println("--- DELETE RECENTLY CREATED BILL ---");

      response = bill_obj.delete(bill_id);
      body = response.body();

      if(response.isSuccess()){
        System.out.println(body);
      } else {
        print_error_message(response, body);
      }
    }

    // Total should have decreased if the basic bill was previously created
    System.out.println("--- PRINT # OF EXISTING BILLS ---");

    response = bill_obj.list(null);
    body = response.body();

    if(response.isSuccess()) {
      System.out.println("Total bills: " + response.getTotalEntries());
    } else {
      print_error_message(response, body);
    }

    System.out.println("--- CREATE BILL WITH MFA OF TYPE QUESTION ---");

    map = new HashMap<String, Object>();

    // Once this challenge has been answered, it will need to be changed to
    // test again
    map.put("login", "question.thalia");
    map.put("password", "12345");
    map.put("biller_id", "04257afb-d956-4606-816b-4bc540cd187f");

    response = bill_obj.create(map);
    body = response.body();
    bill_id = "";

    if(response.isSuccess()) {
      System.out.println(body);
      bill_id = (String) body.get("uuid");
      Object challenges = body.get("mfa_challenges");

      if (challenges != null) {
        System.out.println(challenges);

        System.out.println("--- ANSWER CHALLENGE ---");

        Object[] challenges_array = JSON.getArray(challenges);
        Map<String, Object> challenge = (Map<String, Object>) JSON.loadToMap(JSON.dump(challenges_array[0]));
        Map<String, String> challenge_response = new HashMap<String, String>();
        challenge_response.put("id",  (String) challenge.get("uuid"));
        challenge_response.put("type", "question");
        challenge_response.put("response", "thalia");
        ArrayList<Map<String, String>> a = new ArrayList<Map<String, String>>();
        a.add(challenge_response);

        map.put("mfa_challenges", a);
        System.out.println(map.toString());

        response = bill_obj.update(bill_id, map);
        body = response.body();

        if(response.isSuccess()){
          System.out.println(body);
        } else {
          print_error_message(response, body);
        }
      }

      System.out.println("--- REFRESH BILL ---");

      response = bill_obj.refresh(bill_id);
      body = response.body();

      if(response.isSuccess()) {
        System.out.println(body);
      } else {
        print_error_message(response, body);
      }
    } else {
      print_error_message(response, body);
    }

    System.out.println("--- BULK REFRESH ---");

    ArrayList<String> bill_ids = new ArrayList<String>();
    bill_ids.add(bill_id);

    map = new HashMap<String, Object>();
    map.put("bill_ids", bill_ids);

    response = bill_obj.bulk_refresh(map);
    body = response.body();

    if(response.isSuccess()) {
      System.out.println(body);
    } else {
      print_error_message(response, body);
    }
  }
}
