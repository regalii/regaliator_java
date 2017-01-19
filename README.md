# Regalii Java Client

A Java HTTP client for consuming Regalii's API.

## Usage

First of all, you need a `Configuration` instance:

```java
package com.regalii.regaliator;

import com.regalii.regaliator.api.Configuration;
import com.regalii.regaliator.api.Response;
import com.regalii.regaliator.api.Version;
import com.regalii.regaliator.v15.Client;

import java.util.Map;

public class Regaliator {
    static public void main(final String[] args) {
        final Configuration config = new Configuration(Version.v1_5, "api.regalii.dev", "api_key", "secret_key");
        config.setUseSSL(false); // Support of SSL will be added very soon.

        final Client client = (Client) config.buildClient();
        final Response response = client.getAccount().info();
        final Map<String, Object> body = response.body();

        System.out.println(body.get("balance"));
    }
}
```

This will display the balance:
```
=> 1797.41
```

### Versions

The client supports 3 versions:
* `Version.v1_5`
* `Version.v3_0`
* `Version.v3_1`

## API

### Version 1.5

#### Account

* `final Response response = client.getAccount().info();`

#### Bill

* `final Response response = client.getBill().index(params_as_map_of_string_and_object);`
* `final Response response = client.getBill().consult(params_as_map_of_string_and_object);`
* `final Response response = client.getBill().pay(params_as_map_of_string_and_object);`
* `final Response response = client.getBill().check(params_as_map_of_string_and_object);`

#### Biller

* `final Response response = client.getBiller().topups(params_as_map_of_string_and_object);`
* `final Response response = client.getBiller().utilities(params_as_map_of_string_and_object);`

#### Rate

* `final Response response = client.getRate().list(params_as_map_of_string_and_object);`
* `final Response response = client.getRate().history(params_as_map_of_string_and_object);`

#### Transaction

* `final Response response = client.getTransaction().list(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().pay(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().reverse(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().cancel(params_as_map_of_string_and_object);`

### Version 3.0

#### Account

* `final Response response = client.getAccount().info();`

#### Bill

* `final Response response = client.getBill().create(params_as_map_of_string_and_object)`
* `final Response response = client.getBill().show(id)`
* `final Response response = client.getBill().update(id, params_as_map_of_string_and_object)`
* `final Response response = client.getBill().refresh(id)`
* `final Response response = client.getBill().pay(id, params_as_map_of_string_and_object)`
* `final Response response = client.getBill().xdata(id)`
* `final Response response = client.getBill().list(params_as_map_of_string_and_object)`

#### Biller

* `final Response response = client.getBiller().credentials(params_as_map_of_string_and_object);`
* `final Response response = client.getBiller().topups(params_as_map_of_string_and_object);`
* `final Response response = client.getBiller().utilities(params_as_map_of_string_and_object);`

#### Rate

* `final Response response = client.getRate().list(params_as_map_of_string_and_object);`
* `final Response response = client.getRate().history(params_as_map_of_string_and_object);`

#### Transaction

* `final Response response = client.getTransaction().list(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().pay(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().reverse(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().cancel(params_as_map_of_string_and_object);`

### Version 3.1

#### Account

* `final Response response = client.getAccount().info();`

#### Bill

* `final Response response = client.getBill().create(params_as_map_of_string_and_object)`
* `final Response response = client.getBill().show(id)`
* `final Response response = client.getBill().update(id, params_as_map_of_string_and_object)`
* `final Response response = client.getBill().refresh(id)`
* `final Response response = client.getBill().pay(id, params_as_map_of_string_and_object)`
* `final Response response = client.getBill().xdata(id)`
* `final Response response = client.getBill().list(params_as_map_of_string_and_object)`

#### Biller

* `final Response response = client.getBiller().credentials(params_as_map_of_string_and_object);`
* `final Response response = client.getBiller().topups(params_as_map_of_string_and_object);`
* `final Response response = client.getBiller().utilities(params_as_map_of_string_and_object);`

#### Rate

* `final Response response = client.getRate().list(params_as_map_of_string_and_object);`
* `final Response response = client.getRate().history(params_as_map_of_string_and_object);`

#### Transaction

* `final Response response = client.getTransaction().list(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().pay(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().reverse(params_as_map_of_string_and_object);`
* `final Response response = client.getTransaction().cancel(params_as_map_of_string_and_object);`

## Deploy

To deploy the project:

```
$ mvn clean source:jar javadoc:jar gpg:sign deploy -Dgpg.passphrase="THE_PASSPHRASE_OF_GPG_KEY"
```

## Test

To test with your current version of Node.js:

```
$ mvn test
```
