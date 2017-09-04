package com.javarush.task.task32.task3209;

import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument()  { return document; }
    public void         resetDocument(){
        if(document!=null) document.removeUndoableEditListener(view.getUndoListener());
        document = (HTMLDocument) (new HTMLEditorKit()).createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
    public void         setPlainText(String text){
        try {
            this.resetDocument();
            StringReader stringReader = new StringReader(text);
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            htmlEditorKit.read(stringReader, document, 0);
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public String       getPlainText(){
        try {
            StringWriter stringWriter = new StringWriter();
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            htmlEditorKit.write(stringWriter, document, 0, document.getLength());
            return document.getText(0, document.getLength());
        }catch (Exception e){
            ExceptionHandler.log(e);
            return null;
        }
    }
    public void createNewDocument(){
        view.selectHtmlTab();
        this.resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }
    public void openDocument()     {}
    public void saveDocument()     {}
    public void saveDocumentAs()   {
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter((new HTMLFileFilter()));
        if( fileChooser.showSaveDialog(view) == 0 ) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter fileWriter = new FileWriter(currentFile)){
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


    public void init(){ createNewDocument();}
    public void exit(){System.exit(0);}

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
