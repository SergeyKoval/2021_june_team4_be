package com.exadel.discount.service;

import com.exadel.discount.controller.AbstractIT;
import com.exadel.discount.exception.DeletionRestrictedException;
import com.exadel.discount.model.dto.mapper.VendorMapper;
import com.exadel.discount.model.dto.vendor.BaseVendorDTO;
import com.exadel.discount.model.dto.vendor.CreateVendorDTO;
import com.exadel.discount.model.entity.Vendor;
import com.exadel.discount.repository.VendorRepository;
import com.exadel.discount.service.impl.VendorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VendorServiceTest extends AbstractIT {
    private static final UUID ID = UUID.fromString("3633f3cf-7208-4d67-923d-ce6b2cec29e2");

    @Autowired
    private VendorServiceImpl vendorService;

    @Mock
    private VendorRepository vendorRepository;

    @Mock
    private VendorMapper mapper;

    private CreateVendorDTO input;

    @BeforeEach
    public void setUp() {
        input = CreateVendorDTO.builder()
                .name("test")
                .build();
    }

    @Test
    public void testSave() {
        var result = vendorService.save(input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetAll() {
        List<Vendor> expected = List.of(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(expected);
        VendorServiceImpl implS = Mockito.mock(VendorServiceImpl.class);
        List<BaseVendorDTO> actual = implS.getAll();

        Assertions.assertEquals(actual, mapper.getListBaseDTO(expected));
    }

    @Test
    public void testDeleteById() {
        when(vendorRepository.findById(ID)).thenReturn(Optional.of(new Vendor()));
        doNothing().when(vendorRepository).delete(any(Vendor.class));
        vendorRepository.deleteById(UUID.randomUUID());
    }

    @Test
    public void testDeletionRestrictedExceptionDeleteById() {
        Exception exception = assertThrows(DeletionRestrictedException.class,
                () -> vendorService.deleteById(ID));
        Assertions.assertEquals("Vendor with ID " + ID + " doesn't exist or can't be deleted as it has discounts", exception.getMessage());
    }

    @Test
    public void testGetAllArchived() {
        List<Vendor> expected = List.of(new Vendor(), new Vendor());

        when(vendorRepository.findAllByArchived(true)).thenReturn(expected);
        VendorServiceImpl implS = Mockito.mock(VendorServiceImpl.class);
        List<BaseVendorDTO> actual = implS.getAll();

        Assertions.assertEquals(actual, mapper.getListBaseDTO(expected));
    }
}
