package com.ouc.mallproduct;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Reader;

@Transactional
@Rollback
public class BaseMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    @BeforeClass
    public static void init()
    {
        try
        {
            Reader reader= Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public SqlSession getSqlSession()
    {
        return sqlSessionFactory.openSession();
    }
}
