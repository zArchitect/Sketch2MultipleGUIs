package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.elements.Element;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public abstract class AndroidElement implements Element {
    public abstract void draw(Tag root);
    public abstract void drawGuideline(Tag root);

    private final String width = "0dp";
    private final String height = "0dp";
    private String id;
    private String start;
    private String end;
    private String top;
    private String bottom;

    public AndroidElement(String id, String start, String end, String top
            , String bottom) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.top = top;
        this.bottom = bottom;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }
}
