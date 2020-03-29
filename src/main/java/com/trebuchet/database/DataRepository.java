package com.trebuchet.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;



public interface DataRepository extends JpaRepository<MyStromTable,Integer> {

   List<MyStromTable> findAllByDeviceName(String name);

   @Query("FROM MyStromTable WHERE deviceName= ?1 and timestamp > ?2")
   List<MyStromTable> findByDeviceNameAndTimestamp(String deviceName, Timestamp timestamp);
}
