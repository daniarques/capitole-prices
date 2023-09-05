package com.daniarques.capitoleprices.adapters.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.daniarques.capitoleprices.adapters.database.entity.Currency;
import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import com.daniarques.capitoleprices.adapters.database.entity.PriceIdEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PriceControllerTest {


	@Autowired
	private PriceController priceController;

	private final PriceEntity firstRow = PriceEntity.builder()
		.id(PriceIdEntity.builder()
				.brandId(1)
				.startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
				.endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
				.productId(35455)
				.build())
		.priceList(1)
		.priority(0)
		.price(new BigDecimal("35.50"))
		.currency(Currency.EUR)
		.build();
	private final PriceEntity secondRow = PriceEntity.builder()
		.id(PriceIdEntity.builder()
				.brandId(1)
				.startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
				.endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
				.productId(35455)
				.build())
		.priceList(2)
		.priority(1)
		.price(new BigDecimal("25.45"))
		.currency(Currency.EUR)
		.build();
	private final PriceEntity thirdRow = PriceEntity.builder()
		.id(PriceIdEntity.builder()
				.brandId(1)
				.startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
				.endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
				.productId(35455)
				.build())
		.priceList(3)
		.priority(1)
		.price(new BigDecimal("30.50"))
		.currency(Currency.EUR)
		.build();
	private final PriceEntity fourthRow = PriceEntity.builder()
		.id(PriceIdEntity.builder()
				.brandId(1)
				.startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
				.endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
				.productId(35455)
				.build())
		.priceList(4)
		.priority(1)
		.price(new BigDecimal("38.95"))
		.currency(Currency.EUR)
		.build();

	@Test
	void getPriceByProductIdBrandIdAndDate_noPriceFound() {
		// TODO: 05/09/2023 Change exception
		assertThrows(RuntimeException.class,
					 () -> priceController.getPriceByProductIdBrandIdAndDate(0, 0, LocalDateTime.MIN));
	}

	// -          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test1() {
		PriceEntity actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 14, 10, 0, 0));

		assertEquals(firstRow, actual);
	}

	// -          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test2() {
		PriceEntity actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 14, 16, 0, 0));

		assertEquals(secondRow, actual);
	}

	// -          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test3() {
		PriceEntity actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 14, 21, 0, 0));

		assertEquals(firstRow, actual);
	}

	// -          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test4() {
		PriceEntity actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 15, 10, 0, 0));

		assertEquals(thirdRow, actual);
	}

	// -          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test5() {
		PriceEntity actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 15, 21, 0, 0));

		assertEquals(fourthRow, actual);
	}
}