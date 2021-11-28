package uz.pdp.task1.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, int id);
}
