package com.proejectone.springboot.jguzman.app.services;

import ch.qos.logback.core.net.server.Client;
import com.proejectone.springboot.jguzman.app.dao.ClienteDao;
import com.proejectone.springboot.jguzman.app.dao.RegionDao;
import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class IClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteDao clienteDao;


    @Autowired
    private RegionDao regionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {

        return clienteDao.findAll();
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
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Region> regionDb = regionDao.findById(cliente.getRegion().getId());
            if(regionDb.isPresent()){
                cliente.setRegion(regionDb.get());
            }else{
                response.put("mensaje","Region no encontrado con el id ".concat(" : ").concat(cliente.getRegion().getId().toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

            clienteSaved = clienteDao.save(cliente);
            if(clienteSaved == null){
                response.put("mensaje","Error al guardar el cliente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (DataAccessException e){
            response.put("mensaje","Error al consultar la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente fue creado con exito!");
        response.put("cliente creado",clienteSaved);
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
                clientetDb.get().setNombre(cliente.getNombre());
                clientetDb.get().setApellido(cliente.getApellido());
                clientetDb.get().setEmail(cliente.getEmail());
                clientetDb.get().setCreateAt(cliente.getCreateAt());
                clientetDb.get().setRegion(cliente.getRegion());

                Cliente clienteUpdated = clienteDao.save(clientetDb.get());
                if(clienteUpdated != null){
                    response.put("mensaje","cliente actualizado con exito!");
                    response.put("cliente actualizado",clienteUpdated);

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
}
