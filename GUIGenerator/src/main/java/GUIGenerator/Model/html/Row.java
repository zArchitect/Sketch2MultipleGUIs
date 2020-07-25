package SketchBuilderCodeGenerator.Model.html;

import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("elements")
    HtmlJsonElement[] elements;

    public Row(HtmlJsonElement[] elements) {
        this.elements = elements;
    }

    public HtmlJsonElement[] getElements() {
        return elements;
    }

    public void setElements(HtmlJsonElement[] elements) {
        this.elements = elements;
    }
}
