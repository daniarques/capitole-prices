package com.daniarques.capitoleprices.adapters.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Table(name = "PRICES")
@Entity
@Builder
public class PriceEntity {

	@EmbeddedId
	PriceIdEntity id;

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
