package com.proejectone.springboot.jguzman.app.controllers;


import com.proejectone.springboot.jguzman.app.dao.ClienteDao;
import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import com.proejectone.springboot.jguzman.app.services.IClienteService;
import com.proejectone.springboot.jguzman.app.services.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;


    @Autowired
    private IUploadService uploadService;

    @Autowired
    private ClienteDao clienteDao;

    @GetMapping("/clientes")
    public List<Cliente> findAll(){
        List<Cliente> response = clienteService.findAll();
        return response;
    }


    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> findAllPages(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page,2);
        return clienteService.findAll(pageable);
    }

    @GetMapping("/clientes/filter/{name}")
    public List<Cliente> findByClienteName(@PathVariable String name){
        List<Cliente> response = clienteService.searchByCliente(name);
        return response;
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        ResponseEntity<?> response = clienteService.findById(id);
        return response;
    }




    @PostMapping("/clientes")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult result){

        Map<String, Object> errors = new HashMap<>();
        if(result.hasErrors()){

            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            errors.put("errors", errores);
            return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
        }

        if(!cliente.getEmail().isEmpty() && clienteService.existePorEmail(cliente.getEmail())){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
        }

        ResponseEntity<?> response = clienteService.save(cliente);
        return response;
    }


    @PutMapping("/clientes/{clienteId}")
    public ResponseEntity<?>  updateCliente(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long clienteId){
        Map<String, Object> errors = new HashMap<>();
        if(result.hasErrors()){

            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            errors.put("errors", errores);
            return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
        }


        /*
        if(!cliente.getEmail().isEmpty() && clienteService.existePorEmail(cliente.getEmail())){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
        }
        */

        ResponseEntity<?> response = clienteService.update(cliente,clienteId);
        return response;
    }


    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String,Object> response = new HashMap<>();
        Optional<Cliente> cliente = clienteDao.findById(id);

        if(cliente.isEmpty()){

            response.put("error","No se encuentra el usuario con el Identificador :".concat(id.toString()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if(!archivo.isEmpty()){
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.nombrarFoto(archivo);
            }catch (IOException e){
                response.put("mensaje","Error al subir la imagen del cliente");
                return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

            }

            String nombreAnterior = cliente.get().getFoto();
            uploadService.eliminarFoto(nombreAnterior);

            cliente.get().setFoto(nombreArchivo);
            clienteService.save(cliente.get());
            response.put("mensaje","Has subido correctamente la imagen : " + nombreArchivo);
            response.put("cliente",cliente);

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/clientes/upload/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso = null;

        try{
            recurso = uploadService.verFoto(nombreFoto);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);
    }


    @DeleteMapping("/clientes/{clienteId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long clienteId){
        ResponseEntity<?> response = clienteService.delete(clienteId);
        return response;
    }
}
