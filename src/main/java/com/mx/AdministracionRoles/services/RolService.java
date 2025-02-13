package com.mx.AdministracionRoles.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.AdministracionRoles.models.PermisoModel;
import com.mx.AdministracionRoles.models.RolModel;
import com.mx.AdministracionRoles.repositories.IRolRepository;
import com.mx.AdministracionRoles.repositories.IPermisoRepository;

@Service
public class RolService {
	
	@Autowired
    private IRolRepository rolRepository;
    
    @Autowired
    private IPermisoRepository permisoRepository;
    
    public List<RolModel> getRoles() {
        return rolRepository.findAll();
    }
     //guardar un rol
    public RolModel saveRol(RolModel rol, List<Long> permisoIds) throws Exception {
        
    	// Validación para que un nombre del rol sea unico
        Optional<RolModel> existingRol = rolRepository.findByNombreRol(rol.getNombreRol());
        if (existingRol.isPresent()) {
            throw new Exception("Ya existe un rol con el nombre: " + rol.getNombreRol());
        }
        
        // Obentener y estabecer la fecha en automatico al registrar
        rol.setFechaAltaRol(LocalDateTime.now());
        rol.setEstatusRol("Activo");
        
        // Obtiene y asigna los permisos que sean seleccionados, calidando que exista el permiso
        Set<PermisoModel> permisos = new HashSet<>();
        for (Long permisoId : permisoIds) {
            PermisoModel permiso = permisoRepository.findById(permisoId)
                .orElseThrow(() -> new Exception("No se encontró el permiso con id: " + permisoId));
            permisos.add(permiso);
        }
        rol.setPermisos(permisos);
        
        return rolRepository.save(rol);
    }
    
    //busca por id
    public Optional<RolModel> getById(Long id) {
        return rolRepository.findById(id);
    }
    
    //Busca por nombre
    public List<RolModel> searchByNombre(String nombre) {
        return rolRepository.findByNombreRolContaining(nombre);
    }
    
    
    //Busca entre fechas
    public List<RolModel> searchByFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        System.out.println("Buscando roles entre: " + fechaInicio + " y " + fechaFin);
        List<RolModel> roles = rolRepository.findByFechaAltaRolBetween(fechaInicio, fechaFin);
        System.out.println("Roles encontrados: " + roles.size());
        return roles;
    }
    
    //actualiza rol
    public RolModel updateRol(Long id, RolModel rol, List<Long> permisoIds) throws Exception {
        RolModel existingRol = rolRepository.findById(id)
            .orElseThrow(() -> new Exception("No se encontró el rol con id: " + id));
            
        // Validar nombre único si cambió
        if (!existingRol.getNombreRol().equals(rol.getNombreRol())) {
            Optional<RolModel> duplicateRol = rolRepository.findByNombreRol(rol.getNombreRol());
            if (duplicateRol.isPresent()) {
                throw new Exception("Ya existe un rol con el nombre: " + rol.getNombreRol());
            }
        }
        
        existingRol.setNombreRol(rol.getNombreRol());
        existingRol.setImagenRol(rol.getImagenRol());
        existingRol.setEstatusRol(rol.getEstatusRol());
        
        // Actualizar permisos
        Set<PermisoModel> permisos = new HashSet<>();
        for (Long permisoId : permisoIds) {
            PermisoModel permiso = permisoRepository.findById(permisoId)
                .orElseThrow(() -> new Exception("No se encontró el permiso con id: " + permisoId));
            permisos.add(permiso);
        }
        existingRol.setPermisos(permisos);
        
        return rolRepository.save(existingRol);
    }
    
    
    //eliminar un rol
    public Boolean deleteRol(Long id) {
        try {
            // Verificar si existe el rol
            RolModel rol = rolRepository.findById(id)
                .orElseThrow(() -> new Exception("No se encontró el rol con id: " + id));
                
            // Cambiar el estatus a "Desactivado" en lugar de eliminar
            rol.setEstatusRol("Desactivado");
            
            // Guardar el cambio en la base de datos
            rolRepository.save(rol);
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
}
