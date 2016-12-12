package com.aggor.spider;

import com.aggor.spider.model.Tree;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.jsoup.Jsoup.connect;

public class Crawler {
    private final BiFunction<String, String, Tree> func = (url, domain) -> {
        try {
            return findChildren(url, domain);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    private Tree findChildren(final String url, final String domain) throws IOException {
        final Document doc = connect(url).get();
        final Elements links = doc.select("a[href]");

        final List<Tree> children = links.stream()
                .map(link -> {
                    final Element element = link.select("a").first();
                    return element.attr("abs:href");
                })
                .filter(u -> u.startsWith(domain))
                .map(u -> singletonList(func.apply(u, domain)))
                .flatMap(Collection::stream)
                .collect(toList());

        return new Tree(url, children);
    }

    public Tree crawl(String domain, String page) {
        return func.apply(domain + page, domain);
    }
}