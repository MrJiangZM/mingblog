package com.ming.blog.service.impl;

import com.google.common.collect.Lists;
import com.ming.blog.dao.ESPersonDao;
import com.ming.blog.dao.ESStudentDao;
import com.ming.blog.domain.es.Person;
import com.ming.blog.domain.es.Student;
import com.ming.blog.service.ESTestService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ESTestServiceImpl implements ESTestService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ESPersonDao esTestDao;
    @Autowired
    private ESStudentDao esStudentDao;

    @Override
    public void add() {
        Person person = new Person();
        person.setId(1);
        person.setDate(new Date());
        person.setPrice(15.23456);
        person.setUrl("http://localhost:9200/");
        person.setTitle("文档一开始就介绍 CrudRepository ，比如，" +
                "继承 Repository，其他比如 JpaRepository、MongoRepository" +
                "是继承CrudRepository。也对其中的方法做了简单说明，我们一起来看一下");
        esTestDao.save(person);

        Student s1 = new Student();
        s1.setId(10000L);
        s1.setName("List转到Iterator容易，JDK本身就支持，反过来的实现方式如下：");
        s1.setPassword("List转到Iterator容易，JDK本身就支持，反过来的实现方式如下：");

        Student s2 = new Student();
        s2.setId(10001L);
        s2.setName("csxypr\n" +
                "陈新月-Phyllis： 感谢博主的分享，对于 Iterator 转成 List有些了解了");
        s2.setPassword("csxypr\n" +
                "陈新月-Phyllis： 感谢博主的分享，对于 Iterator 转成 List有些了解了");
        ArrayList<Student> students = Lists.newArrayList(s1, s2);
        esStudentDao.saveAll(students);
    }

    @Override
    public void update() {
        Optional<Student> byId = esStudentDao.findById(10001L);
        byId.ifPresent(s -> {
            s.setName("比如：你的方法名叫做：findByTitle，那么它就知道你是根据title查询，然后自动帮你完成，无需写实现类。");
            s.setPassword("ll");
            esStudentDao.save(byId.get());
        });
    }

    @Override
    public void delete() {
        esStudentDao.deleteById(10000L);
    }

    @Override
    public List<Student> like(String str) {
//        MatchPhraseQueryBuilder b1 = QueryBuilders.matchPhraseQuery("name", "感谢");
//        MatchPhraseQueryBuilder b2 = QueryBuilders.matchPhraseQuery("name", "感谢");
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(b1)
//                .add(QueryBuilders.matchPhraseQuery("name", searchContent),
//                        ScoreFunctionBuilders.weightFactorFunction(100))
//                .add(QueryBuilders.matchPhraseQuery("description", searchContent),
//                        ScoreFunctionBuilders.weightFactorFunction(100))
//                //设置权重分 求和模式
//                .scoreMode("sum")
//                //设置权重分最低分
//                .setMinScore(10);
        Iterable<Student> byName = esStudentDao.findByName(str);
        return Lists.newArrayList(byName);
    }

    @Override
    public List contain(String str) {
        return Lists.newArrayList(esStudentDao.findByNameContains(str));

    }

    /**
     * SearchQuery的构建
     * @param str
     * @return
     */
    @Override
    public List search(String str) {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("name", str));
        // 搜索，获取结果
        Page<Student> items = esStudentDao.search(queryBuilder.build());
        return items.getContent();
    }

    /**
     * SearchQuery的构建
     * @param str
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List searchKey(String str, Integer pageNum, Integer pageSize) {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("name", str));
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        queryBuilder.withPageable(pageable);
        // 搜索，获取结果
        Page<Student> items = esStudentDao.search(queryBuilder.build());
        return items.getContent();
    }

    @Override
    public List searchAll(String a, Integer page, Integer size) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(page).size(size);
        System.out.println(sourceBuilder);

        return null;
    }

    @Override
    public List<Student> likeKey(String str, Integer pageNum, Integer pageSize) {
        FuzzyQueryBuilder fuzzyQueryBuilder;
        fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", str);
        return Lists.newArrayList(esStudentDao.search(fuzzyQueryBuilder));
    }

    @Override
    public List<Student> query(String str) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termsQuery("name", "啦啦", "哈哈"))
                .mustNot(QueryBuilders.termQuery("password", "哒哒"))
                .should(QueryBuilders.termQuery("name", "嘎嘎"));
        Iterable<Student> search = esStudentDao.search(queryBuilder);
        return Lists.newArrayList(search);
    }

    @Override
    public List<Student> containQuery(String str) {
        ConstantScoreQueryBuilder boost = QueryBuilders.constantScoreQuery(
                QueryBuilders.termQuery("name", "啪啪")).boost(2.0f);
        Iterable<Student> search = esStudentDao.search(boost);
        return Lists.newArrayList(search);
    }

    @Override
    public List<Person> find() {
        return null;
    }

    @Override
    public void createIndex(Class clazz) {
        elasticsearchTemplate.createIndex(clazz);
    }

    @Override
    public List<Person> findPerson() {
        Iterable<Person> all = esTestDao.findAll();
        return Lists.newArrayList(all);
    }

    @Override
    public List<Student> findStudent() {
        QueryBuilder queryBuilder = QueryBuilders.termsQuery("name", "kimchy", "wenbronk", "vini");
//        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
//        Iterable<Student> search = esStudentDao.search(queryBuilder);
        return Lists.newArrayList(esStudentDao.findAll());
    }

    public List<Student> disMaxQuery() {
        QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
                .add(QueryBuilders.termQuery("name", "kimch"))  // 查询条件
                .add(QueryBuilders.termQuery("password", "hello"))
                .boost(1.3f)
                .tieBreaker(0.7f);
        Iterable<Student> search = esStudentDao.search(queryBuilder);
        return Lists.newArrayList(search);
    }

    @Override
    public List<Student> hasChildQuery() {
//        JoinQueryBuilders.hasChildQuery()
//        QueryBuilder queryBuilder = QueryBuilders.hasChildQuery("sonDoc", QueryBuilders.termQuery("name", "vini"));
        QueryBuilder queryBuilder = JoinQueryBuilders
                .hasChildQuery("salesVariationInfo",
                        QueryBuilders.matchQuery("searchTitle", "哈哈"), ScoreMode.Max);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(PageRequest.of(0, 10))
                .build();
        searchQuery.addIndices("listing");
        searchQuery.addTypes("salesListing");
        Page<Student> search = esStudentDao.search(searchQuery);
        return Lists.newArrayList(search);
    }

}
