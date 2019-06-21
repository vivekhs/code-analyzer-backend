/**
 * 
 */
package com.hackathon.models.response.codeanalysis;

import com.hackathon.models.analysisreport.CodeAnalysisTransaction;

/**
 * @author vivekhs
 *
 */
public class CodeAnalysisResponse {

    private String error;
    private CodeAnalysisTransaction data;

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the data
     */
    public CodeAnalysisTransaction getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(CodeAnalysisTransaction data) {
        this.data = data;
    }

}
