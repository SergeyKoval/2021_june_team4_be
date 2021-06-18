package com.exadel.discount.entity;

import com.exadel.discount.config.EnumPostgresSQLType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
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
public class User implements Serializable {

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

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
    }
}
