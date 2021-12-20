import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import entities.EFAccount;
import entities.EFPrime;
import entities.EFProxy;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.jetbrains.annotations.Nullable;
import result.PageResult;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author LostVirt
 * @created 20/12/2021 - 14:59
 * @project EFClient
 */
@AllArgsConstructor
public class EFClient
{
	private final OkHttpClient client;
	private final String apiKey;

	private HttpUrl getBaseUrl()
	{
		return HttpUrl.parse("https://eternalfarm.net/api/");
	}

	public boolean postAccountCreate(EFAccount account)
	{
		try (Response response = client.newCall(buildAccountCreateRequest(new Gson().toJson(account))).execute())
		{
			return response.isSuccessful();
		}
		catch (IOException e)
		{
			return false;
		}
	}

	public boolean postAccountUpdate(EFAccount account)
	{
		try (Response response = client.newCall(buildAccountUpdateRequest(account.getId(), new Gson().toJson(account))).execute())
		{
			return response.isSuccessful();
		}
		catch (IOException e)
		{
			return false;
		}
	}

	public PageResult<EFAccount> getAccountPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFAccount.class, buildFetchAccountsRequest(Math.max(page, 1), Math.max(perPage, 1)));
	}

	public PageResult<EFProxy> getProxyPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFProxy.class, buildFetchProxyRequest(Math.max(page, 1), Math.max(perPage, 1)));
	}

	public PageResult<EFPrime> getPrimePage(int page, int perPage) throws IOException
	{
		return getPageResult(EFPrime.class, buildFetchPrimeRequest(Math.max(page, 1), Math.max(perPage, 1)));
	}


	private <T> PageResult<T> getPageResult(Class<T> cls, Request request) throws IOException
	{
		try (Response response = client.newCall(request).execute())
		{
			if (!response.isSuccessful())
			{
				if (response.code() == 404)
				{
					return null;
				}

				throw new IOException("Error retrieving data from Eternal Farm: " + response);
			}

			String responseBody = Objects.requireNonNull(response.body()).string();

			JsonObject element = JsonParser.parseString(responseBody).getAsJsonObject();
			if (element.has("success") && !element.get("success").getAsBoolean())
			{
				throw new IOException("Data retrieval was not successful.");
			}

			if (!element.has("data"))
			{
				throw new IOException("Data was not found.");
			}

			Type type = TypeToken.getParameterized(PageResult.class, cls).getType();
			return new Gson().fromJson(element.get("data"), type);
		}
	}

	private Request buildFetchProxyRequest(int page, int perPage)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment("proxies")
						.addQueryParameter("key", apiKey)
						.addQueryParameter("page", String.valueOf(page))
						.addQueryParameter("per_page", String.valueOf(perPage))
						.build())
				.build();
	}

	private Request buildFetchAccountsRequest(int page, int perPage)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment("accounts")
						.addQueryParameter("key", apiKey)
						.addQueryParameter("page", String.valueOf(page))
						.addQueryParameter("per_page", String.valueOf(perPage))
						.build())
				.build();
	}

	private Request buildAccountCreateRequest(String data)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment("accounts")
						.addQueryParameter("key", apiKey)
						.build())
				.post(RequestBody.create(data, MediaType.parse("application/json; charset=utf-8")))
				.build();
	}

	private Request buildFetchPrimeRequest(int page, int perPage)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment("prime-link-requests")
						.addQueryParameter("key", apiKey)
						.addQueryParameter("page", String.valueOf(page))
						.addQueryParameter("per_page", String.valueOf(perPage))
						.build())
				.build();
	}

	private Request buildAccountUpdateRequest(int accountId, String data)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment("accounts")
						.addPathSegment(String.valueOf(accountId))
						.addPathSegment("status")
						.addQueryParameter("key", apiKey)
						.build())
				.post(RequestBody.create(data, MediaType.parse("application/json; charset=utf-8")))
				.build();
	}
}
