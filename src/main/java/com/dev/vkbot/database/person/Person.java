package com.dev.vkbot.database.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person", schema = "vkapp")
public class Person {
    @Id
    private long id;
    @Column(name = "vkId")
    private long vkId;
    @Column(name = "isVerified")
    private boolean isVerified;

    public Person() {

    }

    public Person(long id, long vkId, boolean isVerified) {
        this.setId(id);
        this.setVkId(vkId);
        this.setIsVerified(isVerified);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVkId() {
        return vkId;
    }

    public void setVkId(long vkId) {
        this.vkId = vkId;
    }


    public void setIsVerified(boolean flag) {
        this.isVerified = flag;
    }

    public boolean getIsVerified() {
        return this.isVerified;
    }


}
