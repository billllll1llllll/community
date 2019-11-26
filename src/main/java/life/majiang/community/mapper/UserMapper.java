package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("SELECT * FROM user WHERE TOKEN = #{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("UPDATE USER SET name = #{name} , token = #{token} , gmt_modified = #{gmtModified} , avatar_url = #{avatarUrl} WHERE account_id = #{accountId}")
    void update(User user);
}
