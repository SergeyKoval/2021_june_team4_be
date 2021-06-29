package com.exadel.discount.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "discounts")
@EqualsAndHashCode(exclude = {"tags"})
@ToString(exclude = {"tags"})
public class Discount {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "promo", length = 50, nullable = false)
    private String promo;
    @Column(name = "percent")
    private int percent;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "discounts_locations",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<VendorLocation> vendorLocations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tags_discounts",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    //TODO
    /*@OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Coupon> coupons;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Favorite> favorites;*/

}
