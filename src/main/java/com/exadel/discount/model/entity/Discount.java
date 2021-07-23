package com.exadel.discount.model.entity;

import com.exadel.discount.model.entity.type.EnumPostgresSQLType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "discounts")
@EqualsAndHashCode(exclude = {"tags", "vendorLocations", "coupons", "favorites"})
@ToString(exclude = {"tags", "vendorLocations", "coupons", "favorites"})
@TypeDef(
        name = "discount_type",
        typeClass = EnumPostgresSQLType.class
)
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

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "discountType", nullable = false)
    @Type(type = "discount_type")
    private DiscountType discountType;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean active;

    @Column(name = "archived", columnDefinition = "boolean default false")
    private boolean archived;

    @Column(name = "view_number", columnDefinition = "integer default 0")
    private Integer viewNumber = 0;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "discount")
    private Set<DiscountImage> discountImages;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Favorite> favorites;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Coupon> coupons;
}
