package com.dev.vkbot.database.vkgroup;

import jakarta.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vkGroups")
public class VkGroup {
    @Id
    private long id;

    @Column(name = "groupId")
    private long groupId;

    @Column(name = "accessToken")
    private String accessToken;

    public VkGroup() {

    }

    public VkGroup(long id, long groupId, String accessToken) {
        this.setId(id);
        this.setGroupId(groupId);
        this.setAccessToken(accessToken);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
