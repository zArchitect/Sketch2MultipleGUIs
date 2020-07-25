package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidSeekBar extends AndroidElement {
    private final String view = "SeekBar";

    public AndroidSeekBar(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
    }

    public String getView() {
        return view;
    }

    @Override
    public void draw(Tag root) {
        Tag seekBar = new Tag(getView());
        seekBar.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        seekBar.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        seekBar.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        seekBar.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        seekBar.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        seekBar.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        seekBar.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(seekBar);
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
