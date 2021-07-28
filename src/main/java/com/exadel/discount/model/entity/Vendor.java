package com.exadel.discount.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"vendorLocations", "discounts"})
@ToString(exclude = {"vendorLocations", "discounts"})
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

    @Column(name = "contacts", length = 255)
    private String contacts;

    @Column(name = "archived", columnDefinition = "boolean default false")
    private boolean archived;

    @OneToMany(cascade = CascadeType.REMOVE,
            mappedBy = "vendor")
    private Set<VendorLocation> vendorLocations;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "vendor")
    private Set<Discount> discounts;
}
