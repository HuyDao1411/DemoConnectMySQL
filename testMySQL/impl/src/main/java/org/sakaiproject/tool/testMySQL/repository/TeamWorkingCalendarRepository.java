package org.sakaiproject.tool.testMySQL.repository;

import org.sakaiproject.tool.testMySQL.entity.TeamWorkingCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamWorkingCalendarRepository extends JpaRepository<TeamWorkingCalendar, Long> {
	
}
