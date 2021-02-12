package com.ml.ProductsApi.stubs;

import com.ml.ProductsApi.model.ArticleDTO;
import com.ml.ProductsApi.model.read.ArticleFromJsonDTO;

import java.util.ArrayList;
import java.util.List;

public class StubArticleFromJson {
    public static ArticleFromJsonDTO getStubArticle(int i){
        ArticleFromJsonDTO newStub = new ArticleFromJsonDTO();
        newStub.setQuantity(5+i);
        newStub.setBrand("testBrand");
        newStub.setCategory("testCategory");
        newStub.setName("testName");
        newStub.setPrestige(i%6);
        newStub.setFreeShipping(true);
        return newStub;
    }
    public static List<ArticleFromJsonDTO> getStubArticles(int lenght){
        List<ArticleFromJsonDTO> newStubs = new ArrayList<>();
        for (int i = 0; i < lenght; i++) {
            newStubs.add(getStubArticle(i));
        }
        return newStubs;
    }
}
