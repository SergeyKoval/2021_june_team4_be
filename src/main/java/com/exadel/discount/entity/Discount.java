package com.exadel.discount.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
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
    @Column(name = "description")
    private String description;
    @Column(name = "promo", length = 50, nullable = false)
    private String promo;
    @Column(name = "percent")
    private int percent;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "end_time")
    private Timestamp endTime;
    @Column(name = "active", nullable = false)
    private boolean active;

}
