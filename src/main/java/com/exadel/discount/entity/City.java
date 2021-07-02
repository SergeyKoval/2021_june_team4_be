package com.exadel.discount.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
//    Check and uncomment after adding User and VendorLocation entities

//@EqualsAndHashCode(exclude = {"users", "vendorLocations"})
//@ToString(exclude = {"users", "vendorLocations"})
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable=false)
    private Country country;

//    Check and uncomment after adding User and VendorLocation entities

//    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
//    private List<User> users;

//    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
//    private List<VendorLocation> vendorLocations;
}