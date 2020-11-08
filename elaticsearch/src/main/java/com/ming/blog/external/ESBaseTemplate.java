package com.ming.blog.external;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

@Slf4j
public abstract class ESBaseTemplate <T> {

    List<T> searchByQueryBuilder(QueryBuilders queryBuilder) {
        return null;
    }

    List<T> searchBySearchQuery(SearchQuery searchQuery) {
        return null;
    }

    List<T> searchByBuilderAndPage(SearchQuery searchQuery) {
        return null;
    }

}
