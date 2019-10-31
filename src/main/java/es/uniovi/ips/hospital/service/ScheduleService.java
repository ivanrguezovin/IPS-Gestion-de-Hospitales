package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Schedule;
import es.uniovi.ips.hospital.repository.ScheduleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ScheduleService")
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return scheduleRepository.findByStartTimeAndEmployee(schedule.getStartTime(), schedule.getEmployee());
    }
    
    public Schedule updateSchedule(Schedule schedule) {
    	Schedule dbSchedule = scheduleRepository.findByStartTimeAndEmployee(schedule.getStartTime(), schedule.getEmployee());
		if (dbSchedule == null)
			createSchedule(schedule);
		else {
			dbSchedule.setEndTime(schedule.getEndTime());
			scheduleRepository.save(dbSchedule);
		}
		return dbSchedule;
    }

	public void updateSchedules(List<Schedule> schedules) {
		schedules.forEach(x -> updateSchedule(x));
	}
	
}
