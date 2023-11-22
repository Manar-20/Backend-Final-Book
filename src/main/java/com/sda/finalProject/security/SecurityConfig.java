package com.sda.finalProject.security;

import com.sda.finalProject.filters.CustomAuthenticationFilter;
import com.sda.finalProject.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests()
                .antMatchers("/auth/login/**").permitAll()
                .antMatchers("/auth/signup").permitAll()
                .antMatchers("/all-books").permitAll();
        http.authorizeRequests().antMatchers("/getRating/byUser/{userId}").permitAll();
        http.authorizeRequests().antMatchers("/all-books").permitAll();
        http.authorizeRequests().antMatchers("/Ratings").permitAll();
        http.authorizeRequests().antMatchers("/RatingByBook/{bookId}").permitAll();
        http.authorizeRequests().antMatchers("/deleteRating/{idRate}").permitAll();
        http.authorizeRequests().antMatchers("getReview/byUser/{userId").permitAll();
        http.authorizeRequests().antMatchers("/Reviews").permitAll();
        http.authorizeRequests().antMatchers("/ReviewByBook/{bookId}").permitAll();
        http.authorizeRequests().antMatchers("/delete-review/{idReview}").permitAll();

        http.authorizeRequests().antMatchers("/all-users").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/add-new-book").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/update/{idBook}").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/delete-book/{idBook}").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers("/byBookId/{idBook}").hasAuthority("ROLE_ADMIN");


        http.authorizeRequests().antMatchers("/add-rating/{userId}/{bookId}").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/addReview/{userId}/{bookId}").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/byBookId/{idBook}").hasAuthority("ROLE_USER");


        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}