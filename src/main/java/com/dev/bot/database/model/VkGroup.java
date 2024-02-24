package com.dev.bot.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "VkGroup", schema = "vkapp")
public class VkGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    
    @Column(name = "vkId")
    private long vkId;

    @Column(name = "accessToken")
    private String accessToken;

    public VkGroup() {

    }

    public VkGroup(long id, long groupId, String accessToken) {
        this.setId(id);
        this.setVkId(groupId);
        this.setAccessToken(accessToken);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

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
}
