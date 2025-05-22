package eol.items;

import java.util.HashMap;

import eol.components.StatsComponent;

public class Item {
	
    HashMap<String, Integer> statModifiers = new HashMap<>();

	// these are the items main competent 
    private  String Id;
	private  String Name;
	private  String Rairty;

	// these are the items main constructor  

	public Item(String Id, String Name,String Rairty) {
        this.Id = Id;
        this.Name = Name;
        this.Rairty = Rairty;
	}
	
	// these are the items id  

	  public void MyId() {
	        this.Id = ""; 
	    }
		// these are the get items id  

		public  String getId() {
			return Id;	
		}
		// these are the set items id  

		public void setId(String Id) {
			this.Id = Id;
		}	
		// these are the items name  

		  public void MyName() {
		        this.Name = ""; 
		    }
			// these are get items name  

		public  String getName() {
			return Name;	
		}
		// these are set items name  

		public void setName(String Name) {
			this.Name = Name;
		}	
		// these are the items set rarity  

		  public void MyRairty() {
		        this.Rairty = ""; 
		    }
			// these are the items get rarity  

		public  String getRairty() {
			return Rairty;	
		}
		// these are the items set rarity  

		public void setRairty(String Rairty) {
			this.Rairty = Rairty;
		}	



}

