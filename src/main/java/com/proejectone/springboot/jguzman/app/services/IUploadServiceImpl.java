package com.proejectone.springboot.jguzman.app.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class IUploadServiceImpl implements IUploadService{
    @Override
    public String nombrarFoto(MultipartFile archivo) throws IOException {
        String nombreArchivo = UUID.randomUUID().toString() + "_" +archivo.getOriginalFilename().replace(" ","");
        Path rutaArchivo = getPath(nombreArchivo);

        var archivoInputStream = archivo.getInputStream();
        Files.copy(archivoInputStream, rutaArchivo);
        archivoInputStream.close();
        return nombreArchivo;

    }

    @Override
    public Resource verFoto(String nombreFoto) throws MalformedURLException {
        Path rutaArchivo = getPath(nombreFoto);
        Resource recurso = new UrlResource(rutaArchivo.toUri());

        System.out.println(recurso);

        if(!recurso.exists() && !recurso.isReadable()) {

            rutaArchivo = Paths.get("src/main/resources/static").resolve("no-usuario.png").toAbsolutePath();


            recurso = new UrlResource(rutaArchivo.toUri());
            System.out.println(recurso);
        }
        return recurso;
    }

    @Override
    public boolean eliminarFoto(String nombreFoto) {
        if(nombreFoto != null && nombreFoto.length() > 0){
            Path rutaAnterior = getPath(nombreFoto);
            File archivoFoto = rutaAnterior.toFile();
            if(archivoFoto.exists() && archivoFoto.canRead()){
                archivoFoto.delete();
            }
            return true;
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
    }


}
