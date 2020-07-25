package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HtmlProgressBar extends HTMLElement {

    private final String  DIV_CLASS ="progress";
    private final String DIV2_CLASS ="progress-bar";
    private final String  DIV_ROLE ="progressbar";
    private final String  DIV_STYLE ="width: 50%";
    private final String  DIV_ARIA_VALUE_NOW ="50";
    private final String  DIV_ARIA_VALUE_MIN ="0";
    private final String  DIV_ARIA_VALUE_MAX ="100";


    public HtmlProgressBar(HtmlJsonElement jsonModel) {
        super( jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(), jsonModel.getWidth(),jsonModel.getHeight());
    }






    @Override
    public void draw(Tag root) {
        Tag div2 = new Tag("div");
        div2.setAttribute("class",this.DIV2_CLASS);
        div2.setAttribute("role",this.DIV_ROLE);
        div2.setAttribute("style",this.DIV_STYLE);
        div2.setAttribute("aria-valuenow",this.DIV_ARIA_VALUE_NOW);
        div2.setAttribute("aria-valuemin",this.DIV_ARIA_VALUE_MIN);
        div2.setAttribute("aria-valuemax",this.DIV_ARIA_VALUE_MAX);
        div2.setAttribute("width", this.getWidth() + "%");
        div2.setAttribute("height", this.getHeight() + "%");

        Tag div = new Tag("div");
        div.setAttribute("class",this.DIV_CLASS);
        div.addChild(div2);
        root.addChild(div);
    }
}
