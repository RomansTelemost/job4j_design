package ru.job4j.ood.srp.wrongdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Нарушение принципа SRP:
 * ArticleGenerator выполняет роль и хранилища и генератора,
 * а так же обладает сервисными функциями поиска статьи по предикату.
 *
 */
public class ArticleGenerator implements GeneratorSRP {

    private List<String> articlesList;

    public void setArticlesList(List<String> articlesList) {
        this.articlesList = articlesList;
    }

    public List<String> getArticlesList() {
        return articlesList;
    }

    public List<String> findArticle(Predicate<String> predicate) {
        return articlesList.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public void generate() {
        /**
         * generate
         */
        articlesList = new ArrayList<>();
        articlesList.add("Article1");
    }
}
