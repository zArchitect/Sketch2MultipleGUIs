package SketchBuilderCodeGenerator.Model;


import com.google.gson.annotations.SerializedName;

public class JSONModel {

    @SerializedName("views")
    JSONModel[] children;
    @SerializedName("width")
    private String width;
    @SerializedName("height")
    private String height;
    @SerializedName("view")
    private String view;
    @SerializedName("text")
    private String text;
    @SerializedName("checked")
    private boolean checked;
    @SerializedName("id")
    private String id;
    @SerializedName("start_percent")
    private String start;
    @SerializedName("end_percent")
    private String end;
    @SerializedName("top_percent")
    private String top;
    @SerializedName("bottom_percent")
    private String bottom;

    //get attributes
    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getView() {
        return view;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getTop() {
        return top;
    }

    public String getBottom() {
        return bottom;
    }

    public String getId() {
        return id;
    }

    public JSONModel[] getChildren() {
        return children;
    }

    public JSONModel getObject() {
        return this;
    }

}
