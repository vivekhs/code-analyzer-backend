/**
 * 
 */
package com.hackathon.controllers.codeanalysis;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hackathon.models.request.AnalyseCodeRequest;
import com.hackathon.models.response.codeanalysis.CodeAnalysisResponse;

/**
 * @author vivekhs
 *
 */
public interface CodeAnalysis {

    CodeAnalysisResponse analyseCode(AnalyseCodeRequest analyseCode, HttpServletResponse response);

    CodeAnalysisResponse uploadCodeForAnalysis(MultipartFile file, String userId, String fileName, String language,
            HttpServletResponse response);

}
