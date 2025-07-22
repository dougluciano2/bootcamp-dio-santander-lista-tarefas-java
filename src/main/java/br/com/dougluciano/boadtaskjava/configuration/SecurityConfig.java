package br.com.dougluciano.boadtaskjava.configuration;

import br.com.dougluciano.boadtaskjava.enums.ApplicationRoutes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] WHITE_LIST = {
            ApplicationRoutes.FORM_LOGIN.getPath(),
            ApplicationRoutes.CSS_DIRECTORY.getPath(),
            ApplicationRoutes.JAVA_SCRIPT_DIRECTORY.getPath(),
            ApplicationRoutes.ASSETS_DIRECTORY.getPath(),
            ApplicationRoutes.REGISTER_URL.getPath()
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, ApplicationRoutes.USERS_URL.getPath()).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form.loginPage(ApplicationRoutes.FORM_LOGIN.getPath())
                                .defaultSuccessUrl(ApplicationRoutes.TASKS_URL.getPath(), true)
                                .permitAll()

                )
                .logout(logout -> logout.permitAll());

        return httpSecurity.build();

    }


}
