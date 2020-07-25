package SketchBuilderCodeGenerator.languagewrite;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tag {

    private String name;
    private ArrayList<Pair<String, String>> attributes;
    private List<Tag> children;
    private String value;

    public Tag(String name) {
        this.name = name;
        this.children = new ArrayList<Tag>();
        this.attributes = new ArrayList<>();
    }
    public Tag(String name, ArrayList<Pair<String, String>> attributes, List<Tag> children) {
        this.name = name;
        this.attributes = attributes;
        this.children = children;
    }

    public Tag(String name, ArrayList<Pair<String, String>> attributes, String value) {
        this.name = name;
        this.attributes = attributes;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pair<String, String>> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Pair<String, String>> attributes) {
        this.attributes.addAll(attributes);
    }

    public void setAttribute(String name, String value) {
        this.attributes.add(new Pair<>(name, value));
    }

    public List<Tag> getChildren() {
        return children;
    }

    public void setChildren(List<Tag> children) {
        if(this.value.equals("")) {
            this.children = children;
        }
    }

    public String getValue() {
        return value;
    }

    public void setTagValue(String value) {
        this.value = value;
    }

    public void setTagValue(List<Tag> children) {
        this.children = children;
    }

    public void addChild(Tag tag){
        this.children.add(tag);
    }
}
