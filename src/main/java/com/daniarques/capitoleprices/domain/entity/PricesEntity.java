package com.daniarques.capitoleprices.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity(name = "PRICES")
public class PricesEntity {

	@EmbeddedId
	PricesIdEntity id;

	@Column(name = "PRICE_LIST")
	@Digits(integer = 8, fraction = 0)
	Integer priceList;

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
