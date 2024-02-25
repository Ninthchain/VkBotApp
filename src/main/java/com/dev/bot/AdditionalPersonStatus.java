package com.dev.bot;

public enum AdditionalPersonStatus {
		SharingFriendLink((short)125),
		CheckingFriend ((short)140);
		private final short code;
		AdditionalPersonStatus(short statusCode) {
				this.code = statusCode;
		}
		
		public short code() {
				return this.code;
		}
}
