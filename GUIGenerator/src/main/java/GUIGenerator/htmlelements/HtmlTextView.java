package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HtmlTextView extends HTMLElement {

    private final String P_CLASS ="text-justify";

    public HtmlTextView(HtmlJsonElement jsonModel) {
        super( jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(), jsonModel.getWidth(),jsonModel.getHeight());
    }




    @Override
    public void draw(Tag root) {
        Tag p = new Tag("p");
        p.setAttribute("class",this.P_CLASS);
        p.setTagValue("this is text");
        p.setAttribute("width", this.getWidth() + "%");
        p.setAttribute("height", this.getHeight() + "%");
        root.addChild(p);
    }
}
