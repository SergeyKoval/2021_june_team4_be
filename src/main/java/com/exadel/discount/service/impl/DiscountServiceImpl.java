package com.exadel.discount.service.impl;

import com.exadel.discount.exception.NotFoundException;
import com.exadel.discount.model.dto.discount.CreateDiscountDTO;
import com.exadel.discount.model.dto.discount.DiscountDTO;
import com.exadel.discount.model.dto.discount.DiscountFilter;
import com.exadel.discount.model.dto.discount.UpdateDiscountDTO;
import com.exadel.discount.model.dto.mapper.DiscountMapper;
import com.exadel.discount.model.entity.Category;
import com.exadel.discount.model.entity.Discount;
import com.exadel.discount.model.entity.QDiscount;
import com.exadel.discount.model.entity.Tag;
import com.exadel.discount.model.entity.Vendor;
import com.exadel.discount.model.entity.VendorLocation;
import com.exadel.discount.repository.CategoryRepository;
import com.exadel.discount.repository.DiscountRepository;
import com.exadel.discount.repository.TagRepository;
import com.exadel.discount.repository.VendorLocationRepository;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.repository.query.QueryPredicateBuilder;
import com.exadel.discount.service.DiscountService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final int SEARCH_WORD_MIN_LENGTH = 3;

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
    @Transactional
    public DiscountDTO getById(UUID id) {
        log.debug(String.format("Finding Discount with ID %s", id));
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Discount discount = discountRepository
                .findByIdAndArchivedWithFavoritesByUser(id, false, userEmail)
                .orElseThrow(() -> new NotFoundException(String.format("Discount with id %s not found", id)));
        discountRepository.increaseViewNumberById(id);
        DiscountDTO discountDTO = discountMapper.getDTO(discount);
        if (!discount.getFavorites().isEmpty()) {
            discountDTO.setFavorite(true);
        }
        log.debug(String.format("Successfully found Discount with ID %s", id));
        return discountDTO;
    }

    @Override
    public List<DiscountDTO> getAll(String sortBy, String sortDir, Integer page, Integer size, DiscountFilter filter) {
        Sort sort = "desc".equalsIgnoreCase(sortDir) ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy);
        log.debug("Getting list of all Discounts by filter");
        List<UUID> discountIds = discountRepository
                .findAllDiscountIds(preparePredicateForFindingAll(filter), PageRequest.of(page, size, sort));
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DiscountDTO> discountDTOS = findAllDiscounts(discountIds, sort, userEmail, filter)
                .stream()
                .map(discount -> {
                    DiscountDTO discountDTO = discountMapper.getDTO(discount);
                    if (!discount.getFavorites().isEmpty()) {
                        discountDTO.setFavorite(true);
                    }
                    return discountDTO;
                })
                .collect(Collectors.toList());
        log.debug("Successfully got list of all Discounts by filter");
        return discountDTOS;
    }

    @Override
    @Transactional
    public DiscountDTO updateDiscountById(UpdateDiscountDTO updateDiscountDTO, UUID id) {
        log.debug("Update discount by ID");
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not all tags with IDs %s exist", id)));

        if (updateDiscountDTO.getCategoryId() != null) {
            findCategory(updateDiscountDTO.getCategoryId());
        }
        if (updateDiscountDTO.getVendorId() != null) {
            findVendor(updateDiscountDTO.getVendorId());
        }
        if (updateDiscountDTO.getVendorId() != null && updateDiscountDTO.getVendorLocationsIds() != null) {
            findVendorLocations(updateDiscountDTO.getVendorId(), updateDiscountDTO.getVendorLocationsIds());
        }
        if (updateDiscountDTO.getTagIds() != null) {
            findTags(updateDiscountDTO.getTagIds());
        }
        discount = discountMapper.update(updateDiscountDTO, discount);
        discount.setId(id);
        DiscountDTO discountDTO = discountMapper.getDTO(discountRepository.save(discount));
        log.debug("Successfully updated discount by ID");
        return discountDTO;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        log.debug(String.format("Deleting Discount with ID %s", id));
        if (!discountRepository.existsByIdAndArchived(id, false)) {
            throw new NotFoundException(String.format("Discount with ID %s not found", id));
        }
        discountRepository.setArchivedById(id, true);
        log.debug(String.format("Successfully deleted Discount with ID %s", id));
    }

    @Override
    @Transactional
    public DiscountDTO restoreById(UUID id) {
        log.debug("Finding archived Discount by ID");
        if (!discountRepository.existsByIdAndArchivedAndVendorArchived(id, true, false)) {
            throw new NotFoundException(
                    String.format("Archived Discount with id %s not found or can't be restored", id));
        }
        discountRepository.setArchivedById(id, false);
        Discount discount = discountRepository
                .findByIdAndArchived(id, false)
                .orElseThrow(() -> new NotFoundException(String.format("Restored Discount with id %s not found", id)));
        log.debug("Successfully restored Discount");
        return discountMapper.getDTO(discount);
    }

    @Override
    public List<DiscountDTO> search(Integer size, String searchText) {
        Sort sort = Sort.by("viewNumber").descending();
        log.debug("Getting list of all Discounts by searchText");
        List<UUID> discountsIds = discountRepository
                .findAllDiscountIds(prepareSearchPredicate(searchText), PageRequest.of(0, size, sort));
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DiscountDTO> discountDTOS = discountRepository
                .findAllByIdInWithFavoritesByUser(discountsIds, sort, userEmail)
                .stream()
                .map(discount -> {
                    DiscountDTO discountDTO = discountMapper.getDTO(discount);
                    if (!discount.getFavorites().isEmpty()) {
                        discountDTO.setFavorite(true);
                    }
                    return discountDTO;
                })
                .collect(Collectors.toList());
        log.debug("Successfully got list of all Discounts by searchText");
        return discountDTOS;
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
                .orElseThrow(() -> new NotFoundException(
                        String.format("Category with ID %s doesn't exist", categoryId)
                ));
    }

    private Vendor findVendor(UUID vendorId) {
        return vendorRepository
                .findByIdAndArchived(vendorId, false)
                .orElseThrow(() -> new NotFoundException(String.format("Vendor with ID %s doesn't exist", vendorId)));
    }

    private Predicate preparePredicateForFindingAll(DiscountFilter filter) {
        return ExpressionUtils.and(
                QueryPredicateBuilder.init()
                        .append(filter.getCountryIds(), QDiscount.discount.vendorLocations.any().city.country.id::in)
                        .append(filter.getCityIds(), QDiscount.discount.vendorLocations.any().city.id::in)
                        .buildOr(),
                QueryPredicateBuilder.init()
                        .append(filter.getArchived(), QDiscount.discount.archived::eq)
                        .append(filter.getEndDateFrom(), QDiscount.discount.endTime::goe)
                        .append(filter.getEndDateTo(), QDiscount.discount.endTime::loe)
                        .append(filter.getCategoryIds(), QDiscount.discount.category.id::in)
                        .append(filter.getTagIds(), QDiscount.discount.tags.any().id::in)
                        .append(filter.getVendorIds(), QDiscount.discount.vendor.id::in)
                        .buildAnd());
    }

    private Predicate prepareSearchPredicate(String searchText) {
        List<Predicate> searchPredicates = Stream
                .of(StringUtils.split(searchText, StringUtils.SPACE))
                .filter(word -> word.length() >= SEARCH_WORD_MIN_LENGTH)
                .map(word -> QueryPredicateBuilder.init()
                        .append(word, QDiscount.discount.name::containsIgnoreCase)
                        .append(word, QDiscount.discount.description::containsIgnoreCase)
                        .append(word, QDiscount.discount.vendor.name::containsIgnoreCase)
                        .append(word, QDiscount.discount.category.name::containsIgnoreCase)
                        .append(word, QDiscount.discount.tags.any().name::containsIgnoreCase)
                        .buildOr())
                .collect(Collectors.toList());
        searchPredicates.add(QueryPredicateBuilder.init()
                .append(false, QDiscount.discount.archived::eq)
                .buildAnd());
        return ExpressionUtils.allOf(searchPredicates);
    }

    private List<Discount> findAllDiscounts(List<UUID> discountIds, Sort sort, String userEmail,
                                            DiscountFilter filter) {
        if (filter.getCityIds() != null || filter.getCountryIds() != null) {
            return discountRepository
                    .findAllByIdInWithFavoritesByUserAndLocations(discountIds, sort, userEmail,
                            filter.getCityIds(), filter.getCountryIds());
        } else {
            return discountRepository
                    .findAllByIdInWithFavoritesByUser(discountIds, sort, userEmail);
        }

    }
}
