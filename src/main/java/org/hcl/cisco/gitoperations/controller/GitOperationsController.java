package org.hcl.cisco.gitoperations.controller;

import org.hcl.cisco.gitoperations.model.GitOperationModel;
import org.hcl.cisco.gitoperations.service.GitOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitOperationsController {

    @Autowired
    GitOperationsService gitOperationsService;

    @PostMapping("/generateReport")
    public ResponseEntity<String>
            generateReportAndNotify(@RequestBody GitOperationModel gitOperationModel){
        try{
            String response = gitOperationsService.performGitOperation(gitOperationModel);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
