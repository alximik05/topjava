package ru.javawebinar.topjava.util;

import org.springframework.jdbc.core.RowMapper;
import ru.javawebinar.topjava.model.UserMeal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alximik on 27/12/15.
 */
public class UserMealRowMapper implements RowMapper {
    @Override
    public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        LocalDateTime dateTime = rs.getTimestamp("dateTime").toLocalDateTime();
        String desc = rs.getString("description");
        int calories = rs.getInt("calories");
        return new UserMeal(id, dateTime, desc, calories);
        }
}

