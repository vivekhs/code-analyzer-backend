/**
 * 
 */
package com.hackathon.models.analysisreport;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vivekhs
 *
 */
@Document(collection = "analysisreports")
public class CodeAnalysisTransaction {
    private String userId;
    private String transactionId;
    private Date createdTS;
    private AnalysisReport[] analysisReport;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId
     *            the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the createdTS
     */
    public Date getCreatedTS() {
        return createdTS;
    }

    /**
     * @param createdTS
     *            the createdTS to set
     */
    public void setCreatedTS(Date createdTS) {
        this.createdTS = createdTS;
    }

    /**
     * @return the analysisReport
     */
    public AnalysisReport[] getAnalysisReport() {
        return analysisReport;
    }

    /**
     * @param analysisReport
     *            the analysisReport to set
     */
    public void setAnalysisReport(AnalysisReport[] analysisReport) {
        this.analysisReport = analysisReport;
    }

}
