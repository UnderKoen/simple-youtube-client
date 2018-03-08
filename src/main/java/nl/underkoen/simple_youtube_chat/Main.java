package nl.underkoen.simple_youtube_chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import nl.underkoen.simple_youtube_chat.entities.Message;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 08/03/2018.
 */
public class Main {
    private static final String refresh_token = "";
    private static final String client_id = "";
    private static final String client_secret = "";

    public static void main(String[] args) throws Exception {
        JsonObject accesToken = refreshToken();
        JsonObject streams = getStreams(accesToken);
        List<String> liveChatIds = new ArrayList<>();
        streams.getAsJsonArray("items").forEach(jsonElement -> {
            liveChatIds.add(jsonElement.getAsJsonObject().getAsJsonObject("snippet").get("liveChatId").getAsString());
        });
        List<Message> oldMessages = new ArrayList<>();
        while (true) {
            JsonObject jsonMsgs = getMessages(liveChatIds.get(0), accesToken);
            List<Message> messages = new ArrayList<>();
            jsonMsgs.getAsJsonArray("items").forEach(jsonElement -> {
                messages.add(new Gson().fromJson(jsonElement, Message.class));
            });
            for (Message message : messages) {
                if (oldMessages.contains(message)) continue;
                System.out.println(message.authorDetails.displayName + ": " + message.snippet.displayMessage);
                if (message.snippet.displayMessage.contains("hey")) {
                    sendMessage(liveChatIds.get(0), "hoi", accesToken);
                }
            }
            oldMessages = messages;
            Thread.sleep(jsonMsgs.get("pollingIntervalMillis").getAsLong());
        }
    }

    public static JsonObject sendMessage(String chatId, String message, JsonObject json) throws Exception {
        HttpPost request = new HttpPost("https://www.googleapis.com/youtube/v3/liveChat/messages?part=snippet");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Authorization", json.get("token_type").getAsString() + " " + json.get("access_token").getAsString());
        JsonObject jsonMsg = new JsonObject();
        JsonObject snippet = new JsonObject();
        snippet.addProperty("liveChatId", chatId);
        snippet.addProperty("type", "textMessageEvent");
        JsonObject textMessageDetails = new JsonObject();
        textMessageDetails.addProperty("messageText", message);
        snippet.add("textMessageDetails", textMessageDetails);
        jsonMsg.add("snippet", snippet);
        HttpEntity entity = new StringEntity(jsonMsg.toString());
        request.setEntity(entity);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        return new JsonParser().parse(EntityUtils.toString(response.getEntity())).getAsJsonObject();
    }

    public static JsonObject getMessages(String chatId, JsonObject json) throws Exception {
        HttpGet request = new HttpGet("https://www.googleapis.com/youtube/v3/liveChat/messages?part=snippet,authorDetails&liveChatId=" + chatId);
        request.addHeader("Authorization", json.get("token_type").getAsString() + " " + json.get("access_token").getAsString());
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        return new JsonParser().parse(EntityUtils.toString(response.getEntity())).getAsJsonObject();
    }

    public static JsonObject getStreams(JsonObject json) throws Exception {
        HttpGet request = new HttpGet("https://www.googleapis.com/youtube/v3/liveBroadcasts?part=snippet&broadcastStatus=all");
        request.addHeader("Authorization", json.get("token_type").getAsString() + " " + json.get("access_token").getAsString());
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        return new JsonParser().parse(EntityUtils.toString(response.getEntity())).getAsJsonObject();
    }

    public static JsonObject refreshToken() throws Exception {
        HttpPost request = new HttpPost("https://www.googleapis.com/oauth2/v4/token");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("refresh_token", refresh_token));
        pairs.add(new BasicNameValuePair("client_id", client_id));
        pairs.add(new BasicNameValuePair("client_secret", client_secret));
        pairs.add(new BasicNameValuePair("grant_type", "refresh_token"));
        HttpEntity entity = new UrlEncodedFormEntity(pairs);
        request.setEntity(entity);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);
        return new JsonParser().parse(EntityUtils.toString(response.getEntity())).getAsJsonObject();
    }
}
