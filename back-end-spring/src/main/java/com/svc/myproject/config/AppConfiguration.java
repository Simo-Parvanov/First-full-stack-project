package com.svc.myproject.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "dr7iltbog");
        valuesMap.put("api_key", "261973236744725");
        valuesMap.put("api_secret", "lONTj0B6NfpJqR7UrogcxD22nnU");
        return new Cloudinary(valuesMap);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
