package com.daniarques.capitoleprices.adapters.api;

import com.daniarques.capitoleprices.adapters.api.model.PriceResponseBody;
import com.daniarques.capitoleprices.adapters.api.model.mapper.PriceMapper;
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
	private final PriceMapper priceMapper;

	@GetMapping(value = "/prices")
	public PriceResponseBody getPriceByProductIdBrandIdAndDate(Integer productId,
															   Integer brandId,
															   LocalDateTime applicationDate) {
		PriceEntity priceEntity =
			priceService.getPriceByProductIdBrandIdAndDate(productId, brandId, applicationDate);

		return priceMapper.toPriceResponseBody(priceEntity);
	}

}
