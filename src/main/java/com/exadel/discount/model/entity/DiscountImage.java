package com.exadel.discount.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.UUID;

@Data
@Entity
@Table(name = "discounts_image")
@EqualsAndHashCode(exclude = {"discount"})
@ToString(exclude = {"discount"})
@NoArgsConstructor
@AllArgsConstructor
public class DiscountImage {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "by_default", nullable = false)
    private boolean byDefault;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
