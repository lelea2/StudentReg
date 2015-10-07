package edu.sjsu.cmpe272.eight;

import javax.swing.*;

/**
 * Created by Martin on 9/24/2015.
 */
public class CasePanel extends JPanel {
    private String caseName, tester, sqlScript, description, result;

    public CasePanel() {
        super();
        caseName = new String();
        tester = new String();
        sqlScript = new String();
        description = new String();
        result = new String();
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
        this.setName(caseName);
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getSqlScript() {
        return sqlScript;
    }

    public void setSqlScript(String sqlScript) {
        this.sqlScript = sqlScript;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
