package com.example.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderAttributeConverterTest {

    private final GenderAttributeConverter converter = new GenderAttributeConverter();

    @Test
    void convertToDatabaseColumn_ShouldReturnOrdinal_WhenGenderIsNotNull() {
        assertEquals(0, converter.convertToDatabaseColumn(Customer.Gender.MALE));
        assertEquals(1, converter.convertToDatabaseColumn(Customer.Gender.FEMALE));
    }

    @Test
    void convertToDatabaseColumn_ShouldReturnNull_WhenGenderIsNull() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttribute_ShouldReturnGender_WhenOrdinalIsNotNull() {
        assertEquals(Customer.Gender.MALE, converter.convertToEntityAttribute(0));
        assertEquals(Customer.Gender.FEMALE, converter.convertToEntityAttribute(1));
    }

    @Test
    void convertToEntityAttribute_ShouldReturnNull_WhenOrdinalIsNull() {
        assertNull(converter.convertToEntityAttribute(null));
    }
}