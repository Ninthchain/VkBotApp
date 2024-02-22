package com.dev.vkbot;

import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.callback.objects.user.CallbackUserBlock;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.messages.Message;

public class LongPollCallbackApiHandler extends CallbackApiLongPoll {
		
		public LongPollCallbackApiHandler(VkApiClient client, GroupActor actor) {
				super(client, actor);
		}
		
		@Override
		public void messageNew(Integer groupId, Message message) {
				super.messageNew(groupId, message);
		}
		
		@Override
		public void userBlock(Integer groupId, CallbackUserBlock message) {
				super.userBlock(groupId, message);
		}
}
