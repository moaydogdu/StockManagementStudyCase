package com.study.stockmanagementstudycase.model.dto.request.stock;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockSaleRequest {
    @DecimalMin(value = "0.0001", message = "Amount 0'dan büyük olmalıdır.")
    private BigDecimal amount;

    private LocalDateTime saleTime;

    @UUID
    private String wareHouseId;

    @AssertTrue(message = "İleri Tarihli Bir Stok İşlemi Yapılamaz.")
    boolean dateTimeFieldCantBeFurther() {
        if (saleTime.isAfter(LocalDateTime.now())) {
            return false;
        }
        return true;
    }
}
