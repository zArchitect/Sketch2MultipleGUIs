package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HtmlListView extends HTMLElement {

    private final String UL_CLASS = "list-group";
    private final String li_CLASS = "list-group-item active";
    private final String lii_CLASS ="list-group-item";


    public HtmlListView(HtmlJsonElement jsonModel) {
        super( jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(), jsonModel.getWidth(),jsonModel.getHeight());
    }
    @Override
    public void draw(Tag root) {


        Tag li = new Tag("li");
        li.setAttribute("class",this.li_CLASS);
        li.setTagValue("list view 1 ");

        Tag lii = new Tag("li");
        lii.setAttribute("alt",this.lii_CLASS);
        lii.setTagValue("list view 2 ");

        Tag ul = new Tag("ul");
        ul.setAttribute("class",this.UL_CLASS);
        ul.setAttribute("width", this.getWidth() + "%");
        ul.setAttribute("height", this.getHeight() + "%");


        ul.addChild(li);
        ul.addChild(lii);
        root.addChild(ul);


    }
}
