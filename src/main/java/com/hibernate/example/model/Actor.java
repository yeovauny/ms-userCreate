package com.hibernate.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="ACTOR")
public class Actor {

        @Id
        @Column(name="ACTORID")
        @GeneratedValue(strategy = GenerationType.SEQUENCE  , generator = "actor_sequence")
        @NotNull
        private long actorid;

        @Column(name="LOGIN")
        private String login;

        @Column(name ="AVATAR")
        private String avatar;

    public long getActorid() {
        return actorid;
    }

    public void setActorid(long actorid) {
        this.actorid = actorid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
