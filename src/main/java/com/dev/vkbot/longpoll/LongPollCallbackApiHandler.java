package com.dev.vkbot.longpoll;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.callback.objects.user.CallbackUserBlock;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LongPollCallbackApiHandler extends CallbackApiLongPoll {
    public LongPollCallbackApiHandler(VkApiClient client, GroupActor actor) {
        super(client, actor);

    }

    @Override
    public void messageNew(Integer groupId, Message message) {

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/app/")).GET().build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void userBlock(Integer groupId, CallbackUserBlock message) {
        super.userBlock(groupId, message);
    }

}
