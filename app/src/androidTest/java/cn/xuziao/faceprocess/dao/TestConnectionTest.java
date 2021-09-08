package cn.xuziao.faceprocess.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.SQLException;

public class TestConnectionTest {

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        new TestConnection().testConnection();
    }

}