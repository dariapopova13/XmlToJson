package com.popova.jobtest.model;

import java.io.Serializable;
import java.util.List;

public class TreeNode implements Serializable {

    private double value;
    private String name;
    private List<TreeNode> children;

    public TreeNode() {
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
