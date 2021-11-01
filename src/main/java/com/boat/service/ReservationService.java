package com.boat.service;

import com.boat.model.Reservation;
import com.boat.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return reservationRepository.getReservation(reservationId);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> e = reservationRepository.getReservation(reservation.getIdReservation());
            if (e.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }
    
    public boolean deleteReservation(int id){
        Optional<Reservation> miReserva = reservationRepository.getReservation(id);
        
        if (miReserva.isEmpty()){
            return false;
        }else{
            reservationRepository.delete(miReserva.get());
            return true;
        }
    }
    
    public Reservation updateReservation(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reserva = reservationRepository.getReservation(reservation.getIdReservation());

            if (!reserva.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    reserva.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate()!= null) {
                    reserva.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus()!= null) {
                    reserva.get().setStatus(reservation.getStatus());
                }
                

                return reservationRepository.save(reserva.get());
            } else {
                return reservation;
            }
        }
        return reservation;
    }
}
