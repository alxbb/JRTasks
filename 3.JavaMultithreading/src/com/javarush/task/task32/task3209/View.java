package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

public class View extends JFrame implements ActionListener {
    private Controller    controller;
    private JTabbedPane   tabbedPane    = new JTabbedPane();
    private JTextPane     htmlTextPane  = new JTextPane();
    private JEditorPane   plainTextPane = new JEditorPane();
    private FrameListener frameListener;
    private JMenuBar      menuBar;
    private UndoManager   undoManager   = new UndoManager();
    private UndoListener  undoListener  = new UndoListener(undoManager);

    public Controller getController() { return controller; }
    public void setController(Controller controller) { this.controller = controller; }

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void init(){
        initGui();
        frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        this.setVisible(true);
    }
    public void initMenuBar(){
//        Файл, Редактировать, Стиль, Выравнивание, Цвет, Шрифт и Помощь.
        menuBar = new JMenuBar();
        MenuHelper.initFileMenu (this, menuBar);
        MenuHelper.initEditMenu (this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu (this, menuBar);
        MenuHelper.initHelpMenu (this, menuBar);
        this.getContentPane().add(menuBar,BorderLayout.NORTH);
    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", htmlScrollPane);
        JScrollPane textScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", textScrollPane);
        tabbedPane.setPreferredSize(new Dimension(800,600));
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void         exit()              { controller.exit();                  }
    public boolean      canUndo()           { return undoManager.canUndo();       }
    public boolean      canRedo()           { return undoManager.canRedo();       }
    public void         resetUndo()         {       undoManager.discardAllEdits();}
    public UndoListener getUndoListener()   { return undoListener;                }
    public boolean      isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;
    }
    public void         update()            { htmlTextPane.setDocument(controller.getDocument());}
    public void         showAbout()         { JOptionPane.showMessageDialog(this, "HTML Editor ver.: 0.0.1", "About", JOptionPane.INFORMATION_MESSAGE);}
    public void         selectHtmlTab()     { tabbedPane.setSelectedIndex(0); resetUndo(); }
    public void         selectedTabChanged(){
        int tab = tabbedPane.getSelectedIndex();
        if( tab == 0 ){
            controller.setPlainText(plainTextPane.getText());
        } else if ( tab == 1 ){
            plainTextPane.setText(controller.getPlainText());
        }
        this.resetUndo();
    }

    public void undo(){
        try{
            undoManager.undo();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public void redo(){
        try{
            undoManager.redo();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                this.showAbout();
                break;
        }
    }
}
