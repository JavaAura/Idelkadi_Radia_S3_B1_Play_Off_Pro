package org.spring.dao.daoImpl;

import org.spring.dao.TournoiDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class TournoiDaoImpl implements TournoiDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long calculerdureeEstimeeTournoi(Long tournoiId) {
        // Logique pour calculer la durée estimée
        return null; // Remplacez par votre logique
    }
}
