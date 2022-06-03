import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import entities.*;
import lombok.AllArgsConstructor;
import okhttp3.*;
import result.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
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
	private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();

	private HttpUrl getBaseUrl()
	{
		return HttpUrl.parse("https://api.eternalfarm.net/v1");
	}

	public PageResult<EFAccount> getAccountPage(int page, int perPage) throws IOException
	{
		return getAccountPage(page, perPage, null);
	}

	public PageResult<EFAccount> getAccountPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFAccount.class, buildFetchRequest("accounts", page, perPage, filters));
	}

	public GetResult<EFAccount> getAccount(int id) throws IOException
	{
		return getGetResult(EFAccount.class, buildGetRequest("accounts", id));
	}

	public CreateResult<EFAccount> createAccount(EFAccount account) throws IOException
	{
		return getCreateResult(EFAccount.class, buildCreateRequest("accounts", gson.toJson(account)));
	}

	public UpdateResult<EFAccount> updateAccount(EFAccount account) throws IOException
	{
		return getUpdateResult(EFAccount.class, buildUpdateRequest("accounts", account.getId(), gson.toJson(account)));
	}

	public DeleteResult<EFAccount> deleteAccount(EFAccount account) throws IOException
	{
		return getDeleteResult(EFAccount.class, buildDeleteRequest("accounts", account.getId()));
	}

	public GetResult<EFAccount> getNextTutorialAccount(Map<String, String> filters) throws IOException
	{
		return getGetResult(EFAccount.class, buildFetchRequest("accounts/next-tutorial", 0, 0, filters));
	}

	public PageResult<EFAccount> getNextCheckAccounts(int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFAccount.class, buildFetchRequest("accounts/next-check", 0, perPage, filters));
	}

	public PageResult<EFAccountCategory> getAccountCategoryPage(int page, int perPage) throws IOException
	{
		return getAccountCategoryPage(page, perPage, null);
	}

	public PageResult<EFAccountCategory> getAccountCategoryPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFAccountCategory.class, buildFetchRequest("account-categories", page, perPage, filters));
	}

	public GetResult<EFAccountCategory> getAccountCategory(int id) throws IOException
	{
		return getGetResult(EFAccountCategory.class, buildGetRequest("account-categories", id));
	}

	public CreateResult<EFAccountCategory> createAccountCategory(EFAccountCategory accountCategory) throws IOException
	{
		return getCreateResult(EFAccountCategory.class, buildCreateRequest("account-categories", gson.toJson(accountCategory)));
	}

	public UpdateResult<EFAccountCategory> updateAccountCategory(EFAccountCategory accountCategory) throws IOException
	{
		return getUpdateResult(EFAccountCategory.class, buildUpdateRequest("account-categories", accountCategory.getId(), gson.toJson(accountCategory)));
	}

	public DeleteResult<EFAccountCategory> deleteAccountCategory(EFAccountCategory accountCategory) throws IOException
	{
		return getDeleteResult(EFAccountCategory.class, buildDeleteRequest("account-categories", accountCategory.getId()));
	}

	public PageResult<EFBot> getBotPage(int page, int perPage) throws IOException
	{
		return getBotPage(page, perPage, null);
	}

	public PageResult<EFBot> getBotPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFBot.class, buildFetchRequest("bots", page, perPage, filters));
	}

	public GetResult<EFBot> getBot(int id) throws IOException
	{
		return getGetResult(EFBot.class, buildGetRequest("bots", id));
	}

	public CreateResult<EFBot> createBot(EFBot bot) throws IOException
	{
		return getCreateResult(EFBot.class, buildCreateRequest("bots", gson.toJson(bot)));
	}

	public UpdateResult<EFBot> updateBot(EFBot bot) throws IOException
	{
		return getUpdateResult(EFBot.class, buildUpdateRequest("bots", bot.getId(), gson.toJson(bot)));
	}

	public DeleteResult<EFBot> deleteBot(EFBot bot) throws IOException
	{
		return getDeleteResult(EFBot.class, buildDeleteRequest("bots", bot.getId()));
	}

	public PageResult<EFAgent> getAgentPage(int page, int perPage) throws IOException
	{
		return getAgentPage(page, perPage, null);
	}

	public PageResult<EFAgent> getAgentPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFAgent.class, buildFetchRequest("agents", page, perPage, filters));
	}

	public GetResult<EFAgent> getAgent(int id) throws IOException
	{
		return getGetResult(EFAgent.class, buildGetRequest("agents", id));
	}

	public CreateResult<EFAgent> createAgent(EFAgent agent) throws IOException
	{
		return getCreateResult(EFAgent.class, buildCreateRequest("agents", gson.toJson(agent)));
	}

	public UpdateResult<EFAgent> updateAgent(EFAgent agent) throws IOException
	{
		return getUpdateResult(EFAgent.class, buildUpdateRequest("agents", agent.getId(), gson.toJson(agent)));
	}

	public DeleteResult<EFAgent> deleteAgent(EFAgent agent) throws IOException
	{
		return getDeleteResult(EFAgent.class, buildDeleteRequest("agents", agent.getId()));
	}

	public PageResult<EFProxy> getProxyPage(int page, int perPage) throws IOException
	{
		return getProxyPage(page, perPage, null);
	}

	public PageResult<EFProxy> getProxyPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFProxy.class,buildFetchRequest("proxies", page, perPage, filters));
	}

	public GetResult<EFProxy> getProxy(int id) throws IOException
	{
		return getGetResult(EFProxy.class, buildGetRequest("proxies", id));
	}

	public CreateResult<EFProxy> createProxy(EFProxy proxy) throws IOException
	{
		return getCreateResult(EFProxy.class, buildCreateRequest("proxies", gson.toJson(proxy)));
	}

	public UpdateResult<EFProxy> updateProxy(EFProxy proxy) throws IOException
	{
		return getUpdateResult(EFProxy.class, buildUpdateRequest("proxies", proxy.getId(), gson.toJson(proxy)));
	}

	public DeleteResult<EFProxy> deleteProxy(EFProxy proxy) throws IOException
	{
		return getDeleteResult(EFProxy.class, buildDeleteRequest("proxies", proxy.getId()));
	}

	public PageResult<EFProxyCategory> getProxyCategoryPage(int page, int perPage) throws IOException
	{
		return getProxyCategoryPage(page, perPage, null);
	}

	public PageResult<EFProxyCategory> getProxyCategoryPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFProxyCategory.class, buildFetchRequest("proxy-categories", page, perPage, filters));
	}

	public GetResult<EFProxyCategory> getProxyCategory(int id) throws IOException
	{
		return getGetResult(EFProxyCategory.class, buildGetRequest("proxy-categories", id));
	}

	public CreateResult<EFProxyCategory> createProxyCategory(EFProxyCategory proxyCategory) throws IOException
	{
		return getCreateResult(EFProxyCategory.class, buildCreateRequest("proxy-categories", gson.toJson(proxyCategory)));
	}

	public UpdateResult<EFProxyCategory> updateProxyCategory(EFProxyCategory proxyCategory) throws IOException
	{
		return getUpdateResult(EFProxyCategory.class, buildUpdateRequest("proxy-categories", proxyCategory.getId(), gson.toJson(proxyCategory)));
	}

	public DeleteResult<EFProxyCategory> deleteAccount(EFProxyCategory proxy) throws IOException
	{
		return getDeleteResult(EFProxyCategory.class, buildDeleteRequest("proxy-categories", proxy.getId()));
	}

	public PageResult<EFTask> getTaskPage(int page, int perPage) throws IOException
	{
		return getTaskPage(page, perPage, null);
	}

	public PageResult<EFTask> getTaskPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFTask.class, buildFetchRequest("tasks", page, perPage, filters));
	}

	public GetResult<EFTask> getTask(int id) throws IOException
	{
		return getGetResult(EFTask.class, buildGetRequest("tasks", id));
	}

	public CreateResult<EFTask> createTask(EFTask task) throws IOException
	{
		return getCreateResult(EFTask.class, buildCreateRequest("tasks", gson.toJson(task)));
	}

	public UpdateResult<EFTask> updateTask(EFTask task) throws IOException
	{
		return getUpdateResult(EFTask.class, buildUpdateRequest("tasks", task.getId(), gson.toJson(task)));
	}

	public DeleteResult<EFTask> deleteTask(EFTask task) throws IOException
	{
		return getDeleteResult(EFTask.class, buildDeleteRequest("tasks", task.getId()));
	}

	public StartTaskResult startTaskResult(EFTask task, int agentId, int accountId) throws IOException
	{
		try (Response response = client.newCall(buildStartTaskRequest(task.getId(), agentId, accountId)).execute())
		{
			String responseBody = response.body() != null ? Objects.requireNonNull(response.body()).string() : "";

			if (!response.isSuccessful())
			{
				if (response.code() == 401)
				{
					throw new IOException("Unauthorized request, API key is either undefined or invalid.");
				}

				throw new IOException("Error starting task with Eternal Farm: " + response + " - " + responseBody);
			}

			JsonObject element = JsonParser.parseString(responseBody).getAsJsonObject();
			if (element.has("success") && !element.get("success").getAsBoolean())
			{
				throw new IOException("Start task request was not successful.");
			}

			return gson.fromJson(element, StartTaskResult.class);
		}
	}

	public StopTaskResult stopTaskResult(EFTask task, int agentId, int accountId) throws IOException
	{
		try (Response response = client.newCall(buildStopTaskRequest(task.getId(), agentId, accountId)).execute())
		{
			String responseBody = response.body() != null ? Objects.requireNonNull(response.body()).string() : "";

			if (!response.isSuccessful())
			{
				if (response.code() == 401)
				{
					throw new IOException("Unauthorized request, API key is either undefined or invalid.");
				}

				throw new IOException("Error stopping task with Eternal Farm: " + response + " - " + responseBody);
			}

			JsonObject element = JsonParser.parseString(responseBody).getAsJsonObject();
			if (element.has("success") && !element.get("success").getAsBoolean())
			{
				throw new IOException("Stop task request was not successful.");
			}

			return gson.fromJson(element, StopTaskResult.class);
		}
	}

	public PageResult<EFPrimeLinkRequest> getPrimeLinkRequestPage(int page, int perPage) throws IOException
	{
		return getPrimeLinkRequestPage(page, perPage, null);
	}

	public PageResult<EFPrimeLinkRequest> getPrimeLinkRequestPage(int page, int perPage, Map<String, String> filters) throws IOException
	{
		return getPageResult(EFPrimeLinkRequest.class, buildFetchRequest("prime-link-requests", page, perPage, filters));
	}

	public GetResult<EFPrimeLinkRequest> getPrimeLinkRequest(int id) throws IOException
	{
		return getGetResult(EFPrimeLinkRequest.class, buildGetRequest("prime-link-requests", id));
	}

	public CreateResult<EFPrimeLinkRequest> createPrimeLinkRequest(EFPrimeLinkRequest primeLinkRequest) throws IOException
	{
		return getCreateResult(EFPrimeLinkRequest.class, buildCreateRequest("prime-link-requests", gson.toJson(primeLinkRequest)));
	}

	public UpdateResult<EFPrimeLinkRequest> updatePrimeLinkRequest(EFPrimeLinkRequest primeLinkRequest) throws IOException
	{
		return getUpdateResult(EFPrimeLinkRequest.class, buildUpdateRequest("prime-link-requests", primeLinkRequest.getId(), gson.toJson(primeLinkRequest)));
	}

	public DeleteResult<EFPrimeLinkRequest> deletePrimeLinkRequest(EFPrimeLinkRequest primeLinkRequest) throws IOException
	{
		return getDeleteResult(EFPrimeLinkRequest.class, buildDeleteRequest("prime-link-requests", primeLinkRequest.getId()));
	}

	private <T> PageResult<T> getPageResult(Class<T> cls, Request request) throws IOException
	{
		try (Response response = client.newCall(request).execute())
		{
			String responseBody = response.body() != null ? Objects.requireNonNull(response.body()).string() : "";

			if (!response.isSuccessful())
			{
				if (response.code() == 401)
				{
					throw new IOException("Unauthorized request, API key is either undefined or invalid.");
				}

				throw new IOException("Error retrieving data from Eternal Farm: " + response + " - " + responseBody);
			}

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
			return gson.fromJson(element, type);
		}
	}

	private <T> GetResult<T> getGetResult(Class<T> cls, Request request) throws IOException
	{
		try (Response response = client.newCall(request).execute())
		{
			String responseBody = response.body() != null ? Objects.requireNonNull(response.body()).string() : "";

			if (!response.isSuccessful())
			{
				if (response.code() == 401)
				{
					throw new IOException("Unauthorized request, API key is either undefined or invalid.");
				}

				throw new IOException("Error getting data from Eternal Farm: " + response + " - " + responseBody);
			}

			JsonObject element = JsonParser.parseString(responseBody).getAsJsonObject();
			if (element.has("success") && !element.get("success").getAsBoolean())
			{
				throw new IOException("Data get was not successful.");
			}

			if (!element.has("data"))
			{
				throw new IOException("Data was not found.");
			}

			Type type = TypeToken.getParameterized(GetResult.class, cls).getType();
			return gson.fromJson(element, type);
		}
	}

	private <T> CreateResult<T> getCreateResult(Class<T> cls, Request request) throws IOException
	{
		try (Response response = client.newCall(request).execute())
		{
			String responseBody = response.body() != null ? Objects.requireNonNull(response.body()).string() : "";

			if (!response.isSuccessful())
			{
				if (response.code() == 401)
				{
					throw new IOException("Unauthorized request, API key is either undefined or invalid.");
				}

				throw new IOException("Error creating data from Eternal Farm: " + response + " - " + responseBody);
			}

			JsonObject element = JsonParser.parseString(responseBody).getAsJsonObject();
			if (element.has("success") && !element.get("success").getAsBoolean())
			{
				throw new IOException("Data create was not successful.");
			}

			if (!element.has("data"))
			{
				throw new IOException("Data was not found.");
			}

			Type type = TypeToken.getParameterized(CreateResult.class, cls).getType();
			return gson.fromJson(element, type);
		}
	}

	private <T> UpdateResult<T> getUpdateResult(Class<T> cls, Request request) throws IOException
	{
		try (Response response = client.newCall(request).execute())
		{
			String responseBody = response.body() != null ? Objects.requireNonNull(response.body()).string() : "";

			if (!response.isSuccessful())
			{
				if (response.code() == 401)
				{
					throw new IOException("Unauthorized request, API key is either undefined or invalid.");
				}

				throw new IOException("Error updating data from Eternal Farm: " + response + " - " + responseBody);
			}

			JsonObject element = JsonParser.parseString(responseBody).getAsJsonObject();
			if (element.has("success") && !element.get("success").getAsBoolean())
			{
				throw new IOException("Data update was not successful.");
			}

			if (!element.has("data"))
			{
				throw new IOException("Data was not found.");
			}

			Type type = TypeToken.getParameterized(UpdateResult.class, cls).getType();
			return gson.fromJson(element, type);
		}
	}

	private <T> DeleteResult<T> getDeleteResult(Class<T> cls, Request request) throws IOException
	{
		try (Response response = client.newCall(request).execute())
		{
			if (!response.isSuccessful())
			{
				if (response.code() == 401)
				{
					throw new IOException("Unauthorized request, API key is either undefined or invalid.");
				}

				throw new IOException("Error deleting data from Eternal Farm: " + response);
			}

			String responseBody = Objects.requireNonNull(response.body()).string();

			JsonObject element = JsonParser.parseString(responseBody).getAsJsonObject();
			if (element.has("success") && !element.get("success").getAsBoolean())
			{
				throw new IOException("Data delete was not successful.");
			}

			Type type = TypeToken.getParameterized(DeleteResult.class, cls).getType();
			return gson.fromJson(element, type);
		}
	}

	private Request buildFetchRequest(String type, int page, int perPage, Map<String, String> filters)
	{
		HttpUrl.Builder urlBuilder = getBaseUrl().newBuilder()
				.addPathSegment(type)
				.addQueryParameter("key", apiKey);

		if (page > 0)
		{
			urlBuilder.addQueryParameter("page", String.valueOf(page));
		}

		if (perPage > 0)
		{
			urlBuilder.addQueryParameter("per_page", String.valueOf(perPage));
		}

		if (filters != null)
		{
			filters.forEach(urlBuilder::addQueryParameter);
		}

		return new Request.Builder()
				.url(urlBuilder.build())
				.build();
	}

	private Request buildGetRequest(String type, int id)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment(type)
						.addPathSegment(String.valueOf(id))
						.addQueryParameter("key", apiKey)
						.build())
				.get()
				.build();
	}

	private Request buildCreateRequest(String type, String data)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment(type)
						.addQueryParameter("key", apiKey)
						.build())
				.post(RequestBody.create(data, MediaType.parse("application/json; charset=utf-8")))
				.build();
	}

	private Request buildUpdateRequest(String type, int id, String data)
	{
		System.out.println(data);
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment(type)
						.addPathSegment(String.valueOf(id))
						.addQueryParameter("key", apiKey)
						.build())
				.put(RequestBody.create(data, MediaType.parse("application/json; charset=utf-8")))
				.build();
	}

	private Request buildDeleteRequest(String type, int id)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment(type)
						.addPathSegment(String.valueOf(id))
						.addQueryParameter("key", apiKey)
						.build())
				.delete()
				.build();
	}

	private Request buildStartTaskRequest(int taskId, int agentId, int accountId)
	{
		JsonObject data = new JsonObject();
		data.addProperty("agent_id", agentId);
		data.addProperty("account_id", accountId);

		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment("tasks")
						.addPathSegment(String.valueOf(taskId))
						.addPathSegment("start")
						.addQueryParameter("key", apiKey)
						.build())
				.post(RequestBody.create(data.toString(), MediaType.parse("application/json; charset=utf-8")))
				.build();
	}

	private Request buildStopTaskRequest(int taskId, int agentId, int accountId)
	{
		JsonObject data = new JsonObject();
		data.addProperty("agent_id", agentId);
		data.addProperty("account_id", accountId);

		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment("tasks")
						.addPathSegment(String.valueOf(taskId))
						.addPathSegment("stop")
						.addQueryParameter("key", apiKey)
						.build())
				.post(RequestBody.create(data.toString(), MediaType.parse("application/json; charset=utf-8")))
				.build();
	}
}
