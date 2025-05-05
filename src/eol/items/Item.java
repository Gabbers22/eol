package eol.items;

import java.util.HashMap;

public class Item {
    HashMap<String, Integer> statModifiers;
    private  String Id;
	private  String Name;
	private  String Rairty;

	public Item(String Id, String Name,String Rairty) {
        statModifiers = new HashMap<>();
        this.Id = Id;
        this.Name = Name;
        this.Rairty = Rairty;
	}
	    
    public void MyId() {
	    this.Id = ""; 
	}
		
	public String getId() {
		return Id;	
	}
		
	public void setId(String Id) {
		this.Id = Id;
	}	
		
    public void MyName() {
        this.Name = ""; 
    }
			
	public  String getName() {
		return Name;	
	}
		
	public void setName(String Name) {
		this.Name = Name;
	}	

	public void MyRairty() {
		this.Rairty = ""; 
	}

	public  String getRairty() {
		return Rairty;	
	}
		
	public void setRairty(String Rairty) {
		this.Rairty = Rairty;
	}	
	
}


