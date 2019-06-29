package com.popova.jobtest.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class TreeNode implements Serializable {

    private double value;
    private String name;
    private List<TreeNode> children;

    public TreeNode() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return Double.compare(treeNode.value, value) == 0 &&
                Objects.equals(name, treeNode.name) &&
                Objects.equals(children, treeNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, name, children);
    }

    public TreeNode(double value, String name, List<TreeNode> children) {
        this.value = value;
        this.name = name;
        this.children = children;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
