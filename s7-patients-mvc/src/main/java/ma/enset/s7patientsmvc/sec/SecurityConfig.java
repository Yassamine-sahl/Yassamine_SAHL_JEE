package ma.enset.s7patientsmvc.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//La premiere classe qui va demarrer c'est la classe configuration
@Configuration
//Accuser la securite web
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Injecter le meme DATA Source de l'application
    @Autowired
    private DataSource dataSource;

    @Override
    //Preciser comment spring securite va chercher les utilisateurs et les roles
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    /*Les utilisateurs qui ont le droit d'acceder a l'application*/
        PasswordEncoder passwordEncoder= passwordEncoder();

        /*
        Deux methodes pour encoder le
             Methode 1 : auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");
             Methode 2 : utilier la methode PasswordEncoder
         */

        /* Memory Authentification
        String encodedPWD = passwordEncoder.encode("1234");
        System.out.println(encodedPWD);
        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(encodedPWD).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(encodedPWD).roles("USER","ADMIN");
        */

        /*
        JBDC Authentifiaction
            Requete1 : Chercher l'utilisateur
            Requete2 : Charger les roles de cet utilisateur

         */
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from users where username = ?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username= ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);

    }

    @Override
    //Speficier les droits d'acces
    protected void configure(HttpSecurity http) throws Exception {
        //Demander a Spring d'utiliser un formulaire d'authentification par defaut
        http.formLogin();

        //N'essecite pas une authentification a la page Home
        http.authorizeRequests().antMatchers("/").permitAll();

        //Acceder au ressource Statique
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        //Toutes les URL qui commence par /ADMIN/** n'essecite d'etre un ADMIN
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        //Les ressources accessibles en tant que USER
        http.authorizeRequests().antMatchers("/user/**").hasRole("USER");

        //Toutes les requetes http necessite une identification
        http.authorizeRequests().anyRequest().authenticated();

        //Permet de retourner une page 403
        http.exceptionHandling().accessDeniedPage("/403");

        //Tout ca necessite d'avoir un role ADMIN
        //http.authorizeRequests().antMatchers("/delete/**","/edit/**","/save/**","/formPatients/**").hasRole("ADMIN");
        //Les ressources accessibles en tant que USER
        //http.authorizeRequests().antMatchers("/index/**").hasRole("USER");

    }


    @Bean
    //Permet de creer un password encoder
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}







