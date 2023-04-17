package eddamghi.ressourcesmanagement.DAO.repositories;

import eddamghi.ressourcesmanagement.DAO.entities.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findByTitleContains(String keyword, Pageable pageable);
}
