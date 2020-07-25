package SketchBuilderCodeGenerator.Model.html;

import com.google.gson.annotations.SerializedName;

public class HtmlJsonElement {


    @SerializedName("element")
    private String element;
    @SerializedName("id")
    private String id;
    @SerializedName("start_column")
    private String start_column;
    @SerializedName("width")
    private String width;
    @SerializedName("height")
    private String height;


    public HtmlJsonElement( String element , String id, String start_column ,String width, String height) {

        this.element = element;
        this.id = id;
        this.start_column=start_column;
        this.width = width;
        this.height = height;

    }


    public String getElement() {
        return element;
    }

    public String getId() {
        return id;
    }

    public String getStart_column() {
        return start_column;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }





    public void setElement(String element) {
        this.element = element;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStart_column(String start_column) {
        this.start_column = start_column;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }


}
