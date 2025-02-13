package com.mx.AdministracionRoles.controllers;

import com.mx.AdministracionRoles.dto.RolRequestDTO;
import com.mx.AdministracionRoles.models.RolModel;
import com.mx.AdministracionRoles.services.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
    private RolService rolService;
    
    @GetMapping
    public List<RolModel> getRoles() {
        return rolService.getRoles();
    }
    
    @PostMapping
    public ResponseEntity<?> createRol(@RequestBody RolRequestDTO request) {
        try {
            RolModel rol = new RolModel();
            rol.setNombreRol(request.getNombreRol());
            rol.setImagenRol(request.getImagenRol());
            rol.setEstatusRol(request.getEstatusRol());
            
            RolModel newRol = rolService.saveRol(rol, request.getPermisoIds());
            return ResponseEntity.ok(newRol);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getRolById(@PathVariable Long id) {
        return rolService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<RolModel>> searchRoles(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) LocalDateTime fechaInicio,
            @RequestParam(required = false) LocalDateTime fechaFin) {
        
        if (nombre != null && !nombre.isEmpty()) {
            return ResponseEntity.ok(rolService.searchByNombre(nombre));
        } else if (fechaInicio != null && fechaFin != null) {
            return ResponseEntity.ok(rolService.searchByFechas(fechaInicio, fechaFin));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRol(@PathVariable Long id, @RequestBody RolRequestDTO request) {
        try {
            RolModel rol = new RolModel();
            rol.setNombreRol(request.getNombreRol());
            rol.setImagenRol(request.getImagenRol());
            rol.setEstatusRol(request.getEstatusRol());
            
            RolModel updatedRol = rolService.updateRol(id, rol, request.getPermisoIds());
            return ResponseEntity.ok(updatedRol);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id) {
        boolean deleted = rolService.deleteRol(id);
        if (deleted) {
            return ResponseEntity.ok().body("Rol eliminado exitosamente");
        } else {
            return ResponseEntity.badRequest().body("Error al eliminar el rol");
        }
    }
}
