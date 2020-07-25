package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.elements.Element;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public abstract class HTMLElement implements Element {
    public abstract void draw(Tag root);

    private String element;
    private String id;
    private String start_column;
    private String width;
    private String height;



    public HTMLElement ( String element ,String id,String start_column ,  String width , String height ){
        this.element = element;
        this.id = id;
        this.start_column = start_column;
        this.width = width;
        this.height = height;

    }

    public String getElement() {
        return element;
    }
    public void setElement(String element) {
        this.element = element;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getStart_column() {
        return start_column;
    }
    public void setStart_column(String start_column) {
        this.start_column = start_column;
    }


    public String getWidth() {
        return width;
    }
    public void setWidth(String top) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }
    public void setHeight(String top) {
        this.height = height;
    }




}
