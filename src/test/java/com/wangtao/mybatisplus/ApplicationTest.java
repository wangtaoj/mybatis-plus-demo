package com.wangtao.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.wangtao.mybatisplus.dao.UserMapper;
import com.wangtao.mybatisplus.po.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangtao
 * Created at 2026-05-11
 */
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void contextLoad() {

    }

    @Test
    public void testSelectListWithoutPage() {
        IPage<User> page = Page.of(1, -1);

        List<User> userList = userMapper.selectList(page, null);
        //List<User> userList = userMapper.selectList((IPage<User>)null, null);
        System.out.println(userList);
    }

    @Test
    public void testUpdateNull() {
        ChainWrappers.lambdaUpdateChain(userMapper)
            .set(User::getAge, null)
            .eq(User::getId, 28)
            .update();
    }

    @Test
    public void testFillCustomMethod() {
        User user = new User();
        user.setId(28);
        user.setAge(30);
        userMapper.updateAgeById(user);

        userMapper.updateAgeByIdWithParamName(user);
    }

    @Transactional
    @Test
    public void testMutiSqlSession() {
        User user = new User();
        user.setId(28);
        user.setAge(30);
        userMapper.updateAgeById(user);

        SqlSession sqlSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        Assertions.assertThrows(
            TransientDataAccessResourceException.class,
            () -> sqlSession.update("com.wangtao.mybatisplus.dao.UserMapper.updateAgeById", user)
        );
    }
}
