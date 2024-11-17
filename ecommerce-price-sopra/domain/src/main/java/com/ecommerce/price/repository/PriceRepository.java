package com.ecommerce.price.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import com.ecommerce.price.Price;

public interface PriceRepository {

	Collection<Price> findPriorityPriceByBrandProductAndDate(Integer brandId, Long productId, LocalDateTime selectedDate);

}
