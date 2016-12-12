package com.aggor.spider;

import com.aggor.spider.model.Tree;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CrawlerTest {
    private final Crawler crawler = new Crawler();

    @Test
    public void shouldBuildTreeWithNoChildren() throws Exception {
        final Tree expectedTree = new Tree("http://localhost:8080/spider/external_links.html", emptyList());

        final Tree tree = crawler.crawl("http://localhost:8080/spider", "/external_links.html");

        assertThat(tree, is(expectedTree));
    }

    @Test
    public void shouldBuildTreeWithOneChild() throws Exception {
        final Tree appendix = new Tree("http://localhost:8080/spider/appendix.html", emptyList());
        final Tree expectedTree = new Tree("http://localhost:8080/spider/only_child.html", singletonList(appendix));

        final Tree tree = crawler.crawl("http://localhost:8080/spider","/only_child.html");

        assertThat(tree, is(expectedTree));
    }

    @Test
    public void shouldBuildTreeWithMultipleChildren() throws Exception {
        final Tree front = new Tree("http://localhost:8080/spider/front.html", emptyList());
        final Tree back = new Tree("http://localhost:8080/spider/back.html", emptyList());
        final Tree expectedTree = new Tree("http://localhost:8080/spider/two_kids.html", asList(front, back));

        final Tree tree = crawler.crawl("http://localhost:8080/spider", "/two_kids.html");

        assertThat(tree, is(expectedTree));
    }

    @Test
    public void shouldBuildDomainTree() throws Exception {
        final Tree appendix = new Tree("http://localhost:8080/spider/appendix.html", emptyList());
        final Tree ch02 = new Tree("http://localhost:8080/spider/ch02.html", emptyList());
        final Tree ch01 = new Tree("http://localhost:8080/spider/ch01.html", singletonList(ch02));
        final Tree expectedTree = new Tree("http://localhost:8080/spider/", asList(ch01, ch02, appendix));

        final Tree tree = crawler.crawl("http://localhost:8080/spider", "/");

        assertThat(tree, is(expectedTree));
    }


}