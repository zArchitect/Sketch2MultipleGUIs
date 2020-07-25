package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidButton extends AndroidElement {
    private final String view = "Button";
    private final String text = "ClickMe";


    public AndroidButton(JSONModel jsonModel) {
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
        Tag button = new Tag(getView());
        button.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        button.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        button.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        button.setAttribute(AppKeys.TEXT_KEY, this.getText());
        button.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        button.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        button.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        button.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(button);
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
