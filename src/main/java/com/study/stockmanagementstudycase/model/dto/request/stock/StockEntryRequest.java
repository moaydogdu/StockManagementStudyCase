package com.study.stockmanagementstudycase.model.dto.request.stock;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class StockEntryRequest {

    @DecimalMin(value = "0", message = "Amount 0'dan küçük olamaz.")
    private BigDecimal entryAmount;

    private LocalDateTime entryTime;

    private String wareHouseId;

    @AssertTrue(message = "İleri Tarihli Bir Stok İşlemi Yapılamaz.")
    boolean dateTimeFieldCantBeFurther() {
        if (entryTime.isAfter(LocalDateTime.now())) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Amount değeri belirtilen stoğun deposu da belirtilmelidir.")
    boolean ifAmountIsBiggerThanZeroThanWareHouseIdCannotBeNull() {
        if (Objects.isNull(entryAmount)) {
            return true;
        }

        if (Boolean.TRUE.equals(entryAmount.compareTo(BigDecimal.ZERO) > 0) && Objects.isNull(wareHouseId)) {
            return false;
        }

        return true;
    }

}
