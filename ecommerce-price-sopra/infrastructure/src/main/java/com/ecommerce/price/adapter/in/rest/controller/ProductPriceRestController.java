package com.ecommerce.price.adapter.in.rest.controller;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.price.adapter.in.rest.controller.ProductPriceRestController;
import com.ecommerce.price.adapter.in.rest.controller.response.mapper.ProductRestMapper;
import com.ecommerce.price.adapter.in.rest.interfaces.BrandApi;
import com.ecommerce.price.adapter.in.rest.interfaces.dto.ProductRest;
import com.ecommerce.price.use_case.SearchPrice;

import lombok.RequiredArgsConstructor;

/**
 * Implementa el controller generado por OpenApi
 * @author fjcastano
 */
@RequiredArgsConstructor
@RestController
public class ProductPriceRestController implements BrandApi {

	private static final Logger LOGGER = Logger.getLogger(ProductPriceRestController.class.getName());

	private final SearchPrice searchPrice;
	private final ProductRestMapper mapper;

	@Override
	public ResponseEntity<ProductRest> findProductWithPriorityPriceByBrandProductAndDate(Integer brandId, Long productId,
			OffsetDateTime selectedDate) {

		Objects.requireNonNull(brandId);
		Objects.requireNonNull(productId);
		Objects.requireNonNull(selectedDate);

		LOGGER.info(new StringBuilder().append("Search priority price by brand (").append(brandId).append("), product (").append(productId)
				.append(") and date (").append(selectedDate).append(").").toString());

		return ResponseEntity
				.ok(this.mapper.mapTo(this.searchPrice.findPriorityPriceByBrandProductAndDate(brandId, productId, selectedDate.toLocalDateTime())));
	}

}
