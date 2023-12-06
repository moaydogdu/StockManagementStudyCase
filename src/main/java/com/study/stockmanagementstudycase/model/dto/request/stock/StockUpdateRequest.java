package com.study.stockmanagementstudycase.model.dto.request.stock;

import com.study.stockmanagementstudycase.model.enums.UnitType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdateRequest {
    @NotBlank(message = "Name alanı boş bırakılamaz.")
    private String name;

    @DecimalMin(value = "0.0001", message = "Price 0'dan büyük olmalıdır.")
    private BigDecimal price;

    @DecimalMin(value = "0", message = "Amount 0'dan küçük olamaz.")
    private BigDecimal amount;

    private LocalDateTime updateTime;

    private UnitType unitType;

    private String wareHouseId;

    @AssertTrue(message = "İleri Tarihli Bir Stok İşlemi Yapılamaz.")
    boolean dateTimeFieldCantBeFurther() {
        if (updateTime.isAfter(LocalDateTime.now())) {
            return false;
        }
        return true;
    }

}
