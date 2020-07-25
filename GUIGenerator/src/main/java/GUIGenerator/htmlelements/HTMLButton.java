package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HTMLButton extends HTMLElement {

    private final String  BUTTON_TYPE ="button" ;
    private final String BUTTON_CLASS ="btn btn-primary" ;

    public HTMLButton(HtmlJsonElement jsonModel) {
        super(  jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(), jsonModel.getWidth(),jsonModel.getHeight());
    }





    @Override
    public void draw(Tag root) {
        Tag button = new Tag("button");
        button.setAttribute("type",this.BUTTON_TYPE);
        button.setAttribute("class",this.BUTTON_CLASS);
        button.setAttribute("width", this.getWidth() + "%");
        button.setAttribute("height", this.getHeight() + "%");
        button.setTagValue("Button");
        root.addChild(button);

    }
}
