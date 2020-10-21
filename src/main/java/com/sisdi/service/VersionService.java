package com.sisdi.service;

import com.sisdi.model.Version;
import java.util.List;

/**
 *
 * @author Vale
 */
public interface VersionService {
    
    public List<Version> list_versions();
    
    public void save_version(Version v);
    
//    public void delete_version(Version v);
    
    public Version find_version(Version v);
    
}
