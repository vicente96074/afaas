package com.kojstarinnovations.afaas.commons.mapper;

import com.kojstarinnovations.afaas.commons.exception.MapperException;
import org.hibernate.Hibernate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Customized model mapper class that maps fields from a source object to a destination object
 *
 * @Author: KojStar Innovations
 */
public class ModelMapperCustomized {

    /**
     * The method map
     *
     * @param source        is the source object
     * @param dest          is the destination class
     * @param <SOURCE>      is the source object type
     * @param <DESTINATION> is the destination object type
     * @return the destination object
     */
    public <SOURCE, DESTINATION> DESTINATION map(SOURCE source, Class<DESTINATION> dest) {

        if(source == null) {
            throw new MapperException("Source object is null");
        }

        DESTINATION destination;

        try {
            destination = dest.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new MapperException("Could not instantiate the destination class '" + dest.getName() + "'");
        }

        // Initialize and unproxy the source object
        source = (SOURCE) initializeAndUnproxy(source);

        Class<?> sourceClass = source.getClass();
        Class<?> destinationClass = destination.getClass();

        Set<Field> sourceFields = getAllFields(sourceClass);
        Set<Field> destinationFields = getAllFields(destinationClass);

        SOURCE finalSource = source;
        sourceFields.forEach(sourceField -> destinationFields.stream()
                .filter(destinationField -> destinationField.getName().equals(sourceField.getName()))
                .findFirst()
                .ifPresent(destinationField -> {
                    sourceField.setAccessible(true);
                    destinationField.setAccessible(true);

                    Object value;
                    try {
                        value = sourceField.get(finalSource);
                    } catch (IllegalAccessException e) {
                        throw new MapperException("Could not access the field '" + sourceField.getName() + "' of the source class '" + sourceClass.getName() + "'");
                    }

                    try {
                        Object mappedValue = mapField(value, destinationField.getType());
                        destinationField.set(destination, mappedValue);
                    } catch (IllegalAccessException e) {
                        throw new MapperException("Could not access the field '" + destinationField.getName() + "' of the destination class '" + destinationClass.getName() + "'");
                    }
                }));

        return destination;
    }

    private Object initializeAndUnproxy(Object source) {

        //Check if the source is a Hibernate proxy and initialize it if needed
        if(!Hibernate.isInitialized(source)){
            Hibernate.initialize(source);
        }

        // unproxy the source to get the actual instance
        return Hibernate.unproxy(source);
    }

    private Set<Field> getAllFields(Class<?> type) {
        Set<Field> fields = new HashSet<>();
        Class<?> current = type;
        while (current != null && current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        fields.removeIf(field -> Modifier.isStatic(field.getModifiers()));
        return fields;
    }

    private Object mapField(Object value, Class<?> destinationFieldType) {
        if (value == null) {
            return null;
        }
        Class<?> sourceFieldType = value.getClass();

        if (destinationFieldType.isEnum() && sourceFieldType.equals(String.class)) {
            try {
                return Enum.valueOf((Class<Enum>) destinationFieldType, (String) value);
            } catch (IllegalArgumentException e) {
                throw new MapperException("Could not map String value '" + value + "' to enum type '" + destinationFieldType.getName() + "'");
            }
        }

        if (sourceFieldType.equals(destinationFieldType)) {
            return value;
        }

        throw new MapperException("Incompatible data types for field. Expected '" + destinationFieldType.getName() + "' but found '" + sourceFieldType.getName() + "'");
    }
}
