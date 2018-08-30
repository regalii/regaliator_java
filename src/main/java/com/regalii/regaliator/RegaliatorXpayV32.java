package com.regalii.regaliator;

import com.regalii.regaliator.api.Configuration;
import com.regalii.regaliator.api.Response;
import com.regalii.regaliator.api.Version;
import com.regalii.regaliator.v32.Client;
import com.regalii.regaliator.v32.Transaction;
import com.regalii.regaliator.utils.JSON;

import java.util.Map;
import java.util.HashMap;

public class RegaliatorXpayV32 {
  static public void main(final String[] args) {
    String api_key = System.getenv("API_KEY");
    String secret_key = System.getenv("SECRET_KEY");

    final Configuration config = new Configuration(Version.v3_2,
      "apix.casiregalii.com", api_key, secret_key);
    config.setUseSSL(true);

    final Client client = (Client) config.buildClient();

    // Testing account
    Response response = client.getAccount().info();
    Map<String, Object> body = response.body();

    if(response.isSuccess()){
      System.out.println(body.get("balance"));
    } else {
      System.out.println("Code:" + response.httpCode());
      System.out.println("Error:" + body.get("message"));
    }

    Transaction txn_obj = client.getTransaction();

    // Testing POST/transactions
    Map<String, Object> map = new HashMap<String, Object>();

    map.put("account_number", "1741-22");
    map.put("payer_born_on", "1994-05-10");
    map.put("amount", "5.0");
    map.put("currency", "USD");
    map.put("phone_number", "6462342383");
    map.put("client_number", "917-650-0937");
    map.put("first_name", "John");
    map.put("last_name", "Doe");
    map.put("rpps_biller_id", "692b45ea-7369-4232-bdd6-2bd662fe1ebd");

    Map<String, String> address = new HashMap<String, String>();

    address.put("state", "NJ");
    address.put("street", "148 PINE GROVE AVE");
    address.put("zip_code", "07901");
    address.put("city", "Summit");

    map.put("address", address);

    response = txn_obj.create(map);
    body = response.body();
    String txn_id = "";

    if(response.isSuccess()){
      txn_id = (String) body.get("id");
      System.out.println("Recently created txn: " + txn_id);
    } else {
      System.out.println("Code:" + response.httpCode());
      System.out.println("Error:" + body.get("message"));
    }

    if (!txn_id.equals("")) {
      // Testing DELETE/transactions/id
      response = txn_obj.delete(txn_id);
      body = response.body();

      if(response.isSuccess()){
        System.out.println(body);
      } else {
        System.out.println("Code:" + response.httpCode());
        System.out.println("Error:" + body.get("message"));
      }
    }

    // Testing GET/transactions
    response = txn_obj.list(null);
    body = response.body();

    if(response.isSuccess()) {
      Object txns = body.get("transactions");
      Object[] txns_array = JSON.getArray(txns);
      System.out.println("Total transactions: " + txns_array.length);
    } else {
      System.out.println("Code:" + response.httpCode());
      System.out.println("Error:" + body.get("message"));
    }
  }
}
