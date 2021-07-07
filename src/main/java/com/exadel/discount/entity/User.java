package com.exadel.discount.entity;


import com.exadel.discount.config.EnumPostgresSQLType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"coupons", "favorites"})
@ToString(exclude = {"coupons", "favorites"})

@TypeDef(
        name = "user_role",
        typeClass = EnumPostgresSQLType.class
)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;
  
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
  
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
  
    @Column(name = "phone", length = 50, nullable = false)
    private String phone;
  
    @Column(name = "email", length = 50, nullable = false)
    private String email;
  
    @Column(name = "login", length = 50, nullable = false)
    private String login;
  
    @Column(name = "password", length = 225, nullable = false)
    private String password;

    @Fetch(FetchMode.JOIN)
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role", nullable = false)
    @Type(type = "user_role")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> coupons = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites = new ArrayList<>();

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id", nullable=false)
    private City city;
}
