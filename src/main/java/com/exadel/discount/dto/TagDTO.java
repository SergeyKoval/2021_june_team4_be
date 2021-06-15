package com.exadel.discount.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class TagDTO {
    public interface New {
    }
    @Null(groups = New.class)
    private UUID id;
    @NotBlank(groups = New.class)
    @Size(max = 50, groups = New.class)
    private String name;
}
