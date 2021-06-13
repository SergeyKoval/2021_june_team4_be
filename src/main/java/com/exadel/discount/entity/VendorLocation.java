package com.exadel.discount.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vendor_locations")
public class VendorLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Vendor vendor;

    private UUID country_id;
    private UUID city_id;
    private String contact;
    private String coordinates;


    public VendorLocation(Vendor vendor, UUID country_id, UUID city_id, String contact, String coordinates) {
        this.vendor = vendor;
        this.country_id = country_id;
        this.city_id = city_id;
        this.contact = contact;
        this.coordinates = coordinates;
    }

}
