package ru.javawebinar.topjava.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {
    private static Logger LOG = LoggerFactory.getLogger(JdbcUserMealRepositoryImpl.class);

    private UserMealRowMapper ROW_MAPPER = new UserMealRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insert;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
    }


        @Override
    public UserMeal save(UserMeal userMeal, int userId) {
            MapSqlParameterSource map = new MapSqlParameterSource()
                    .addValue("id", userMeal.getId())
                    .addValue("datetime", Timestamp.valueOf(userMeal.getDateTime()))
                    .addValue("description", userMeal.getDescription())
                    .addValue("calories", userMeal.getCalories());
            if (userMeal.isNew()) {
                Number id = insert.executeAndReturnKey(map);
                userMeal.setId(id.intValue());
                LOG.debug("Saved new meal with id {}",userMeal.getId());
            } else {
                namedParameterJdbcTemplate.update("update meals SET datetime=:datetime, description=:description, calories=:calories WHERE id=:id" ,map);
                LOG.debug("Update meal with id {}",userMeal.getId());
            }
            return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.debug("Delete meal with id {}",id);
        return  jdbcTemplate.update("DELETE FROM meals WHERE id=?", id) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> users = jdbcTemplate.query("SELECT * FROM meals WHERE id=?", ROW_MAPPER, id);
        LOG.debug("Get meal with id {}",id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        List<UserMeal> userMeals = new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM meals order by datetime DESC");
        for (Map<String,Object> row: maps) {
            int id = (int)row.get("id");
            Timestamp tmp = (Timestamp)row.get("dateTime");
            LocalDateTime dateTime = tmp.toLocalDateTime();
            String desc = (String) row.get("description");
            int calories = (int)row.get("calories");
            userMeals.add(new UserMeal(id, dateTime, desc, calories));
        }
        LOG.debug("Get all meals for user with id {}",userId);
        return userMeals;
    }


    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<UserMeal> filteredMealList = getAll(userId);
        filteredMealList.removeIf(userMeal -> !TimeUtil.isBetween(userMeal.getDateTime(),startDate,endDate));
        LOG.debug("Get all meals for user with id {}",userId);
        return filteredMealList;

//        Objects.requireNonNull(startDate);
//        Objects.requireNonNull(endDate);
//        return getAll(userId).stream()
//                .filter(um -> TimeUtil.isBetween(um.getDateTime(), startDate, endDate))
//                .collect(Collectors.toList());
    }

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
}
