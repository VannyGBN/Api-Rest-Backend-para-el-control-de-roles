package com.mx.AdministracionRoles.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.AdministracionRoles.models.PermisoModel;


@Repository
public interface IPermisoRepository extends JpaRepository<PermisoModel, Long> {

	Optional<PermisoModel> findByNombrePermiso(String nombrePermiso);
}
