package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidSwitch extends AndroidElement {
    private final String view = "Switch";
    private boolean checked;

    public AndroidSwitch(JSONModel jsonModel) {
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

    @Override
    public void draw(Tag root) {
        Tag iSwitch = new Tag(getView());
        iSwitch.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        iSwitch.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        iSwitch.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        iSwitch.setAttribute(AppKeys.CHECKED_KEY, String.valueOf(this.isChecked()));
        iSwitch.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        iSwitch.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        iSwitch.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        iSwitch.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(iSwitch);
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
