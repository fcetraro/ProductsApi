package com.ml.ProductsApi;

import com.ml.ProductsApi.dao.IProductDAO;
import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.service.IProductService;
import com.ml.ProductsApi.stubs.StubArticle;
import com.ml.ProductsApi.util.Assembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
		Map<String, String> emptyMap = new HashMap<>();
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		when(repository.getArticles(emptyMap)).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetHerramientas() throws Exception {
		String url = "/api/v1/articles?category=Herramientas";
		Map<String, String> herramientasMap = new HashMap<>();
		herramientasMap.put("category", "Herramientas");
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (ArticleDTO stub:stubList) {
			stub.setCategory("Herramientas");
		}
		when(repository.getArticles(herramientasMap)).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetSomeArticles() throws Exception {
		String url = "/api/v1/articles?category=Herramientas&freeShipping=true";
		Map<String, String> herramientasMap = new HashMap<>();
		herramientasMap.put("category", "Herramientas");
		herramientasMap.put("freeShipping", "true");
		List<ArticleDTO> stubList = StubArticle.getStubArticles(8);
		List<ArticleDTO> expectedList = new ArrayList<>();
		for (int i = 0; i < stubList.size(); i++) {
			if(i<3){
				if(i==0) stubList.get(i).setFreeShipping(false);
				stubList.get(i).setCategory("Herramientas");
				expectedList.add(stubList.get(i));
			}
		}
		when(repository.getArticles(herramientasMap)).thenReturn(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
	@Test
	void shouldGetSortByNameAsc() throws Exception {
		String url = "/api/v1/articles?order=0";
		Map<String, String> map = new HashMap<>();
		map.put("order", "0");
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setName(((char)70-i)+"testName");
		}
		when(repository.getArticles(map)).thenReturn(stubList);
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
		Map<String, String> map = new HashMap<>();
		map.put("order", "1");
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setName(((char)65+i)+"testName");
		}
		when(repository.getArticles(map)).thenReturn(stubList);
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
		Map<String, String> map = new HashMap<>();
		map.put("order", "1");
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setPrice(4-i);
		}
		when(repository.getArticles(map)).thenReturn(stubList);
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
		Map<String, String> map = new HashMap<>();
		map.put("order", "2");
		List<ArticleDTO> stubList = StubArticle.getStubArticles(4);
		for (int i = 0; i < 4; i++) {
			stubList.get(i).setPrice(i);
		}
		when(repository.getArticles(map)).thenReturn(stubList);
		Collections.reverse(stubList);
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(Assembler.
						transformToStringJson(Collections.singletonList(stubList)), false));
	}
}
