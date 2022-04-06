package ru.kata.spring.boot_security.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor

public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String role;
//    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
//    private Set<User> user;

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "role_user",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    // private Collection<User> user;
    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }
    public Role(long id) {
        this.id = id;
    }
    public Role(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole().toUpperCase();
    }
}
