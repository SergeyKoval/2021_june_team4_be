package com.exadel.discount.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "discounts")
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
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    @Column(name = "promo", length = 50, nullable = false)
    private String promo;
    @Column(name = "percent")
    private int percent;
    @Column(name = "start_time", nullable = false)
    private ZonedDateTime start_time;
    @Column(name = "end_time", nullable = false)
    private ZonedDateTime end_time;
    @Column(name = "active", nullable = false)
    private boolean active;

}
