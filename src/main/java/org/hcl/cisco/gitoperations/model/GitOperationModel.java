package org.hcl.cisco.gitoperations.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GitOperationModel implements Serializable {
    private String gitUrl;
    private String author;
    private String since;
    private String until;
    private EmailConfiguration emailConfiguration;
}
