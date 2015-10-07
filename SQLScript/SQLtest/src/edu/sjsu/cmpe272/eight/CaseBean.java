package edu.sjsu.cmpe272.eight;

/**
 * Created by Martin on 9/26/2015.
 */
public class CaseBean {
    private String caseName, tester, caseDescription, sqlScript, result;
    public CaseBean(){

    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDiscription) {
        this.caseDescription = caseDiscription;
    }

    public String getSqlScript() {
        return sqlScript;
    }

    public void setSqlScript(String sqlScript) {
        this.sqlScript = sqlScript;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
