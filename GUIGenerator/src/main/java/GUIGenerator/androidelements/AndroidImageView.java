package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import SketchBuilderCodeGenerator.languagewrite.Tag;

public class AndroidImageView extends AndroidElement {
    private final String view = "ImageView";
    private String src;
    private final String scaleType = "centerCrop";

    public AndroidImageView(JSONModel jsonModel) {
        super(jsonModel.getId(), jsonModel.getStart(), jsonModel.getEnd(), jsonModel.getTop(), jsonModel.getBottom());
    }

    public String getView() {
        return view;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public String getScaleType() {
        return scaleType;
    }

    @Override
    public void draw(Tag root) {
        Tag imageView = new Tag(getView());
        imageView.setAttribute(AppKeys.ID_KEY, "@+id/" + this.getId());
        imageView.setAttribute(AppKeys.WIDTH_KEY, this.getWidth());
        imageView.setAttribute(AppKeys.HEIGHT_KEY, this.getHeight());
        imageView.setAttribute(AppKeys.SRC_KEY, this.getSrc());
        imageView.setAttribute(AppKeys.SCALE_KEY, this.getScaleType());
        imageView.setAttribute(AppKeys.START_KEY, "@id/" + this.getId() + "_start_guideline");
        imageView.setAttribute(AppKeys.END_KEY, "@id/" + this.getId() + "_end_guideline");
        imageView.setAttribute(AppKeys.TOP_KEY, "@id/" + this.getId() + "_top_guideline");
        imageView.setAttribute(AppKeys.BOTTOM_KEY, "@id/" + this.getId() + "_bottom_guideline");
        root.addChild(imageView);
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
