package SketchBuilderCodeGenerator.htmlelements;


import SketchBuilderCodeGenerator.Model.html.HtmlJsonElement;
import SketchBuilderCodeGenerator.Model.html.HtmlModel;
import SketchBuilderCodeGenerator.Model.html.Row;
import com.google.gson.Gson;
import SketchBuilderCodeGenerator.languagewrite.Tag;
import SketchBuilderCodeGenerator.languagewrite.WriteMarkup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class WriteHtml {

    private static String path;
    private static String inputPath;
    private static String outputPath;
    private static String filename;
    private static File outputFile;
    private static String image = "placeholder.jpg";
    private static Path source;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private final static String DESIGN = "design";
    private static final String CHARSET = "utf-8";
    private static final String META_NAME = "viewport";
    private static final String META_CONTENT = "width=device-width, initial-scale=1, shrink-to-fit=no";
    private static final String LINK_REL = "stylesheet";
    private static final String LINK_HREF = "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css";
    private static final String LINK_INTEGRITY = "sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T";
    private static final String CROSSORIGIN = "anonymous";
    private static final String SCRIPT_SRC = "https://code.jquery.com/jquery-3.3.1.slim.min.js";
    private static final String SCRIPT_INTEGRITY = "sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo";
    private static final String SCRIPT2_SRC = "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js";
    private static final String SCRIPT2_INTEGRITY = "sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1";
    private static final String SCRIPT3_SRC = "https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js";
    private static final String SCRIPT3_INTEGRITY = "sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM";
    private static final String PARENT_DIV_CLASS = "container";
    private static final String MAIN_DIV_CLASS = "container body-content";

    public WriteHtml(String[] args) {
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



    public static void buildHTML(Row[] rows) {

        Tag meta = new Tag("meta");
        meta.setAttribute("charset", CHARSET);

        Tag meta2 = new Tag("meta");
        meta2.setAttribute("name", META_NAME);
        meta2.setAttribute("content", META_CONTENT);

        Tag link = new Tag("link");
        link.setAttribute("rel", LINK_REL);
        link.setAttribute("href", LINK_HREF);
        link.setAttribute("integrity", LINK_INTEGRITY);
        link.setAttribute("crossorigin", CROSSORIGIN);

        Tag head = new Tag("head");

        head.addChild(meta);
        head.addChild(meta2);
        head.addChild(link);

        Tag script = new Tag("script");
        script.setAttribute("src", SCRIPT_SRC);
        script.setAttribute("integrity", SCRIPT_INTEGRITY);
        script.setAttribute("crossorigin", CROSSORIGIN);

        Tag script2 = new Tag("script");
        script2.setAttribute("src", SCRIPT2_SRC);
        script2.setAttribute("integrity", SCRIPT2_INTEGRITY);
        script2.setAttribute("crossorigin", CROSSORIGIN);

        Tag script3 = new Tag("script");
        script3.setAttribute("src", SCRIPT3_SRC);
        script3.setAttribute("integrity", SCRIPT3_INTEGRITY);
        script3.setAttribute("crossorigin", CROSSORIGIN);

        Tag parentDev = new Tag("div");
        parentDev.setAttribute("class", PARENT_DIV_CLASS);


        Tag body = new Tag("body");
        Tag mainDev = new Tag("div");
        mainDev.setAttribute("class", MAIN_DIV_CLASS);


        for (Row row : rows) {
           HtmlJsonElement [] elements = row.getElements();
            RowTag rowTag = new RowTag("div");
            for (HtmlJsonElement element : elements) {
                ColumnTag columnTag = new ColumnTag("div");
                addChildren(element, columnTag);
                rowTag.addChild(columnTag);
            }

            parentDev.addChild(rowTag);
        }
        mainDev.addChild(parentDev);
        body.addChild(mainDev);

        body.addChild(script);
        body.addChild(script2);
        body.addChild(script3);

        Tag html = new Tag("html");
        html.addChild(head);
        html.addChild(body);
        generateFiles((filename + "_" + DESIGN + ".html"), html);



    }

    public static void addChildren(HtmlJsonElement el, ColumnTag columnTag) {
        //for (Column child : children) {
           // HtmlJsonElement el = child.getElement();

            //for(HtmlJsonElement el: elements){
                String view = el.getElement();
                if (view.equalsIgnoreCase("Button")) {
                    HTMLButton btn = new HTMLButton(el);
                    btn.draw(columnTag);
                } else if (view.equalsIgnoreCase("CheckBox_on")) {
                    HtmlCheckBox checkON = new HtmlCheckBox(el);
                    checkON.draw(columnTag);
                } else if (view.equalsIgnoreCase("CheckBox_off")) {
                    HtmlCheckBox checkOFF = new HtmlCheckBox(el);
                    checkOFF.draw(columnTag);
                }  else if (view.equalsIgnoreCase("EditText")) {
                    HtmlEditText edit = new HtmlEditText(el);
                    edit.draw(columnTag);

                } else if (view.equalsIgnoreCase("ImageView")) {
                    HtmlImageView imageView = new HtmlImageView(el);
                    imageView.draw(columnTag);
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
                    HtmlListView list = new HtmlListView(el);
                    list.draw(columnTag);
                } else if (view.equalsIgnoreCase("ProgressBar")) {
                    HtmlProgressBar progs = new HtmlProgressBar(el);
                    progs.draw(columnTag);
                } else if (view.equalsIgnoreCase("RadioButton_on")) {
                    HtmlRadioButton radioON = new HtmlRadioButton(el);
                    radioON.draw(columnTag);
                } else if (view.equalsIgnoreCase("RadioButton_off")) {
                    HtmlRadioButton radioOFF = new HtmlRadioButton(el);
                    radioOFF.draw(columnTag);
                } else if (view.equalsIgnoreCase("slidBar")) {
                    HtmlSeekBar seek = new HtmlSeekBar(el);
                    seek.draw(columnTag);
                } else if (view.equalsIgnoreCase("comboBox")) {
                    HtmlSpinner spinr = new HtmlSpinner(el);
                    spinr.draw(columnTag);
                } else if (view.equalsIgnoreCase("Switch")) {
                    HtmlSwitch swich = new HtmlSwitch(el);
                    swich.draw(columnTag);
                } else if (view.equalsIgnoreCase("TextView")) {
                    HtmlTextView txt = new HtmlTextView(el);
                    txt.draw(columnTag);
                }
           // }

       // }
    }

    private static void generateFiles(String file, Tag root) {
        File dir;
        dir = new File(outputFile.getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdirs();
            if (OS.contains("win")) {
                WriteMarkup writeMarkup = new WriteMarkup(root);
                writeMarkup.setDocumentDeclaration("<!DOCTYBE html>");
                writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "\\" + file), WriteMarkup.MarkupFormat.HTML);
                outputFile.listFiles();
            } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                WriteMarkup writeMarkup = new WriteMarkup(root);
                writeMarkup.setDocumentDeclaration("<!DOCTYBE html>");
                writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "/" + file), WriteMarkup.MarkupFormat.HTML);
            }
        } else {
            if (OS.contains("win")) {
                WriteMarkup writeMarkup = new WriteMarkup(root);
                writeMarkup.setDocumentDeclaration("<!DOCTYBE html>");
                writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "\\" + file), WriteMarkup.MarkupFormat.HTML);
            } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
                WriteMarkup writeMarkup = new WriteMarkup(root);
                writeMarkup.setDocumentDeclaration("<!DOCTYBE html>");
                writeMarkup.writeMarkupFile((dir.getAbsolutePath() + "/" + file), WriteMarkup.MarkupFormat.HTML);
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
                if (content.contains("\"rows\": [")) {
                    // Convert json content to java object
                    Gson gson = new Gson();
                    HtmlModel jsonFile = gson.fromJson(content, HtmlModel.class);
                    buildHTML(jsonFile.getRows());
                }
            } catch (IOException e) {
                System.out.println(e);
                System.exit(1);
            }
        }
    }


}


