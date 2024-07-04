# SrpingBoot backend for questionnaire project

## To switch configuration
- Open `application.yaml`
- Set `active` to `'dev'` or `'prod'` or any other custom configuration

## Email configuration
1. Create `application-secret.properties` file with the following structure:
   - `settings.mail.host=`
   - `settings.mail.port=`
   - `settings.mail.username=`
   - `settings.mail.password=`
2.
   Assign the corresponding values:
   - `settings.mail.host=smtp.gmail.com`
   - `settings.mail.port=587`
   - `settings.mail.username=YOUR_EMAIL`
   - `settings.mail.password=YOUR_PASSWORD`
   
OR

 Use `'dev'` configuration. It uses a dummy email service implementation that doesn't require configuration.

## To run:
```cmd
mvn spring-boot:run
