package com.packt.volunteerhoursrecorder.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HourRepository extends CrudRepository<Hours, Long> {
	List<Hours> findbyDate(@Param("date") Date date);

	List<Hours> findbyUser(@Param("user") User user);
}
