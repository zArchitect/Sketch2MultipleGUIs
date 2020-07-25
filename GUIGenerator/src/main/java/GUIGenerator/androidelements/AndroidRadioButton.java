package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidRadioButton extends AndroidElement {
    private final String view = "RadioButton";
    private boolean checked;
    private final String text = "RadioButton";

    public AndroidRadioButton(JSONModel jsonModel) {
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
        Tag radioButton = new Tag(getView());
        radioButton.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        radioButton.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        radioButton.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        radioButton.setAttribute(AppKeys.CHECKED_KEY, String.valueOf(this.isChecked()));
        radioButton.setAttribute(AppKeys.TEXT_KEY, this.getText());
        radioButton.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        radioButton.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        radioButton.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        radioButton.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(radioButton);
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
