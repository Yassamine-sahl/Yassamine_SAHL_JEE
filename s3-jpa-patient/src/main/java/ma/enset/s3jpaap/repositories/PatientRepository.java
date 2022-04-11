package ma.enset.s3jpaap.repositories;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import ma.enset.s3jpaap.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    //Creation de la methode em utilisant find
    List<Patient> findByMalade(boolean m);

    Page<Patient> findByMalade(boolean m, Pageable pageable);

    List<Patient> findByMaladeAndScoreLessThan(Boolean m, int score);

    List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);

    List<Patient> findByDateNBetweenAndMaladeIsTrue(Date d1, Date d2);

    //Creation de la methode en lui attribut une annotation @Query
    @Query("select p from Patient p where p.nom like :x and p.score < :y ")
    List<Patient> cherhcerPatients(@Param("x") String nom, @Param("y") int score);


}
