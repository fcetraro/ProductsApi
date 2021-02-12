package com.ml.ProductsApi;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsApiApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Test
	@Order(1)
	void shouldGetAll() throws Exception {
		String url = "/api/v1/articles";
		String expectedResult = "[{\"name\":\"Desmalezadora\",\"category\":\"Herramientas\",\"brand\":\"Makita\",\"" +
				"price\":9600,\"quantity\":5,\"prestige\":4,\"id\":0,\"freeShipping\":true},{\"name\":\"Taladro\",\"" +
				"category\":\"Herramientas\",\"brand\":\"Black & Decker\",\"price\":12500,\"quantity\":7,\"prestige" +
				"\":4,\"id\":1,\"freeShipping\":true},{\"name\":\"Soldadora\",\"category\":\"Herramientas\",\"brand" +
				"\":\"Black & Decker\",\"price\":7215,\"quantity\":10,\"prestige\":3,\"id\":2,\"freeShipping\":true}" +
				",{\"name\":\"Zapatillas Deportivas\",\"category\":\"Deportes\",\"brand\":\"Nike\",\"price\":14000" +
				",\"quantity\":4,\"prestige\":5,\"id\":3,\"freeShipping\":true},{\"name\":\"Zapatillas Deportivas" +
				"\",\"category\":\"Deportes\",\"brand\":\"Adidas\",\"price\":13650,\"quantity\":6,\"prestige\":4," +
				"\"id\":4,\"freeShipping\":true},{\"name\":\"Camiseta\",\"category\":\"Deportes\",\"brand\":\"" +
				"Topper\",\"price\":2300,\"quantity\":2,\"prestige\":3,\"id\":5,\"freeShipping\":false},{\"name" +
				"\":\"Redmi Note 9\",\"category\":\"Celulares\",\"brand\":\"Xiaomi\",\"price\":40000,\"quantity" +
				"\":15,\"prestige\":4,\"id\":6,\"freeShipping\":true},{\"name\":\"Smartwatch\",\"category\":\"" +
				"Celulares\",\"brand\":\"Noga\",\"price\":1900,\"quantity\":20,\"prestige\":2,\"id\":7,\"" +
				"freeShipping\":false},{\"name\":\"Remera\",\"category\":\"Indumentaria\",\"brand\":\"Taverniti" +
				"\",\"price\":2300,\"quantity\":2,\"prestige\":3,\"id\":8,\"freeShipping\":false},{\"name\":\"" +
				"Chomba\",\"category\":\"Indumentaria\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"" +
				"prestige\":3,\"id\":9,\"freeShipping\":false},{\"name\":\"Medias\",\"category\":\"Indumentaria\",\"" +
				"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"prestige\":1,\"id\":10,\"freeShipping\":false" +
				"},{\"name\":\"Short\",\"category\":\"Indumentaria\",\"brand\":\"Lacoste\",\"price\":3900,\"quantity" +
				"\":9,\"prestige\":3,\"id\":11,\"freeShipping\":true}]";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json((expectedResult)));
	}
	@Test
	@Order(1)
	void shouldFiltersAndGetNothing() throws Exception {
		String url = "/api/v1/articles?price=312312&quantity=14&prestige=3&brand=Nike";
		String expectedResult = "[]";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json((expectedResult)));
	}
	@Test
	@Order(1)
	void shouldFilters() throws Exception {
		String url = "/api/v1/articles?category=Indumentaria&sendFree=false";
		String expectedResult = "[{\"name\":\"Remera\",\"category\":\"Indumentaria\",\"brand\":\"Taverniti\",\"" +
				"price\":2300,\"quantity\":2,\"prestige\":3,\"id\":8,\"freeShipping\":false},{\"name\":\"Chomba" +
				"\",\"category\":\"Indumentaria\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"" +
				"prestige\":3,\"id\":9,\"freeShipping\":false},{\"name\":\"Medias\",\"category\":\"Indumentaria" +
				"\",\"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"prestige\":1,\"id\":10,\"freeShipping" +
				"\":false}]";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json((expectedResult)));
	}
	@Test
	@Order(1)
	void shouldFiltersAndOrder() throws Exception {
		String url = "/api/v1/articles?category=Indumentaria&order=0&sendFree=false";
		String expectedResult = "[{\"name\":\"Chomba\",\"category\":\"Indumentaria\",\"brand\":\"Taverniti\",\"price" +
				"\":2400,\"quantity\":6,\"prestige\":3,\"id\":9,\"freeShipping\":false},{\"name\":\"Medias\",\"" +
				"category\":\"Indumentaria\",\"brand\":\"Mistral\",\"price\":500,\"quantity\":8,\"prestige\":1,\"" +
				"id\":10,\"freeShipping\":false},{\"name\":\"Remera\",\"category\":\"Indumentaria\",\"brand\":\"" +
				"Taverniti\",\"price\":2300,\"quantity\":2,\"prestige\":3,\"id\":8,\"freeShipping\":false}]";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json((expectedResult)));
	}
	@Test
	@Order(1)
	void shouldFiltersAndOrder3() throws Exception {
		String url = "/api/v1/articles?category=Indumentaria&order=3&sendFree=false";
		String expectedResult = "[{\"name\":\"Medias\",\"category\":\"Indumentaria\",\"brand\":\"Mistral" +
				"\",\"price\":500,\"quantity\":8,\"prestige\":1,\"id\":10,\"freeShipping\":false},{\"name" +
				"\":\"Remera\",\"category\":\"Indumentaria\",\"brand\":\"Taverniti\",\"price\":2300,\"quantity" +
				"\":2,\"prestige\":3,\"id\":8,\"freeShipping\":false},{\"name\":\"Chomba\",\"category\":\"" +
				"Indumentaria\",\"brand\":\"Taverniti\",\"price\":2400,\"quantity\":6,\"prestige\":3,\"id\":9,\"" +
				"freeShipping\":false}]";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json((expectedResult)));
	}
	@Test
	@Order(1)
	void shouldGetById() throws Exception {
		String url = "/api/v1/articles/search?id=0&id=1";
		String expectedResult = "{\"articles\":[{\"name\":\"Taladro\",\"category\":\"Herramientas\",\"brand" +
				"\":\"Black & Decker\",\"price\":12500,\"quantity\":7,\"prestige\":4,\"id\":1,\"freeShipping" +
				"\":true},{\"name\":\"Desmalezadora\",\"category\":\"Herramientas\",\"brand\":\"Makita\",\"" +
				"price\":9600,\"quantity\":5,\"prestige\":4,\"id\":0,\"freeShipping\":true}]}";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json((expectedResult)));
	}
	@Test
	@Order(1)
	void shouldGetError() throws Exception {
		String url = "/api/v1/articles?notFound=1000";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	@Order(1)
	void shouldGetErrorById() throws Exception {
		String url = "/api/v1/articles/search?id=0&id=1000";
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	@Order(2)
	void shouldNotBuy() throws Exception {
		String url = "/api/v1/articles/buy";
		String requestJson = "{\"articles\":[{\"id\":1,\"quantityBought\":1000},{\"id\":2,\"quantityBought\":1}]}";
		this.mockMvc.perform(put(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isNotFound());
	}


}
