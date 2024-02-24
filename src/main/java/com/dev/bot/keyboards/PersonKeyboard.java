package com.dev.bot.keyboards;

import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonActionType;

public class PersonKeyboard extends Keyboard {
		public PersonKeyboard() {
				KeyboardButtonAction action = new KeyboardButtonAction();
				action.setType(KeyboardButtonActionType.START);
				
		}
		
		
}
