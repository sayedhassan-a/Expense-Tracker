package com.example.expenseTracker.user.model;

import com.example.expenseTracker.category.model.Category;
import com.example.expenseTracker.income.model.Income;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.expenseTracker.expense.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private ROLE role;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Expense> expenses;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Income> incomes;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Category> categories;

    public Boolean getActive() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
