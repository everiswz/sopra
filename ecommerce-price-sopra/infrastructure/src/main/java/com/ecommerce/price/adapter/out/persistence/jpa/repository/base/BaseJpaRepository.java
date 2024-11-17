package com.ecommerce.price.adapter.out.persistence.jpa.repository.base;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.price.adapter.out.persistence.jpa.PriceEntity;

@Repository
public interface BaseJpaRepository extends JpaRepository<PriceEntity, Long> {

	List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer brandId, Long productId,
			LocalDateTime selectedDateOne, LocalDateTime selectedDateTwo);

}
