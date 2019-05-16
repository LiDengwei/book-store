package com.book.manager.api.impl;

import com.book.manager.api.service.AuthorityService;
import com.book.manager.dao.security.AuthorityDao;
import com.book.manager.dao.security.ResourceDao;
import com.book.manager.domain.security.*;
import com.book.manager.util.CommonUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 *
 * TODO(权限控制).
 * @ClassName: AuthorityServiceImpl
 * @author Johnny_L_Q
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

    @Resource
    private AuthorityDao authorityDao;
    @Resource
    private ResourceDao resourceDao;

    /*
     * <p>Title: getAllAuthoritys</p>
     * <p>Description: </p>
     * @return
     * @see com.doone.wisdom.service.iface.AuthorityService#getAllAuthoritys()
     */
    @Override
    public List<AuthorityEntity> getAllAuthoritys() {
        // TODO Auto-generated method stub
        return authorityDao.getAllAuthoritys();
    }

    @Override
    public AuthorityEntity getById(Integer authorityId) {
        return authorityDao.getById(authorityId);
    }

    @Override
    public AuthorityEntity getByAuthName(String authorityName) {
        AuthorityEntity temp=new AuthorityEntity();
        temp.setAuthorityName(authorityName);
        List<AuthorityEntity> entities=authorityDao.selectByPage(temp,new RowBounds(0,2));
        if (entities!=null && entities.size()>0){
            return entities.get(0);
        }
        return null;
    }

    @Override
    public List<AuthorityEntity> selectByPage(AuthorityEntity authorityEntity) {
        if (authorityEntity==null){
            authorityEntity=new AuthorityEntity();
            authorityEntity.setPage(1);
            authorityEntity.setPageSize(20);
        }
        int offset=(authorityEntity.getPage()-1)*authorityEntity.getPageSize();
        return authorityDao.selectByPage(authorityEntity,new RowBounds(offset,authorityEntity.getPageSize()));
    }

    @Override
    public List<AuthorityEntity> getAuthoritiesByUserId(String userAccount) {
        return authorityDao.getAuthoritiesByUserId(userAccount);
    }

    @Override
    public List<AuthorityEntity> getAuthoritiesByRoleId(Integer roleId,Integer issy) {
        Map<String,Integer> param = new HashMap<String,Integer>() ;
        param.put("roleId",roleId) ;
        param.put("issy",issy) ;
        return authorityDao.getAuthoritiesByRoleId(param);
    }

    @Override
    public int countByPage(AuthorityEntity authorityEntity) {
        return authorityDao.countByPage(authorityEntity);
    }

    @Override
    public Integer insert(AuthorityEntity authorityEntity) {
        authorityDao.insert(authorityEntity);
        RoleAuthorityEntity roleAuthorityEntity = new RoleAuthorityEntity() ;
        roleAuthorityEntity.setAuthorityId(authorityEntity.getId());
        roleAuthorityEntity.setIssy(true);
        roleAuthorityEntity.setRoleId(1);
        return authorityDao.insertRoleAuth(roleAuthorityEntity) ;
    }

    @Override
    public int updateByPrimaryKeySelective(AuthorityEntity authorityEntity) {
        return authorityDao.updateByPrimaryKeySelective(authorityEntity);
    }

    @Override
    public int delete(AuthorityEntity authorityEntity) {
        return authorityDao.delete(authorityEntity);
    }

    @Override
    public Boolean giveAuthToRole(Integer roleId, String authIds) {
        if (roleId!=null && roleId>0) {
            if (authIds != null && authIds != "") {
                Integer[] ids = CommonUtil.stringToInteger(authIds);
                if (ids != null) {
                    int deleteRows=authorityDao.deleteByRoleId(roleId);
                    if (deleteRows>=0) {
                        RoleAuthorityEntity param = null;
                        for (Integer authid : ids) {
                            param = new RoleAuthorityEntity();
                            param.setRoleId(roleId);
                            param.setAuthorityId(authid);
                            authorityDao.insertRoleAuth(param);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<AuthorityEntity> getAuthorityPublic() {
        return authorityDao.getAuthorityPublic();
    }

    @Override
    public int batchInsertRoleAuthority(List<RoleAuthorityEntity> roleAuthorityEntities) {
        return authorityDao.batchInsertRoleAuthority(roleAuthorityEntities);
    }

    @Override
    public int updateRoleAuthority(Integer roleId, Integer authorityId , boolean issy) {
        RoleAuthorityEntity roleAuthorityEntity = new RoleAuthorityEntity() ;
        roleAuthorityEntity.setRoleId(roleId);
        roleAuthorityEntity.setAuthorityId(authorityId);
        roleAuthorityEntity.setIssy(issy);
        return authorityDao.updateRoleAuthority(roleAuthorityEntity);
    }

    @Override
    public RoleAuthorityEntity  getRoleAuthority(Integer roleId, Integer autorityId) {
        RoleAuthorityEntity roleAuthorityEntity = new RoleAuthorityEntity() ;
        roleAuthorityEntity.setRoleId(roleId);
        roleAuthorityEntity.setAuthorityId(autorityId);
        return authorityDao.getRoleAuthority(roleAuthorityEntity);
    }

    @Override
    public boolean giveResourcesToAuth(List<ResourceTreeEntity> list, Integer authId) {
        int addSize = -1;
        int updateSize = -1;
        int updateNotSize = -1;
        //获取权限下的所有资源关联数据
        List<ResourceEntity> resourcesOfAuth = resourceDao.getResourceByAuthIdDoNotCareChecked(authId);
        //所有资源关联数据的资源id
        List<Integer> resourceIdList = new ArrayList<>() ; // 权限资源集合
        if(resourcesOfAuth.size()>0 && resourcesOfAuth.get(0)!=null){
            for(ResourceEntity resourceEntity : resourcesOfAuth){
                resourceIdList.add(resourceEntity.getId()) ;
            }
        }
        //新增关联
        List<AuthorityResourceEntity> addRs = new ArrayList<>();
        //修改选中
        List<AuthorityResourceEntity> updateRs = new ArrayList<>();
        //修改取消选中
        List<AuthorityResourceEntity> updateNotRs = new ArrayList<>();
        Date date = new Date();
        if(list != null && list.size() > 0){
            for(ResourceTreeEntity entity : list){
                if(resourceIdList == null || resourceIdList.size() == 0 || !resourceIdList.contains(entity.getId())){//id不在resourceList中的就是要新增的
                    AuthorityResourceEntity authorityResourceEntity = new AuthorityResourceEntity() ;
                    authorityResourceEntity.setAuthorityId(authId);
                    authorityResourceEntity.setResourceId(entity.getId());
                    authorityResourceEntity.setCreateDate(date);
                    authorityResourceEntity.setIschecked(AuthorityResourceEntity.ISCHECKEDENUM.checked.getValue());
                    addRs.add(authorityResourceEntity);

                }else if(resourceIdList != null && resourceIdList.contains(entity.getId())){//id在resourceList中的就是要修改选中的
                    AuthorityResourceEntity authorityResourceEntity = new AuthorityResourceEntity() ;
                    authorityResourceEntity.setAuthorityId(authId);
                    authorityResourceEntity.setResourceId(entity.getId());
                    authorityResourceEntity.setCreateDate(date);
                    authorityResourceEntity.setIschecked(AuthorityResourceEntity.ISCHECKEDENUM.checked.getValue());
                    updateRs.add(authorityResourceEntity);
                    //删除修改选中的
                    resourceIdList.remove(entity.getId());
                }
            }
        }
        //resourceIdList 里面剩下的就是都要取消选中的
        if(resourceIdList != null && resourceIdList.size() > 0){
            for(Integer resource : resourceIdList){
                AuthorityResourceEntity authorityResourceEntity = new AuthorityResourceEntity() ;
                authorityResourceEntity.setAuthorityId(authId);
                authorityResourceEntity.setResourceId(resource);
                authorityResourceEntity.setCreateDate(date);
                authorityResourceEntity.setIschecked(AuthorityResourceEntity.ISCHECKEDENUM.not_checked.getValue());
                updateNotRs.add(authorityResourceEntity);
            }
        }
        if(addRs.size() > 0){//新增
            addSize = authorityDao.giveResourcesToAuth(addRs);
        }
        if(updateRs.size() > 0){//选中
            updateSize = authorityDao.updateAuthoritiesResourcesChecked(updateRs);
        }
        if(updateNotRs.size() > 0){//取消选中
            updateNotSize = authorityDao.updateAuthoritiesResourcesNotChecked(updateNotRs);
        }

        return  updateSize>0 || addSize>0 || updateNotSize > 0;
    }

    @Override
    public List<Integer> queryResourceId(List<Integer> list) {
        return authorityDao.queryResourceId(list);
    }
}
