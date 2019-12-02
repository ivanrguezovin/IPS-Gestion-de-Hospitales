package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.Schedule;
import es.uniovi.ips.hospital.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ScheduleService")
public class ScheduleService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule createSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        log.info("the schedule for the user " + schedule.getEmployee().getEmail() + " has been created");
        return scheduleRepository.findByStartTimeAndEmployee(schedule.getStartTime(), schedule.getEmployee());
    }

    public Schedule updateSchedule(Schedule schedule) {
        Schedule dbSchedule = scheduleRepository.findByStartTimeAndEmployee(schedule.getStartTime(), schedule.getEmployee());
        if (dbSchedule == null) {
            createSchedule(schedule);
        } else {
            dbSchedule.setEndTime(schedule.getEndTime());
            scheduleRepository.save(dbSchedule);
        }
		log.info("the schedule for the user " + schedule.getEmployee().getEmail() + " has been updated");
        return dbSchedule;
    }

    public void updateSchedules(List<Schedule> schedules) {
        schedules.forEach(x -> updateSchedule(x));
    }

    public void updateBreakSchedule(Schedule break_) {
        Schedule left = scheduleRepository.findMatchingSchedule(break_.getStartTime(), break_.getEmployee());
        Schedule right = scheduleRepository.findMatchingSchedule(break_.getEndTime(), break_.getEmployee());
        if (left == null && right == null)                                                        // No schedule clashes with this break
            return;
        if (left.equals(right)) {                                                                // One schedule wraps the whole break, divide it
            createSchedule(new Schedule(break_.getEndTime(), right.getEndTime(), break_.getEmployee()));
            left.setEndTime(break_.getStartTime());
            scheduleRepository.save(left);
            return;
        }
        if (left != null) {                                                                        // One schedule clashes with the break on the extreme, modify it
            left.setEndTime(break_.getStartTime());
            scheduleRepository.save(left);
        }
        if (right != null) {                                                                    // One schedule clashes with the break on the extreme, modify it
            right.setStartTime(break_.getEndTime());
            scheduleRepository.save(right);
        }

    }

    public void updateBreakSchedules(List<Schedule> breaks) {
        breaks.forEach(x -> updateBreakSchedule(x));
    }

}
