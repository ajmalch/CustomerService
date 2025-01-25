package com.example.customer;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenderAttributConverter implements AttributeConverter <Customer.Gender,Integer> {
    @Override
    public Integer convertToDatabaseColumn(Customer.Gender gender) {
        if(gender == null){
            return null;
        }
        return gender.ordinal();
    }

    @Override
    public  Customer.Gender convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }
        return Customer.Gender.values()[integer];
    }
}
