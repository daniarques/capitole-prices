package com.daniarques.capitoleprices.adapters.api;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import com.daniarques.capitoleprices.domain.service.PriceService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController {

	private final PriceService priceService;

	@GetMapping(value = "/prices")
	public PriceEntity getPriceByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate) {
		return priceService.getPriceByProductIdBrandIdAndDate(productId, brandId, applicationDate);
	}

}
