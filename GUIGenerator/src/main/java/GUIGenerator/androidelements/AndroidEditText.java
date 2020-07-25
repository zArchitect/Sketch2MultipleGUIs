package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidEditText extends AndroidElement {
    private final String view = "EditText";


    public AndroidEditText(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
    }

    public String getView() {
        return view;
    }

    @Override
    public void draw(Tag root) {
        Tag editText = new Tag(getView());
        editText.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        editText.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        editText.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        editText.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        editText.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        editText.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        editText.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(editText);
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
