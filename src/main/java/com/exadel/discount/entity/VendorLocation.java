package com.exadel.discount.entity;

import lombok.AllArgsConstructor;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vendor_locations")
@EqualsAndHashCode(exclude = {"discounts", "vendor"})
@ToString(exclude = {"discounts", "vendor"})
@AllArgsConstructor
public class VendorLocation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "contact", length = 50)
    private String contact;

    @Column(name = "coordinate_x")
    private Double coordinateX;

    @Column(name = "coordinate_y")
    private Double coordinateY;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    //TODO
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;*/

    @ManyToMany(mappedBy = "vendorLocations", fetch = FetchType.LAZY)
    private Set<Discount> discounts;
}
