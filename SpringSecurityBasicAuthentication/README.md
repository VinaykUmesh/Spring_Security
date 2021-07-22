## Spring Security Basic Authentication

# Dependency needed

    1. Security.
    2. Web.
    3. Java Persistent Api (JPA).
    4. Mysql connector java.
    5. devtools (optional).
    
# Steps to achieve basic authentication

    1. Users attributes for model class.
    2. Registration process for account creation to backend with passcode encoder with BCryptPasswordEncoder.
    3. Configure AuthenticationManagerBuilder class and DaoAuthenticatorProvider that implement UserDetailsService and UsersDetails to retrive crendentials from database
    4. Configure passcode encoder with BCryptPasswordEncoder
    5. Configure HttpSecurity to navigate the APIs to navigate from login page.
