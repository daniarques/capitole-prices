package com.daniarques.capitoleprices.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
public class PricesEntity {

	@Column(name = "BRAND_ID")
	@Digits(integer = 8, fraction = 0)
	Integer brandId;

	@Column(name = "START_DATE")
	LocalDateTime startDate;

	@Column(name = "END_DATE")
	LocalDateTime endDate;

	@Column(name = "PRICE_LIST")
	@Digits(integer = 8, fraction = 0)
	Integer priceList;

	@Column(name = "PRODUCT_ID")
	@Digits(integer = 8, fraction = 0)
	Integer productId;

	@Column(name = "PRIORITY")
	@Digits(integer = 8, fraction = 0)
	Integer priority;

	@Column(name = "PRICE")
	@Digits(integer = 8, fraction = 2)
	BigDecimal price;

	@Column(name = "CURR")
	@Enumerated(EnumType.STRING)
	Currency currency;
}
