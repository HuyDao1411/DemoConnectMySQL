package org.sakaiproject.tool.testMySQL.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
@Entity
@Table(name = "wt_teamcal")
public class TeamWorkingCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    private String account;
    private String section;
    private String mon;
    private String tue;
    private String wed;
    private String thur;
    private String fri;
    private String sat;
    private String sun;

    // Getters and Setters
}
