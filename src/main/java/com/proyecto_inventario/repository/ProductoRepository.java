    package com.proyecto_inventario.repository;

    import com.proyecto_inventario.model.Producto;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface ProductoRepository extends JpaRepository<Producto, Long> {
    }


