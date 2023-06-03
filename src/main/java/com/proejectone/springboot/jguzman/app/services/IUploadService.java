package com.proejectone.springboot.jguzman.app.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadService {
    public String nombrarFoto(MultipartFile archivo) throws IOException;

    public Resource verFoto(String nombreFoto) throws MalformedURLException ;

    public boolean eliminarFoto(String nombreFoto);

    public Path getPath(String nombreFoto);
}
