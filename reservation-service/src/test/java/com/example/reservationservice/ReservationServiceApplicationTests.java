package com.example.reservationservice;

import com.example.reservationservice.dto.Room;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ReservationServiceApplicationTests {

	@Test
	public void contextLoads() {
		Room room = new Room();
		room.setId(19216801);
//		room.setDescription("Test Room Desc");
		room.setMaximumPersonnel(12);
		room.setMinimumPersonnel(8);
		room.setName("16");
		room.setOptimalPersonnel(10);
		room.setStatus(Room.Status.UNAVAILABLE.getCode());
		
		
		System.out.println("===============================================");
		System.out.println(ToStringBuilder.reflectionToString(room, ToStringStyle.DEFAULT_STYLE));
		System.out.println("===============================================");
		System.out.println(ToStringBuilder.reflectionToString(room, ToStringStyle.JSON_STYLE));
		System.out.println("===============================================");
		System.out.println(ToStringBuilder.reflectionToString(room, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println("===============================================");
		System.out.println(ToStringBuilder.reflectionToString(room, ToStringStyle.SIMPLE_STYLE));
		System.out.println("===============================================");
		System.out.println(ToStringBuilder.reflectionToString(room, ToStringStyle.NO_CLASS_NAME_STYLE));
		System.out.println("===============================================");
		System.out.println(ToStringBuilder.reflectionToString(room, ToStringStyle.SHORT_PREFIX_STYLE));
		System.out.println("===============================================");
		
		
	}

}
