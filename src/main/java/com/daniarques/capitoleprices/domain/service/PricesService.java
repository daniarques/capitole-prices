package com.daniarques.capitoleprices.domain.service;

import com.daniarques.capitoleprices.adapters.database.entity.PricesEntity;
import com.daniarques.capitoleprices.adapters.database.repository.PricesRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricesService {

	private final PricesRepository pricesRepository;

	public PricesEntity getPriceByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate) {

		List<PricesEntity> pricesInApplicationDate =
			pricesRepository.findPricesByProductIdBrandIdAndDate(productId, brandId, applicationDate);

		return pricesInApplicationDate.stream()
			.max(Comparator.comparingInt(PricesEntity::getPriority))
			.orElseThrow(() -> new RuntimeException("Not found"));
	}
}
