package edu.sjsu.cmpe272.eight; /**
 * Created by Martin on 9/25/2015.
 */


import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class XmlAnalyzer {
    private String path;
    private CaseBean cBean;
    public XmlAnalyzer(){
//        System.out.println("dir: " + this.getClass().getResource("/").getFile().toString());
        String dir = this.getClass().getResource("/").getFile().toString();


    }
    public void setPath(String dir){
        this.path = dir;

    }
    public CaseBean getCase(String path) throws DocumentException{
        SAXReader reader = new SAXReader();
//        File file = new File(dir + "test_cases/case1.xml");
        File file = new File(path);
        Document document = reader.read(file);
        cBean = new CaseBean();
        Element root = document.getRootElement();
        cBean.setCaseName(root.elementTextTrim("CaseName"));
        cBean.setTester(root.elementTextTrim("Tester"));
        cBean.setCaseDescription(root.elementTextTrim("CaseDiscription"));
        return  cBean;
    }
//    public static void main(String[] args){
//        Dom4jDemo D4 = new Dom4jDemo();
//    }
}
