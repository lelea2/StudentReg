package edu.sjsu.cmpe272.eight; /**
 * Created by Martin on 9/23/2015.
 */

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MainFrame extends JFrame{
    private XTextArea resultTextArea, scriptTextArea;
    private JPanel topPanel, midPanel, bottomPanel, mainPanel, executePanel;
    private JLabel jdbcInfoLabel;
    private JTabbedPane caseTabbedPan;
    private JButton testButn;
    private List<CaseBean> caseList;
    private String caseDir,propertieDir,jdbc_url,jdbc_url_config,jdbc_username,jdbc_pwd;
    private JScrollPane scrollPaneScript,scrollPaneResult;
    private JSplitPane splitPaneTop, mainSplitPanel;
    private boolean isCaseSelected;
    private MainFrame thisFrame = this;
    public void initUI(){

        resultTextArea = new XTextArea();
        testButn = new JButton("Execute this Test");
        scriptTextArea = new XTextArea();
        scriptTextArea.setText("Choose one of the cases above," +
                " then press the button- \"Execute this test\", to verify the result that will appear below.");
        scrollPaneScript = new JScrollPane();
        scrollPaneScript.setViewportView(scriptTextArea);
        scrollPaneScript.setPreferredSize(new Dimension(100, 200));

        topPanel = new JPanel();
        topPanel.setBorder(new TitledBorder("Testing cases"));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setPreferredSize(new Dimension(100, 100));

        caseTabbedPan = new JTabbedPane();
        topPanel.add(caseTabbedPan);

        midPanel = new JPanel();
        midPanel.setBorder(new TitledBorder("Testing script"));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setSize(new Dimension(100, 500));
        midPanel.add(scrollPaneScript);
        executePanel= new JPanel();
        jdbcInfoLabel = new JLabel("Jdbc information");
        executePanel.add(testButn);
        executePanel.add(jdbcInfoLabel);
        midPanel.add(executePanel);

        scrollPaneResult = new JScrollPane();
        scrollPaneResult.setBorder(new TitledBorder("Testing result"));
        scrollPaneResult.setViewportView(resultTextArea);
        scrollPaneResult.setSize(new Dimension(100, 200));

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(scrollPaneResult);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        splitPaneTop = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,topPanel,midPanel);
        splitPaneTop.setOneTouchExpandable(true);
        mainSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,splitPaneTop,bottomPanel);
        mainSplitPanel.setOneTouchExpandable(true);
        mainSplitPanel.setDividerLocation(600);
        mainPanel.add(mainSplitPanel);

        mainPanel.repaint();
        this.setContentPane(mainPanel);
    }
    private void initData()throws Exception{
        System.out.println("caseDir: "+caseDir+"\n propertieDir: "+propertieDir);
        System.out.println("dir: " + this.getClass().getResource("/").getFile().toString());
        isCaseSelected = false;
        caseList = new ArrayList<CaseBean>();
        caseDir = this.getClass().getResource("/").getFile().toString()+"test_cases\\";
        System.out.println("caseDir: "+caseDir+"\n propertieDir: "+propertieDir);
        propertieDir = this.getClass().getResource("/").getFile().toString()+"jdbc.properties";
//        System.out.println("caseDir: " + caseDir + "\n propertieDir: " + propertieDir);
        loadJDBCInfo();
    }
    private void loadJDBCInfo() throws Exception{
        Properties props = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(propertieDir));
        props.load(in);
        jdbc_url = props.getProperty ("url");
        jdbc_username = props.getProperty ("username");
        jdbc_pwd = props.getProperty ("password");
        jdbc_url_config = props.getProperty ("url_config");
        this.jdbcInfoLabel.setText("at server : " + jdbc_url);
        echo(jdbc_url_config + "\n" + jdbc_username + "\n" + jdbc_pwd + "");

    }
    /*
    * load testing cases from a specific folder
    * */
    private void loadCases() throws DocumentException{
        String path= this.caseDir;
        File file=new File(path);
        echo("scan directory: " + file);
        File[] tempList = file.listFiles();
        echo("found files: " + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("working on: "+tempList[i]);
                try {
                    caseList.add(parseXml2Case(tempList[i]+""));
                } catch (DocumentException e) {
                    e.printStackTrace();
                    echo("an error happened during reading file: "+ tempList[i]);
                    continue;
                }
            }

        }
    }
    private CaseBean parseXml2Case(String path)throws DocumentException {
        CaseBean cBean = new CaseBean();
        SAXReader reader = new SAXReader();
        File file = new File(path);
        Document document = reader.read(file);
        Element root = document.getRootElement();
        cBean.setCaseName(root.elementTextTrim("CaseName"));
        cBean.setTester(root.elementTextTrim("Tester"));
        cBean.setCaseDescription(root.elementTextTrim("CaseDescription"));
        cBean.setSqlScript(root.elementTextTrim("SQLScript"));
        cBean.setResult(root.elementTextTrim("Result"));
        return  cBean;
    }
    private void createCasePanel() throws Exception {
        if(caseList.size()< 1)
            throw  new Exception("no cases found");
        for(CaseBean cb : caseList){
            echo("adding: " + cb.getCaseName());
            JLabel textContent = new JLabel("<html>" +
                    "Case Name: <br>" + cb.getCaseName() + "<br>" +
                    "Tester: <br>" + cb.getTester() +"<br>" +
                    "Case Description: <br>" + cb.getCaseDescription() +
                    "<br>Sql Script: <br>" + cb.getSqlScript() +
                    "<br>expected: <br>"+ cb.getResult()+
                    "</html>");

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(textContent);
            caseTabbedPan.add(cb.getCaseName(), scrollPane);
            this.repaint();
        }

    }
    private void addListener(){
        caseTabbedPan.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                isCaseSelected = true;
                int selectCaseId = caseTabbedPan.getSelectedIndex();
                scriptTextArea.setText("" + caseList.get(selectCaseId).getSqlScript());

            }
        });

        testButn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                XTextArea resultText = thisFrame.resultTextArea;
                if(isCaseSelected) {

                    String coms = scriptTextArea.getText().trim();
                    if(coms.indexOf(";") > 1){//execute multiple commands
                        String[] commands = coms.split(";");
                        echo("\n\n");
                        echo(">>>>>>>>start a new test case with "+ commands.length+" commands.<<<<<<<<<<");
                        int i = 0;
                        for(String c : commands) {
                            echo("[sys]: command No."+ ++i);
                            JDBCtool jdbc = new JDBCtool();
                            jdbc.init(jdbc_url_config, jdbc_username, jdbc_pwd);
                            jdbc.excute(c + ";", resultTextArea);
                            /*try {
                                Thread.sleep(300);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }*/
//                            thisFrame.OptionPane.showMessageDialog(thisFrame, "Please choose a case first.",
//                                    "No script found",
//                                    JOptionPane.ERROR_MESSAGE);
//                            showErro("next?", new Exception("ok"));
                        }
                    }else{//single command
                        echo(">>>>>>>>start a test case with 1 command.<<<<<<<<<<");
                        JDBCtool jdbc = new JDBCtool();
                        jdbc.init(jdbc_url_config, jdbc_username, jdbc_pwd);
                        jdbc.excute(coms, resultTextArea);
                    }

                    echo(">>>>>>>>case testing ended.<<<<<<<<<<");
                }else{
                    JOptionPane.showMessageDialog(thisFrame, "Please choose a case first.",
                            "No script found",
                            JOptionPane.ERROR_MESSAGE);
                }
                thisFrame.setResultTextAreaScrollMax();
            }
        });
    }
    /*
    * print out msg to GUI
    * */
    public void echo(String str){
        resultTextArea.appendLine(str);
        resultTextArea.setCaretPosition(resultTextArea.getDocument().getLength());
//        JScrollBar jb = scrollPaneResult.getVerticalScrollBar();
//        jb.setValue(jb.getMaximum());
//        this.setResultTextAreaScrollMax();
    }
    public void setResultTextAreaScrollMax(){
        JScrollBar jb = scrollPaneResult.getVerticalScrollBar();
        jb.setValue(jb.getMaximum());
        resultTextArea.repaint();
    }
    public MainFrame()
    {
        super();
        this.setSize(1000, 900);
        this.setTitle("DB Testing Program");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void echoWelcomeInfo(){
        echo("Boot successfully...");
        echo("*********************** DB Tester V0.01 ***********************");
        echo("This is a DataBass Tester testing all the SQL script prepared");
        echo("for webservice in the further step.");
        echo("By Group 8 @CMPE 272, SJSU");
        echo("Instruction:");
        echo("1.Choose a testing case from the top panel. ");
        echo("2.Read the description of this case. ");
        echo("3.Modify the SQL script if needs.                            ");
        echo("4.Press 'Test' button to verify the result.                  ");
        echo("***************************************************************");
    }



    private void showErro(String str, Exception e){
        JOptionPane.showMessageDialog(this, e.toString(),str,
                    JOptionPane.ERROR_MESSAGE);
        echo(e.toString());
    }
    public static void main(String[] args)
    {
        MainFrame w = new MainFrame();
//        try {
            System.out.println("--------------running-------------------");
            w.initUI();
        try {
            w.initData();
        } catch (Exception e) {
            w.showErro("An error occurred during initiating data", e);
        }
        try {
            w.loadCases();
        } catch (DocumentException e) {
            e.printStackTrace();

            w.showErro("An error occurred during loading testing cases", e);
        }
        try {
            w.createCasePanel();
        } catch (Exception e) {
            w.showErro("An error occurred during creating testing cases", e);
        }
        w.addListener();
            w.echoWelcomeInfo();
            w.setVisible(true);
            w.setResultTextAreaScrollMax();
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(w, e.toString()
//                    "An exception occurred",
//                    JOptionPane.ERROR_MESSAGE);
//        }
    }

    public XTextArea getResultTextArea() {
        return resultTextArea;
    }
}