package org.divyasingh.fse.repository;

import java.util.List;

import org.divyasingh.fse.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends Repository<Subject, Long> {
	List<Subject> findBySubtitleIgnoreCaseContaining(String title);
	List<Subject> findAll();
    Subject findById(Long subjectId);
    Subject save(Subject subject);
    void delete(Subject subject);
    
}
