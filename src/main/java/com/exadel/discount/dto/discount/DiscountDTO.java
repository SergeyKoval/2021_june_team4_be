package com.exadel.discount.dto.discount;

import com.exadel.discount.dto.CategoryDTO;
import com.exadel.discount.dto.TagDTO;
import com.exadel.discount.dto.location.LocationDTO;
import com.exadel.discount.dto.vendor.BaseVendorDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class DiscountDTO {
    private UUID id;
    private CategoryDTO category;
    private String name;
    private String description;
    private String promo;
    private Integer percent;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;
    private boolean archived;
    private Set<TagDTO> tags;
    private Set<LocationDTO> vendorLocations;
    private BaseVendorDTO vendor;
    private List<String> images = new ArrayList<>();

    {
        images.add("https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510_960_720.jpg");
        images.add("https://cdn.pixabay.com/photo/2013/07/02/22/20/bouquet-142876_960_720.jpg");
        images.add("https://cdn.pixabay.com/photo/2013/07/30/12/25/bouquet-168831_960_720.jpg");
    }
}
