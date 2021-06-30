package com.exadel.discount.controller;

import com.exadel.discount.dto.location.CreateLocationDTO;
import com.exadel.discount.dto.vendor.BaseVendorDTO;
import com.exadel.discount.dto.vendor.CreateVendorDTO;
import com.exadel.discount.dto.vendor.VendorDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/vendors")
public class VendorController {

    @GetMapping
    @ApiOperation("Get list of all vendors")
    public List<BaseVendorDTO> getVendorsList() {
        return null;
    }


}
