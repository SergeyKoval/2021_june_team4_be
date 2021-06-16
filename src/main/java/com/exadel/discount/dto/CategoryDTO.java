package com.exadel.discount.dto;

import com.exadel.discount.dto.validation.Create;
import com.exadel.discount.entity.Discount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = {"discounts"})
@ToString(exclude = {"discounts"})
public class CategoryDTO {
    @Null(groups = Create.class)
    private UUID id;
    @NotBlank(groups = Create.class)
    private String name;
    @Null(groups = Create.class)
    private List<Discount> discounts;

}
