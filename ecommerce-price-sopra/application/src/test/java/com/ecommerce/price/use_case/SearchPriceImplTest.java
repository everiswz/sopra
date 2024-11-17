package com.ecommerce.price.use_case;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.price.Price;
import com.ecommerce.price.exception.ProductoNotFoundException;
import com.ecommerce.price.repository.PriceRepository;

@ExtendWith(MockitoExtension.class)
class SearchPriceImplTest {

	@InjectMocks
	private SearchPriceImpl searchPrice;

	@Mock
	private PriceRepository pricesRepository;

	@Test
	void searchProductInDate_Ok() {
		when(this.pricesRepository.findPriorityPriceByBrandProductAndDate(Mockito.anyInt(), Mockito.anyLong(), Mockito.any()))
				.thenReturn(getPriceCollection());
		assertEquals(5, this.searchPrice.findPriorityPriceByBrandProductAndDate(1, 1L, LocalDateTime.now()).getPriority());
	}

	@Test
	void searchProductInDate_productNotFound() {
		assertThrows(ProductoNotFoundException.class, () -> this.searchPrice.findPriorityPriceByBrandProductAndDate(1, 1L, LocalDateTime.now()));
	}

	@Test
	void searchProductInDate_brandIdNull() {
		assertThrows(NullPointerException.class, () -> this.searchPrice.findPriorityPriceByBrandProductAndDate(null, 1L, LocalDateTime.now()));
	}

	@Test
	void searchProductInDate_productIdNull() {
		assertThrows(NullPointerException.class, () -> this.searchPrice.findPriorityPriceByBrandProductAndDate(1, null, LocalDateTime.now()));
	}

	@Test
	void searchProductInDate_selectedDateNull() {
		assertThrows(NullPointerException.class, () -> this.searchPrice.findPriorityPriceByBrandProductAndDate(1, 1L, null));
	}
	
	private Collection<Price> getPriceCollection(){
		return Arrays.asList(Price.maker().priority(1).build(), Price.maker().priority(5).build());
	}

}
