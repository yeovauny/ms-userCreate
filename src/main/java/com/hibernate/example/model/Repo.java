package com.hibernate.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="REPO")
public class Repo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "repo_sequence")
    @NotNull
    @Column(name="REPOID")
    private Long repoid;

    @Column(name ="NAME")
    private String name;

    @Column(name="URL")
    private String url;

    public Long getRepoid() {
        return repoid;
    }

    public void setRepoid(Long repoid) {
        this.repoid = repoid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
