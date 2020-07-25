package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidTextView extends AndroidElement {
    private final String view = "TextView";
    private final String text = "HelloXML";


    public AndroidTextView(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
    }

    public String getView() {
        return view;
    }

    public String getText() {
        return text;
    }

    @Override
    public void draw(Tag root) {
        Tag textView = new Tag(getView());
        textView.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        textView.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        textView.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        textView.setAttribute(AppKeys.TEXT_KEY, this.getText());
        textView.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        textView.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        textView.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        textView.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(textView);
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
