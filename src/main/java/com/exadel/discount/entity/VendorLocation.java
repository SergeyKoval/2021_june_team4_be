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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vendor_locations")
/*@EqualsAndHashCode(exclude = {"discounts"})
@ToString(exclude = {"discounts"})*/
public class VendorLocation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column(name = "country_id")
    private UUID countryId;
    @Column(name = "city_id")
    private UUID cityId;
    @Column(name = "contact", length = 50)
    private String contact;
    @Column(name = "coordinates")
    private String coordinates;

    /*@ManyToMany(mappedBy = "vendorLocations", fetch = FetchType.LAZY)
    private Set<Discount> discounts;*/


}
