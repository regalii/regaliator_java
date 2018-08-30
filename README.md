# arcus Java Client

A Java HTTP client for consuming arcus API.

## Usage

### Pre-requisites

1.  Your arcus [credentials](https://www.arcusfi.com/contact-us/).

2.  Download [Gson](https://github.com/google/gson) library and put it in the
    root directory of this project:

```
regalii:regaliator_java$ ls
README.md gson-2.8.5.jar  pom.xml   src   target
```

Note: Latest version as of Aug. 2018 is 2.8.5

3.  Be sure to have [Maven](https://maven.apache.org/) installed:

```
regalii:regaliator_java$ mvn --version
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T14:39:06-05:00)
Maven home: /usr/local/Cellar/maven/3.5.0/libexec
```

### Example code

We have included a couple of example classes to help you start testing our
products right away:

- xData: `src/main/java/com/regalii/regaliator/RegaliatorXdataV32.java`
- xPay: `src/main/java/com/regalii/regaliator/RegaliatorXpayV32.java`

1.  Package Regaliator executing:

```
regalii:regaliator_java$ mvn package
```

This should generate the jar file: `target/regaliator-0.0.1-SNAPSHOT.jar`.

2.  Run any of the example classes provided like this:

```
regalii:regaliator_java$ java -cp "gson-2.8.5.jar:target/regaliator-0.0.1-SNAPSHOT.jar" com.regalii.regaliator.RegaliatorXdataV32
```

For more information about our API, please refer to the
[official documentation](https://docx.regalii.com/):

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

### Versions

The client supports 4 versions:

- `Version.v3_2` (recommended)
- `Version.v3_1`
- `Version.v3_0`
- `Version.v1_5`

## API

### Version 3.2

#### Account

- `final Response response = client.getAccount().info();`

#### Bill

- `final Response response = client.getBill().create(params_as_map_of_string_and_object)`
- `final Response response = client.getBill().show(uuid)`
- `final Response response = client.getBill().update(uuid, params_as_map_of_string_and_object)`
- `final Response response = client.getBill().list(params_as_map_of_string_and_object)`
- `final Response response = client.getBill().delete(uuid)`
- `final Response response = client.getBill().refresh(uuid)`
- `final Response response = client.getBill().bulk_refresh(params_as_map_of_string_and_object)`

#### Biller

- `final Response response = client.getBiller().list(params_as_map_of_string_and_object);`

#### Transaction

- `final Response response = client.getTransaction().list(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().create(params_as_map_of_string_and_object);`
- `final Response response = client.getTransaction().delete(uuid);`

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
