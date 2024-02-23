package com.dev.vkbot.utils;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VkApiUtil {
		public static VkApiClient GetApiClient() {
				return new VkApiClient(new HttpTransportClient());
		}
}
