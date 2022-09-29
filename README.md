# Jmix Demo: MySQL Binary16 UUID

This project demonstrates usage of `binary(16)` column for storing UUID values (primary and foreign keys) on MySQL.

[DemoMysqlDbmsFeatures](src/main/java/com/company/demo/dbms/DemoMysqlDbmsFeatures.java) bean defines the `eclipselink.target-database` property pointing to the `DemoMySQLPlatform` class. This bean is used for MySQL databases because of the value returned from `getTypeAndVersion()` method. 

[DemoMySQLPlatform](src/main/java/com/company/demo/dbms/DemoMySQLPlatform.java) defines parameters of working with UUIDs for MySQL databases.

[application.properties](src/main/resources/application.properties) file has `jmix.liquibase.parameters.uuid.type = binary(16)` property to define the column type for UUID attributes. 

All Liquibase changelogs must use `${uuid.type}` as a type of primary and foreign key columns, for example:

```xml
<changeSet id="1" author="demo">
    <createTable tableName="FOO">
        <column name="ID" type="${uuid.type}">
            <constraints nullable="false" primaryKey="true" primaryKeyName="PK_FOO"/>
        </column>
        <column name="VERSION" type="INT">
            <constraints nullable="false"/>
        </column>
        <column name="NAME" type="VARCHAR(255)"/>
        <column name="USER_ID" type="${uuid.type}"/>
    </createTable>
</changeSet>
```

To insert a UUID, `uuid_to_bin()` function is used: 

```xml
<insert tableName="USER_" dbms="mysql">
    <column name="ID" valueComputed="uuid_to_bin('60885987-1b61-4247-94c7-dff348347f93')"/>
    <column name="VERSION" value="1"/>
    <column name="USERNAME" value="admin"/>
    <column name="PASSWORD" value="{noop}admin"/>
    <column name="ACTIVE" valueBoolean="true"/>
</insert>
```