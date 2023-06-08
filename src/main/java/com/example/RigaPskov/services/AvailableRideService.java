package com.example.RigaPskov.services;

import com.example.RigaPskov.entities.AvailableRide;
import com.example.RigaPskov.repositories.AvailableRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

@Service
public class AvailableRideService {

    private final AvailableRideRepository availableRideRepository;

    @Autowired
    public AvailableRideService(AvailableRideRepository availableRideRepository) {
        this.availableRideRepository = availableRideRepository;
    }

    // Получение всех доступных поездок
    public List<AvailableRide> findAll() {
        return availableRideRepository.findAll();
    }

    // Добавление новой доступной поездки
    public AvailableRide save(AvailableRide availableRide) {
        return availableRideRepository.save(availableRide);
    }

    // Автоматическое добавление доступных поездок на заданный период времени (например, на 2 недели вперёд)
    public void addAutomaticRides(String direction, Time time, int daysInFuture) {
        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < daysInFuture; i++) {
            cal.add(Calendar.DAY_OF_MONTH, 1); // увеличиваем на 1 день
            Date date = new Date(cal.getTimeInMillis()); // конвертируем в java.sql.Date

            AvailableRide ride = new AvailableRide();
            ride.setDate(date);
            ride.setTime(time);
            ride.setDirection(direction);

            availableRideRepository.save(ride);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")  // Запуск ежедневно в полночь
    public void addAutomaticRidesForNextTwoWeeks() {
        addAutomaticRides("Riga-Pskov", Time.valueOf("08:00:00"), 14);
        addAutomaticRides("Pskov-Riga", Time.valueOf("08:00:00"), 14);
    }

}
