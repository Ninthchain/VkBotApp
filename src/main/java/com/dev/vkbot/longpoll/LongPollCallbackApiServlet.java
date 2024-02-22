package com.dev.vkbot.longpoll;

import com.dev.vkbot.database.dao.VkGroupDao;
import com.dev.vkbot.database.model.VkGroup;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(loadOnStartup = 1, urlPatterns = {"/bot"})
public class LongPollCallbackApiServlet extends HttpServlet {
    VkGroupDao vkGroupDao;
    LongPollCallbackApiHandler callbackApiHandler;
    VkApiClient vkApiClient;

    @Override
    public void init() throws ServletException {
        super.init();
        vkGroupDao = new VkGroupDao();
        this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());


        VkGroup vkGroup = this.vkGroupDao.getByColumnValue("groupId", 203296903).getFirst();
        GroupActor actor = new GroupActor(Math.toIntExact(vkGroup.getGroupId()), vkGroup.getAccessToken());
        try {
            vkApiClient.groupsLongPoll().setLongPollSettings(actor).enabled(true)
                    .wallPostNew(true)
                    .messageNew(true)
                    .execute();

            this.callbackApiHandler = new LongPollCallbackApiHandler(this.vkApiClient, actor);
            new Thread(() -> {
                try {
                    this.callbackApiHandler.run();
                } catch (ClientException | ApiException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }

    }
}
