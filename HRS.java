import java.util.*;
class Room {
    private int id;
    private String type;
    private boolean isAvailable;

    public Room(int id, String type) {
        this.id = id;
        this.type = type;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
	
	public String toString() {
		return String.format("%-10d %-15s %-15s", id, type, isAvailable ? "Available" : "Booked");
	}

    
}

class Booking {
    private String customerName;
    private int roomId;

    public Booking(String customerName, int roomId) {
        this.customerName = customerName;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Booking{" + "Customer='" + customerName + '\'' + ", Room ID=" + roomId + '}';
    }
}



class RoomService {
    private List<Room> rooms;

	public RoomService() {
		rooms = new ArrayList<>();
		String[] roomTypes = {"Single", "Double", "Suite"};
		
		for (int i = 0; i < 20; i++) {
			int roomId = i + 1;
			String roomType = roomTypes[i % roomTypes.length]; // Cycle through Single, Double, Suite
			rooms.add(new Room(roomId, roomType));
		}
	}


    public List<Room> getAvailableRooms(String type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType().equalsIgnoreCase(type)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean markRoomAsBooked(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId && room.isAvailable()) {
                room.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    public List<Room> getAllRooms() {
        return rooms;
    }
	
	public boolean isRoomAvailable(int roomId) {
		for (Room room : rooms) {
			if (room.getId() == roomId) {
				return room.isAvailable();  
			}
		}
		return false; 
	}

	
}


class BookingService{
	private List<Booking>bookings;
	public BookingService(){
		bookings=new ArrayList<>();
	}
	
	public void addBooking(String CustomerName,int roomId){
		bookings.add(new Booking(CustomerName,roomId));
	}
	public  List<Booking>getAllBookings(){
		return bookings;
	}
}
 public class HRS{
	public static void main(String arg[]){
		RoomService roomService=new RoomService();
		BookingService bookingService=new BookingService();
		Scanner ob=new Scanner(System.in);
		
		while(true){
			System.out.println("\nHotel Reservation System");
			System.out.println("1.View All Rooms:");
			System.out.println("2.Search Available Rooms");
			System.out.println("3.Book a Room");
			System.out.println("4.Veiw All Bookings");
			System.out.println("5.Exit");
			System.out.print("Enter your Choice:");
			
			int choice=ob.nextInt();
			switch(choice){
				case 1:
				List<Room>allRooms=roomService.getAllRooms();
				for(Room room:allRooms){
					System.out.println(room);
				}
				break;
				
				case 2:
				System.out.print("Enter room type(Single/Double/Suite):");
				String type=ob.next();
				List<Room>availableRooms=roomService.getAvailableRooms(type);
				if(availableRooms.isEmpty()){
					System.out.println("No Rooms available of this type.");
				}
				else{
					System.out.println("Available Rooms");
					for(Room room:availableRooms){
						System.out.println(room);
					}
				}
				
				break;
				
				case 3:
				System.out.print("Enter Room ID  to book:");
				int roomId=ob.nextInt();
				System.out.print("Enter your Name:");
				String CustomerName=ob.next();
				boolean booked=roomService.markRoomAsBooked(roomId);
				
				if(booked){
					bookingService.addBooking(CustomerName,roomId);
					System.out.println("Room Booked Successfully");
				}
				else if(!roomService.isRoomAvailable(roomId)){
					System.out.println("Room is Already Booked.Please Choose another Room");
					
				}
				
				else{
					System.out.println("Invaild RoomID");
				}
				
				break;
				
				case 4:
				List<Booking>allBookings=bookingService.getAllBookings();
				if(allBookings.isEmpty()){
					System.out.println("No bookings Found");
				}
				
				else{
				
					for(Booking booking:allBookings){
						System.out.println(booking);
					}
				}
				
				break;
				
				case 5:
				System.out.println("Thank you Using Online Hotel Reservation System");
				ob.close();
				return;
				
				default:
				System.out.println("Invaild choice , Try Again");
				
			}
			
		}
	}
 
 }




