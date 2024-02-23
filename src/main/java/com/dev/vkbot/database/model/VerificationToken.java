package com.dev.vkbot.database.model;


import com.vk.api.sdk.objects.apps.App;
import com.vk.api.sdk.objects.wall.AppPost;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
public class VerificationToken {
		private static final int EXPIRATION = 60 * 24 * 7;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		private String token;
		
		@OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
		@JoinColumn(nullable = false, name="person_token")
		private Person person;
		
		private Date expiryDate;
		
		private Date calculateExpiryDate() {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Timestamp(cal.getTime().getTime()));
				cal.add(Calendar.MINUTE, EXPIRATION);
				return new Date(cal.getTime().getTime());
		}
}
