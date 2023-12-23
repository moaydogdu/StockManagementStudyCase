package com.study.stockmanagementstudycase.repository;

import com.study.stockmanagementstudycase.model.entities.StockTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransactionEntity, String> {

    @Modifying
    @Query("UPDATE StockTransactionEntity SET " +
            "beforeAmount =  beforeAmount + :amount, " +
            "afterAmount = afterAmount + :amount " +
            " WHERE date > :date")
    void updateBeforeAmountAndAfterAmountAfterSpecifiedDate(
            @Param("date") final LocalDateTime date,
            @Param("amount") final BigDecimal amount
    );

    @Modifying
    @Query("UPDATE StockTransactionEntity SET " +
            "beforeAmount =  beforeAmount - :amount, " +
            "afterAmount = afterAmount - :amount " +
            " WHERE date > :date")
    void updateBeforeAmountAndAfterAmountAfterSpecifiedDateBySubtractAmount(
            @Param("date") final LocalDateTime date,
            @Param("amount") final BigDecimal amount
    );


    @Query("SELECT st FROM StockTransactionEntity st " +
            "WHERE st.date < :date " +
            "ORDER BY  st.date DESC " +
            "LIMIT 1"
    )
    Optional<StockTransactionEntity> findStockTransactionEntityByDateBefore(
            @Param("date") final LocalDateTime date
    );



}
