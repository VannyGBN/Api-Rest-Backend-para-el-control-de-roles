package com.mx.AdministracionRoles.models;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;



@Entity
@Table(name="rol")

public class RolModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String nombreRol;
    
    @Column
    private String imagenRol;
    
    @Column
    private String estatusRol;
    
    @Column
    private LocalDateTime fechaAltaRol;
    
    @ManyToMany
    @JoinTable(
        name = "rol_permiso",
        joinColumns = @JoinColumn(name = "rol_id"),
        inverseJoinColumns = @JoinColumn(name = "permiso_id")
    )
    private Set<PermisoModel> permisos = new HashSet<>();
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getFechaAltaRol() {
        return fechaAltaRol;
    }
    
    public void setFechaAltaRol(LocalDateTime fechaAltaRol) {
        this.fechaAltaRol = fechaAltaRol;
    }
    
    public Set<PermisoModel> getPermisos() {
        return permisos;
    }
    
    public void setPermisos(Set<PermisoModel> permisos) {
        this.permisos = permisos;
    }
}