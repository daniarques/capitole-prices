package com.daniarques.capitoleprices.adapters.api;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import com.daniarques.capitoleprices.domain.service.PricesService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PricesController {

	private final PricesService pricesService;

	@GetMapping(value = "/prices")
	public PriceEntity getPriceByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate) {
		return pricesService.getPriceByProductIdBrandIdAndDate(productId, brandId, applicationDate);
	}

}
