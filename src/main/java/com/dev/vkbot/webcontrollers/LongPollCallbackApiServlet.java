package com.dev.vkbot.webcontrollers;

import com.dev.vkbot.LongPollCallbackApiHandler;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;


public class LongPollCallbackApiServlet extends HttpServlet {
    LongPollCallbackApiHandler callbackApiHandler;
    VkApiClient vkApiClient;

    @Override
    public void init() throws ServletException {
        super.init();
        vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

    }
}
