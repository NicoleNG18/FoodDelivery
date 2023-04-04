package bg.softuni.fooddelivery.config;

import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.service.FoodDeliveryUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests()
                //for the static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                //visible for everyone
                .requestMatchers("/").permitAll()
                //for anonymous users
                .requestMatchers("/users/login"
                        , "/users/register",
                        "/users/login-error",
                        "/",
                        "/contact",
                        "/menu",
                        "/menu/**").anonymous()
                //for users
                .requestMatchers("/menu/**",
                        "/menu",
                        "/closed",
                        "/contact",
                        "/users/edit/**",
                        "/orders/finalize",
                        "/",
                        "/cart",
                        "/orders/details/**",
                        "/orders/history",
                        "/users/profile").hasRole(UserRoleEnum.USER.name())
                //for workers
                .requestMatchers("/",
                        "/menu",
                        "/menu/**",
                        "/users/edit/**",
                        "/orders/details/**",
                        "/orders/all/history",
                        "/users/profile").hasRole(UserRoleEnum.WORKER.name())
                //for admins
                .requestMatchers("/",
                        "/products/add",
                        "/users/all",
                        "/menu",
                        "/menu/**",
                        "/products/edit/**",
                        "/orders/all/history",
                        "/users/change/**",
                        "/users/profile",
                        "/users/profile/**").hasAnyRole(UserRoleEnum.ADMIN.name())

                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")

                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)

                .and()
                .rememberMe()
                .key("placeForUniqueKeyAtNikolFoodDeliveryProject")
                .tokenValiditySeconds(5 * 24 * 60 * 60);

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new FoodDeliveryUserDetailsService(userRepository);
    }

}
