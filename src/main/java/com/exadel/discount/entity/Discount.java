package com.exadel.discount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tags", "coupons", "favorites"})
@ToString(exclude = {"tags", "coupons", "favorites"})
public class Discount {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "promo", nullable = false, length = 50)
    private String promo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tags_discounts",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Coupon> coupons;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Favorite> favorites;
}
