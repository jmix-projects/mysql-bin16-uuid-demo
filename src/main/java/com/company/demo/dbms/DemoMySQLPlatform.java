package com.company.demo.dbms;

import io.jmix.eclipselink.impl.dbms.UuidMappingInfo;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.platform.database.MySQLPlatform;
import org.eclipse.persistence.sessions.Session;

import java.nio.ByteBuffer;
import java.sql.Types;
import java.util.UUID;

public class DemoMySQLPlatform extends MySQLPlatform implements UuidMappingInfo {

    @Override
    public int getUuidSqlType() {
        return Types.BINARY;
    }

    @Override
    public Class<?> getUuidType() {
        return byte[].class;
    }

    @Override
    public String getUuidColumnDefinition() {
        return "binary(16)";
    }

    @Override
    public Converter getUuidConverter() {
        return Bin16UuidConverter.INSTANCE;
    }

    public static class Bin16UuidConverter implements Converter {

        private static Bin16UuidConverter INSTANCE = new Bin16UuidConverter();

        public byte[] uuidToBytes(Object objectValue) {
            if (objectValue == null)
                return null;
            UUID uuid = (UUID) objectValue;
            byte[] buffer = new byte[16];
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            bb.putLong(uuid.getMostSignificantBits());
            bb.putLong(uuid.getLeastSignificantBits());
            return buffer;
        }

        @Override
        public Object convertObjectValueToDataValue(Object objectValue, Session session) {
            return uuidToBytes(objectValue);
        }

        @Override
        public Object convertDataValueToObjectValue(Object dataValue, Session session) {
            if (dataValue == null)
                return null;
            ByteBuffer bb = ByteBuffer.wrap((byte[]) dataValue);
            long high = bb.getLong();
            long low = bb.getLong();
            return new UUID(high, low);
        }

        @Override
        public boolean isMutable() {
            return false;
        }

        @Override
        public void initialize(DatabaseMapping mapping, Session session) {
        }
    }
}
