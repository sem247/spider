package com.aggor.spider.model;

import java.util.LinkedList;
import java.util.List;

public class Tree {
    private final String node;
    private final List<Tree> children = new LinkedList<>();

    public Tree(String node, List<Tree> children) {
        this.node = node;
        this.children.addAll(children);
    }

    public String getNode() {
        return node;
    }

    public List<Tree> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree tree = (Tree) o;

        if (!node.equals(tree.node)) return false;
        return children.equals(tree.children);
    }

    @Override
    public int hashCode() {
        int result = node.hashCode();
        result = 31 * result + children.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "node='" + node + '\'' +
                ", children=" + children +
                '}';
    }
}