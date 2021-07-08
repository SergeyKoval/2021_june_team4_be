package com.exadel.discount.controller;

import com.exadel.discount.dto.coupon.CouponDTO;
import com.exadel.discount.dto.coupon.CreateCouponDTO;
import com.exadel.discount.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    @ApiOperation("Get sorted page-list of all coupons with fate filtering")
    /**  TEMPORARY:
     * Get list of all coupons with sorting by params
     * sortDirection - ASC or DSC (unsorted - by default) ;
     * sortField - name of sorted field by (date - by default)- date/id
     * filtering - after startDate and/or before endDate
     * (default filtering - between 2000-01-10T00:00:00 and 9999-01-10T00:00:00 */
    public List<CouponDTO> getAllCoupons(@RequestParam(name = "pageNumber", defaultValue = "1", required = false)
                                                     int pageNumber,
                                         @RequestParam(name = "pageSize", defaultValue = "10", required = false)
                                                    int pageSize,
                                         @RequestParam(value = "sortDirection", defaultValue = "", required = false)
                                                     String sortDirection,
                                         @RequestParam(value = "sortField", defaultValue = "date", required = false)
                                                     String sortField,
                                         @RequestParam(value = "startDate", defaultValue = "2000-01-10T00:00:00", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                             final LocalDateTime startDate,
                                         @RequestParam(value = "endDate", defaultValue = "9999-01-10T00:00:00", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                             final LocalDateTime endDate) {
        return couponService.findAllCoupons(pageNumber, pageSize, sortDirection, sortField, startDate, endDate);
    }

    @GetMapping("{id}")
    @ApiOperation("Get coupon by ID")
    public CouponDTO getCouponById(@PathVariable @NotNull final UUID id) {
        return couponService.findCouponById(id);
    }

    @PostMapping
    @ApiOperation("Save new coupon to user")
    public CouponDTO addCoupon(@RequestBody @NotNull final CreateCouponDTO createCouponDTO) {
        return couponService.assignCouponToUser(createCouponDTO);
    }

    @GetMapping("/ofuser")
    @ApiOperation("Get sorted page-list of coupons of certain user")
    public List<CouponDTO> getCouponsOfUser(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "sortDirection", defaultValue = "") String
                                                        sortDirection,
                                            @RequestParam(value = "sortField", defaultValue = "date") String
                                                        sortField,
                                            @RequestParam(value = "userId") UUID userId) {
        return couponService.getCouponsOfUser(pageNumber, pageSize, sortDirection, sortField, userId);
    }

    //Time example : 2021-06-15T11:58:11
    @GetMapping("/date")
    @ApiOperation("Get coupon by certain date")
    public CouponDTO getCouponByDate(@RequestParam("date")
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                         @NotNull final LocalDateTime date) {
        return couponService.findCouponByDate(date);
    }
}
