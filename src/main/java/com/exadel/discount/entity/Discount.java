package com.exadel.discount.entity;

mport lombok.AllArgsConstructor;
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
import javax.persistence.Table;
import java.time.LocalDateTime;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "discounts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tags", "coupons", "favorites"})
@ToString(exclude = {"tags", "coupons", "favorites"})
public class Discount {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "promo", nullable = false, length = 50)
    private String promo;
  
    @Column(name = "description")
    private String description;
    
    @Column(name = "percent")
    private int percent;
  
    @Column(name = "start_time")
    private LocalDateTime startTime;
  
    @Column(name = "end_time")
    private LocalDateTime endTime;
  
    @Column(name = "active")
    private boolean active;
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

  
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
