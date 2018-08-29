# Regalii Java Client

A Java HTTP client for consuming Regalii's API.

## Usage

You need a `Configuration` instance and get from Regalii your [credentials](https://www.regalii.com/contact-us).

```java
package com.regalii.regaliator;

import com.regalii.regaliator.api.Configuration;
import com.regalii.regaliator.api.Response;
import com.regalii.regaliator.api.Version;
import com.regalii.regaliator.v15.Client;

import java.util.Map;

public class Regaliator {
    static public void main(final String[] args) {
        final Configuration config = new Configuration(Version.v3_2, "apix.casiregalii.com", "api_key", "secret_key");
        config.setUseSSL(true);

        final Client client = (Client) config.buildClient();
        final Response response = client.getAccount().info();
        final Map<String, Object> body = response.body();

        if(response.isSuccess()){
          System.out.println(body.get("balance"));
        } else {
          System.out.println("Code:" + response.httpCode());
          System.out.println("Error:" + body.get("message"));
        }
    }
}
```

This will display the balance:

```
=> 1797.41
```

### Example

1.  Save the previous code in: `src/main/java/com/regaliator/Regaliator.java`

2.  Download [Gson](https://github.com/google/gson) library and put it in the root dir of this project:

```
regalii:regaliator_java$ ls
README.md gson-2.8.5.jar  pom.xml   src   target
```

3.  Be sure to have [Maven](https://maven.apache.org/) installed:

```
regalii:regaliator_java$ mvn --version
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T14:39:06-05:00)
Maven home: /usr/local/Cellar/maven/3.5.0/libexec
```

4.  Package Regaliator executing:

```
regalii:regaliator_java$ mvn package
```

This should generate the jar file: `target/regaliator-0.0.1-SNAPSHOT.jar`.

5.  Run the main class we just added to the project, this will output your balance:

```
regalii:regaliator_java$ java -cp "gson-2.8.5.jar:target/regaliator-0.0.1-SNAPSHOT.jar" com.regalii.regaliator.Regaliator
1797.41
```

In case of wrong credentials, you will a 401 Unauthorized HTTP response.

### Versions

The client supports 3 versions:

- `Version.v1_5`
- `Version.v3_0`
- `Version.v3_1`

## API

### Version 1.5

#### Account

- `final Response response = client.getAccount().info();`

#### Bill

- `final Response response = client.getBill().index(params_as_map_of_string_and_object);`
- `final Response response = client.getBill().consult(params_as_map_of_string_and_object);`
- `final Response response = client.getBill().pay(params_as_map_of_string_and_object);`
- `final Response response = client.getBill().check(params_as_map_of_string_and_object);`

#### Biller

- `final Response response = client.getBiller().topups(params_as_map_of_string_and_object);`
- `final Response response = client.getBiller().utilities(params_as_map_of_string_and_object);`

#### Rate

- `final Response response = client.getRate().list(params_as_map_of_string_and_object);`
- `final Response response = client.getRate().history(params_as_map_of_string_and_object);`

#### Transaction

- `final Response response = client.getTransaction().list(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().pay(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().reverse(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().cancel(params_as_map_of_string_and_object);`

### Version 3.0

#### Account

- `final Response response = client.getAccount().info();`

#### Bill

- `final Response response = client.getBill().create(params_as_map_of_string_and_object)`
- `final Response response = client.getBill().show(id)`
- `final Response response = client.getBill().update(id, params_as_map_of_string_and_object)`
- `final Response response = client.getBill().refresh(id)`
- `final Response response = client.getBill().pay(id, params_as_map_of_string_and_object)`
- `final Response response = client.getBill().xdata(id)`
- `final Response response = client.getBill().list(params_as_map_of_string_and_object)`

#### Biller

- `final Response response = client.getBiller().credentials(params_as_map_of_string_and_object);`
- `final Response response = client.getBiller().topups(params_as_map_of_string_and_object);`
- `final Response response = client.getBiller().utilities(params_as_map_of_string_and_object);`

#### Rate

- `final Response response = client.getRate().list(params_as_map_of_string_and_object);`
- `final Response response = client.getRate().history(params_as_map_of_string_and_object);`

#### Transaction

- `final Response response = client.getTransaction().list(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().pay(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().reverse(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().cancel(params_as_map_of_string_and_object);`

### Version 3.1

#### Account

- `final Response response = client.getAccount().info();`

#### Bill

- `final Response response = client.getBill().create(params_as_map_of_string_and_object)`
- `final Response response = client.getBill().show(id)`
- `final Response response = client.getBill().update(id, params_as_map_of_string_and_object)`
- `final Response response = client.getBill().refresh(id)`
- `final Response response = client.getBill().pay(id, params_as_map_of_string_and_object)`
- `final Response response = client.getBill().xdata(id)`
- `final Response response = client.getBill().list(params_as_map_of_string_and_object)`

#### Biller

- `final Response response = client.getBiller().credentials(params_as_map_of_string_and_object);`
- `final Response response = client.getBiller().topups(params_as_map_of_string_and_object);`
- `final Response response = client.getBiller().utilities(params_as_map_of_string_and_object);`

#### Rate

- `final Response response = client.getRate().list(params_as_map_of_string_and_object);`
- `final Response response = client.getRate().history(params_as_map_of_string_and_object);`

#### Transaction

- `final Response response = client.getTransaction().list(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().pay(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().reverse(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().cancel(params_as_map_of_string_and_object);`

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
