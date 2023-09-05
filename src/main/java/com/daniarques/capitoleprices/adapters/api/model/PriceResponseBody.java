package com.daniarques.capitoleprices.adapters.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PriceResponseBody {

	Integer productId;
	Integer brandId;
	Integer priceList;
	LocalDateTime startDate;
	LocalDateTime endDate;
	BigDecimal price;

}
