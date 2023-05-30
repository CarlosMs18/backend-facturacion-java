package com.proejectone.springboot.jguzman.app.services;

import com.proejectone.springboot.jguzman.app.dao.ProductoDao;
import com.proejectone.springboot.jguzman.app.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class IProductoImpl implements IProductoService {
    @Autowired
    private ProductoDao productoDao;
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long id) {
        Optional<Producto> producto = null;
        Map<String, Object> response = new HashMap<>();
        try{
            producto = productoDao.findById(id);
            if(producto.isEmpty()){
                response.put("mensaje","No se encuentra el producto por el id ".concat(": ").concat(id.toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("mensaje","Producto Encontrado");
                response.put("producto",producto.get());
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
    public ResponseEntity<?> save(Producto producto) {
        Producto product = null;
        Map<String, Object> response = new HashMap<>();
        try{
            product = productoDao.save(producto);
        }catch (DataAccessException e){
            response.put("mensaje","Error al realizar la insercion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El producto ha sido creado con exito!");
        response.put("producto creado", product);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Producto producto, Long productId) {
        Optional<Producto> product = null;
        Map<String, Object> response = new HashMap<>();

        try{
            product = productoDao.findById(productId);
            if(product.isEmpty()){
                response.put("mensaje","No se encuentra el producto por el id ".concat(": ").concat(productId.toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                product.get().setNombre(producto.getNombre());
                product.get().setPrecio(producto.getPrecio());

                Producto productoUpdated = productoDao.save(product.get());

                if(productoUpdated == null){
                    response.put("mensaje","Producto no actualizado!");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
                }else{
                    response.put("mensaje","Producto actualizado con exito!");
                    response.put("producto", productoUpdated);
                }
            }
        }catch(DataAccessException e){
            response.put("mensaje","Error al realizar la actualizacion en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> delete(Long id) {
        Optional<Producto> producto = null;
        Map<String, Object> response = new HashMap<>();
        try{
            producto = productoDao.findById(id);
            if(producto.isEmpty()){
                response.put("mensaje","No se encuentra el producto por el id ".concat(": ").concat(id.toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                productoDao.deleteById(id);
                response.put("mensaje", "Producto eliminado con exito");

            }
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar el producto en la basem de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
    }
}
