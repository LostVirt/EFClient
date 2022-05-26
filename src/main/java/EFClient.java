import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import entities.*;
import lombok.AllArgsConstructor;
import okhttp3.*;
import result.*;

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
		return HttpUrl.parse("https://api.eternalfarm.net/v1");
	}

	public PageResult<EFAccount> getAccountPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFAccount.class, buildFetchRequest("accounts", page, perPage));
	}

	public CreateResult<EFAccount> createAccount(EFAccount account) throws IOException
	{
		return getCreateResult(EFAccount.class, buildCreateRequest("accounts", new Gson().toJson(account)));
	}

	public UpdateResult<EFAccount> updateAccount(EFAccount account) throws IOException
	{
		return getUpdateResult(EFAccount.class, buildUpdateRequest("accounts", account.getId(), new Gson().toJson(account)));
	}

	public DeleteResult<EFAccount> deleteAccount(EFAccount account) throws IOException
	{
		return getDeleteResult(EFAccount.class, buildDeleteRequest("accounts", account.getId()));
	}

	public PageResult<EFAccountCategory> getAccountCategoryPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFAccountCategory.class, buildFetchRequest("account-categories", page, perPage));
	}

	public CreateResult<EFAccountCategory> createAccountCategory(EFAccountCategory accountCategory) throws IOException
	{
		return getCreateResult(EFAccountCategory.class, buildCreateRequest("account-categories", new Gson().toJson(accountCategory)));
	}

	public UpdateResult<EFAccountCategory> updateAccountCategory(EFAccountCategory accountCategory) throws IOException
	{
		return getUpdateResult(EFAccountCategory.class, buildUpdateRequest("account-categories", accountCategory.getId(), new Gson().toJson(accountCategory)));
	}

	public DeleteResult<EFAccountCategory> deleteAccountCategory(EFAccountCategory accountCategory) throws IOException
	{
		return getDeleteResult(EFAccountCategory.class, buildDeleteRequest("account-categories", accountCategory.getId()));
	}

	public PageResult<EFBot> getBotPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFBot.class, buildFetchRequest("bots", page, perPage));
	}

	public CreateResult<EFBot> createBot(EFBot bot) throws IOException
	{
		return getCreateResult(EFBot.class, buildCreateRequest("bots", new Gson().toJson(bot)));
	}

	public UpdateResult<EFBot> updateBot(EFBot bot) throws IOException
	{
		return getUpdateResult(EFBot.class, buildUpdateRequest("bots", bot.getId(), new Gson().toJson(bot)));
	}

	public DeleteResult<EFBot> deleteBot(EFBot bot) throws IOException
	{
		return getDeleteResult(EFBot.class, buildDeleteRequest("bots", bot.getId()));
	}

	public PageResult<EFAgent> getAgentPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFAgent.class, buildFetchRequest("agents", page, perPage));
	}

	public CreateResult<EFAgent> createAgent(EFAgent agent) throws IOException
	{
		return getCreateResult(EFAgent.class, buildCreateRequest("agents", new Gson().toJson(agent)));
	}

	public UpdateResult<EFAgent> updateAgent(EFAgent agent) throws IOException
	{
		return getUpdateResult(EFAgent.class, buildUpdateRequest("agents", agent.getId(), new Gson().toJson(agent)));
	}

	public DeleteResult<EFAgent> deleteAgent(EFAgent agent) throws IOException
	{
		return getDeleteResult(EFAgent.class, buildDeleteRequest("agents", agent.getId()));
	}

	public PageResult<EFProxy> getProxyPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFProxy.class,buildFetchRequest("proxies", page, perPage));
	}

	public CreateResult<EFProxy> createProxy(EFProxy proxy) throws IOException
	{
		return getCreateResult(EFProxy.class, buildCreateRequest("proxies", new Gson().toJson(proxy)));
	}

	public UpdateResult<EFProxy> updateProxy(EFProxy proxy) throws IOException
	{
		return getUpdateResult(EFProxy.class, buildUpdateRequest("proxies", proxy.getId(), new Gson().toJson(proxy)));
	}

	public DeleteResult<EFProxy> deleteProxy(EFProxy proxy) throws IOException
	{
		return getDeleteResult(EFProxy.class, buildDeleteRequest("proxies", proxy.getId()));
	}

	public PageResult<EFProxyCategory> getProxyCategoryPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFProxyCategory.class, buildFetchRequest("proxy-categories", page, perPage));
	}

	public CreateResult<EFProxyCategory> createProxyCategory(EFProxyCategory proxyCategory) throws IOException
	{
		return getCreateResult(EFProxyCategory.class, buildCreateRequest("proxy-categories", new Gson().toJson(proxyCategory)));
	}

	public UpdateResult<EFProxyCategory> updateProxyCategory(EFProxyCategory proxyCategory) throws IOException
	{
		return getUpdateResult(EFProxyCategory.class, buildUpdateRequest("proxy-categories", proxyCategory.getId(), new Gson().toJson(proxyCategory)));
	}

	public DeleteResult<EFProxyCategory> deleteAccount(EFProxyCategory proxy) throws IOException
	{
		return getDeleteResult(EFProxyCategory.class, buildDeleteRequest("proxy-categories", proxy.getId()));
	}

	public PageResult<EFTask> getTaskPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFTask.class, buildFetchRequest("tasks", page, perPage));
	}

	public CreateResult<EFTask> createTask(EFTask task) throws IOException
	{
		return getCreateResult(EFTask.class, buildCreateRequest("tasks", new Gson().toJson(task)));
	}

	public UpdateResult<EFTask> updateTask(EFTask task) throws IOException
	{
		return getUpdateResult(EFTask.class, buildUpdateRequest("tasks", task.getId(), new Gson().toJson(task)));
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

			return new Gson().fromJson(element, StartTaskResult.class);
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

			return new Gson().fromJson(element, StopTaskResult.class);
		}
	}

	public PageResult<EFPrimeLinkRequest> getPrimeLinkRequestPage(int page, int perPage) throws IOException
	{
		return getPageResult(EFPrimeLinkRequest.class, buildFetchRequest("prime-link-requests", page, perPage));
	}

	public CreateResult<EFPrimeLinkRequest> createPrimeLinkRequest(EFPrimeLinkRequest primeLinkRequest) throws IOException
	{
		return getCreateResult(EFPrimeLinkRequest.class, buildCreateRequest("prime-link-requests", new Gson().toJson(primeLinkRequest)));
	}

	public UpdateResult<EFPrimeLinkRequest> updatePrimeLinkRequest(EFPrimeLinkRequest primeLinkRequest) throws IOException
	{
		return getUpdateResult(EFPrimeLinkRequest.class, buildUpdateRequest("prime-link-requests", primeLinkRequest.getId(), new Gson().toJson(primeLinkRequest)));
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
			return new Gson().fromJson(element, type);
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
			return new Gson().fromJson(element, type);
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
			return new Gson().fromJson(element, type);
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
			return new Gson().fromJson(element, type);
		}
	}

	private Request buildFetchRequest(String type, int page, int perPage)
	{
		return new Request.Builder()
				.url(getBaseUrl().newBuilder()
						.addPathSegment(type)
						.addQueryParameter("key", apiKey)
						.addQueryParameter("page", String.valueOf(Math.max(page, 1)))
						.addQueryParameter("per_page", String.valueOf(Math.max(perPage, 1)))
						.build())
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
