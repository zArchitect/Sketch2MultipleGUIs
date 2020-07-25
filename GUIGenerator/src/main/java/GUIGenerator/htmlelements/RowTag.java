package SketchBuilderCodeGenerator.htmlelements;

import javafx.util.Pair;
import SketchBuilderCodeGenerator.languagewrite.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RowTag extends Tag {

    private final String classAttribute ="row justify-content-start";
    private final String styleAttribute = "padding-top:10px;";

    public RowTag(String name){
        super(name);
        this.setAttribute("class",this.classAttribute);
        this.setAttribute("style",this.styleAttribute);
    }
    public RowTag(String name , ArrayList<Pair<String, String>> attributes, List<Tag>children){
        super(name,attributes,children);
        this.setAttribute("class",this.classAttribute);
        this.setAttribute("style",this.styleAttribute);

    }

    public RowTag(String name , ArrayList<Pair<String, String>> attributes, String value){
        super(name,attributes,value);
        this.setAttribute("class",this.classAttribute);
        this.setAttribute("style",this.styleAttribute);
    }

}
