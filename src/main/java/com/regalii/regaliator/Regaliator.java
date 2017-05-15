package com.regalii.regaliator;

import com.regalii.regaliator.api.Configuration;
import com.regalii.regaliator.api.Response;
import com.regalii.regaliator.api.Version;
import com.regalii.regaliator.v15.Client;

import java.util.Map;

public class Regaliator {
    static public void main(final String[] args) {
        final Configuration config = new Configuration(Version.v1_5, "api.casiregalii.com", "4a657ff861ac02ad1015cb1be0606f71", "sTH1069LHLscRizk5zqR8HhP9PBH6tVg1yKij4CNg51Q9jJzkukWifiFbXY6iGhytTTyoiWND8N8ectwRfJl2A==");
        config.setUseSSL(true);

        final Client client = (Client) config.buildClient();
        final Response response = client.getAccount().info();
        final Map<String, Object> body = response.body();

        System.out.println(body.get("balance"));
    }
}

/*echo -n "application/json,mZFLkyvTelC5g8XnyQrpOw==,/account,Mon, 15 May 2017 17:13:08 GMT" | openssl dgst -sha1 -binary -hmac "sTH1069LHLscRizk5zqR8HhP9PBH6tVg1yKij4CNg51Q9jJzkukWifiFbXY6iGhytTTyoiWND8N8ectwRfJl2A==" | base64
/*
echo -n "application/json,mZFLkyvTelC5g8XnyQrpOw==,/account,Mon, 15 May 2017 17:04:29 GMT" | openssl dgst -sha1 -binary -hmac "sTH1069LHLscRizk5zqR8HhP9PBH6tVg1yKij4CNg51Q9jJzkukWifiFbXY6iGhytTTyoiWND8N8ectwRfJl2A==" | base64

# Desde la terminal, puedes pasar la cabecera correcta en cada petición
curl "https://api.casiregalii.com/account"
  -H "Accept: application/vnd.regalii.v1.5+json"
  -H "Content-Type: application/json"
  -H "Content-MD5:"
  -H "Date: Wed, 02 Nov 2016 17:26:52 GMT"
  -H "Authorization: APIAuth your-secret-key:<checksum>"
*/
