package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidSpinner extends AndroidElement {
    private final String view = "Spinner";

    public AndroidSpinner(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
    }

    public String getView() {
        return view;
    }

    @Override
    public void draw(Tag root) {
        Tag spinner = new Tag(getView());
        spinner.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        spinner.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        spinner.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        spinner.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        spinner.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        spinner.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        spinner.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(spinner);
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
