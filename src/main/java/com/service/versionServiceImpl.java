
package com.service;

import com.dao.versionDao;
import com.sisdi.model.version;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vale
 */
@Service
public class versionServiceImpl implements versionService{
    
    @Autowired
    versionDao versionDao;

//    @Override
//    //@Transactional(readOnly = true) //importar de spring 
//    public List<version> listVersions() {
////        return versionDao.findAll();
//    }
//
//    @Override
////    @Transactional
//    public void save(version v) {
//        versionDao.save(v);
//    }
//
//    @Override
////    @Transactional
//    public void erase(version v) {
//        versionDao.delete(v);
//    }
//
//    @Override
////    @Transactional(readOnly=true)
//    public version findVersion(version v) {
////        return versionDao.findById.(v.getId_version()).orElse(null);
//    }
    
}
