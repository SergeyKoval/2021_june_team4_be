package com.exadel.discount.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vendor_locations")
public class VendorLocation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @Column(name = "country_id")
    private UUID countryId;
    @Column(name = "city_id")
    private UUID cityId;
    @Column(name = "contact", length = 50)
    private String contact;
    @Column(name = "coordinates")
    private String coordinates;


}
