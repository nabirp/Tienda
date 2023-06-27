/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.service;

/**
DOCUMENTO DE TIPO JAVA INTERFACE
 */
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseStorageService {
//DEFINIMOS LA FIRMA DE UN METODO
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);
//EN FIREBASE NOS DIRIGIMOS A CONFIGURACIONES-GENERAL, BAJAMOS Y SALE EL CODIGO
    //El BuketName es el <id_del_proyecto> + ".appspot.com"
    //archivoJsonFile = “xxxNombre del archivo Json";
    final String BucketName = "techshop-955f0.appspot.com";

    //Esta es la ruta básica de este proyecto Techshop
    final String rutaSuperiorStorage = "techshop";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    
    //El nombre del archivo Json
    //final String archivoJsonFile = “xxxNombre del archivo Json";
    final String archivoJsonFile = "techshop-955f0-firebase-adminsdk-tt64p-6a7fc6cb04.json";
}