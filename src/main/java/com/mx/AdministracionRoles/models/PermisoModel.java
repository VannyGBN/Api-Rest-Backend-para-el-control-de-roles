package com.mx.AdministracionRoles.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permiso")
public class PermisoModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String nombrePermiso;
    
    @Column
    private String descripcion;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "permisos")
    private Set<RolModel> roles = new HashSet<>();
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombrePermiso() {
        return nombrePermiso;
    }
    
    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Set<RolModel> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<RolModel> roles) {
        this.roles = roles;
    }
}