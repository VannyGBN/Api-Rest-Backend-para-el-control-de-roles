package com.mx.AdministracionRoles.repositories;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.AdministracionRoles.models.RolModel;



@Repository
public interface IRolRepository extends JpaRepository<RolModel, Long> {
    Optional<RolModel> findByNombreRol(String nombreRol);
    
    @Query("SELECT r FROM RolModel r WHERE r.nombreRol LIKE %:nombre%")
    List<RolModel> findByNombreRolContaining(@Param("nombre") String nombre);
    
    @Query("SELECT r FROM RolModel r WHERE r.fechaAltaRol BETWEEN :fechaInicio AND :fechaFin")
    List<RolModel> findByFechaAltaRolBetween(
        @Param("fechaInicio") LocalDateTime fechaInicio, 
        @Param("fechaFin") LocalDateTime fechaFin
    );
    
    @Query("SELECT r FROM RolModel r WHERE r.estatusRol = 'Activo'")
    List<RolModel> findAllActive();
}
