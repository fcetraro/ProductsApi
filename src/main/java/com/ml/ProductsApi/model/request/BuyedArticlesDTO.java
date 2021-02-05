package com.ml.ProductsApi.model.request;

import java.util.List;

public class BuyedArticlesDTO {
    List<QuantityArticleDTO> articles;

    public List<QuantityArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<QuantityArticleDTO> articles) {
        this.articles = articles;
    }
}
