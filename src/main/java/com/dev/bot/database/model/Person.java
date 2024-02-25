package com.dev.bot.database.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Person", schema = "vkapp")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "vkId")
    private long vkId;
    @Column(name = "isVerified")
    private boolean isVerified;
    
    @Column(name = "token")
    private String token;
    
    @Column(name = "status")
    private short status;
    
    public Person() {

    }

    public Person(long id, long vkId, boolean isVerified, String token, short status) {
        this.setId(id);
        this.setVkId(vkId);
        this.setIsVerified(isVerified);
        this.setToken(token);
        this.setStatus(status);
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
    
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public short getStatus() {
        return status;
    }
    
    public void setStatus(short status) {
        this.status = status;
    }
}
