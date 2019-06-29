package com.popova.jobtest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.popova.jobtest.model.TreeNode;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public interface JsonXmlFileService {
    TreeNode getXmlNodes(File file) throws IOException, SAXException;

    TreeNode getXmlNodes(MultipartFile file) throws IOException, SAXException;

    String writeTreeToJson(TreeNode treeNode) throws JsonProcessingException;

    String parseXmlToJson(File file) throws IOException, SAXException;

    String parseXmlToJson(MultipartFile file) throws IOException, SAXException;

}
