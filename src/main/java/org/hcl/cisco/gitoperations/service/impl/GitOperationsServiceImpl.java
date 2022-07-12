package org.hcl.cisco.gitoperations.service.impl;

import org.hcl.cisco.gitoperations.Test;
import org.hcl.cisco.gitoperations.model.GitOperationModel;
import org.hcl.cisco.gitoperations.service.GitOperationsService;
import org.hcl.cisco.gitoperations.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class GitOperationsServiceImpl implements GitOperationsService {

    @Autowired
    SendEmailService sendEmailService;

    @Value("${test.directory.loc}")
    private String tempLocation;

    @Override
    public String performGitOperation(GitOperationModel gitOperationModel) {
        Path path = Paths.get(tempLocation);
        try{
            runCommand(path, "git", "log","--since", gitOperationModel.getSince());
            return sendEmailService.sendEmail(gitOperationModel.getEmailConfiguration());
        }catch(IOException | InterruptedException e){
            return e.getMessage();
        }
    }

    public static void runCommand(Path directory, String... command)
            throws IOException, InterruptedException {
        Objects.requireNonNull(directory, "directory");
        if (!Files.exists(directory)) {
            throw new RuntimeException("can't run command in non-existing directory '" + directory + "'");
        }
        ProcessBuilder pb = new ProcessBuilder()
                .command(command)
                .directory(directory.toFile());
        Process p = pb.start();
        GitOperationsServiceImpl.StreamGobbler errorGobbler = new GitOperationsServiceImpl.StreamGobbler(p.getErrorStream(), "ERROR");
        GitOperationsServiceImpl.StreamGobbler outputGobbler = new GitOperationsServiceImpl.StreamGobbler(p.getInputStream(), "OUTPUT");
        outputGobbler.start();
        errorGobbler.start();
        int exit = p.waitFor();
        errorGobbler.join();
        outputGobbler.join();
        if (exit != 0) {
            throw new AssertionError(String.format("runCommand returned %d", exit));
        }
    }

    private static class StreamGobbler extends Thread {

        private final InputStream is;
        private final String type;

        private StreamGobbler(InputStream is, String type) {
            this.is = is;
            this.type = type;
        }

        @Override
        public void run() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(type + "> " + line);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
