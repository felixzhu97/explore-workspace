package com.ai.common.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/** Stores every {@link OwnerId} attribute as its owner_id column value. */
@Converter(autoApply = true)
public class OwnerIdAttributeConverter implements AttributeConverter<OwnerId, String> {

  @Override
  public String convertToDatabaseColumn(OwnerId attribute) {
    return attribute == null ? null : attribute.getValue();
  }

  @Override
  public OwnerId convertToEntityAttribute(String dbData) {
    return dbData == null || dbData.isBlank() ? null : OwnerId.parseKey(dbData);
  }
}
