package com.proejectone.springboot.jguzman.app.services;

import com.proejectone.springboot.jguzman.app.dao.RegionDao;
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
public class IRegionImpl implements IRegionService{

    @Autowired
    private RegionDao regionDao;
    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(Long id) {
        Optional<Region> region = null;
        Map<String, Object> response = new HashMap<>();
        try{
            region = regionDao.findById(id);
            if(region.isEmpty()){
                response.put("mensaje","No se encuentra el region por el id ".concat(": ").concat(id.toString()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }else{
                response.put("mensaje","Region Encontrada");
                response.put("region",region.get());
            }
        }catch (DataAccessException e){
            response.put("mensaje","Error al consultar la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(Region region) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Region region) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }
}
