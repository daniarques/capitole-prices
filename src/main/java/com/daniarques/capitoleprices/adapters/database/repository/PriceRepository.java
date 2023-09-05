package com.daniarques.capitoleprices.adapters.database.repository;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

	List<PriceEntity> findPricesByProductIdBrandIdAndDate(Integer productId, Integer brandId, LocalDateTime applicationDate);

}
