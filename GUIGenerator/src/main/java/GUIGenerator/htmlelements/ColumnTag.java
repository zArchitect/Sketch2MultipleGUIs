package SketchBuilderCodeGenerator.htmlelements;

import javafx.util.Pair;
import SketchBuilderCodeGenerator.languagewrite.Tag;

import java.util.ArrayList;
import java.util.List;

public class ColumnTag extends Tag {
    private final String COLUMN_CLASS ="col";
    private final String COLUMN_STYLE = "padding-top:10px;";

    public ColumnTag(String name){
        super(name);
        this.setAttribute("class",this.COLUMN_CLASS);
        this.setAttribute("style",this.COLUMN_STYLE);
    }
    public ColumnTag(String name , ArrayList<Pair<String, String>> attributes, List<Tag> children){
        super(name,attributes,children);
        this.setAttribute("class",this.COLUMN_CLASS);
        this.setAttribute("style",this.COLUMN_STYLE);

    }

    public ColumnTag(String name , ArrayList<Pair<String, String>>  attributes, String value){
        super(name,attributes,value);
        this.setAttribute("class",this.COLUMN_CLASS);
        this.setAttribute("style",this.COLUMN_STYLE);
    }
}
