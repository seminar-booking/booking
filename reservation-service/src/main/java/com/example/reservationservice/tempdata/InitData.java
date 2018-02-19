package com.example.reservationservice.tempdata;

import com.example.reservationservice.dto.Reservation;
import com.example.reservationservice.dto.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitData {

    private List<Room> roomList = new ArrayList<>();
    private List<Reservation> reservationList = new ArrayList<>();

    public InitData(){
        this.createRooms();
    }

    public void createRooms(){
        for(int i = 2; i < 6; i++){
            Random randomGenerator = new Random();


            Room room = new Room();
            room.setId(i);
            room.setName(String.valueOf(i));
            room.setDescription(String.valueOf(i) + "번째 방");
            room.setMinimumPersonnel(i);
            room.setMaximumPersonnel(i + 2);
            room.setOptimalPersonnel((i + 2) / 2);
            room.setStatus(Room.Status.AVAILABLE);

            roomList.add(room);
        }
    }

    public List<Room> getRoomList(){
        return this.roomList;
    }

    public void setReservation(Reservation reservation){
        this.reservationList.add(reservation);
    }

    public List<Reservation> getReservationList(){
        return this.reservationList;
    }

}
