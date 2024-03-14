package killian.brunet.sg.kata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import killian.brunet.sg.kata.models.entity.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query(value = "SELECT * FROM operation WHERE id_account = ?1", name = "findOperations", nativeQuery = true)
    List<Operation> findAccountOperations(Long id);
}
