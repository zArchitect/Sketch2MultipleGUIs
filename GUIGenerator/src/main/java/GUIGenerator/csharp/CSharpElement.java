package SketchBuilderCodeGenerator.csharp;

public abstract class CSharpElement {
    public abstract void drawInit(StringBuilder initialize, CSharpElement element);
    public abstract void drawObj(StringBuilder root, CSharpElement element);
    public abstract void drawCont(StringBuilder controls, CSharpElement element);
    public abstract void drawVars(StringBuilder initialize, CSharpElement element);

    private String id;
    private String width;
    private String height;
    private String left;
    private String top;
    private int tab;

    public CSharpElement(String id, String width, String height, String left, String top) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.left = left;
        this.top = top;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
}
