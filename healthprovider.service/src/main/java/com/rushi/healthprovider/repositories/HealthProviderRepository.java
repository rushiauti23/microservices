package com.rushi.healthprovider.repositories;

import com.rushi.healthprovider.models.HealthProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthProviderRepository extends MongoRepository<HealthProvider, String> {

    @Query(value = "{'hpDepartment': ?0, $or: [ {'isDailyAvailable': true}, { 'daysAvailable' :?1, 'isDailyAvailable': false } ] }",
            collation = "{ 'locale': 'en' }")
    List<HealthProvider> findProvidersByDateAndDepartment(String department, Long daysOfWeekValue);

}
