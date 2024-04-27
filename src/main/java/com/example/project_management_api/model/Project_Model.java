package com.example.project_management_api.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Project")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Project_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
}
