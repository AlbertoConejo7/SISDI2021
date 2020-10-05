
package com.sisdi.dao;

import com.sisdi.model.version;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Vale
 */
public interface VersionDao extends CrudRepository <version, String> {
    
}
