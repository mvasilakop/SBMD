package com.example.ec.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Security User Entity.
 *
 * Created by Mary Ellen Bowman
 */
@Entity
@Table(name = "security_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<Role>(Arrays.asList(role));
    }
    
    public User(String username, String password, Role role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<Role>(Arrays.asList(role));
        this.firstName = firstName;
        this.lastName = lastName;
    } 

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    /**
     * Default Constructor.
     */
    protected User() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns
            = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))


    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    
//    public Set<GrantedAuthority> getAuthorities() {
////  		    List<GrantedAuthority> authorities = new ArrayList<>();
////  		    for (Role role: getRoles()) {
////  		        authorities.add(new SimpleGrantedAuthority(role.getRolename()));
//////  		        role.getPrivileges().stream()
////  		         .map(p -> new SimpleGrantedAuthority(p.getName()))
////  		         .forEach(authorities::add);
//    	Set<GrantedAuthority> authorities = new HashSet<>();
//        for (Role role : this.getRoles()){
//            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
//        }
//  		    System.out.println("TEST - Just before mapping authrities...");
//  		    return authorities;
//  		}
}
