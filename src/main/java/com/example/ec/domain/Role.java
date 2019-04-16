package com.example.ec.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Entity of a Security Role
 * Created by Mary Ellen BOwman
 */
@Entity
@Table(name="security_role")
public class Role  implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String rolename;

    @Column(name="description")
    private String description;

    @Override
    public String getAuthority() {
        return rolename;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return rolename;
    }

    public void setRoleName(String roleName) {
        this.rolename = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}