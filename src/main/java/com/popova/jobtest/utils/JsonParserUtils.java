package com.popova.jobtest.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.popova.jobtest.model.TreeNode;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonParserUtils extends JsonSerializer<TreeNode> {


    @Override
    public void serialize(TreeNode treeNode,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {


        for (TreeNode child : treeNode.getChildren()) {

            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectFieldStart(child.getName());
            jsonGenerator.writeNumberField("value", child.getValue());

            if (child.getChildren() != null) {
                for (TreeNode gChild : child.getChildren()) {
                    serializeNode(gChild, jsonGenerator);
                }
            }
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        }
    }

    private void serializeNode(TreeNode treeNode,
                               JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectFieldStart(treeNode.getName());
        jsonGenerator.writeNumberField("value", treeNode.getValue());
        if (treeNode.getChildren() != null) {
            for (TreeNode child : treeNode.getChildren()) {
                serializeNode(child, jsonGenerator);
            }
        }
        jsonGenerator.writeEndObject();
    }
}
