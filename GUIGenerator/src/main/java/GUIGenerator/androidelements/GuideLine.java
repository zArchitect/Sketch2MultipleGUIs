package SketchBuilderCodeGenerator.androidelements;


import SketchBuilderCodeGenerator.languagewrite.Tag;

public class GuideLine {
    private final String GUIDE_LINE = "android.support.constraint.Guideline";
    private final String width = "0dp";
    private final String height = "0dp";
    private String id;
    private String startPercent;
    private String topPercent;
    private String endPercent;
    private String bottomPercent;

    public GuideLine(String id, String startPercent, String topPercent, String endPercent, String bottomPercent) {
        this.id = id;
        this.startPercent = startPercent;
        this.topPercent = topPercent;
        this.endPercent = endPercent;
        this.bottomPercent = bottomPercent;
    }

    public String getGUIDE_LINE() {
        return GUIDE_LINE;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartPercent() {
        return startPercent;
    }

    public void setStartPercent(String startPercent) {
        this.startPercent = startPercent;
    }

    public String getTopPercent() {
        return topPercent;
    }

    public void setTopPercent(String topPercent) {
        this.topPercent = topPercent;
    }

    public String getEndPercent() {
        return endPercent;
    }

    public void setEndPercent(String endPercent) {
        this.endPercent = endPercent;
    }

    public String getBottomPercent() {
        return bottomPercent;
    }

    public void setBottomPercent(String bottomPercent) {
        this.bottomPercent = bottomPercent;
    }

    private String percentage(String value) {
        double x = Double.valueOf(value);
        x /= 100;
        return String.valueOf(x);
    }

    public Tag getStartGuideLine() {
        Tag startGuideline = new Tag(getGUIDE_LINE());
        startGuideline.setAttribute(AppKeys.ID_KEY, "@+id/" + getId() + "_start_guideline");
        startGuideline.setAttribute(AppKeys.GUIDEPERCENT_KEY, percentage(getStartPercent()));
        startGuideline.setAttribute(AppKeys.WIDTH_KEY, getWidth());
        startGuideline.setAttribute(AppKeys.HEIGHT_KEY, getHeight());
        startGuideline.setAttribute(AppKeys.ORIENTATION_KEY, AppKeys.VERTICAL);
        return startGuideline;
    }

    public Tag getEndGuideLine() {
        Tag endGuideline = new Tag(getGUIDE_LINE());
        endGuideline.setAttribute(AppKeys.ID_KEY, "@+id/" + getId() + "_end_guideline");
        endGuideline.setAttribute(AppKeys.GUIDEPERCENT_KEY, percentage(getEndPercent()));
        endGuideline.setAttribute(AppKeys.WIDTH_KEY, getWidth());
        endGuideline.setAttribute(AppKeys.HEIGHT_KEY, getHeight());
        endGuideline.setAttribute(AppKeys.ORIENTATION_KEY, AppKeys.VERTICAL);
        return endGuideline;
    }

    public Tag getTopGuideLine() {
        Tag topGuideline = new Tag(getGUIDE_LINE());
        topGuideline.setAttribute(AppKeys.ID_KEY, "@+id/" + getId() + "_top_guideline");
        topGuideline.setAttribute(AppKeys.GUIDEPERCENT_KEY, percentage(getTopPercent()));
        topGuideline.setAttribute(AppKeys.WIDTH_KEY, getWidth());
        topGuideline.setAttribute(AppKeys.HEIGHT_KEY, getHeight());
        topGuideline.setAttribute(AppKeys.ORIENTATION_KEY, AppKeys.HORIZONTAL);
        return topGuideline;
    }

    public Tag getBottomGuideLine() {
        Tag bottomGuideline = new Tag(getGUIDE_LINE());
        bottomGuideline.setAttribute(AppKeys.ID_KEY, "@+id/" + getId() + "_bottom_guideline");
        bottomGuideline.setAttribute(AppKeys.GUIDEPERCENT_KEY, percentage(getBottomPercent()));
        bottomGuideline.setAttribute(AppKeys.WIDTH_KEY, getWidth());
        bottomGuideline.setAttribute(AppKeys.HEIGHT_KEY, getHeight());
        bottomGuideline.setAttribute(AppKeys.ORIENTATION_KEY, AppKeys.HORIZONTAL);
        return bottomGuideline;
    }

}
