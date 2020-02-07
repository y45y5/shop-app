package com.github.shop_app.dao;

import com.github.shop_app.utility.UserSession;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class DAO<T> {

    protected void deleteObject(T t){
        Transaction transaction = UserSession.session.beginTransaction();
        UserSession.session.delete(t);
        transaction.commit();
    }

    protected void saveObject(T t){
        Transaction transaction = UserSession.session.beginTransaction();
        UserSession.session.save(t);
        transaction.commit();
    }

    protected List<T> getObjectList(Class<T> type){
        CriteriaBuilder builder = UserSession.session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return UserSession.session.createQuery(criteria).getResultList();
    }

    protected void updateObject(T t){
        Transaction transaction = UserSession.session.beginTransaction();
        UserSession.session.update(t);
        transaction.commit();
    }

    protected void mergeObject(T t){
        Transaction transaction = UserSession.session.beginTransaction();
        UserSession.session.merge(t);
        transaction.commit();
    }
}
