package simulatie;

import java.util.Random;

public class Dispenser 
{
    private connectdtb database= new connectdtb();
    private String dispenserId="";
    private String Naam="";
    private Integer hoeveelheidMedicijnen= 50;
    private String Status= "Goed";
    
    Random randomMedicijnen = new Random();
    int randomM = randomMedicijnen.nextInt(7);
    
    int medicijnAdd = 50;
    
    
    public Dispenser(String Naam)
    {
        this.Naam=Naam;
        if(randomM==0)
        {
            randomM++;
        }
    }
    
    public void setkamerId(String kamerId)
    {
        this.Naam=Naam;
    }
    
    public String getkamerId()
    {
        return Naam;
    }
    
    public void setStatus(String Status)
    {      
        try
        {
            database.setStatus(Naam, Status);
            this.Status=Status;
        }
        
        
        catch(Exception ex)
        {
            ex.printStackTrace();	
        }
    }
    
    public String getStatus()
    {
        return Status;
    }
    
    public void sethoeveelheidMedicijnen(int hoeveelheidMedicijnen)
    {
        try
        {
            //database.setMedicijnen(Naam, this.hoeveelheidMedicijnen.toString());
            this.hoeveelheidMedicijnen=hoeveelheidMedicijnen;
            if(gethoeveelheidMedicijnen()<10)
            {
                 setStatus("Leeg");
            }
        }
        
        
        catch(Exception ex)
        {
            ex.printStackTrace();	
        }
    }
    
    public Integer gethoeveelheidMedicijnen()
    {
        return hoeveelheidMedicijnen;
    }
    
    public void Print()
    {
        System.out.println("DispenserID: "+dispenserId);
        System.out.println("Naam: "+Naam);
        System.out.println("Status: "+Status);
        System.out.println("Aantal medicijnen: "+hoeveelheidMedicijnen);
    }
    
    public void simulatieDag()
    {
        try
        {
            Random err = new Random();
            int error = err.nextInt(11);
            //System.out.println(error);
            /*if(error==1)
            {  
               setStatus("Error");
               System.out.println(getStatus());
            }*/
            
            if(gethoeveelheidMedicijnen()<=25)
            {
                sethoeveelheidMedicijnen(gethoeveelheidMedicijnen()+medicijnAdd);
                System.out.println("Medicijnen aangevuld");
            }
            
            else if(error==1)
            {
               setStatus("Error");
               //System.out.println("Status Dispenser: "+getStatus());
            }
            
            else if(getStatus().equals("Error"))
            {
                setStatus("Goed");
            }
            
            sethoeveelheidMedicijnen(gethoeveelheidMedicijnen()-randomM);
            //System.out.println( database.setMedicijnen(Naam, gethoeveelheidMedicijnen().toString()));
            database.setMedicijnen(Naam, gethoeveelheidMedicijnen().toString());
            System.out.println("Naam: "+getkamerId());
            System.out.println("Aantal Medicijnen: "+gethoeveelheidMedicijnen());
            System.out.println("Status Dispenser: "+getStatus());
            System.out.println("");
        }
        
        
        catch(Exception ex)
        {
            ex.printStackTrace();	
        }
    }
    

}
