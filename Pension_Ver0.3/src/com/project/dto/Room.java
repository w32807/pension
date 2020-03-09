package com.project.dto;

public class Room {
	
	private int room_Price;			// 방 가격
	private int room_Num;			// 방 호실
	private String room_Name;		// 방 이름
	private int	room_Bed;			// 침대 갯수
	private int room_Capacity;		// 최대 수용 인원
	private int room_Floor;			// 층
	private boolean room_Resv;		// 예약 여부
	
	
	
	public int getRoom_Price() {
		return room_Price;
	}
	public void setRoom_Price(int room_Price) {
		this.room_Price = room_Price;
	}
	public int getRoom_Num() {
		return room_Num;
	}
	public void setRoom_Num(int room_Num) {
		this.room_Num = room_Num;
	}
	public String getRoom_Name() {
		return room_Name;
	}
	public void setRoom_Name(String room_Name) {
		this.room_Name = room_Name;
	}
	public int getRoom_Bed() {
		return room_Bed;
	}
	public void setRoom_Bed(int room_Bed) {
		this.room_Bed = room_Bed;
	}
	public int getRoom_Capacity() {
		return room_Capacity;
	}
	public void setRoom_Capacity(int room_Capacity) {
		this.room_Capacity = room_Capacity;
	}
	public int getRoom_Floor() {
		return room_Floor;
	}
	public void setRoom_Floor(int room_Floor) {
		this.room_Floor = room_Floor;
	}
	public boolean isRoom_Resv() {
		return room_Resv;
	}
	public void setRoom_Resv(boolean room_Resv) {
		this.room_Resv = room_Resv;
	}

	@Override
	public String toString() {
		String str1 = " "+ "방 이름 : " + room_Name +"\n"
				+"방 호실 : " + room_Num +"\n"
				+"방 가격 : " + room_Price+"\n"
				+"침대 갯수 : " + room_Bed+"\n"
				+"최대 수용 인원" + room_Capacity+"\n"
				+"층 위치 : " + room_Floor+"\n"
				+"예약 여부" + room_Resv;
		return str1;
	}
}
