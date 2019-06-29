package com.popova.jobtest.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.popova.jobtest.model.TreeNode;
import com.popova.jobtest.service.JsonXmlFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class JsonXmlFileServiceImpl implements JsonXmlFileService {

    private DocumentBuilder documentBuilder;
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String writeTreeToJson(TreeNode treeNode) throws JsonProcessingException {
        return objectMapper.writeValueAsString(treeNode);
    }

    @Override
    public String parseXmlToJson(File file) throws IOException, SAXException {
        TreeNode treeNode = getXmlNodes(file);
        return writeTreeToJson(treeNode);
    }

    @Override
    public String parseXmlToJson(MultipartFile file) throws IOException, SAXException {
        TreeNode treeNode = getXmlNodes(file);
        return writeTreeToJson(treeNode);
    }

    @Override
    public TreeNode getXmlNodes(MultipartFile file) throws IOException, SAXException {
        Document doc = documentBuilder.parse(file.getInputStream());
        TreeNode treeNode = getTreeStructure(doc);
        return treeNode;
    }

    @Override
    public TreeNode getXmlNodes(File file) throws IOException, SAXException {
        Document doc = documentBuilder.parse(file);
        TreeNode treeNode = getTreeStructure(doc);
        return treeNode;
    }

    @Autowired
    public void setDocumentBuilder(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }


    private TreeNode getTreeStructure(Node node) {
        TreeNode treeNode = new TreeNode();
        treeNode.setName(node.getNodeName());
        if (node.hasChildNodes()) {
            NodeList children = node.getChildNodes();
            treeNode.setChildren(new ArrayList<>(children.getLength()));

            for (int i = 0; i < children.getLength(); i++) {
                if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    TreeNode childTreeNode = getTreeStructure(children.item(i));
                    treeNode.getChildren().add(childTreeNode);
                }
            }
        }
        if (node.getTextContent() != null) {
            String[] treeStringContent = node.getTextContent().replaceAll("[^0-9.]+", " ").split(" ");
            double sum = 0;
            for (String s : treeStringContent) {
                if (!s.isEmpty())
                    sum += Double.valueOf(s);
            }
            treeNode.setValue(sum);
        }
        return treeNode;
    }

}
