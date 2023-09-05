package com.daniarques.capitoleprices.adapters.database.repository;

import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import com.daniarques.capitoleprices.adapters.database.entity.PriceIdEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepositoryJPAImpl extends PricesRepository, JpaRepository<PriceEntity, PriceIdEntity> {

	@Query("""
		SELECT p FROM PricesEntity p 
		WHERE p.id.productId = :productId 
		AND p.id.brandId = :brandId 
		AND p.id.startDate <= :applicationDate 
		AND p.id.endDate >= :applicationDate""")
	List<PriceEntity> findPricesByProductIdBrandIdAndDate(@Param("productId") Integer productId,
														  @Param("brandId") Integer brandId,
														  @Param("applicationDate") LocalDateTime applicationDate);
}
