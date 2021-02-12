package com.ml.ProductsApi.stubs;

import com.ml.ProductsApi.model.ArticleDTO;

import java.util.ArrayList;
import java.util.List;

public class StubArticle {
    public static ArticleDTO getStubArticle(int id){
        ArticleDTO newStub = new ArticleDTO();
        newStub.setId(id);
        newStub.setQuantity(5+id);
        newStub.setBrand("testBrand");
        newStub.setCategory("testCategory");
        newStub.setName("testName");
        newStub.setPrestige(id%6);
        newStub.setFreeShipping(true);
        return newStub;
    }
    public static List<ArticleDTO> getStubArticles(int lenght){
        List<ArticleDTO> newStubs = new ArrayList<>();
        for (int i = 0; i < lenght; i++) {
            newStubs.add(getStubArticle(i));
        }
        return newStubs;
    }
}
