package com.ecommerce.price.use_case;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ecommerce.price.Price;
import com.ecommerce.price.exception.ProductoNotFoundException;
import com.ecommerce.price.repository.PriceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchPriceImpl implements SearchPrice {

	private final PriceRepository pricesRepository;

	@Override
	public Price findPriorityPriceByBrandProductAndDate(Integer brandId, Long productId, LocalDateTime selectedDate) {

		Objects.requireNonNull(brandId);
		Objects.requireNonNull(productId);
		Objects.requireNonNull(selectedDate);

		return this.pricesRepository.findPriorityPriceByBrandProductAndDate(brandId, productId, selectedDate).stream()
				.sorted(Comparator.comparing(Price::getPriority, (s1, s2) -> {return s2.compareTo(s1);})).findFirst()
				.orElseThrow(ProductoNotFoundException::new);
	}

}
