package com.example.webapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "ID of msisdn", example = "11", required = false, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty(notes = "Person phone number", example = "998991901234", required = false, position = 1)
    @Column(nullable = false, unique = true)
    @Size(max = 15)
    private String Msisdn;

    @Column(name = "ins_date", nullable = false)
    @CreatedDate
    private Date insDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
