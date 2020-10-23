package com.example.webapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table
public class Msisdn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Size(max = 15)
    private String Msisdn;

    @Column(name = "ins_date", nullable = false)
    @CreatedDate
    private Date insDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JoinColumn(name="person_id", nullable = false)
    private Person person;

    public long getId() {
        return id;
    }

    public String getMsisdn() {
        return Msisdn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMsisdn(String msisdn) {
        Msisdn = msisdn;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public Date getInsDate() {
        return insDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public Msisdn() {
    }
}
