package com.proejectone.springboot.jguzman.app.services;

import ch.qos.logback.core.net.server.Client;
import com.proejectone.springboot.jguzman.app.dao.ClienteDao;
import com.proejectone.springboot.jguzman.app.dao.FacturaDao;
import com.proejectone.springboot.jguzman.app.dao.RegionDao;
import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Factura;
import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class IClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteDao clienteDao;


    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private RegionDao regionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {

        return clienteDao.findAll();
    }

    @Override

    public List<Cliente> searchByCliente(String name) {
        return clienteDao.findByNameLike(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long id) {
        Optional<Cliente> cliente = null;
        Map<String, Object> response = new HashMap<>();
        try{
            cliente = clienteDao.findById(id);
            if(cliente.isEmpty()){
                response.put("mensaje","No se encuentra el cliente por el id ".concat(": ").concat(id.toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("mensaje","Cliente Encontrado");
                response.put("cliente",cliente.get());
            }
        }catch (DataAccessException e){
            response.put("mensaje","Error al consultar la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(Cliente cliente) {
        Cliente clienteSaved = null;
        System.out.println("1");
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Region> regionDb = regionDao.findById(cliente.getRegion().getId());
            if(regionDb.isPresent()){
                cliente.setRegion(regionDb.get());
                System.out.println("2");
            }else{
                response.put("mensaje","Region no encontrado con el id ".concat(" : ").concat(cliente.getRegion().getId().toString()));
                System.out.println("3");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

            }

            clienteSaved = clienteDao.save(cliente);
            System.out.println("4");
            if(clienteSaved == null){
                response.put("mensaje","Error al guardar el cliente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (DataAccessException e){
            System.out.println("5");
            response.put("mensaje","Error al consultar la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println("6");
        response.put("mensaje","El cliente fue creado con exito!");
        response.put("cliente_creado",clienteSaved);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Cliente cliente, Long clienteId) {
        Optional<Cliente> clientetDb = null;
        Map<String, Object> response = new HashMap<>();
        try{



           Optional<Region> regionDb = regionDao.findById(cliente.getRegion().getId());
            if(regionDb.isPresent()){
                cliente.setRegion(regionDb.get());
            }else{
                response.put("mensaje","La region no existe en la base de datos!");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            clientetDb = clienteDao.findById(clienteId);
            if(clientetDb.isPresent()){
                System.out.println(cliente.getEmail().equalsIgnoreCase(clientetDb.get().getEmail()));
                System.out.println(!cliente.getEmail().equalsIgnoreCase(clientetDb.get().getEmail()));
                System.out.println(clienteDao.porEmail(cliente.getEmail()).isPresent());
                if (!cliente.getEmail().isEmpty() &&
                        !cliente.getEmail().equalsIgnoreCase(clientetDb.get().getEmail()) &&
                        clienteDao.porEmail(cliente.getEmail()).isPresent()) {
                    return ResponseEntity.badRequest()
                            .body(Collections
                                    .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
                }


                clientetDb.get().setNombre(cliente.getNombre());
                clientetDb.get().setApellido(cliente.getApellido());
                clientetDb.get().setEmail(cliente.getEmail());
                clientetDb.get().setCreateAt(cliente.getCreateAt());
                clientetDb.get().setRegion(cliente.getRegion());

                Cliente clienteUpdated = clienteDao.save(clientetDb.get());
                if(clienteUpdated != null){
                    response.put("mensaje","cliente actualizado con exito!");
                    response.put("cliente_actualizado",clienteUpdated);

                }else{
                    response.put("mensaje","cliente no actualizado");
                    response.put("cliente",clienteUpdated);
                    return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
                }
            }else{
                response.put("mensaje","No se encuentra el producto con el id ".concat(" :").concat(clienteId.toString()));
                return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
            }

        }catch  (DataAccessException e){
            response.put("mensaje","Error al consultar la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Optional<Cliente> clienteDb = null;
        Map<String, Object> response = new HashMap<>();
        try{
            clienteDb = clienteDao.findById(id);
            if(clienteDb.isEmpty()){
                response.put("mensaje","No se encuentra el cliente por el id ".concat(": ").concat(id.toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                clienteDao.deleteById(id);
                response.put("mensaje", "Cliente eliminado con exito");

            }
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar el cliente en la basem de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
    }

    @Override
    public boolean existePorEmail(String email) {
        return clienteDao.existsByEmail(email);
    }

    @Override
    public Optional<Cliente> porEmail(String email) {
        return clienteDao.porEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long clienteId) {
        return facturaDao.findById(clienteId).orElse(null);
    }
}
