package SketchBuilderCodeGenerator.Model;


import com.google.gson.annotations.SerializedName;

public class JSONModelCSharp {

    @SerializedName("objects")
    JSONModelCSharp[] children;
    @SerializedName("width")
    private String width;
    @SerializedName("height")
    private String height;
    @SerializedName("object")
    private String view;
    @SerializedName("text")
    private String text;
    @SerializedName("checked")
    private boolean checked;
    @SerializedName("id")
    private String id;
    @SerializedName("left")
    private String left;
    @SerializedName("top")
    private String top;

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

    public String getLeft() {
        return left;
    }

    public String getTop() {
        return top;
    }

    public String getId() {
        return id;
    }

    public JSONModelCSharp[] getChildren() {
        return children;
    }

    public JSONModelCSharp getObject() {
        return this;
    }

}
