package com.daniarques.capitoleprices.adapters.api;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.daniarques.capitoleprices.adapters.api.model.PriceResponseBody;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private PriceController priceController;

	private final PriceResponseBody firstRow = PriceResponseBody.builder()
		.brandId(1)
		.startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
		.endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
		.productId(35455)
		.priceList(1)
		.price(new BigDecimal("35.50"))
		.build();
	private final PriceResponseBody secondRow = PriceResponseBody.builder()
		.brandId(1)
		.startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
		.endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
		.productId(35455)
		.priceList(2)
		.price(new BigDecimal("25.45"))
		.build();
	private final PriceResponseBody thirdRow = PriceResponseBody.builder()
		.brandId(1)
		.startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
		.endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
		.productId(35455)
		.priceList(3)
		.price(new BigDecimal("30.50"))
		.build();
	private final PriceResponseBody fourthRow = PriceResponseBody.builder()
		.brandId(1)
		.startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
		.endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
		.productId(35455)
		.priceList(4)
		.price(new BigDecimal("38.95"))
		.build();

	@Test
	void getPriceByProductIdBrandIdAndDate_noPriceFound() {
		String errorMessage = given()
			.port(port)
			.queryParam("productId", 123)
			.queryParam("brandId", 11)
			.queryParam("applicationDate", LocalDateTime.of(1996, 12, 11, 10, 0)
				.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
			.when()
			.get("/prices")
			.then().assertThat().statusCode(HttpStatus.NOT_FOUND.value())
			.extract().asString();

		assertEquals("Price with productId:123, brandId:11 and applicationDate:1996-12-11T10:00 not found", errorMessage);

	}

	// -          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test1() {
		PriceResponseBody actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 14, 10, 0, 0));

		assertEquals(firstRow, actual);
	}

	// -          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test2() {
		PriceResponseBody actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 14, 16, 0, 0));

		assertEquals(secondRow, actual);
	}

	// -          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test3() {
		PriceResponseBody actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 14, 21, 0, 0));

		assertEquals(firstRow, actual);
	}

	// -          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test4() {
		PriceResponseBody actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 15, 10, 0, 0));

		assertEquals(thirdRow, actual);
	}

	// -          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	@Test
	void getPriceByProductIdBrandIdAndDate_test5() {
		PriceResponseBody actual =
			priceController.getPriceByProductIdBrandIdAndDate(35455, 1, LocalDateTime.of(2020, 6, 15, 21, 0, 0));

		assertEquals(fourthRow, actual);
	}
}