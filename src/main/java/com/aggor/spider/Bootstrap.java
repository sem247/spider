package com.aggor.spider;

import com.aggor.spider.model.Tree;

public class Bootstrap {
    public static void main(String[] args) {
        System.out.println("The application started");

        final String domain = System.getProperty("domain");

        final Crawler crawler = new Crawler();
        System.out.println("Crawling: " + domain);

        final Tree tree = crawler.crawl(domain, "/");

        System.out.println("=====================SITE MAP==========================");
        System.out.println(tree);
        System.out.println("===============================================");
    }

}