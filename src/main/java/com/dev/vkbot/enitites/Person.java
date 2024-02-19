package com.dev.vkbot.enitites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
public class Person {
		@Id
		long id;
		@Column(name = "role")
		String role;
		@Column(name = "vkId")
		long vkId;
		@Column(name = "isVerified")
		boolean isVerified;
		
		public long getVkId() {
				return vkId;
		}
		
		public void setVkId(long vkId) {
				this.vkId = vkId;
		}
		
		public long getId() {
				return id;
		}
		
		public void setId(long id) {
				this.id = id;
		}
		
		public void setIsVerified(boolean flag) {
				this.isVerified = flag;
		}
		
		public boolean getIsVerified() {
				return this.isVerified;
		}
		
		
}
