package com.dev.bot;



public enum PersonStatus {
		Unknown ((short) 0),
		UnverifiedAge ((short) 1),
		Verified ((short) 2),
		Invalid ((short) -4);
		
		private final short code;
		PersonStatus(short statusCode) {
			this.code = statusCode;
		}
		
		public short code() {
				return this.code;
		}
}
