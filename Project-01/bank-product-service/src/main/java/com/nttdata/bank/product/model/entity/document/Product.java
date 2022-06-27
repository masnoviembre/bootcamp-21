package com.nttdata.bank.product.model.entity.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private Integer productId;
    private String productName;
    private String productType;
    private Float productMaintenance;
    private Integer productMonthlyMovements;
}