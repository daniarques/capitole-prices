package com.daniarques.capitoleprices.domain.service;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import com.daniarques.capitoleprices.adapters.database.entity.PriceIdEntity;
import com.daniarques.capitoleprices.adapters.database.repository.PriceRepository;
import com.daniarques.capitoleprices.domain.service.error.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

	private static final int PRODUCT_ID = 123;
	private static final int BRAND_ID = 11;
	private static final LocalDateTime APPLICATION_DATE = LocalDateTime.of(1996, 12, 11, 10, 0);
	private static final int PRODUCT_ID1 = 1;
	private static final int PRODUCT_ID2 = 2;
	private static final int LOW_PRIORITY = 0;
	private static final int HIGH_PRIORITY = Integer.MAX_VALUE;

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceService priceService;

	private final PriceIdEntity priceId = PriceIdEntity.builder()
		.productId(PRODUCT_ID1)
		.build();
	private final PriceEntity price = PriceEntity.builder()
		.id(priceId)
		.priority(LOW_PRIORITY)
		.build();

	private final PriceIdEntity priceId_highPriority = PriceIdEntity.builder()
		.productId(PRODUCT_ID2)
		.build();
	private final PriceEntity price_highPriority = PriceEntity.builder()
		.id(priceId_highPriority)
		.priority(HIGH_PRIORITY)
		.build();

	@Test
	void getPriceByProductIdBrandIdAndDate_noPricesFound() {
		given(priceRepository.findPricesByProductIdBrandIdAndDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE))
			.willReturn(emptyList());

		assertThatThrownBy(() -> priceService.getPriceByProductIdBrandIdAndDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Price with productId:123, brandId:11 and applicationDate:1996-12-11T10:00 not found");
	}

	@Test
	void getPriceByProductIdBrandIdAndDate_onePriceFound() {
		given(priceRepository.findPricesByProductIdBrandIdAndDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE))
			.willReturn(List.of(price));

		PriceEntity actual =
			priceService.getPriceByProductIdBrandIdAndDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE);

		assertEquals(price, actual);
	}

	@Test
	void getPriceByProductIdBrandIdAndDate_twoPricesFound() {
		given(priceRepository.findPricesByProductIdBrandIdAndDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE))
			.willReturn(List.of(price, price_highPriority));

		PriceEntity actual =
			priceService.getPriceByProductIdBrandIdAndDate(PRODUCT_ID, BRAND_ID, APPLICATION_DATE);

		assertEquals(price_highPriority, actual);
	}
}