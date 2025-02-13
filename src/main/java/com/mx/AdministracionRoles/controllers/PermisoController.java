package com.mx.AdministracionRoles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.AdministracionRoles.models.PermisoModel;
import com.mx.AdministracionRoles.services.PermisoService;

@RestController
@RequestMapping("/permiso")
@CrossOrigin(origins = "*")
public class PermisoController {

	@Autowired
    private PermisoService permisoService;
    
    @GetMapping
    public List<PermisoModel> getAllPermisos() {
        return permisoService.getAllPermisos();
    }
    
    @PostMapping
    public ResponseEntity<?> createPermiso(@RequestBody PermisoModel permiso) {
        try {
            PermisoModel newPermiso = permisoService.savePermiso(permiso);
            return ResponseEntity.ok(newPermiso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getPermisoById(@PathVariable Long id) {
        return permisoService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePermiso(@PathVariable Long id, @RequestBody PermisoModel permiso) {
        try {
            PermisoModel updatedPermiso = permisoService.updatePermiso(id, permiso);
            return ResponseEntity.ok(updatedPermiso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermiso(@PathVariable Long id) {
        try {
            permisoService.deletePermiso(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
