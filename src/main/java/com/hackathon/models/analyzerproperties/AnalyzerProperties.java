/**
 * 
 */
package com.hackathon.models.analyzerproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author vivekhs
 *
 */

@PropertySource(ignoreResourceNotFound = true, value = "classpath:codeanalyser.properties")
@Component
public class AnalyzerProperties {

    @Value("${infer.path}")
    private String inferCodeAnalyserPath;

    @Value("${codes.directory}")
    private String codesDirectory;

    @Value("${analysis.out.directory}")
    private String analysisOutDirectory;

    @Value("${token.secret}")
    private String tokenSecret;

    @Value("${password.hash.key_length}")
    private int passwordHashKeyLength;

    @Value("${password.salt.secret}")
    private String saltSecret;

    @Value("${password.hash.algorithm}")
    private String passwordHashAlgorithm;

    @Value("${password.hash.iterations}")
    private int passwordHashIterations;

    /**
     * @return the inferCodeAnalyserPath
     */
    public String getInferCodeAnalyserPath() {
        return inferCodeAnalyserPath;
    }

    /**
     * @param inferCodeAnalyserPath
     *            the inferCodeAnalyserPath to set
     */
    public void setInferCodeAnalyserPath(String inferCodeAnalyserPath) {
        this.inferCodeAnalyserPath = inferCodeAnalyserPath;
    }

    /**
     * @return the codesDirectory
     */
    public String getCodesDirectory() {
        return codesDirectory;
    }

    /**
     * @param codesDirectory
     *            the codesDirectory to set
     */
    public void setCodesDirectory(String codesDirectory) {
        this.codesDirectory = codesDirectory;
    }

    /**
     * @return the analysisOutDirectory
     */
    public String getAnalysisOutDirectory() {
        return analysisOutDirectory;
    }

    /**
     * @param analysisOutDirectory
     *            the analysisOutDirectory to set
     */
    public void setAnalysisOutDirectory(String analysisOutDirectory) {
        this.analysisOutDirectory = analysisOutDirectory;
    }

    /**
     * @return the tokenSecret
     */
    public String getTokenSecret() {
        return tokenSecret;
    }

    /**
     * @param tokenSecret
     *            the tokenSecret to set
     */
    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    /**
     * @return the passwordHashKeyLength
     */
    public int getPasswordHashKeyLength() {
        return passwordHashKeyLength;
    }

    /**
     * @param passwordHashKeyLength
     *            the passwordHashKeyLength to set
     */
    public void setPasswordHashKeyLength(int passwordHashKeyLength) {
        this.passwordHashKeyLength = passwordHashKeyLength;
    }

    /**
     * @return the saltSecret
     */
    public String getSaltSecret() {
        return saltSecret;
    }

    /**
     * @param saltSecret
     *            the saltSecret to set
     */
    public void setSaltSecret(String saltSecret) {
        this.saltSecret = saltSecret;
    }

    /**
     * @return the passwordHashAlgorithm
     */
    public String getPasswordHashAlgorithm() {
        return passwordHashAlgorithm;
    }

    /**
     * @param passwordHashAlgorithm
     *            the passwordHashAlgorithm to set
     */
    public void setPasswordHashAlgorithm(String passwordHashAlgorithm) {
        this.passwordHashAlgorithm = passwordHashAlgorithm;
    }

    /**
     * @return the passwordHashIterations
     */
    public int getPasswordHashIterations() {
        return passwordHashIterations;
    }

    /**
     * @param passwordHashIterations
     *            the passwordHashIterations to set
     */
    public void setPasswordHashIterations(int passwordHashIterations) {
        this.passwordHashIterations = passwordHashIterations;
    }

}
