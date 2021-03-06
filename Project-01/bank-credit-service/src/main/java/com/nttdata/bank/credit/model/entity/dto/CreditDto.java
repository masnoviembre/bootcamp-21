package com.nttdata.bank.credit.model.entity.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CreditDto {

    private Integer creditId;
    private String creditNumber;
    private Integer clientId;
    private Integer productId;
    private Float creditLine;
    private Integer creditMonths;
    private Float creditBalance;
    private Date creditDateOpen;
    private Date creditDateClose;
}
