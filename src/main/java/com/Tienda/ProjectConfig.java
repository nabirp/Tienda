/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda;

import java.util.Locale;
import org.springframework.context.MessageSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
}    

