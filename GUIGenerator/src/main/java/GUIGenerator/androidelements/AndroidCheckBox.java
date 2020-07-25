package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidCheckBox extends AndroidElement {
    private final String view = "CheckBox";
    private boolean checked;
    private final String text = "CheckBox";

    public AndroidCheckBox(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
        this.checked = jsonModel.isChecked();
    }

    public String getView() {
        return view;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getText() {
        return text;
    }

    @Override
    public void draw(Tag root) {
        Tag checkBox = new Tag(getView());
        checkBox.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        checkBox.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        checkBox.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        checkBox.setAttribute(AppKeys.CHECKED_KEY, String.valueOf(this.isChecked()));
        checkBox.setAttribute(AppKeys.TEXT_KEY, this.getText());
        checkBox.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        checkBox.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        checkBox.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        checkBox.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(checkBox);
    }

    @Override
    public void drawGuideline(Tag root) {
        GuideLine gl = new GuideLine(this.getId(), this.getStart(), this.getTop(), this.getEnd(), this.getBottom());
        root.addChild(gl.getStartGuideLine());
        root.addChild(gl.getEndGuideLine());
        root.addChild(gl.getTopGuideLine());
        root.addChild(gl.getBottomGuideLine());
    }
}
