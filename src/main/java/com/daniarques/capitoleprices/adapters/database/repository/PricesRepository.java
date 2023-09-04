package com.daniarques.capitoleprices.adapters.database.repository;

import com.daniarques.capitoleprices.adapters.database.entity.PricesEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository {

	List<PricesEntity> findPricesByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate);

}
