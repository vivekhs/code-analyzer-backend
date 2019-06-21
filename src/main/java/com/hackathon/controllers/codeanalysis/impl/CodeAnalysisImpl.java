/**
 * 
 */
package com.hackathon.controllers.codeanalysis.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.controllers.codeanalysis.CodeAnalysis;
import com.hackathon.models.analysisreport.CodeAnalysisTransaction;
import com.hackathon.models.request.AnalyseCodeRequest;
import com.hackathon.models.response.codeanalysis.CodeAnalysisResponse;
import com.hackathon.services.codeanalysis.CodeAnalysisService;
import com.hackathon.services.storage.StorageService;

/**
 * @author vivekhs
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/code")
public class CodeAnalysisImpl implements CodeAnalysis {

    @Autowired
    private CodeAnalysisService codeAnalysisService;

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/analyse", method = RequestMethod.POST)
    public CodeAnalysisResponse analyseCode(@RequestBody AnalyseCodeRequest analyseCodeRequest,
            HttpServletResponse response) {
        CodeAnalysisResponse codeAnalysisResponse = new CodeAnalysisResponse();
        try {
            String filePath = codeAnalysisService.writeCodeInFile(analyseCodeRequest);
            CodeAnalysisTransaction codeAnalysisTransaction = codeAnalysisService
                    .analyseCode(analyseCodeRequest.getUserName(), filePath, analyseCodeRequest.getLanguage());
            codeAnalysisResponse.setData(codeAnalysisTransaction);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            codeAnalysisResponse.setError("Something went wrong");
        }
        return codeAnalysisResponse;
    }

    @PostMapping(value = "/upload_analyse")
    public CodeAnalysisResponse uploadCodeForAnalysis(@RequestParam("file") MultipartFile file,
            @RequestParam("user_id") String userId, @RequestParam("file_name") String fileName,
            @RequestParam("language") String language, HttpServletResponse response) {
        CodeAnalysisResponse codeAnalysisResponse = new CodeAnalysisResponse();
        try {
            String filePath = storageService.store(file, fileName, userId);
            CodeAnalysisTransaction codeAnalysisTransaction = codeAnalysisService.analyseCode(userId, filePath,
                    language);
            codeAnalysisResponse.setData(codeAnalysisTransaction);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            codeAnalysisResponse.setError("Something went wrong");
        }
        return codeAnalysisResponse;
    }

}
