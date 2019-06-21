/**
 * 
 */
package com.hackathon.models.analysisreport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vivekhs
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisReport {

    @JsonProperty("kind")
    private String type;
    @JsonProperty("bug_type")
    private String bugType;
    private String qualifier;
    private String severity;
    private String line;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the bugType
     */
    public String getBugType() {
        return bugType;
    }

    /**
     * @param bugType
     *            the bugType to set
     */
    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    /**
     * @return the qualifier
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * @param qualifier
     *            the qualifier to set
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * @return the severity
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * @param severity
     *            the severity to set
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * @return the line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line
     *            the line to set
     */
    public void setLine(String line) {
        this.line = line;
    }

}
