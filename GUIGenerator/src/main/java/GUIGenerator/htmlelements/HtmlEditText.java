package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HtmlEditText extends HTMLElement {



    private final String INPUT_CLASS ="form-control";


    public HtmlEditText(HtmlJsonElement jsonModel) {
        super( jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(), jsonModel.getWidth(),jsonModel.getHeight());
    }





    @Override
    public void draw(Tag root) {
        Tag input = new Tag("input");
        input.setAttribute("class",this.INPUT_CLASS);
        input.setAttribute("width", this.getWidth() + "%");
        input.setAttribute("height", this.getHeight() + "%");
        root.addChild(input);
    }
}
