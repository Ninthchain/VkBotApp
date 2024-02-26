package com.dev.bot.database.model;

import javax.persistence.*;

@Entity
@Table(name="SellPoint", schema = "vkapp")
public class SellPoint {
		@Id
		@GeneratedValue(strategy =  GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private long id;
		@Column(name = "address")
		private String address;
		
		@Column(name = "city")
		private String city;
		
		@Column(name = "sellerId")
		private long sellerId;
		
		@Column(name = "isActive")
		private boolean isActive;
		
		public SellPoint() {
		
		}
		
		public SellPoint(long id, String address, String city, long sellerId, boolean isActive) {
				this.setId(id);
				this.setAddress(address);
				this.setCity(city);
				this.setSellerId(sellerId);
				this.setActive(isActive);
		}
		
		public long getId() {
				return id;
		}
		
		public void setId(long id) {
				this.id = id;
		}
		
		public String getAddress() {
				return address;
		}
		
		public void setAddress(String address) {
				this.address = address;
		}
		
		public String getCity() {
				return city;
		}
		
		public void setCity(String city) {
				this.city = city;
		}
		
		
		public boolean isActive() {
				return isActive;
		}
		
		public void setActive(boolean active) {
				isActive = active;
		}
		
		public long getSellerId() {
				return sellerId;
		}
		
		public void setSellerId(long sellerId) {
				this.sellerId = sellerId;
		}
}
