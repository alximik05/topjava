package ru.javawebinar.topjava.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {

            User user = userRepository.get(userId);
            userMeal.setUser(user);
            return em.merge(userMeal);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
         Query query = em.createQuery("delete from UserMeal um where um.id=:id and um.user.id=:userId")
                .setParameter("id", id)
                .setParameter("userId", userId);
        return query.executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        Query query = em.createQuery("SELECT um FROM UserMeal um where um.user.id=:userId and um.id=:id");
        query.setParameter("userId", userId)
                .setParameter("id", id);
        return (UserMeal) query.getSingleResult();
    }

    @Override
    public List<UserMeal> getAll(int userId) {
//        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter(1, userId).getResultList();
        return em.createQuery("SELECT um FROM UserMeal um where um.user.id=:userId order by um.dateTime desc").setParameter("userId",userId).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createQuery("SELECT um FROM UserMeal um where um.user.id=:userId and um.dateTime > :startDate and um.dateTime < :endDate order by um.dateTime desc")
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate).getResultList();
    }
}