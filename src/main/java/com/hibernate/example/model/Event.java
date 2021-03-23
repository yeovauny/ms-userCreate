package com.hibernate.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

/**
 * important note
 * @NotNull is always on ID field , if not there, you could be a not null error when the application start!!
 *
 * */


@Entity
@Table(name="Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    @NotNull
    @Column(name="EVENTID")
    private Long eventid;

    @Column(name="JOB")
    @NotNull(message = "Please enter your profession")
    @Pattern(regexp = "^[A-Za-z\\s]*$")
    private String job;

    @Column(name="NAME")
    @NotNull(message ="Please enter your name")
    @Pattern(regexp = "^[A-Za-z\\s]*$")
    private String  name;

    @Column(name="RUT")
    @NotNull(message ="Please enter your RUT")
    @Pattern(regexp = "^[0-9]{1,8}-[Kk0-9]{1}")
    private String rut;

    @Column(name="BIRTHDAY")
    @NotNull(message ="Please enter your birthday with si format DAY-MONTH-YEAR ")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthday;



    // OneToMany Seccion for testing
    @OneToMany(cascade = {CascadeType.ALL} ,orphanRemoval = true)
    @JoinColumn(name="fk_eventid", referencedColumnName = "EVENTID", nullable = false)
    @JsonProperty("actor")
    private List<Actor> actor;

    @OneToMany(cascade = {CascadeType.ALL} , orphanRemoval = true)
    @JoinColumn(name="fk_eventid", referencedColumnName = "EVENTID" ,nullable = false)
    @JsonProperty("repo")
    private List<Repo> repo;


    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
