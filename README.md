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

Eternal Farm API has paging implemented, so we need to specify the page number and amount per page.

### List Accounts
```java
// Get up to 100 of your accounts on eternal farm.
PageResult<EFAccount> pageResult = efClient.getAccountPage(1, 100);

// Print out all of your retrieved accounts.
pageResult.getData().forEach(System.out::println);
```

### List Accounts (With Filters)
```java
// All get page methods also support filter options.
Map<String, String> filters = new HashMap<>()
{{
	put("account_category_id", "1,2,3");
	put("country_code", "US,GB");
}};
PageResult<EFAccount> pageResult = efClient.getAccountPage(1, 100, filters);

// Print out all of your retrieved accounts.
pageResult.getData().forEach(System.out::println);
```

### Get Account By ID
```java
// Get a single account by id.
GetResult<EFAccount> result = efClient.getAccount(123);

// Print your retrieved account.
System.out.println(result.getData());
```

### Create Account
```java
// Create a new account entity and set some attributes.
// Username & Password are the only required attributes for creation.
EFAccount account = new EFAccount();
account.setUsername("test69@gmail.com");
account.setPassword("password420");

// Create the account record.
CreateResult<EFAccount> result = efClient.createAccount(account);

// Print the created account id.
System.out.println(result.getData().getId());
```

### Update Account By ID
#### (Fetching Existing Record First)
```java
// Get a single account by id.
EFAccount account = efClient.getAccount(123).getData();

// Change some attributes on the account.
account.setAccountCategoryId(69);
account.setOtpKey("ABC69420");

// Update the account record.
UpdateResult<EFAccount> result = efClient.updateAccount(account);
```

### Delete Account By ID
```java
// Create an account entity and set the id.
// You can optionally retrieve the account first then delete.
EFAccount account = new EFAccount();
account.setId(69);

// Delete the account record.
DeleteResult<EFAccount> result = efClient.deleteAccount(account);
```

### Get Account That Needs Tutorial Island Completion
#### (Returns an account that does not yet have the tutorialStatus field set to 1000, you must update this value on the account record for the call to work correctly. This is varp 281 in-game.)
```java
// Get next account that needs tutorial island completion.
Map<String, String> filters = new HashMap<>()
{{
    put("account_category_id", "1,2,3");
    put("country_code", "US,GB");
}};
GetResult<EFAccount> result = efClient.getNextTutorialAccount(filters);

// Print your retrieved account.
System.out.println(result.getData());
```

### Get Accounts That Need Checking
#### (Returns accounts that have not had their lastCheckedAt timestamp updated within the last 24 hours, you should update this timestamp after checking the account status. If you do not update this value, the account will only be retrieved from the API once per hour)
```java
// All get page methods also support filter options.
Map<String, String> filters = new HashMap<>()
{{
	put("account_category_id", "1,2,3");
	put("country_code", "US,GB");
}};
PageResult<EFAccount> pageResult = efClient.getNextCheckAccounts(3, filters);

// Print out all of your retrieved accounts.
pageResult.getData().forEach(System.out::println);
```

### List Account Categories
```java
// Get up to 100 of your account categories on eternal farm.
PageResult<EFAccountCategory> pageResult = efClient.getAccountCategoryPage(1, 100);

// Print out all of your retrieved account categories.
pageResult.getData().forEach(System.out::println);
```

### List Proxies
```java
// Get up to 100 of your proxies on eternal farm.
PageResult<EFProxy> pageResult = efClient.getProxyPage(1, 100);

// Print out all of your retrieved proxies.
pageResult.getData().forEach(System.out::println);
```

### List Proxies (With Filters)
```java
// All get page methods also support filter options.
Map<String, String> filters = new HashMap<>()
{{
	put("proxy_category_id", "1,2,3");
	put("country_code", "US,GB");
}};
PageResult<EFProxy> pageResult = efClient.getProxyPage(1, 100, filters);

// Print out all of your retrieved proxies.
pageResult.getData().forEach(System.out::println);
```

### Get Proxy By ID
```java
// Get a single proxy by id.
GetResult<EFProxy> result = efClient.getProxy(69);

// Print your retrieved proxy.
System.out.println(result.getData());
```

### Create Proxy
```java
// Create a new proxy entity and set some attributes.
// Username & Password are the only required attributes for creation.
EFProxy proxy = new EFProxy();
proxy.setType("socks5");
proxy.setIpAddress("127.0.0.1");
proxy.setPort(6969);
proxy.setUsername("test69");
proxy.setPassword("password420");

// Create the proxy record.
CreateResult<EFProxy> result = efClient.createProxy(proxy);

// Print the created proxy id.
System.out.println(result.getData().getId());
```

### Update Proxy By ID
#### (Fetching Existing Record First)
```java
// Get a single proxy by id.
EFProxy proxy = efClient.getProxy(69).getData();

// Change some attributes on the proxy.
proxy.setProxyCategoryId(69);

// Update the proxy record.
UpdateResult<EFProxy> result = efClient.updateProxy(proxy);
```

### Delete Proxy By ID
```java
// Create an proxy entity and set the id.
// You can optionally retrieve the proxy first then delete.
EFProxy proxy = new EFProxy();
proxy.setId(69);

// Delete the proxy record.
DeleteResult<EFProxy> result = efClient.deleteProxy(proxy);
```

### List Proxy Categories
```java
// Get up to 100 of your proxy categories on eternal farm.
PageResult<EFProxyCategory> pageResult = efClient.getProxyCategoryPage(1, 100);

// Print out all of your retrieved proxy categories.
		pageResult.getData().forEach(System.out::println);
```

### List Agents
```java
// Get up to 100 of your agents on eternal farm.
PageResult<EFAgent> pageResult = efClient.getAgentPage(1, 100);

// Print out all of your retrieved agents.
		pageResult.getData().forEach(System.out::println);
```

### List Bots
```java
// Get up to 100 of your bots on eternal farm.
PageResult<EFBot> pageResult = efClient.getBotPage(1, 100);

// Print out all of your retrieved bots.
pageResult.getData().forEach(System.out::println);
```

### List Tasks
```java
// Get up to 100 of your tasks on eternal farm.
PageResult<EFTask> pageResult = efClient.getTaskPage(1, 100);

// Print out all of your retrieved tasks.
pageResult.getData().forEach(System.out::println);
```

### Start Task On An Agent
```java
// Fetch task with id 69
EFTask task = efClient.getTask(69).getData();

// Start the task on agent with id 420 and account with id 1337
StartTaskResult result = efClient.startTaskResult(task, 420, 1337);
```

### Stop Task On An Agent
```java
// Fetch task with id 69
EFTask task = efClient.getTask(69).getData();

// Stop the task on agent with id 420 and account with id 1337
StartTaskResult result = efClient.stopTaskResult(task, 420, 1337);
```

### List Prime Link Requests
```java
// Get up to 100 of your prime accounts on eternal farm.
PageResult<EFPrimeLinkRequest> pageResult = efClient.getPrimeLinkRequestPage(1, 100);

// Print out all of your retrieved prime link requests.
pageResult.getData().forEach(System.out::println);
```

### Entities Available

The library can be used to fetch, create, update & delete the following entities using similar methods outlined above:

* Accounts (also fetch next tutorial / next check accounts)
* Account Categories
* Proxies
* Proxy Categories
* Agents
* Bots
* Tasks (also start/stop tasks on agents)
* Prime Link Requests

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
