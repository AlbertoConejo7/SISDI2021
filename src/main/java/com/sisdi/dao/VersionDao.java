
package com.sisdi.dao;

import com.sisdi.model.version;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vale
 */
@Repository
public interface VersionDao extends CrudRepository <version, String> {
    
}
