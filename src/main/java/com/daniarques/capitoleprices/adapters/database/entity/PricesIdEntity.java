package com.daniarques.capitoleprices.adapters.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Embeddable
public class PricesIdEntity {

	@Column(name = "BRAND_ID")
	@Digits(integer = 8, fraction = 0)
	Integer brandId;

	@Column(name = "START_DATE")
	LocalDateTime startDate;

	@Column(name = "END_DATE")
	LocalDateTime endDate;

	@Column(name = "PRODUCT_ID")
	@Digits(integer = 8, fraction = 0)
	Integer productId;

}
