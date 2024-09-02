package com.gersimuca.cma.feature.user;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  Optional<UserEntity> findByEmailIgnoreCase(String email);

  Optional<UserEntity> findByUsername(String username);

  // Query using JPQL with index params
  @Query("SELECT c FROM UserEntity  c WHERE c.firstName = ?1 AND c.lastName = ?2")
  UserEntity findByFirstNameAndLastNameIndexParams(String firstName, String lastName);

  // Query using JPQL with named params
  @Query("SELECT c FROM UserEntity  c WHERE c.firstName = :firstName AND c.lastName = :lastName")
  UserEntity findByFirstnameAndLastnameNamedParams(
      @Param("firstName") String firstName, @Param("lastName") String lastName);

  // Query using Native SQL with index params
  @Query(
      value = "SELECT c FROM UserEntity  c WHERE c.firstName = ?1 AND c.lastName = ?2",
      nativeQuery = true)
  UserEntity findByFirstNameAndLastNameNativeQueryIndexParams(String firstName, String lastName);

  // Query using Native SQL with index params
  @Query(
      value =
          "SELECT c FROM UserEntity  c WHERE c.firstName = :firstName AND c.lastName = :lastName",
      nativeQuery = true)
  UserEntity findByFirstNameAndLastNameNativeQueryNamedParams(
      @Param("firstName") String firstName, @Param("lastName") String lastName);
}
