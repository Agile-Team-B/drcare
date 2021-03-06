package ie.ait.bteam.drcare.data.repository;

import ie.ait.bteam.drcare.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userType = ?1")
	List<User> findByUserType(String userType);

	@Query("select u from User u where u.username like %:username% and u.userType = :userType")
	List<User> findByUsernameAndUserType(String username, String userType);

	@Query("select u from User u where u.userType is null and isAdmin = false")
	List<User> findPatients();

	@Query("select u from User u where u.patientEmergencyId = ?1")
	User findByPatientEmergencyId(String patientEmergencyId);

	@Query("select u from User u where u.username = ?1")
	User findUserByUsername(String username);

	@Query("select u from User u where u.email = ?1")
	User findUserByEmail(String email);
}
