package com.project;


import com.project.controller.AdminController;
import com.project.controller.CustomerController;		
import com.project.controller.RoomController;
import com.project.controller.SearchController;
import com.project.dao.ProjectDao;
import com.project.service.RoomService;
import com.project.view.Viewer;

public class ProjectMain {
	private static CustomerController cc = new CustomerController();
	private static RoomController rc = new RoomController();
	private static SearchController sc = new SearchController();
	public static ProjectDao db;
	public static RoomService rs = new RoomService();
	public static AdminController ac = new AdminController();
	
   public static void main(String[] args) {
	   
	  ProjectMain.db = new ProjectDao();
	  ProjectMain.db.getConnection();
	   
	  rs.loadDBData();
	  
	  
      Viewer mv = Viewer.getInstance();
      
      while (true) {
         
    	  Viewer.getInstance().printMainMenu();
    	 int sel =  Viewer.getInstance(). input_Price();
    	  
         if(sel == 0) {
            Viewer.getInstance().print_mesg(1);
            break;
         }
         switch (sel) {
         case 1:// 손님
            customerMenu();

            break;

         case 2:// 관리자
        	 ac.AdminMain();
            break;

         default:
            break;
         }
      }

   }

	//---------------------------------------------
	// v0.1
	// 작업자 : 정성규
	// 내  용 : 손님 / 관리자 모드 분기
	//---------------------------------------------
   public static void customerMenu() {
	 
	   while(true) {
		   Viewer.getInstance().printCustomerMenu();
		   int sel =  Viewer.getInstance().input_Price();

		   if(sel == 0) {
			   Viewer.getInstance().print_mesg(2);
			   break;
		   }
		   
		   switch(sel) {
		   case 1: 	// 방 보기
			   rc.roomMain();
			   break;
			   
		   case 2:	// 예약하기
			   cc.customerMain();
			   break;
			   
		   }
	   }
   }
   
}