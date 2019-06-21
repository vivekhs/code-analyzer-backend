/**
 * 
 */
package com.hackathon.dao.analysisreport;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hackathon.models.analysisreport.AnalysisReport;
import com.hackathon.models.analysisreport.CodeAnalysisTransaction;

/**
 * @author vivekhs
 *
 */

public interface AnalysisReportDAO extends MongoRepository<CodeAnalysisTransaction, String> {

    List<AnalysisReport> findByUserId(String userId);

    CodeAnalysisTransaction save(CodeAnalysisTransaction analysisReport);

}
