package com.ml.ProductsApi;

import com.ml.ProductsApi.dao.IProductDAO;
import com.ml.ProductsApi.exception.concreteExceptions.ProductNotFoundException;
import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.response.ArticlesResponseDTO;
import com.ml.ProductsApi.service.IProductService;
import com.ml.ProductsApi.stubs.StubArticle;
import com.ml.ProductsApi.util.Assembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsApiApplicationMockTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IProductDAO repository;

	@Autowired
	private IProductService service;

	@Test
	void shouldGetAll() throws Exception {
		String url = "/api/v1/articles";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		when(repository.getArticles()).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetHerramientas() throws Exception {
		String url = "/api/v1/articles?category=Herramientas";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (ArticleDTO stub:stubList) {
			stub.setCategory("Herramientas");
		}
		when(repository.getArticles()).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetHerramientasAndFreeShippingArticles() throws Exception {
		String url = "/api/v1/articles?category=Herramientas&sendFree=true";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(8);
		List<ArticleDTO> expectedList = new ArrayList<>();
		for (int i = 0; i < stubList.size(); i++) {
			if(i<3){
				if(i==0){
					stubList.get(i).setFreeShipping(false);
				} else {
					expectedList.add(stubList.get(i));
				}
				stubList.get(i).setCategory("Herramientas");
			}
		}
		when(repository.getArticles()).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(expectedList)), false));
	}
	@Test
	void shouldGetWrongCastFilterException() throws Exception {
		String url = "/api/v1/articles?category=Herramientas&quantity=notValidValue";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(8);
		when(repository.getArticles()).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	void shouldGetSortByNameAsc() throws Exception {
		String url = "/api/v1/articles?order=0";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setName(((char)70-i)+"testName");
		}
		when(repository.getArticles()).thenReturn(stubList);
		Collections.reverse(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetSortByNameDesc() throws Exception {
		String url = "/api/v1/articles?order=1";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setName(((char)65+i)+"testName");
		}
		when(repository.getArticles()).thenReturn(stubList);
		Collections.reverse(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetSortByPriceAsc() throws Exception {
		String url = "/api/v1/articles?order=1";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setPrice(4-i);
		}
		when(repository.getArticles()).thenReturn(stubList);
		Collections.reverse(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetSortByPriceDesc() throws Exception {
		String url = "/api/v1/articles?order=2";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setPrice(i);
		}
		when(repository.getArticles()).thenReturn(stubList);
		Collections.reverse(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetById() throws Exception {
		String url = "/api/v1/articles/search?id=0&id=1";
		ArticlesResponseDTO expectedResponse = new ArticlesResponseDTO();
		List<ArticleDTO> expectedList = StubArticle.getStubArticles(2);
		expectedResponse.setArticles(expectedList);
		when(repository.getArticleById(0)).thenReturn(expectedList.get(0));
		when(repository.getArticleById(1)).thenReturn(expectedList.get(1));
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(expectedResponse)), false));
	}
	@Test
	void shouldGetProductNotFoundException() throws Exception {
		String url = "/api/v1/articles/search?id=56216";
		ArticlesResponseDTO expectedResponse = new ArticlesResponseDTO();
		List<ArticleDTO> expectedList = StubArticle.getStubArticles(2);
		expectedResponse.setArticles(expectedList);
		when(repository.getArticleById(56216)).thenThrow(ProductNotFoundException.class);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	void shouldGetFilterNotFoundException() throws Exception {
		String url = "/api/v1/articles?notFilter=56216";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(2);
		when(repository.getArticles()).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	void shouldNotBuy() throws Exception {
		String url = "/api/v1/articles/buy";
		String requestJson = "{\"articles\":[{\"id\":1,\"quantityBought\":1000},{\"id\":2,\"quantityBought\":1}]}";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(5);
		when(repository.getArticleById(1)).thenReturn(stubList.get(1));
		when(repository.getArticleById(2)).thenReturn(stubList.get(2));
		this.mockMvc.perform(put(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	void shouldBuy() throws Exception {
		String url = "/api/v1/articles/buy";
		String requestJson = "{\"articles\":[{\"id\":1,\"quantityBought\":1},{\"id\":2,\"quantityBought\":1}]}";
		List<ArticleDTO> stubList = StubArticle.getStubArticles(5);
		when(repository.getArticleById(1)).thenReturn(stubList.get(1));
		when(repository.getArticleById(2)).thenReturn(stubList.get(2));
		this.mockMvc.perform(put(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
