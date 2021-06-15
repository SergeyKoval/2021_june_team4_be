package com.exadel.discount.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"vendorLocations"})
@ToString(exclude = {"vendorLocations"})
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE,
            mappedBy = "vendor")
    private List<VendorLocation> vendorLocations = new ArrayList<>();

}
