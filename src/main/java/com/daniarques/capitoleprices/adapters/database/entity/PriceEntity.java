package com.daniarques.capitoleprices.adapters.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "PRICES")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
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
