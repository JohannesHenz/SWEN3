package com.dms.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dms.api.model.FileEntity;

@Repository
public interface FileRepository extends CrudRepository<FileEntity, Long> {
  


}
