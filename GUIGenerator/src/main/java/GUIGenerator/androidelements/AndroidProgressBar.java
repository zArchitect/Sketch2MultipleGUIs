package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidProgressBar extends AndroidElement {
    private final String view = "ProgressBar";

    public AndroidProgressBar(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
    }

    public String getView() {
        return view;
    }

    @Override
    public void draw(Tag root) {
        Tag progressBar = new Tag(getView());
        progressBar.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        progressBar.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        progressBar.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        progressBar.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        progressBar.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        progressBar.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        progressBar.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(progressBar);
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
