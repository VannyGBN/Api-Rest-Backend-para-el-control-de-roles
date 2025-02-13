package com.mx.AdministracionRoles.dto;

import java.util.List;

public class RolRequestDTO {
	
	private String nombreRol;
    private String imagenRol;
    private String estatusRol;
    private List<Long> permisoIds;
    
    // Getters y Setters
    public String getNombreRol() {
        return nombreRol;
    }
    
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    public String getImagenRol() {
        return imagenRol;
    }
    
    public void setImagenRol(String imagenRol) {
        this.imagenRol = imagenRol;
    }
    
    public String getEstatusRol() {
        return estatusRol;
    }
    
    public void setEstatusRol(String estatusRol) {
        this.estatusRol = estatusRol;
    }
    
    public List<Long> getPermisoIds() {
        return permisoIds;
    }
    
    public void setPermisoIds(List<Long> permisoIds) {
        this.permisoIds = permisoIds;
    }

}
