package com.exadel.discount.entity;

import com.exadel.discount.config.EnumPostgresSQLType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.exadel.discount.model.security.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role")
    @Type(type = "user_role")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Coupon> coupons = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Favorite> favorites = new ArrayList<>();
}
