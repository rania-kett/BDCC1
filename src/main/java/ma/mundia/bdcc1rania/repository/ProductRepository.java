package ma.mundia.bdcc1rania.repository;

import ma.mundia.bdcc1rania.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
