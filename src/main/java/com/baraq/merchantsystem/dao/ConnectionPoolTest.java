package com.baraq.merchantsystem.dao;

import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;

public class ConnectionPoolTest {
    private static ConnectionPool instance;
    private static int maxIdleConnection = 5;
    private static int keepAlive = 5;
    private static TimeUnit timeUnit = TimeUnit.MINUTES;

    private ConnectionPoolTest() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool(maxIdleConnection, keepAlive, timeUnit);
        }
        return instance;
    }
}
