
package com.sisdi.service;

import com.sisdi.dao.VersionDao;
import com.sisdi.model.version;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vale
 */
@Service
public class VersionServiceImp implements VersionService{

    @Autowired
    private VersionDao versionDao;
    
    @Override
    public List<version> list_versions() {
        return (List<version>) versionDao.findAll();
    }

    @Override
    public void save_version(version v) {
        versionDao.save(v);
    }

    @Override
    public version find_version(version v) {
        return versionDao.findById(v.getId_office()).orElse(null);
    }
    
}
