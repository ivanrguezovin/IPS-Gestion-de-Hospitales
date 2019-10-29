package es.uniovi.ips.hospital.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "startTime", nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "endTime", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    private Staff employee;
    
    public Schedule() {}

    public Schedule(@NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime, Staff employee) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Staff getEmployee() {
        return employee;
    }

    public void setEmployee(Staff employee) {
        this.employee = employee;
    }

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", employee=" + employee
				+ "]";
	}
    
    
}