package com.exadel.discount.model.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import java.util.UUID;

@Entity
@Table(name = "discounts_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DiscountImage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "by_default", nullable = false)
    private boolean byDefault;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "discount_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Discount discount;
}