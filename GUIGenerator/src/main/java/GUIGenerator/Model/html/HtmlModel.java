package SketchBuilderCodeGenerator.Model.html;

import com.google.gson.annotations.SerializedName;

public class HtmlModel {

    @SerializedName("rows")
    Row [] rows;

    public HtmlModel(Row[] rows) {
        this.rows = rows;
    }

    public Row[] getRows() {
        return rows;
    }

    public void setRows(Row[] rows) {
        this.rows = rows;
    }
}
