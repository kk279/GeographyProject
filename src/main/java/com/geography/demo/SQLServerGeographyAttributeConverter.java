package com.geography.demo;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.microsoft.sqlserver.jdbc.Geography;
import org.springframework.data.geo.Point;

import javax.persistence.AttributeConverter;
import javax.persistence.PersistenceException;

public class SQLServerGeographyAttributeConverter implements AttributeConverter<Point, byte[]> {

    @Override
    public byte[] convertToDatabaseColumn(Point attribute) {
        try {
            Geography geography = Geography.point(attribute.getY(), attribute.getX(), 4326);
            return geography.serialize();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Point convertToEntityAttribute(byte[] dbData) {
        try {
            Geography geography = Geography.deserialize(dbData);
            double latitude = geography.getLatitude();
            double longitude = geography.getLongitude();
            Point point = new Point(longitude, latitude);
            return point;
        } catch (SQLServerException sqle) {
            // Handle as appropriate
            sqle.printStackTrace();
            throw new PersistenceException("An error occurred while converting Geography to Point", sqle);
        }
    }
}
