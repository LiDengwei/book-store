package com.book.manager.api.impl;

import com.book.manager.api.service.UserEntityService;
import com.book.manager.dao.security.UserDao;
import com.book.manager.domain.security.UserEntity;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/** 
 * TODO(用户控制).
 * @ClassName: UserServiceImpl
 * @author Johnny_L_Q
 */
@Service("userEntityService")
@Transactional
public class UserServiceImpl implements UserEntityService {


    @Resource
    private UserDao userDao;


    @Override
    public UserEntity getUserByUserAccount(String userAccount) {
        return userDao.getUserByUserAccount(userAccount);
    }

    @Override
    public UserEntity getById(Integer id) {
        return userDao.getById(id);
    }


    @Override
    public List<UserEntity> findUsers(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<UserEntity> selectByPage(UserEntity userEntity) {

        if (userEntity==null){
            userEntity=new UserEntity();
            userEntity.setPage(1);
            userEntity.setPageSize(20);
        }
        int offset=(userEntity.getPage()-1)*userEntity.getPageSize();
        return userDao.selectByPage(userEntity,new RowBounds(offset,userEntity.getPageSize()));
        //return userDao.selectByPage(userEntity);
    }

    @Override
    public int countByPage(UserEntity userEntity) {
        return userDao.countByPage(userEntity);
    }

    @Override
    public Integer insert(UserEntity userEntity) {
        return userDao.insert(userEntity);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity userEntity) {
        return userDao.updateByPrimaryKeySelective(userEntity);
    }

    @Override
    public int delete(UserEntity userEntity) {
        return userDao.delete(userEntity);
    }

    /*
     * <p>Title: loadUserAuthoritiesByName</p>
     * <p>Description: </p>
     * @param username
     * @return
     * @see com.doone.wisdom.service.iface.UserService#loadUserAuthoritiesByName(java.lang.String)
     */
//    public List<GrantedAuthority> loadUserAuthoritiesByName(String username) {
//        UserEntity user = getUser(username);
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
//        for (ResourceEntity resource : this.resourceService
//                .getResourcesByUsername(username)) {
//            grantedAuthorities
//                    .add(new SimpleGrantedAuthority(resource.getUrl()));
//        }
//        user.setGrantedAuthorities(grantedAuthorities);
//        return user;
//    }


    @Override
    public boolean updatePassword(String username, String password) {
        UserEntity userEntity = new UserEntity() ;
        userEntity.setUserName(username);
        userEntity.setPassword(password);
        return userDao.updatePassword(userEntity)>0;
    }
}
