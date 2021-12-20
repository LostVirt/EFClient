# EFClient
Simple API Client that allows you to interact with eternal farm API with ease.


## Getting Started

```java
// Declare your API key.
String apiKey = "apiKey";

// Create a new instance of OkHttpClient.
OkHttpClient okHttpClient = new OkHttpClient();

// Create a new instance of EFClient.
EFClient efClient = new EFClient(okHttpClient, apiKey);
```

Eternal farm API has paging implemented, so we need to specify the page number and amount per page.
The default page number is 1 and the default amount per page is 1.


### Accounts
```java
// Get up to 100 of your accounts on eternal farm.
PageResult<EFAccount> accountPageResult = efClient.getAccountPage(1, 100);

// Print out all of your retrieved accounts.
accountPageResult.getData().forEach(System.out::println);
```


### Proxies
```java
// Get up to 100 of your proxies on eternal farm.
PageResult<EFProxy> proxyPageResult = efClient.getProxyPage(1, 100);

// Print out all of your retrieved proxies.
proxyPageResult.getData().forEach(System.out::println);
```

### Prime
```java
// Get up to 100 of your prime accounts on eternal farm.
PageResult<EFPrime> primePageResult = efClient.getPrimePage(1, 100);

// Print out all of your retrieved primes.
primePageResult.getData().forEach(System.out::println);
```

Library also able to create new accounts and update existing ones.

#### Maven
To use EFClient you need these dependencies:
```xml
<dependencies>
    <dependency>
        <groupId>com.squareup.okhttp3</groupId>
        <artifactId>okhttp</artifactId>
        <version>4.9.3</version>
    </dependency>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.9</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.22</version>
    </dependency>
</dependencies>
```
