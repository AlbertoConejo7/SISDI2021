
package com.sisdi.service;

import com.sisdi.dao.VersionDao;
import com.sisdi.model.Version;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vale
 */
@Service
public class VersionServiceImp implements VersionService{
    
   
    @Autowired
    private VersionDao versionDao;
    
    @Transactional(readOnly=true)
    @Override
    public List<Version> list_versions() {
        return (List<Version>) versionDao.findAll();
    }

    @Override
    @Transactional
    public void save_version(Version v) {
        versionDao.save(v);
    }

    @Override
    @Transactional(readOnly=true)
    public Version find_version(Version v) {
        return versionDao.findById(v.getId()).orElse(null);
    }
    
}
