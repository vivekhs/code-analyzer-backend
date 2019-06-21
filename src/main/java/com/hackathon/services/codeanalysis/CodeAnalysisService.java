/**
 * 
 */
package com.hackathon.services.codeanalysis;

import java.io.IOException;

import com.hackathon.models.analysisreport.CodeAnalysisTransaction;
import com.hackathon.models.request.AnalyseCodeRequest;

/**
 * @author vivekhs
 *
 */
public interface CodeAnalysisService {

    CodeAnalysisTransaction analyseCode(String userName, String filePath, String language)
            throws IOException, InterruptedException;

    String writeCodeInFile(AnalyseCodeRequest analyseCodeRequest) throws IOException;

}
