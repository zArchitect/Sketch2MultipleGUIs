package SketchBuilderCodeGenerator.languagewrite;

import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WriteMarkup {

    private final String TAG_OPEN_START = "<";
    private final String TAG_END = ">";
    private final String TAG_CLOSE_START = "</";
    public enum MarkupFormat {HTML, XML}

    private List<Tag> tags;
    private StringBuilder markupFile;


    private String documentDeclaration;


    public WriteMarkup(List<Tag> tags) {
        this.tags = tags;
        markupFile = new StringBuilder();
    }

    public WriteMarkup(Tag tag) {
        this.tags = new ArrayList<Tag>();
        this.tags.add(tag);
        markupFile = new StringBuilder();
    }


    public String getDocumentDeclaration() {
        return documentDeclaration;
    }

    public void setDocumentDeclaration(String documentDeclaration) {
        this.documentDeclaration = documentDeclaration;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    private void generateMarkupString(MarkupFormat format){
        markupFile.append(documentDeclaration+"\n");
        Tag tag = tags.get(0);
        getTagData(tag, 0, format);
    }

    private void getTagData(Tag tag, int level, MarkupFormat format){
        String tabs = getNumberOfTabs(level);

        markupFile.append(tabs);
        markupFile.append(TAG_OPEN_START);
        String tagName =  tag.getName();
        markupFile.append(tagName);
        ArrayList<Pair<String, String>>  attributes =  tag.getAttributes();

        for (Pair<String, String> attribute : attributes) {
            String keyValue = attribute.getKey();
            String value = attribute.getValue();
            if(format == MarkupFormat.HTML) {
                String attr = " " + keyValue + "=\"" + value + "\"";
                markupFile.append(attr);
            } else if(format == MarkupFormat.XML) {
                String attr = "\n" + tabs + keyValue + "=\"" + value + "\"";
                markupFile.append(attr);
            }
        }

        if(tag.getValue() != null && !tag.getValue().equals("")) {
            markupFile.append(TAG_END);
        }else{
            markupFile.append(TAG_END + "\n");
        }

        for(int i =0; i<tag.getChildren().size(); ++i) {
            getTagData(tag.getChildren().get(i), level+1, format);
        }

        String tagClose;
        if(tag.getValue() != null && !tag.getValue().equals("")){
            markupFile.append(tag.getValue());
            tagClose = TAG_CLOSE_START + tagName + TAG_END + "\n";
        }else {
            tagClose = tabs + TAG_CLOSE_START + tagName + TAG_END + "\n";
        }

        markupFile.append(tagClose);

    }
    private String getNumberOfTabs(int n){
        String tabs = "";
        for(int i =0; i<n; ++i)
            tabs = tabs.concat("\t");
        return tabs;
    }
   public void writeMarkupFile(String filePath, MarkupFormat format){
        generateMarkupString(format);
       try (Writer writer = new BufferedWriter(
               new OutputStreamWriter(
                       new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
           writer.write(markupFile.toString());
       } catch (IOException e) {
           System.out.println(e);
       }
   }
}
