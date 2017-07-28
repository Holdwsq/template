package com.xdja.template.system.dao;

import java.util.List;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.Result;
import org.jfaster.mango.annotation.Results;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.plugin.page.Page;

import com.xdja.template.system.bean.User;
import com.xdja.template.system.bean.UserRole;

@DB(
        table = "t_sys_user"
)
@Results({
        @Result(column = "n_id", property = "id"),
        @Result(column = "c_account", property = "account"),
        @Result(column = "c_pwd", property = "pwd"),
        @Result(column = "c_name", property = "name"),
        @Result(column = "n_create_time", property = "createTime"),
        @Result(column = "n_status", property = "status"),
        @Result(column = "n_type", property = "type")
})
public interface UserDao {
    String COLUMNS = "n_id, c_account, c_pwd, c_name, n_create_time, n_status, n_type";

    @ReturnGeneratedId
    @SQL("insert into #table(" + COLUMNS + ") values(:id, :account, :pwd, :name, :createTime, :status, :type)")
    int addUser(User user);

    @SQL("select " + COLUMNS + " from #table where n_id = :1")
    User getUser(int id);

//    @SQL("update #table set c_account=:account, c_pwd=:pwd, c_name=:name, n_create_time=:createTime, n_status=:status, n_type=:type where n_id = :id")
//    boolean updateUser(User user);

//    @SQL("delete from #table where n_id = :1")
//    boolean deleteUser(int id);

    @SQL("select " + COLUMNS + " from #table where c_account = :1 and c_pwd = :2")
	User queryByAccountAndPwd(String account, String password);
    
    @SQL("update #table set c_pwd=:2 where n_id = :1")
    void changePwd(int id, String password);
    
    @SQL("select n_id, c_account, c_name, n_create_time, n_status, n_type from #table")
    List<User> query(Page page);
    
    @SQL("update #table set c_pwd = :2 where n_id = :1")
    boolean updatePwd(int id, String pwd);
    
    @SQL("update #table set n_status = :2 where n_id = :1")
    boolean updateStatus(int id, int status);
    
    @SQL("select count(1) from #table where c_account = :1")
    int accountExist(String account);
    
    @SQL("insert into t_sys_user_role(n_user_id, n_role_id) values(:1.userId, :1.roleId)")
    void saveUserRole(List<UserRole> list);
    
    @SQL("select n_role_id from t_sys_user_role where n_user_id = :1")
    List<Integer> queryUserRoles(int userId);
    
    @SQL("update #table set c_name=:name where n_id = :id")
    boolean updateUser(User user);
    
    @SQL("delete from t_sys_user_role where n_user_id = :1")
    void clearUserRoles(int userId);
}