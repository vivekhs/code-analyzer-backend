/**
 * 
 */
package com.hackathon.models.request;

/**
 * @author vivekhs
 *
 */
public class AnalyseCodeRequest {
    private String content;
    private String userName;
    private String language;
    private String fileName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
