package com.book.manager.dao.security;

import com.book.manager.domain.security.UserEntity;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 
 * TODO(用户管理).
 * @ClassName: IUserDAO
 * @author Johnny_L_Q
 */
public interface UserDao {
    

    /**
     * 
     * TODO(根据登录用户名获取用户实体).
     * @param userAccount 登录用户名
     * @return 用户实体
     */
	UserEntity getUserByUserAccount(String userAccount);

    /**
     *
     * TODO(根据用户Id获取用户实体).
     * @param id 用户Id
     * @return 用户实体
     */
	UserEntity getById(Integer id);

	/**
	 * 查询用户集合
	 * @param map
	 * @return
	 */
//	List<UserEntity> findUsers(Map<String,Object> map);

	/**
	 * 查询用户集合
	 * @param userEntity
	 * @return
	 */
	List<UserEntity> selectByPage(UserEntity userEntity, RowBounds rowBounds);

	/**
	 * 查询用户记录数
	 * @param userEntity
	 * @return
	 */
	int countByPage(UserEntity userEntity);

	/**
	 * 查询用户记录数
	 * @param userEntity
	 * @return
	 */
	Integer insert(UserEntity userEntity);

	/**
	 * 查询用户记录数
	 * @param userEntity
	 * @return
	 */
	int updateByPrimaryKeySelective(UserEntity userEntity);

	/**
	 * 删除用户记录数
	 * @param userEntity
	 * @return
	 */
	int delete(UserEntity userEntity);

	public int updatePassword(UserEntity userEntity) ;

	public UserEntity findByUserName(String username) ;

}
