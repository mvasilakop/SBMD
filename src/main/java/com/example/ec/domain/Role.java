 package com.example.ec.domain;

import javax.persistence.*;

/**
 * Entity of a Security Role
 * Created by Mary Ellen BOwman
 * Modified by Michail Vasilakopoulos (10/04/2019)
 */
@Entity
@Table(name="security_role")
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    public Role(String rolename, String description) {
//        this.rolename = rolename;
//        this.description = description;
//    }

    /**
     * Default Constructor.
     */
    protected Role() {
    }

    @Column(name="role_name")
    private String rolename;

    @Column(name="description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRoleName(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}