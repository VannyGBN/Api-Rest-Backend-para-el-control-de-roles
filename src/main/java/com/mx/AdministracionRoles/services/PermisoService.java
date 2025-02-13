package com.mx.AdministracionRoles.services;


import com.mx.AdministracionRoles.models.PermisoModel;
import com.mx.AdministracionRoles.repositories.IPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PermisoService {

	
	@Autowired
    private IPermisoRepository permisoRepository;
    
    public List<PermisoModel> getAllPermisos() {
        return permisoRepository.findAll();
    }
    
    public PermisoModel savePermiso(PermisoModel permiso) throws Exception {
        Optional<PermisoModel> existingPermiso = permisoRepository.findByNombrePermiso(permiso.getNombrePermiso());
        if (existingPermiso.isPresent()) {
            throw new Exception("Ya existe un permiso con el nombre: " + permiso.getNombrePermiso());
        }
        return permisoRepository.save(permiso);
    }
    
    public Optional<PermisoModel> getById(Long id) {
        return permisoRepository.findById(id);
    }
    
    public PermisoModel updatePermiso(Long id, PermisoModel permiso) throws Exception {
        PermisoModel existingPermiso = permisoRepository.findById(id)
            .orElseThrow(() -> new Exception("No se encontró el permiso con id: " + id));
            
        if (!existingPermiso.getNombrePermiso().equals(permiso.getNombrePermiso())) {
            Optional<PermisoModel> duplicatePermiso = permisoRepository.findByNombrePermiso(permiso.getNombrePermiso());
            if (duplicatePermiso.isPresent()) {
                throw new Exception("Ya existe un permiso con el nombre: " + permiso.getNombrePermiso());
            }
        }
        
        existingPermiso.setNombrePermiso(permiso.getNombrePermiso());
        existingPermiso.setDescripcion(permiso.getDescripcion());
        
        return permisoRepository.save(existingPermiso);
    }
    
    public void deletePermiso(Long id) throws Exception {
        if (!permisoRepository.existsById(id)) {
            throw new Exception("No se encontró el permiso con id: " + id);
        }
        permisoRepository.deleteById(id);
    }
    
}
