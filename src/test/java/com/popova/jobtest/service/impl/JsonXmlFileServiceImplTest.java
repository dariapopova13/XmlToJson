package com.popova.jobtest.service.impl;

import com.popova.jobtest.config.RootConfig;
import com.popova.jobtest.model.TreeNode;
import com.popova.jobtest.service.JsonXmlFileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@WebAppConfiguration
public class JsonXmlFileServiceImplTest {

    @Autowired
    JsonXmlFileService jsonXmlFileService;

    private File file;
    private TreeNode expected;
    private String expectedJson;

    @Before
    public void setUp() {
        file = new File(getClass().getClassLoader().getResource("test.xml").getFile());
        expected = setUpExpectedTreeNode();
        expectedJson = "{\"root\":{\"value\":6.0,\"nodeA\":{\"value\":1.0},\"nodeB\":{\"value\":5.0,\"nodeC\":{\"value\":2.0},\"nodeD\":{\"value\":3.0}}}}";
    }

    @Test
    public void parseXmlToJson() throws IOException, SAXException {
        String actualJson = jsonXmlFileService.parseXmlToJson(file);
        Assert.assertEquals(expectedJson,actualJson);
    }

    @Test
    public void writeTreeToJson() throws IOException, SAXException {
        TreeNode actual = jsonXmlFileService.getXmlNodes(file);
        assertTreeEquals(expected, actual);
    }

    private void assertTreeEquals(TreeNode expected, TreeNode actual) {
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getValue(), actual.getValue(), 0.0001);
        Assert.assertTrue((expected.getChildren() == null && actual.getChildren() == null) ||
                (expected.getChildren() != null && actual.getChildren() != null));
        if (expected.getChildren() != null) {
            Assert.assertEquals(expected.getChildren().size(), actual.getChildren().size());
            List<TreeNode> expectedChildren = expected.getChildren();
            expectedChildren.sort((t1, t2) -> Double.compare(t1.getValue(), t2.getValue()));

            List<TreeNode> actualChildren = actual.getChildren();
            actualChildren.sort((t1, t2) -> Double.compare(t1.getValue(), t2.getValue()));

            for (int i = 0; i < expectedChildren.size(); i++) {
                assertTreeEquals(expectedChildren.get(i), actualChildren.get(i));
            }
        }
    }

    private TreeNode setUpExpectedTreeNode() {
        TreeNode document = new TreeNode(0, "#document", new ArrayList<>());
        TreeNode root = new TreeNode(6, "root", new ArrayList<>());
        TreeNode nodeA = new TreeNode(1, "nodeA", new ArrayList<>());
        TreeNode nodeB = new TreeNode(5, "nodeB", new ArrayList<>());

        root.getChildren().add(nodeA);
        root.getChildren().add(nodeB);

        TreeNode nodeC = new TreeNode(2, "nodeC", new ArrayList<>());
        TreeNode nodeD = new TreeNode(3, "nodeD", new ArrayList<>());

        nodeB.getChildren().add(nodeC);
        nodeB.getChildren().add(nodeD);

        document.getChildren().add(root);
        return document;
    }

}
