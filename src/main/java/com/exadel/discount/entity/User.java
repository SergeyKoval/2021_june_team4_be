package com.exadel.discount.entity;

import com.exadel.discount.config.EnumPostgresSQLType;
import com.exadel.discount.dto.user.BaseUserDto;
import com.exadel.discount.dto.user.UserDto;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@TypeDef(
        name = "user_role",
        typeClass = EnumPostgresSQLType.class
)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @NotNull
    private String login;
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "role")
    @Type(type = "user_role")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
    private List<Coupon> coupons = new ArrayList<>();

    public User() {
    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }

    public void removeCoupon(Coupon coupon) {
        coupons.remove(coupon);
    }

    public static User from(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public static User from(BaseUserDto baseUserDto) {
        User user = new User();
        user.setFirstName(baseUserDto.getFirstName());
        user.setLastName(baseUserDto.getLastName());
        user.setPhone(baseUserDto.getPhone());
        user.setEmail(baseUserDto.getEmail());
        user.setLogin(baseUserDto.getLogin());
        user.setPassword(baseUserDto.getPassword());
        user.setRole(baseUserDto.getRole());
        return user;
    }
    public User(String firstName, String lastName, String phone, String email, String login, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
