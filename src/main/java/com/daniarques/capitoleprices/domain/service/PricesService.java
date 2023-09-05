package com.daniarques.capitoleprices.domain.service;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
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

	public PriceEntity getPriceByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate) {

		List<PriceEntity> pricesInApplicationDate =
			pricesRepository.findPricesByProductIdBrandIdAndDate(productId, brandId, applicationDate);

		return pricesInApplicationDate.stream()
			.max(Comparator.comparingInt(PriceEntity::getPriority))
			// TODO: 04/09/2023  Exception handling
			.orElseThrow(() -> new RuntimeException("Not found"));
	}
}
