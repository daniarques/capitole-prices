package com.daniarques.capitoleprices.adapters.database.repository;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository {

	List<PriceEntity> findPricesByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate);

}
