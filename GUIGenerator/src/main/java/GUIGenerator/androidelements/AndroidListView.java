package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidListView extends AndroidElement {
    private final String view = "ListView";

    public AndroidListView(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
    }

    public String getView() {
        return view;
    }

    @Override
    public void draw(Tag root) {
        Tag listView = new Tag(getView());
        listView.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        listView.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        listView.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        listView.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        listView.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        listView.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        listView.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(listView);
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
