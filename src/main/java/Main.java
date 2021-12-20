import entities.EFAccount;
import entities.EFPrime;
import entities.EFProxy;
import okhttp3.OkHttpClient;
import result.PageResult;

import java.io.IOException;

/**
 * @author LostVirt
 * @created 20/12/2021 - 15:07
 * @project EFClient
 */
public class Main
{
	public static void main(String[] args) throws IOException
	{
		// Example of how to use the API.
		// The API is a simple Java library that allows you to interact with eternal farm API.

		// Declare your API key.
		String apiKey = "apiKey";

		// Create a new instance of okHttpClient.
		OkHttpClient okHttpClient = new OkHttpClient();

		// Create a new instance of the EFClient.
		EFClient efClient = new EFClient(okHttpClient, apiKey);

		// Eternal farm API has paging implemented, so we need to specify the page number and amount per page.
		// The default page number is 1 and the default amount per page is 1.

		// Get up to 100 of your accounts on eternal farm.
		PageResult<EFAccount> accountPageResult = efClient.getAccountPage(1, 100);

		// Print out all of your retrieved accounts.
		accountPageResult.getData().forEach(System.out::println);

		// -------------------------------------------------- //

		// Get up to 100 of your proxies on eternal farm.
		PageResult<EFProxy> proxyPageResult = efClient.getProxyPage(1, 100);

		// Print out all of your retrieved proxies.
		proxyPageResult.getData().forEach(System.out::println);

		// -------------------------------------------------- //

		// Get up to 100 of your prime accounts on eternal farm.
		PageResult<EFPrime> primePageResult = efClient.getPrimePage(1, 100);

		// Print out all of your retrieved proxies.
		primePageResult.getData().forEach(System.out::println);

		// -------------------------------------------------- //

		// Library also able to create new accounts and update existing ones.
	}
}
