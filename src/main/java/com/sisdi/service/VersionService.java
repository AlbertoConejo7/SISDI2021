package com.sisdi.service;

import com.sisdi.model.version;
import java.util.List;

/**
 *
 * @author Vale
 */
public interface VersionService {
    
    public List<version> list_versions();
    
    public void save_version(version v);
    
//    public void delete_version(version v);
    
    public version find_version(version v);
    
}
