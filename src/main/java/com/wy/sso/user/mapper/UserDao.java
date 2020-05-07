package com.wy.sso.user.mapper;

import com.wy.sso.user.domain.RoleInfo;
import com.wy.sso.user.domain.UserInfo;

import java.util.List;

/**
 * @author wangyu
 * @title: UserDao
 * @projectName WyUpms
 * @description: TODO
 * @date 2020/4/20 18:48
 */
public interface UserDao {

    int insertUser(UserInfo userInfo);

    UserInfo selectUserByName(String userName);

    List<UserInfo> selectAllUser();

    int deleteUserById(String userId);

    int updateUser(UserInfo userInfo);

    List<RoleInfo> selectUserRoles(Integer flowId);
}
