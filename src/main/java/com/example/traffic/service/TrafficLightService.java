
package com.example.traffic.service;

import com.example.traffic.model.*;
import com.example.traffic.repository.TrafficLightRepository;
import com.example.traffic.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TrafficLightService {

 private final TrafficLightRepository repo = new TrafficLightRepository();
 private final Map<String,ReentrantLock> locks = new ConcurrentHashMap<>();
 private final Map<String,Boolean> paused = new ConcurrentHashMap<>();

 public void createIntersection(String id){
   repo.createIntersection(id);
   locks.putIfAbsent(id,new ReentrantLock());
   paused.putIfAbsent(id,false);
 }

 public void changeLight(String id, Direction dir, LightColor color){
   validate(id);
   ReentrantLock lock = locks.get(id);
   lock.lock();
   try{
     if(paused.get(id)) throw new TrafficSystemPausedException("Paused");
     if(color==LightColor.GREEN){
       for(Map.Entry<Direction,TrafficLightState> e: repo.getState(id).entrySet()){
         if(!e.getKey().equals(dir) && e.getValue().getColor()==LightColor.GREEN)
           throw new ConflictingGreenLightException("Conflicting GREEN");
       }
     }
     repo.updateState(id,dir,color);
   }finally{ lock.unlock(); }
 }

 public void pause(String id){ validate(id); paused.put(id,true); }
 public void resume(String id){ validate(id); paused.put(id,false); }
 public Map<Direction,TrafficLightState> getState(String id){ validate(id); return repo.getState(id); }
 public List<TrafficLightState> getHistory(String id){ validate(id); return repo.getHistory(id); }

 private void validate(String id){
   if(!repo.exists(id)) throw new IntersectionNotFoundException("Not found "+id);
 }
}
