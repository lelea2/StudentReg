package edu.sjsu.cmpe272.eight;

import javax.swing.*;

/**
 * Created by Martin on 9/28/2015.
 */
public class XTextArea extends JTextArea {
    public void appendLine(String str){
        this.append(str+"\n");
    }
    public XTextArea(){
        super();
    }
}
