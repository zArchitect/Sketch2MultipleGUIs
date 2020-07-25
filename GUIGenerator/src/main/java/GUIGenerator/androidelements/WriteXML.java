package SketchBuilderCodeGenerator.androidelements;

import SketchBuilderCodeGenerator.Model.JSONModel;
import com.google.gson.Gson;
import SketchBuilderCodeGenerator.languagewrite.Tag;
import SketchBuilderCodeGenerator.languagewrite.WriteMarkup;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class WriteXML{

    private static String path;
    private static String inputPath;
    private static String outputPath;
    private static String filename;
    private static File outputFile;
    private static String image = "placeholder.jpg";
    private static Path source;
    private static String mainDesign = "design";
    private static String guideDesign = "guideline";
    private static String OS = System.getProperty("os.name").toLowerCase();

    public WriteXML(String[] args) {
        for (int i = 1; i < args.length; i++) {
            inputPath = args[i];
            outputPath = args[args.length - 1];
            File file = new File(inputPath);
            outputFile = new File(outputPath);
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
        filename = path.substring(index + 1).replaceAll(".json", "");
        if (path != null) {
            try {
                // Read json file content into a string
                String content = new String(Files.readAllBytes(Paths.get(path)));

                if (content.contains("\"views\": [")) {
                    // Convert json content to java object
                    Gson gson = new Gson();
                    JSONModel children = gson.fromJson(content, JSONModel.class);

                    buildXML(children, false);
                    buildXML(children, true);
                }
            } catch (IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
    }

    private static void buildXML(JSONModel children, boolean isGuideline) {

        switch (String.valueOf(isGuideline)) {
            case "false":
                Tag designRoot = new Tag(AppKeys.DESIGN_ROOT);
                designRoot.setAttribute(AppKeys.XMLNS_ANDROID, "http://schemas.android.com/apk/res/android");
                designRoot.setAttribute(AppKeys.XMLNS_APP, "http://schemas.android.com/apk/res-auto");
                designRoot.setAttribute(AppKeys.XMLNS_TOOLS, "http://schemas.android.com/tools");
                designRoot.setAttribute(AppKeys.WIDTH_KEY, "match_parent");
                designRoot.setAttribute(AppKeys.HEIGHT_KEY, "match_parent");

                Tag include = new Tag(AppKeys.INCLUDE_KEY);
                include.setAttribute(AppKeys.LAYOUT_KEY, ("@layout/" + filename + "_" + guideDesign).replaceAll(".xml", ""));

                designRoot.addChild(include);
                addChildren(children.getChildren(), designRoot, false);

                generateFiles((filename + "_" + mainDesign + ".xml"), designRoot, false);
                break;
            case "true":
                Tag guidelineRoot = new Tag(AppKeys.GUIDELINE_ROOT);
                guidelineRoot.setAttribute(AppKeys.XMLNS_ANDROID, "http://schemas.android.com/apk/res/android");
                guidelineRoot.setAttribute(AppKeys.XMLNS_APP, "http://schemas.android.com/apk/res-auto");

                addChildren(children.getChildren(), guidelineRoot, true);

                generateFiles((filename + "_" + guideDesign + ".xml"), guidelineRoot, true);
                break;
        }
    }

    private static void addChildren(JSONModel[] children, Tag root, boolean isGuideline) {

        for (JSONModel child : children) {
            String view = child.getView();
            if (view.equalsIgnoreCase("Button")) {
                AndroidButton button = new AndroidButton(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        button.draw(root);
                        break;
                    case "true":
                        button.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("CheckBox_on")) {
                AndroidCheckBox checkBoxON = new AndroidCheckBox(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        checkBoxON.setChecked(true);
                        checkBoxON.draw(root);
                        break;
                    case "true":
                        checkBoxON.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("CheckBox_off")) {
                AndroidCheckBox checkBoxOFF = new AndroidCheckBox(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        checkBoxOFF.setChecked(false);
                        checkBoxOFF.draw(root);
                        break;
                    case "true":
                        checkBoxOFF.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("EditText")) {
                AndroidEditText editText = new AndroidEditText(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        editText.draw(root);
                        break;
                    case "true":
                        editText.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("ImageView")) {
                AndroidImageView imageView = new AndroidImageView(child.getObject());
                File dir = new File(outputFile.getAbsolutePath() + "/app/src/main/res/drawable");
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
                    } catch(IOException e) {
                        System.out.println(e);
                        System.exit(1);
                    }
                }
                imageView.setSrc("@drawable/" + image.replaceAll(".jpg", ""));
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        imageView.draw(root);
                        break;
                    case "true":
                        imageView.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("ListView")) {
                AndroidListView listView = new AndroidListView(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        listView.draw(root);
                        break;
                    case "true":
                        listView.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("ProgressBar")) {
                AndroidProgressBar progressBar = new AndroidProgressBar(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        progressBar.draw(root);
                        break;
                    case "true":
                        progressBar.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("RadioButton_on")) {
                AndroidRadioButton radioButtonON = new AndroidRadioButton(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        radioButtonON.setChecked(true);
                        radioButtonON.draw(root);
                        break;
                    case "true":
                        radioButtonON.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("RadioButton_off")) {
                AndroidRadioButton radioButtonOFF = new AndroidRadioButton(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        radioButtonOFF.setChecked(false);
                        radioButtonOFF.draw(root);
                        break;
                    case "true":
                        radioButtonOFF.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("slidBar")) {
                AndroidSeekBar seekBar = new AndroidSeekBar(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        seekBar.draw(root);
                        break;
                    case "true":
                        seekBar.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("comboBox")) {
                AndroidSpinner spinner = new AndroidSpinner(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        spinner.draw(root);
                        break;
                    case "true":
                        spinner.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("Switch")) {
                AndroidSwitch iSwitch = new AndroidSwitch(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        iSwitch.draw(root);
                        break;
                    case "true":
                        iSwitch.drawGuideline(root);
                        break;
                }
            } else if (view.equalsIgnoreCase("TextView")) {
                AndroidTextView textView = new AndroidTextView(child.getObject());
                switch (String.valueOf(isGuideline)) {
                    case "false":
                        textView.draw(root);
                        break;
                    case "true":
                        textView.drawGuideline(root);
                        break;
                }
            }
        }
    }

    private static void generateFiles(String file, Tag root, boolean isGuideline) {
        File dir;
        switch (String.valueOf(isGuideline)) {
            case "false":
                dir = new File(outputFile.getAbsolutePath() + "/app/src/main/res/layout");
                if (!dir.exists()) {
                    dir.mkdirs();
                    if (OS.contains("win")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "\\" + file), WriteMarkup.MarkupFormat.XML);
                    } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "/" + file), WriteMarkup.MarkupFormat.XML);
                    }
                } else {
                    if (OS.contains("win")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "\\" + file), WriteMarkup.MarkupFormat.XML);
                    } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "/" + file), WriteMarkup.MarkupFormat.XML);
                    }
                }
                break;
            case "true":
                dir = new File(outputFile.getAbsolutePath() + "/app/src/main/res/layout");
                if (!dir.exists()) {
                    dir.mkdirs();
                    if (OS.contains("win")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "\\" + file), WriteMarkup.MarkupFormat.XML);
                    } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "/" + file), WriteMarkup.MarkupFormat.XML);
                    }
                } else {
                    if (OS.contains("win")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "\\" + file), WriteMarkup.MarkupFormat.XML);
                    } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                        WriteMarkup writeMarkup = new WriteMarkup(root);
                        writeMarkup.setDocumentDeclaration("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                        writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "/" + file), WriteMarkup.MarkupFormat.XML);
                    }
                    break;
                }
        }
    }
}
