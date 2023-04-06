package pro.sky.animalshelterbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.animalshelterbot.model.Report;


public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findById(long id);
}
