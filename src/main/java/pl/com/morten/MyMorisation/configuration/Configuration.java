//package pl.edu.wat.am.lab1.restendpoint.configuration;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
///**
//
// */
//@EnableMethodSecurity
//@EnableWebSecurity
//public class Configuration {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.formLogin();
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll().anyRequest().authenticated()
//                )
//                .headers(headers -> headers.frameOptions().disable())
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
//        return http.build();
//    }
//    @Bean
//    public UserDetailsService users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails user = users
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        UserDetails admin = users
//                .username("admin")
//                .password("password")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
//}
