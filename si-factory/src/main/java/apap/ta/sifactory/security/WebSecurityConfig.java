package apap.ta.sifactory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/delivery/**/send").hasAuthority("STAFF_KURIR")
                .antMatchers("/delivery/viewall").hasAnyAuthority("STAFF_OPERASIONAL", "STAFF_KURIR")
                .antMatchers("/item/add").hasAuthority("FACTORY_MANAGER")
                .antMatchers("/item/update/**").hasAuthority("STAFF_GUDANG")
                .antMatchers("/request/viewall").hasAnyAuthority("STAFF_OPERASIONAL", "STAFF_GUDANG")
                .antMatchers("/pegawai/add").hasAuthority("ADMIN")
                .antMatchers("/pegawai/viewall").hasAnyAuthority("FACTORY_MANAGER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //     auth.inMemoryAuthentication()
    //             .passwordEncoder(encoder)
    //             .withUser("admin").password(encoder.encode("pass"))
    //             .roles("admin");
    // }

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
   }
}
