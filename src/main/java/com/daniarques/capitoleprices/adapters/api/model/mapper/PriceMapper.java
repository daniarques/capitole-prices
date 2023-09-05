package com.daniarques.capitoleprices.adapters.api.model.mapper;

import com.daniarques.capitoleprices.adapters.api.model.PriceResponseBody;
import com.daniarques.capitoleprices.adapters.database.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

	@Mapping(target = "productId", source = "id.productId")
	@Mapping(target = "brandId", source = "id.brandId")
	@Mapping(target = "startDate", source = "id.startDate")
	@Mapping(target = "endDate", source = "id.endDate")
	PriceResponseBody toPriceResponseBody(PriceEntity price);

}
