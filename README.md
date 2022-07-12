# TEST
There is a TEST.java class avaialble
which has a main method, if you execute it you will be receiving the commits i did as part of the new project.

I need to check end to end for it.

for project purpose.
used spring boot web depdendency for REst API Provider.
used spring boot mail to send email. 
Please check pom.xml for the dependencies added.
Created a spring boot application
App.java --> Main Class for Spring boot application annotated with @SpringBootApplication.
GitOperationsController.java --> created a "/generateReport" which is a post request which accepts GitOperationModel.java as the body parameters. Please refer google what is this body about.
This method will call gitOperationsService.performGitOperation() to call GitOperationsService.java(interface layer) and GitOperationsServiceImpl.java(Service Layer)


GitOperationsServiceImpl.java will have the actual code to trigger email by using SendEmailServiceImpl.java and ProcesCommand MEthod.

Please check all files carefully and try to understand it.
