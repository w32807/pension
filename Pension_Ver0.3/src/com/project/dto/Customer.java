package com.project.dto;

public class Customer {
	private String custom_Name;			// 예약자
	private int custom_TotalPeople;		// 예약 인원 수 
	private String custom_Phone;		// 예약자 전화번호
	private int custom_RoomNumber;	// 예약한 방 번호
	private String custom_RoomName;
	private Room reservedRoom;
	
	
	public String getCustom_RoomName() {
		return custom_RoomName;
	}
	public void setCustom_RoomName(String custom_RoomName) {
		this.custom_RoomName = custom_RoomName;
	}
	public String getCustom_Name() {
		return custom_Name;
	}
	public void setCustom_Name(String custom_Name) {
		this.custom_Name = custom_Name;
	}
	public int getCustom_TotalPeople() {
		return custom_TotalPeople;
	}
	public void setCustom_TotalPeople(int custom_TotalPeople) {
		this.custom_TotalPeople = custom_TotalPeople;
	}
	public String getCustom_Phone() {
		return custom_Phone;
	}
	public void setCustom_Phone(String custom_Phone) {
		this.custom_Phone = custom_Phone;
	}
	public int getCustom_RoomNumber() {
		return custom_RoomNumber;
	}
	public void setCustom_RoomNumber(int custom_RoomNumber) {
		this.custom_RoomNumber = custom_RoomNumber;
	}
	public Room getReservedRoom() {
		return reservedRoom;
	}
	public void setReservedRoom(Room reservedRoom) {
		this.reservedRoom = reservedRoom;
	}
	@Override
	public String toString() {
		String str = " "+ "예약자 이름 : " + custom_Name+"\n"
				+ "예약 인원 수 : " + custom_TotalPeople+"\n"
				+ "예약자 전화번호 : " + custom_Phone+"\n"
				+ "예약한 방 번호 : " + custom_RoomNumber;

		return str;
	}


}
