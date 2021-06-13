package com.exadel.discount.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private String description;
    private String promo;
    private int percent;
    private Date start_time;
    private Date end_time;
    private boolean active;

    public Discount(Category category, String name, String description, String promo, int percent, Date start_time, Date end_time, boolean active) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.promo = promo;
        this.percent = percent;
        this.start_time = start_time;
        this.end_time = end_time;
        this.active = active;
    }

}
