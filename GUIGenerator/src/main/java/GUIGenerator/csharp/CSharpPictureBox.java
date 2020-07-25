package SketchBuilderCodeGenerator.csharp;

import SketchBuilderCodeGenerator.Model.JSONModelCSharp;

public class CSharpPictureBox extends CSharpElement {

    private final String view = "ImageView";

    public CSharpPictureBox(JSONModelCSharp jsonModelCSharp) {
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
                "            this." + element.getId() + ".TabStop = false;\n" +
                "            this." + element.getId() + ".Image = System.Drawing.Image.FromFile(\"images/placeholder.jpg\");\n" +
                "            this." + element.getId() + ".TabIndex = " + element.getTab() + ";\n");
    }

    @Override
    public void drawObj(StringBuilder root, CSharpElement element) {
        root.append("        private System.Windows.Forms.PictureBox " + element.getId() + ";\n");
    }

    @Override
    public void drawCont(StringBuilder controls, CSharpElement element) {
        controls.append("            this.Controls.Add(this." + element.getId() + ");\n");
    }

    @Override
    public void drawVars(StringBuilder initialize, CSharpElement element) {
        initialize.append("            this." + element.getId() + " = new System.Windows.Forms.PictureBox();\n");
    }
}
