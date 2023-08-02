/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
//Nos permiter ingresar ciertas configuraciones 
public class ProjectConfig implements WebMvcConfigurer{
    /*Los siguientes metodos son para incorporar el tema de internalizacion en el proyecto*/
    @Bean
    //Crea una variable de sesion para el idioma 
    public LocaleResolver localeResolver(){
        var slr= new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        
        return slr;
    }
    //localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma 
    //Variable en la que se almacena el es y el en, conforme cambiemos ese valor el sabe en cual idioma mostrarlo o no
    @Bean
    
    //Este medoto retorna un interceptor, que almacena o detecta el cambio de idioma
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci= new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
     
    //Poder utilizar los mensajes en el properties
    @Override
    public void addInterceptors (InterceptorRegistry registry){
        registry.addInterceptor(localeChangeInterceptor());
        
    }
    //Bean para poder acceder a los Messages.properties en código...
    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    /*Una vista que llama al controlador*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");   
 }
    
    /* El siguiente método se utiliza para completar la clase no es 
    realmente funcional, la próxima semana se reemplaza con usuarios de BD */    
     /*Para tener los usuarios que teniamos en BD pero en memoria,solo existe cada que levanto la aplicacion
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("juan")
                .password("{noop}123")
                .roles("USER", "VENDEDOR", "ADMIN")
                .build();
        UserDetails sales = User.builder()
                .username("rebeca")
                .password("{noop}456")
                .roles("USER", "VENDEDOR")
                .build();
        UserDetails user = User.builder()
                .username("pedro")
                .password("{noop}789")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, sales, admin);
    }
    */
    @Autowired
    private UserDetailsService userDetailsService;
 //Eso es para indicarle como estan incriptadas las contraseñas,para que por ejemplo en la pantalla de login pong 123, el tome la contraseña
//la encripte y luego la compare con el valor de las bases de datos
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /* Limitacion para los roles designados*/
/* Los dos ** significa todo lo que contenga la palabra anterior a esos simbolos*/
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/","/index","/errores/**",
                        "/carrito/**","/pruebas/**","/reportes/**",
                        "/registro/**","/js/**","/webjars/**")
                        .permitAll()
                .requestMatchers(
                        "/producto/nuevo","/producto/guardar",
                        "/producto/modificar/**","/producto/eliminar/**",
                        "/categoria/nuevo","/categoria/guardar",
                        "/categoria/modificar/**","/categoria/eliminar/**",
                        "/usuario/nuevo","/usuario/guardar",
                        "/usuario/modificar/**","/usuario/eliminar/**",
                        "/reportes/**"
                ).hasRole("ADMIN")
                .requestMatchers(
                        "/producto/listado",
                        "/categoria/listado",
                        "/usuario/listado"
                ).hasAnyRole("ADMIN", "VENDEDOR")
                .requestMatchers("/facturar/carrito")
                .hasRole("USER")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }


}    

