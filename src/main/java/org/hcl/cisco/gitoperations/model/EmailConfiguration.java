package org.hcl.cisco.gitoperations.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailConfiguration implements Serializable {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
