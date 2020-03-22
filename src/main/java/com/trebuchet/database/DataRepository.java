package com.trebuchet.database;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<MyStromTable,Integer> {

   List<MyStromTable> findAllByDeviceName(String name);

}
