package org.spring.services.serviceImpl;

import org.spring.dao.TournoiDao;
import org.spring.services.TournamentService;

public class TournamentServiceImpl  implements TournamentService {
    private TournoiDao tournoiDao;

    public void setTournoiDao(TournoiDao tournoiDao) {
        this.tournoiDao = tournoiDao;
    }

    public Long obtenirdureeEstimeeTournoi(Long tournoiId) {
        return tournoiDao.calculerdureeEstimeeTournoi(tournoiId);
    }
}
