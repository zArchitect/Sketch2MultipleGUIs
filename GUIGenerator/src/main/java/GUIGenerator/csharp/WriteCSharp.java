package SketchBuilderCodeGenerator.csharp;

import SketchBuilderCodeGenerator.Model.JSONModelCSharp;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class WriteCSharp {

    private static String path;
    private static String inputPath;
    private static String outputPath;
    private static String filename;
    private static String namespace;
    private static File outputFile;
    private static int tab;
    private static String image = "placeholder.jpg";
    private static Path source;
    private static final String OS = System.getProperty("os.name").toLowerCase();

    public WriteCSharp(String[] args) {
        for (int i = 1; i < args.length; i++) {
            tab = 0;
            inputPath = args[i];
            outputPath = args[args.length - 1];
            File file = new File(inputPath);
            outputFile = new File(outputPath);
            namespace = outputFile.getName();
            if (file.isDirectory() && outputFile.isDirectory()) {
                folderMiner(file);
            } else if (!outputFile.isDirectory()) {
                System.out.println("Output path is not a folder!");
                System.exit(1);
            } else {
                fileMiner(file);
            }
        }
    }

    private static void folderMiner(File file) {
        for (File fileEntry : file.listFiles()) {
            if (fileEntry.isDirectory()) {
                folderMiner(fileEntry);
            } else {
                fileMiner(fileEntry);
            }
        }
    }

    private static void fileMiner(File file) {
        path = file.getPath();
        int index = 0;
        if (OS.contains("win")) {
            index = path.lastIndexOf("\\");
            String check = Paths.get(System.getProperty("user.dir")).toString();
            if(check.contains("build\\libs")) {
                source = Paths.get(System.getProperty("user.dir").replace("build\\libs", "") + "icons/placeholder.jpg");
            } else {
                source = Paths.get(System.getProperty("user.dir") + "/icons/placeholder.jpg");
            }
        } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
            index = path.lastIndexOf("/");
            String check = Paths.get(System.getProperty("user.dir")).toString();
            if(check.contains("build/libs")) {
                source = Paths.get(System.getProperty("user.dir").replace("build/libs", "") + "icons/placeholder.jpg");
            } else {
                source = Paths.get(System.getProperty("user.dir") + "/icons/placeholder.jpg");
            }
        }
        filename = path.substring(index + 1).replaceAll(".json", "").concat("Form");
        if (path != null) {
            try {
                // Read json file content into a string
                String content = new String(Files.readAllBytes(Paths.get(path)));

                if (content.contains("\"objects\": [")) {
                    // Convert json content to java object
                    Gson gson = new Gson();
                    JSONModelCSharp children = gson.fromJson(content, JSONModelCSharp.class);

                    buildCSharp(children);
                }
            } catch (IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    private static void buildCSharp(JSONModelCSharp children) {
        StringBuilder root = new StringBuilder();
        StringBuilder dispose = new StringBuilder();
        StringBuilder initialize = new StringBuilder();
        StringBuilder controls = new StringBuilder();
        StringBuilder objects = new StringBuilder();
        StringBuilder formRoot = new StringBuilder();
        String height = children.getHeight();
        String width = children.getWidth();

        root.append("namespace " + namespace + "\n" +
                "{" + "\n" +
                "    partial class " + filename + "\n" +
                "    {\n" +
                "        /// <summary>\n" +
                "        /// Required designer variable.\n" +
                "        /// </summary>\n" +
                "        private System.ComponentModel.IContainer components = null;\n\n");

        dispose.append("        /// <summary>\n" +
                "        /// Clean up any resources being used.\n" +
                "        /// </summary>\n" +
                "        /// <param name=\"disposing\">true if managed resources should be disposed; otherwise, false.</param>\n" +
                "        protected override void Dispose(bool disposing)\n" +
                "        {\n" +
                "            if (disposing && (components != null))\n" +
                "            {\n" +
                "                components.Dispose();\n" +
                "            }\n" +
                "            base.Dispose(disposing);\n" +
                "        }\n\n");

        root.append(dispose);

        initialize.append("\n        #region Windows Form Designer generated code\n" +
                "\n" +
                "        /// <summary>\n" +
                "        /// Required method for Designer support - do not modify\n" +
                "        /// the contents of this method with the code editor.\n" +
                "        /// </summary>\n" +
                "        private void InitializeComponent()\n" +
                "        {\n");

        controls.append("            // \n" +
                "            // " + filename + "\n" +
                "            // \n" +
                "            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);\n" +
                "            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;\n" +
                "            this.ClientSize = new System.Drawing.Size(" + width + ", " + height + ");\n" +
                "            this.Name = \"" + filename + "\";\n" +
                "            this.Text = \"Design\";\n" +
                "            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;\n" +
                "            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;\n");

        addVariables(children.getChildren(), initialize);
        initialize.append("            this.SuspendLayout();\n");
        addChildren(children.getChildren(), initialize, objects, controls);

        initialize.append(controls +
                "            this.ResumeLayout(false);\n" +
                "            this.PerformLayout();\n");
        root.append(initialize + "    }\n " +
                "\n" +
                "        #endregion\n\n" +
                objects +
                "    }" + "\n" +
                "}");
        generateFiles((filename + ".Designer" + ".cs"), root);

        formRoot.append("using System;\n" +
                "using System.Collections.Generic;\n" +
                "using System.ComponentModel;\n" +
                "using System.Data;\n" +
                "using System.Drawing;\n" +
                "using System.Linq;\n" +
                "using System.Text;\n" +
                "using System.Windows.Forms;\n\n" +
                "namespace " + namespace + "\n" +
                "{" + "\n" +
                "    public partial class " + filename + " : Form\n" +
                "    {\n" +
                "        public " + filename + "()\n" +
                "        {\n" +
                "            InitializeComponent();\n" +
                "        }\n" +
                "    }" + "\n" +
                "}");

        generateFiles((filename + ".cs"), formRoot);
    }

    private static void addVariables(JSONModelCSharp[] children,StringBuilder initialize) {
        for (JSONModelCSharp child : children) {
            String view = child.getView();
            if (view.equalsIgnoreCase("Button")) {
                CSharpButton button = new CSharpButton(child.getObject());
                button.drawVars(initialize, button);
            } else if (view.equalsIgnoreCase("CheckBox_on")) {
                CSharpCheckBox checkBoxON = new CSharpCheckBox(child.getObject());
                checkBoxON.drawVars(initialize, checkBoxON);
            } else if (view.equalsIgnoreCase("CheckBox_off")) {
                CSharpCheckBox checkBoxOFF = new CSharpCheckBox(child.getObject());
                checkBoxOFF.drawVars(initialize, checkBoxOFF);
            } else if (view.equalsIgnoreCase("EditText")) {
                CSharpTextBox editText = new CSharpTextBox(child.getObject());
                editText.drawVars(initialize, editText);
            } else if (view.equalsIgnoreCase("ImageView")) {
                CSharpPictureBox imageView = new CSharpPictureBox(child.getObject());
                imageView.drawVars(initialize, imageView);
                File dir = new File(outputFile.getAbsolutePath() + "/images");
                if (!dir.exists()) {
                    dir.mkdirs();
                    Path target = dir.toPath().resolve(image);
                    try {
                        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                    } catch(IOException e) {
                        System.out.println(e);
                        System.exit(1);
                    }
                } else {
                    Path target = dir.toPath().resolve(image);
                    try {
                        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.out.println(e);
                        System.exit(1);
                    }
                }
            } else if (view.equalsIgnoreCase("ListView")) {
                CSharpListView listView = new CSharpListView(child.getObject());
                listView.drawVars(initialize, listView);
            } else if (view.equalsIgnoreCase("ProgressBar")) {
                CSharpProgressBar progressBar = new CSharpProgressBar(child.getObject());
                progressBar.drawVars(initialize, progressBar);
            } else if (view.equalsIgnoreCase("RadioButton_on")) {
                CSharpRadioButton radioButtonON = new CSharpRadioButton(child.getObject());
                radioButtonON.drawVars(initialize, radioButtonON);
            } else if (view.equalsIgnoreCase("RadioButton_off")) {
                CSharpRadioButton radioButtonOFF = new CSharpRadioButton(child.getObject());
                radioButtonOFF.drawVars(initialize, radioButtonOFF);
            } else if (view.equalsIgnoreCase("slidBar")) {
                CSharpTrackBar seekBar = new CSharpTrackBar(child.getObject());
                seekBar.drawVars(initialize, seekBar);
            } else if (view.equalsIgnoreCase("comboBox")) {
                CSharpComboBox comboBox = new CSharpComboBox(child.getObject());
                comboBox.drawVars(initialize, comboBox);
            } else if (view.equalsIgnoreCase("TextView")) {
                CSharpLabel textView = new CSharpLabel(child.getObject());
                textView.drawVars(initialize, textView);
            }
        }
    }

    private static void addChildren(JSONModelCSharp[] children, StringBuilder initialize, StringBuilder objects, StringBuilder controls) {

        for (JSONModelCSharp child : children) {
            String view = child.getView();
            if (view.equalsIgnoreCase("Button")) {
                CSharpButton button = new CSharpButton(child.getObject());
                button.setTab(tab);
                button.drawInit(initialize, button);
                button.drawCont(controls, button);
                button.drawObj(objects, button);
                tab++;
            } else if (view.equalsIgnoreCase("CheckBox_on")) {
                CSharpCheckBox checkBoxON = new CSharpCheckBox(child.getObject());
                checkBoxON.setTab(tab);
                checkBoxON.setChecked(true);
                checkBoxON.drawInit(initialize, checkBoxON);
                checkBoxON.drawCont(controls, checkBoxON);
                checkBoxON.drawObj(objects, checkBoxON);
                tab++;
            } else if (view.equalsIgnoreCase("CheckBox_off")) {
                CSharpCheckBox checkBoxOFF = new CSharpCheckBox(child.getObject());
                checkBoxOFF.setTab(tab);
                checkBoxOFF.setChecked(false);
                checkBoxOFF.drawInit(initialize, checkBoxOFF);
                checkBoxOFF.drawCont(controls, checkBoxOFF);
                checkBoxOFF.drawObj(objects, checkBoxOFF);
                tab++;
            } else if (view.equalsIgnoreCase("EditText")) {
                CSharpTextBox editText = new CSharpTextBox(child.getObject());
                editText.setTab(tab);
                editText.drawInit(initialize, editText);
                editText.drawCont(controls, editText);
                editText.drawObj(objects, editText);
                tab++;
            } else if (view.equalsIgnoreCase("ImageView")) {
                CSharpPictureBox imageView = new CSharpPictureBox(child.getObject());
                imageView.setTab(tab);
                imageView.drawInit(initialize, imageView);
                imageView.drawCont(controls, imageView);
                imageView.drawObj(objects, imageView);
                tab++;
            } else if (view.equalsIgnoreCase("ListView")) {
                CSharpListView listView = new CSharpListView(child.getObject());
                listView.setTab(tab);
                listView.drawInit(initialize, listView);
                listView.drawCont(controls, listView);
                listView.drawObj(objects, listView);
                tab++;
            } else if (view.equalsIgnoreCase("ProgressBar")) {
                CSharpProgressBar progressBar = new CSharpProgressBar(child.getObject());
                progressBar.setTab(tab);
                progressBar.drawInit(initialize, progressBar);
                progressBar.drawCont(controls, progressBar);
                progressBar.drawObj(objects, progressBar);
                tab++;
            } else if (view.equalsIgnoreCase("RadioButton_on")) {
                CSharpRadioButton radioButtonON = new CSharpRadioButton(child.getObject());
                radioButtonON.setTab(tab);
                radioButtonON.setChecked(true);
                radioButtonON.drawInit(initialize, radioButtonON);
                radioButtonON.drawCont(controls, radioButtonON);
                radioButtonON.drawObj(objects, radioButtonON);
                tab++;
            } else if (view.equalsIgnoreCase("RadioButton_off")) {
                CSharpRadioButton radioButtonOFF = new CSharpRadioButton(child.getObject());
                radioButtonOFF.setTab(tab);
                radioButtonOFF.setChecked(false);
                radioButtonOFF.drawInit(initialize, radioButtonOFF);
                radioButtonOFF.drawCont(controls, radioButtonOFF);
                radioButtonOFF.drawObj(objects, radioButtonOFF);
                tab++;
            } else if (view.equalsIgnoreCase("slidBar")) {
                CSharpTrackBar seekBar = new CSharpTrackBar(child.getObject());
                seekBar.setTab(tab);
                seekBar.drawInit(initialize, seekBar);
                seekBar.drawCont(controls, seekBar);
                seekBar.drawObj(objects, seekBar);
                tab++;
            } else if (view.equalsIgnoreCase("comboBox")) {
                CSharpComboBox comboBox = new CSharpComboBox(child.getObject());
                comboBox.setTab(tab);
                comboBox.drawInit(initialize, comboBox);
                comboBox.drawCont(controls, comboBox);
                comboBox.drawObj(objects, comboBox);
                tab++;
            } else if (view.equalsIgnoreCase("TextView")) {
                CSharpLabel textView = new CSharpLabel(child.getObject());
                textView.setTab(tab);
                textView.drawInit(initialize, textView);
                textView.drawCont(controls, textView);
                textView.drawObj(objects, textView);
                tab++;
            }
        }
    }

    private static void generateFiles(String file, StringBuilder root) {
        File dir;
        dir = new File(outputFile.getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdirs();
            if (OS.contains("win")) {
                try (Writer writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream((dir.getAbsolutePath() + "\\" + file)), StandardCharsets.UTF_8))) {
                    writer.write(root.toString());
                } catch (IOException e) {
                    System.out.println(e);
                }
            } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                try (Writer writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream((dir.getAbsolutePath() + "/" + file)), StandardCharsets.UTF_8))) {
                    writer.write(root.toString());
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } else {
            if (OS.contains("win")) {
                try (Writer writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream((dir.getAbsolutePath() + "\\" + file)), StandardCharsets.UTF_8))) {
                    writer.write(root.toString());
                } catch (IOException e) {
                    System.out.println(e);
                }
            } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                try (Writer writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream((dir.getAbsolutePath() + "/" + file)), StandardCharsets.UTF_8))) {
                    writer.write(root.toString());
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }


}
