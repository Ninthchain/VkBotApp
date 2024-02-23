package com.dev.vkbot.keyboards;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonActionType;

import java.util.ArrayList;
import java.util.List;

public class PersonKeyboard extends Keyboard {
		public PersonKeyboard() {
				KeyboardButtonAction action = new KeyboardButtonAction();
				action.setType(KeyboardButtonActionType.START);
				List<List<KeyboardButton>> buttons = new ArrayList<>() {
						{
								
								{
								
								}
						}
				};
		}
		
		
}
