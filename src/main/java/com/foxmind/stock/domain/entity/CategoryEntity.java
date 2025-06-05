package com.foxmind.stock.domain.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "category")
public class CategoryEntity implements Serializable {
    
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String subCategory;

    @Field("inventoryId")
    private String inventoryId;
}