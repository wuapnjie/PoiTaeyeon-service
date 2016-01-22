package com.flying.xiaopo.dao;

import com.flying.xiaopo.domain.Taeyeon;
import org.eclipse.persistence.internal.jpa.querydef.ExpressionImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import java.util.List;

/**
 * 数据库操作类
 * Created by Flying SnowBean on 2016/1/16.
 */

@Repository
public class TaeyeonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public TaeyeonDao() {
    }

    /**
     * 通过id在数据库中查找数据
     * @param id   id
     * @return  Taeyeon对象
     * @see Taeyeon
     */
    @Transactional
    public Taeyeon findById(final int id) {
        return entityManager.find(Taeyeon.class, id);
    }

    /**
     * 保存Taeyeon对象
     * @param taeyeon   徐保存的bean
     * @return    保存成功的Taeyeon对象
     * @see Taeyeon
     */
    @Transactional
    public Taeyeon save(final Taeyeon taeyeon) {
        return entityManager.merge(taeyeon);
    }

    /**
     * 查找所有对象
     * @return  对象集合
     */
    @Transactional
    public List<Taeyeon> findAll() {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Taeyeon> cq = cb.createQuery(Taeyeon.class);
        final TypedQuery<Taeyeon> q = entityManager.createQuery(cq);
        return q.getResultList();
    }

    /**
     *查找最新的数据
     * @return    最新的Taeyeon对象
     * @see Taeyeon
     */
    @Transactional
    public Taeyeon findLatest() {
        Query query = entityManager.createNativeQuery("SELECT * FROM taeyeon_feed WHERE id=(SELECT MAX(id) FROM taeyeon_feed)",Taeyeon.class);
        return (Taeyeon) query.getSingleResult();
    }
}
