package com.study.stockmanagementstudycase.model.dto.request.stock;

import com.study.stockmanagementstudycase.model.enums.UnitType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockCreateRequest {

    @NotBlank(message = "Name alanı boş bırakılamaz.")
    private String name;

    @DecimalMin(value = "0.0001", message = "Price 0'dan büyük olmalıdır.")
    private BigDecimal price;

    @DecimalMin(value = "0", message = "Amount 0'dan küçük olamaz.")
    private BigDecimal amount;

    private LocalDateTime dateTime;

    private UnitType unitType;

    private String wareHouseId;

    @AssertTrue(message = "İleri Tarihli Bir Stok İşlemi Yapılamaz.")
    boolean dateTimeFieldCantBeFurther() {
        if (dateTime.isAfter(LocalDateTime.now())) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Amount değeri belirtilen stoğun deposu da belirtilmelidir.")
    boolean ifAmountIsBiggerThanZeroThanWareHouseIdCannotBeNull() {
        if (Objects.isNull(amount)) {
            return true;
        }

        if (Boolean.TRUE.equals(amount.compareTo(BigDecimal.ZERO) > 0) && Objects.isNull(wareHouseId)) {
            return false;
        }

        return true;
    }

}
