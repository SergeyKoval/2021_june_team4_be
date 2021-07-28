package com.exadel.discount.model.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ItemDTO {
    protected UUID id;
    protected String name;
    protected Number viewNumber;
    protected Long numberOfGettingPromo;
}
