package com.microjade.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

//Es una clase que permite leer la info pero no modificarla
//El prefijo es accounts, buscara entonces aquellas propiedades que comienzan con accounts
//Asigna a cada propiedad el valor que le corresponde de propiedad.
//En tiempo de inicio de spring boot se inicializan estas propiedades y lo podremos usar
@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, Map<String,String> contactDetails, List<String> onCallSupport) { }
