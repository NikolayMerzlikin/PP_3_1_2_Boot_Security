package ru.kata.spring.boot_security.demo.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private long id;

    @Column/*(nullable = false)*/
    @Setter
    @Getter
    private String username;

    @Column/*(nullable = false)*/
    @Setter
    @Getter
    private int age;

    @Column(unique = true)
    @Setter
    @Getter
    private String email;

    @Column/*(nullable = false)*/
    @Setter
    @Getter
    private String password;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Role> roles;

    public User(String username, int age, String email, String password, Set<Role> role) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(el -> new SimpleGrantedAuthority("ROLE_" + el.getAuthority())).collect(Collectors.toList());
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRoles(String roles) {
        if (roles != null && !roles.isEmpty()) {
            String[] arrayRoles = roles.split("[^A-Za-zФ-Яа-я0-9]");
            if (arrayRoles.length != 0) {
                Set<Role> roleSet = new HashSet<>();
                Arrays.stream(arrayRoles).forEach(el -> roleSet.add(new Role(el)));
                this.roles = roleSet;
            } else {
                this.roles = new HashSet<>();
                this.roles.add(new Role(roles));
            }

        }

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
