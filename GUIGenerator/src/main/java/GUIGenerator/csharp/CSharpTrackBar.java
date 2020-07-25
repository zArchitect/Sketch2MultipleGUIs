package SketchBuilderCodeGenerator.csharp;

import SketchBuilderCodeGenerator.Model.JSONModelCSharp;

public class CSharpTrackBar extends CSharpElement {

    private final String view = "SeekBar";

    public CSharpTrackBar(JSONModelCSharp jsonModelCSharp) {
        super(jsonModelCSharp.getId(), jsonModelCSharp.getWidth(), jsonModelCSharp.getHeight(), jsonModelCSharp.getLeft(), jsonModelCSharp.getTop());
    }


    public String getView() {
        return view;
    }

    @Override
    public void drawInit(StringBuilder initialize, CSharpElement element) {
        initialize.append("            // \n" +
                "            // " + element.getId() + "\n" +
                "            // \n" +
                "            this." + element.getId() + ".Size = new System.Drawing.Size(" + element.getWidth() + ", " + element.getHeight() + ");\n" +
                "            this." + element.getId() + ".Left = " + element.getLeft() + ";\n" +
                "            this." + element.getId() + ".Top = " + element.getTop() + ";\n" +
                "            this." + element.getId() + ".Name = \"" + element.getId() + "\";\n" +
                "            this." + element.getId() + ".Value = 3;\n" +
                "            this." + element.getId() + ".TabIndex = " + element.getTab() + ";\n");
    }

    @Override
    public void drawObj(StringBuilder root, CSharpElement element) {
        root.append("        private System.Windows.Forms.TrackBar " + element.getId() + ";\n");
    }

    @Override
    public void drawCont(StringBuilder controls, CSharpElement element) {
        controls.append("            this.Controls.Add(this." + element.getId() + ");\n");
    }

    @Override
    public void drawVars(StringBuilder initialize, CSharpElement element) {
        initialize.append("            this." + element.getId() + " = new System.Windows.Forms.TrackBar();\n");
    }
}
