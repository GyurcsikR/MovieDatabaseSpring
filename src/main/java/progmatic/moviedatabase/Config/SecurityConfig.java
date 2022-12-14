package progmatic.moviedatabase.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login-error")

                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)

                .and()

                .authorizeRequests()
                .antMatchers("/", "/list", "/p*/**", "/register", "/*.css", "/*.jpg")
                .permitAll()

                .antMatchers("/list/search")
                .hasAnyRole("ADMIN", "USER")

                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
