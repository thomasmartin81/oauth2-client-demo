### OAuth 2.0 Client
Simple demo for a client implementation of OAuth 2.0 again GitHub or SpringBoot as authorization server.

#### GitHub Configuration
Create an application on GitHub (https://github.com/settings/developers).
Set the following required fields: 
* Application name: &lt;Any Name&gt;<br>
* Homepage URL: http://localhost:8080 <br>
* Authorization callback URL: http://localhost:8080/oauth2/code/github

Set the following environment variables with the corresponding info from the created GitHub application:
* GITHUB_CLIENT_ID 
* GITHUB_CLIENT_SECRET

#### SpringBoot Configuration
Create a SpringBoot authorization server.
Set the following required fields: 
* authorizedGrantTypes: authorization_code
* redirectUris: http://localhost:8080/oauth2/code/springboot

Set the following environment variables with the corresponding info from the created GitHub application:
* SPRINGBOOT_CLIENT_ID 
* SPRINGBOOT_CLIENT_SECRET

#### Usage
Run the spring boot application and visit http://localhost:8080. Then click on GitHub/SpringBoot link, maybe login to GitHub/SpringBoot and then you should see your user info as JSON.

#### Additional info
* https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
* https://spring.io/guides/tutorials/spring-boot-oauth2/