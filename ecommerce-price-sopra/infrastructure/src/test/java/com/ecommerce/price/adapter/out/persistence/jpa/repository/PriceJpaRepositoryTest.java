package com.ecommerce.price.adapter.out.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.price.adapter.out.persistence.jpa.mapper.PriceMapper;
import com.ecommerce.price.adapter.out.persistence.jpa.repository.base.BaseJpaRepository;


@ExtendWith(MockitoExtension.class)
class PriceJpaRepositoryTest {

	@InjectMocks
	private PriceJpaRepository priceJpaRepository;

	@Mock
	private BaseJpaRepository repository;

	@Mock
	private PriceMapper mapper;

	@Test
	void findPriorityPriceByBrandProductAndDate_Ok() {
		assertNotNull(this.priceJpaRepository.findPriorityPriceByBrandProductAndDate(1, 1L, LocalDateTime.now()));
	}

	@Test
	void findPriorityPriceByBrandProductAndDate_brandIdNull() {
		assertNotNull(this.priceJpaRepository.findPriorityPriceByBrandProductAndDate(null, 1L, LocalDateTime.now()));
	}

	@Test
	void findPriorityPriceByBrandProductAndDate_productIdNull() {
		assertNotNull(this.priceJpaRepository.findPriorityPriceByBrandProductAndDate(1, null, LocalDateTime.now()));
	}

	@Test
	void findPriorityPriceByBrandProductAndDate_dateNull() {
		assertNotNull(this.priceJpaRepository.findPriorityPriceByBrandProductAndDate(1, 1L, null));
	}

}
