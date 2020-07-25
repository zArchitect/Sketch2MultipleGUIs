package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HtmlSpinner extends HTMLElement {

    private final String DIV_CLASS = "btn-group";
    private final String BUTTON_TYPE = "button" ;
    private final String BUTTON_CLASS = "btn btn-primary dropdown-toggle";
    private final String BUTTON_DATA_TOGGLE = "dropdown";
    private final String BUTTON_ARIA_HASPOPUP = "true";
    private final String BUTTON_ARIA_EXPANDED = "false";


    public HtmlSpinner(HtmlJsonElement jsonModel) {
                super( jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(), jsonModel.getWidth(),jsonModel.getHeight());
            }
    @Override
    public void draw(Tag root) {





        Tag button = new Tag("button");
        button.setAttribute("type",this.BUTTON_TYPE);
        button.setAttribute("class",this.BUTTON_CLASS);
        button.setAttribute("data-toggle",this.BUTTON_DATA_TOGGLE);
        button.setAttribute("aria-haspopup",this.BUTTON_ARIA_HASPOPUP);
        button.setAttribute("aria-expanded",this.BUTTON_ARIA_EXPANDED);
        button.setAttribute("width", this.getWidth() + "%");
        button.setAttribute("height", this.getHeight() + "%");




        Tag div = new Tag("div");
        div.setAttribute("class",this.DIV_CLASS);
        div.addChild(button);
        root.addChild(div);

    }
}
