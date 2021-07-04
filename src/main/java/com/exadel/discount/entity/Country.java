package com.exadel.discount.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"cities"})
@ToString(exclude = {"cities"})
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    String name;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities;

//    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
//    private List<VendorLocation> vendorLocations;
}
