package com.ecommerce.price.adapter.out.persistence.jpa.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.ecommerce.price.Price;
import com.ecommerce.price.adapter.out.persistence.jpa.mapper.PriceMapper;
import com.ecommerce.price.adapter.out.persistence.jpa.repository.base.BaseJpaRepository;
import com.ecommerce.price.repository.PriceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PriceJpaRepository implements PriceRepository {

	private final BaseJpaRepository repository;
	private final PriceMapper mapper;

	@Override
	public Collection<Price> findPriorityPriceByBrandProductAndDate(Integer brandId, Long productId, LocalDateTime selectedDate) {
		return this.mapper.mapTo(this.repository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
				brandId, productId, selectedDate, selectedDate));
	}

}
