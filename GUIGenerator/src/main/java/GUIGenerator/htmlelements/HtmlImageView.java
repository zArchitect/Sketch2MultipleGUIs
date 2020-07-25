package SketchBuilderCodeGenerator.htmlelements;

import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class HtmlImageView extends HTMLElement {


    private final String IMG_SRC ="images/placeholder.jpg";
    private final String IMG_CLASS ="img-fluid" ;
    private final String IMG_ALT ="Responsive image";
    private final String IMG_STYLE = "max-height:500px;max-width:500px;";


    public HtmlImageView(HtmlJsonElement jsonModel) {
        super( jsonModel.getElement(),jsonModel.getId(), jsonModel.getStart_column(),  jsonModel.getWidth(),jsonModel.getHeight());
    }



    @Override
    public void draw(Tag root) {
        Tag img = new Tag("img");



        img.setAttribute("src",this.IMG_SRC);
        img.setAttribute("style", this.IMG_STYLE);
        img.setAttribute("class",this.IMG_CLASS);
        img.setAttribute("alt",this.IMG_ALT);
        img.setAttribute("width", this.getWidth() + "%");
        img.setAttribute("height", this.getHeight() + "%");



        root.addChild(img);

    }
}
