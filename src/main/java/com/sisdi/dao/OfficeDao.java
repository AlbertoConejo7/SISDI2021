package com.sisdi.dao;

import com.sisdi.model.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeDao extends CrudRepository<Office, Integer>{
}
