package com.exadel.discount.service.impl;

import com.exadel.discount.dto.discount.CreateDiscountDTO;
import com.exadel.discount.dto.discount.DiscountDTO;
import com.exadel.discount.dto.discount.DiscountFilter;
import com.exadel.discount.entity.Category;
import com.exadel.discount.entity.Discount;
import com.exadel.discount.entity.QDiscount;
import com.exadel.discount.entity.Tag;
import com.exadel.discount.entity.Vendor;
import com.exadel.discount.entity.VendorLocation;
import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.mapper.DiscountMapper;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.TagRepository;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.DiscountService;
import com.exadel.discount.util.QPredicates;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final VendorRepository vendorRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final VendorLocationRepository locationRepository;
    private final DiscountMapper discountMapper;

    @Override
    public DiscountDTO save(CreateDiscountDTO createDiscountDTO) {
        log.debug("Saving new Discount");
        Discount discount = discountMapper.parseDTO(createDiscountDTO);
        discount.setCategory(findCategory(createDiscountDTO.getCategoryId()));
        discount.setTags(findTags(createDiscountDTO.getTagIds()));
        discount.setVendor(findVendor(createDiscountDTO.getVendorId()));
        discount.setVendorLocations(findVendorLocations(createDiscountDTO.getVendorId(),
                createDiscountDTO.getVendorLocationsIds()));

        Discount savedDiscount = discountRepository.save(discount);
        log.debug("Successfully saved new Discount");
        return discountMapper.getDTO(savedDiscount);
    }

    @Override
    public DiscountDTO getById(UUID id) {
        log.debug(String.format("Finding Discount with ID %s", id));
        Discount discount = discountRepository
                .findByIdAndArchived(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found", id)));
        log.debug(String.format("Successfully found Discount with ID %s", id));
        return discountMapper.getDTO(discount);
    }

    @Override
    public List<DiscountDTO> getAll(String sortBy, String sortDir, Integer page, Integer size, DiscountFilter filter) {
        Sort sort = "desc".equalsIgnoreCase(sortDir) ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        log.debug("Getting list of all Discounts by filter");
        List<DiscountDTO> discountDTOS = discountMapper
                .getListDTO(discountRepository.findAll(getPredicate(filter), pageable).getContent());
        log.debug("Successfully got list of all Discounts by filter");
        return discountDTOS;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting Discount with ID %s", id));
        Discount discount = discountRepository
                .findByIdAndArchived(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Discount with ID %s not found", id)));
        discount.setArchived(true);
        discountRepository.save(discount);
        log.debug(String.format("Successfully deleted Discount with ID %s", id));
    }

    @Override
    @Transactional
    public DiscountDTO restoreById(UUID id) {
        log.debug("Finding archived Discount by ID");
        Discount discount = discountRepository
                .findByIdAndArchived(id, true)
                .orElseThrow(() -> new NotFoundException(String.format("Archived Discount with id %s not found", id)));
        discount.setArchived(false);
        Discount restoredDiscount = discountRepository.save(discount);
        log.debug("Successfully restored Discount");
        return discountMapper.getDTO(restoredDiscount);
    }

    private Set<Tag> findTags(Set<UUID> tagIds) {
        List<Tag> existingTags = tagRepository.findAllById(tagIds);
        List<UUID> existingTagIds = existingTags
                .stream()
                .map(Tag::getId)
                .collect(Collectors.toList());
        if (!existingTagIds.containsAll(tagIds)) {
            throw new NotFoundException(String.format("Not all tags with IDs %s exist", tagIds));
        }
        return new HashSet<>(existingTags);
    }

    private Set<VendorLocation> findVendorLocations(UUID vendorId, Set<UUID> locationIds) {
        List<VendorLocation> existingLocations = locationRepository
                .findByIdInAndVendorId(locationIds, vendorId);
        List<UUID> existingLocationIds = existingLocations
                .stream()
                .map(VendorLocation::getId)
                .collect(Collectors.toList());
        if (!existingLocationIds.containsAll(locationIds)) {
            throw new NotFoundException(String.format("Not all Vendor's locations with IDs %s exist", locationIds));
        }
        return new HashSet<>(existingLocations);
    }

    private Category findCategory(UUID categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new NotFoundException(String.format("Category with ID %s doesn't exist", categoryId)));
    }

    private Vendor findVendor(UUID vendorId) {
        return vendorRepository
                .findById(vendorId)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s doesn't exist", vendorId)));
    }

    private Predicate getPredicate(DiscountFilter filter) {
        return QPredicates.builder()
                .add(filter.getArchived(), QDiscount.discount.archived::eq)
                .add(filter.getPercentFrom(), QDiscount.discount.percent::goe)
                .add(filter.getPercentTo(), QDiscount.discount.percent::loe)
                .add(filter.getEndDateFrom(), QDiscount.discount.endTime::goe)
                .add(filter.getEndDateTo(), QDiscount.discount.endTime::loe)
                .add(filter.getCategoryIds(), QDiscount.discount.category.id::in)
                .add(filter.getTagIds(), QDiscount.discount.tags.any().id::in)
                .add(filter.getVendorIds(), QDiscount.discount.vendor.id::in)
                .buildAnd();
    }
}