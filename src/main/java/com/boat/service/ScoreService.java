package com.boat.service;

import com.boat.model.Score;
import com.boat.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int scoreId) {
        return scoreRepository.getScore(scoreId);
    }

    public Score save(Score score) {
        if (score.getStars() >= 0 && score.getStars() <= 5) {
            if (score.getIdScore() == null) {
                return scoreRepository.save(score);
            } else {
                Optional<Score> e = scoreRepository.getScore(score.getIdScore());
                if (e.isEmpty()) {
                    return scoreRepository.save(score);
                }
            }

        }
        return score;
    }

    public Score update(Score score) {
        if (score.getIdScore() != null) {
            Optional<Score> puntaje = scoreRepository.getScore(score.getIdScore());
            if (!puntaje.isEmpty()) {
                if (score.getMessageText() != null) {
                    puntaje.get().setMessageText(score.getMessageText());
                }
                if (score.getStars() != null && score.getStars() >= 0 && score.getStars() <= 5) {
                    puntaje.get().setStars(score.getStars());
                }
                scoreRepository.save(puntaje.get());
                return puntaje.get();
            } else {
                return score;
            }
        } else {
            return score;
        }
    }
}
