
package com.example.traffic.repository;

import com.example.traffic.model.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TrafficLightRepository {

 private final Map<String, Map<Direction, TrafficLightState>> states = new ConcurrentHashMap<>();
 private final Map<String, List<TrafficLightState>> history = new ConcurrentHashMap<>();

 public void createIntersection(String id){
   states.putIfAbsent(id,new ConcurrentHashMap<>());
   history.putIfAbsent(id,Collections.synchronizedList(new ArrayList<>()));
   states.get(id).put(Direction.NORTH_SOUTH,new TrafficLightState(Direction.NORTH_SOUTH,LightColor.RED));
   states.get(id).put(Direction.EAST_WEST,new TrafficLightState(Direction.EAST_WEST,LightColor.RED));
 }

 public boolean exists(String id){ return states.containsKey(id); }
 public Map<Direction,TrafficLightState> getState(String id){ return states.get(id); }
 public List<TrafficLightState> getHistory(String id){ return history.get(id); }

 public void updateState(String id, Direction dir, LightColor color){
   states.get(id).get(dir).setColor(color);
   history.get(id).add(new TrafficLightState(dir,color));
 }
}
