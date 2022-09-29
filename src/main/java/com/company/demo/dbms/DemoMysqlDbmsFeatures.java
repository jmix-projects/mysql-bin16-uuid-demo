package com.company.demo.dbms;

import io.jmix.eclipselink.impl.dbms.MysqlDbmsFeatures;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DemoMysqlDbmsFeatures extends MysqlDbmsFeatures {

    @Override
    public Map<String, String> getJpaParameters() {
        HashMap<String, String> params = new HashMap<>();
        params.put("eclipselink.target-database", "com.company.demo.dbms.DemoMySQLPlatform");
        return params;
    }

    @Override
    public String getTypeAndVersion() {
        return "mysql";
    }
}
