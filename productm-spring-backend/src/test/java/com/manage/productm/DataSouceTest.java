package com.manage.productm;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@Log4j2
public class DataSouceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    public void DataSourceTest() throws SQLException{
        @Cleanup
        Connection con = dataSource.getConnection();

        Assertions.assertNotNull(con);
        log.info("데이터베이스 연결 성공");

    }
}
