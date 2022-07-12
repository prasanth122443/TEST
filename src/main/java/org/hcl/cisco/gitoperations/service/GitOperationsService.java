package org.hcl.cisco.gitoperations.service;

import org.hcl.cisco.gitoperations.model.GitOperationModel;

public interface GitOperationsService {
    String performGitOperation(GitOperationModel gitOperationModel);
}
