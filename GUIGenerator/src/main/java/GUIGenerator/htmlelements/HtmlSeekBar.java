package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HtmlSeekBar extends HTMLElement {

    private final String LABEL_FOR ="customRange1";
    private final String INPUT_TYPE ="range";
    private final String INPUT_CLASS ="custom-range";
    private final String INPUT_ID ="customRange1";

    public HtmlSeekBar(HtmlJsonElement jsonModel) {
        super(  jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(),  jsonModel.getWidth(),jsonModel.getHeight());
    }




    @Override
    public void draw(Tag root) {
        Tag label = new Tag("label");
        label.setAttribute("for",this.LABEL_FOR);
        root.addChild(label);

        Tag input = new Tag("input");
        input.setAttribute("type",this.INPUT_TYPE);
        input.setAttribute("class",this.INPUT_CLASS);
        input.setAttribute("id",this.INPUT_ID);
        input.setAttribute("width", this.getWidth() + "%");
        input.setAttribute("height", this.getHeight() + "%");
        root.addChild(input);
    }
}
