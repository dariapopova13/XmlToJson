package com.popova.jobtest.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.popova.jobtest.model.TreeNode;
import com.popova.jobtest.utils.JsonParserUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Configuration
@ComponentScan("com.popova.jobtest")
public class RootConfig {

    @Bean
    public DocumentBuilder setDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        return documentBuilderFactory.newDocumentBuilder();
    }

    @Bean
    public ObjectMapper setObjectMapper(JsonParserUtils jsonParserUtils) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(TreeNode.class, jsonParserUtils);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
