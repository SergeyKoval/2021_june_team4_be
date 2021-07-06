package com.exadel.discount.service.impl;

import com.exadel.discount.dto.discount.CreateDiscountDTO;
import com.exadel.discount.dto.discount.DiscountDTO;
import com.exadel.discount.entity.Category;
import com.exadel.discount.entity.Discount;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public List<DiscountDTO> getAll() {
        log.debug("Getting list of all Discounts");
        List<DiscountDTO> discountDTOS = discountMapper.getListDTO(discountRepository.findAllByArchived(false));
        log.debug("Successfully got list of all Discounts");
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
    public List<DiscountDTO> getAllArchived() {
        log.debug("Getting list of all archived Discounts");
        List<DiscountDTO> discountDTOS = discountMapper.getListDTO(discountRepository.findAllByArchived(true));
        log.debug("Successfully got list of all archived Discounts");
        return discountDTOS;
    }

    @Override
    @Transactional
    public DiscountDTO restoreById(UUID id) {
        log.debug("Finding archived Discount by ID");
        Discount discount = discountRepository
                .findByIdAndArchived(id, true)
                .orElseThrow(() -> new NotFoundException(String.format("Archived Discount with id %s not found", id)));
        if (discount.getVendor().isArchived()) {
            throw new NotFoundException(String.format("Discount can't be restored as Vendor with id %s not found", discount.getVendor().getId()));
        }
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
                .findByIdAndArchivedFalse(vendorId)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s doesn't exist", vendorId)));
    }
}