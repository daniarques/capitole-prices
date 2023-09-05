package com.daniarques.capitoleprices.domain.service;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import com.daniarques.capitoleprices.adapters.database.repository.PriceRepository;
import com.daniarques.capitoleprices.domain.service.error.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceService {

	private static final String PRICE_NOT_FOUND_MESSAGE_FORMAT =
		"Price with productId:{}, brandId:{} and applicationDate:{} not found";
	private final PriceRepository priceRepository;

	public PriceEntity getPriceByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate) {

		List<PriceEntity> pricesInApplicationDate =
			priceRepository.findPricesByProductIdBrandIdAndDate(productId, brandId, applicationDate);

		return pricesInApplicationDate.stream()
			.max(Comparator.comparingInt(PriceEntity::getPriority))
			.orElseThrow(() -> buildResourceNotFoundException(productId, brandId, applicationDate));
	}

	private ResourceNotFoundException buildResourceNotFoundException(Integer productId,
																	 Integer brandId,
																	 LocalDateTime applicationDate) {
		log.info(PRICE_NOT_FOUND_MESSAGE_FORMAT, productId, brandId, applicationDate);

		String notFoundMessage =
			MessageFormatter.arrayFormat(PRICE_NOT_FOUND_MESSAGE_FORMAT, List.of(productId, brandId, applicationDate).toArray())
				.getMessage();
		return new ResourceNotFoundException(notFoundMessage);
	}
}
