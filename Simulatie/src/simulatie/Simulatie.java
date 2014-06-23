package simulatie;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Simulatie extends Application 
{
    
    @Override
    public void start(Stage primaryStage) 
    {   
        Dispenser test1 = new Dispenser("A. Bas");
        Dispenser simulatie = new Dispenser("M. Hoogerwerf");
        Dispenser a = new Dispenser("B. Havenaar");
        Dispenser b = new Dispenser("A. Bas");
        Dispenser c = new Dispenser("Z. Kuzu");
        Dispenser d = new Dispenser("D. Kuyt");
        Dispenser e = new Dispenser("W. Rooney");
        Dispenser f = new Dispenser("M. van de Giessen");
        Dispenser g = new Dispenser("L. Messi");
        Dispenser h = new Dispenser("E. van der Ven");
        Dispenser aa = new Dispenser("W. Sneijder");
        
        
        Button sim = new Button("Simulatie");
        
        Label aantalMedicijnen = new Label(test1.gethoeveelheidMedicijnen().toString());
        Button plus = new Button("+");
        Button min = new Button("-");
        TextField HMed= new TextField();
         
        
        Label Status = new Label("Nieuw");
        
        ObservableList<String> options = 
        FXCollections.observableArrayList(
        "Nieuw",
        "Goed",
        "Leeg",
        "Error"
        );
        
        ComboBox comboBox = new ComboBox(options);
        
        Button btn = new Button();
        btn.setText("Set Status");
        
        Button printD1= new Button();
        printD1.setText("Print Dispenser test1");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(10);
        root.setVgap(10);
        
        /*root.add(aantalMedicijnen,1,0);
        root.add(plus,1,1);
        root.add(min,1,2);
        root.add(HMed,1,3);
        root.add(comboBox, 2,1);
        root.add(btn, 2,2);
        root.add(Status, 2,0);
        root.add(printD1, 3 ,0);*/
        root.add(sim,1,0);
        
        
        Scene scene = new Scene(root, 500, 300);
        
        primaryStage.setTitle("Simulatie");
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);
        primaryStage.show();
        
        
        sim.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                  
                  for(int i=0;i<9999999;i++)
                  {
                   
                    System.out.println("Dag: "+(i+1));
                    simulatie.simulatieDag();
                    a.simulatieDag();
                    b.simulatieDag();
                    c.simulatieDag();
                    d.simulatieDag();
                    e.simulatieDag();
                    f.simulatieDag();
                    g.simulatieDag();
                    h.simulatieDag();
                    aa.simulatieDag();
                    
                   try
            {
                // Using Thread.sleep() we can add delay in our application in
                // a millisecond time. For the example below the program will
                // take a deep breath for one second before continue to print
                // the next value of the loop.
                Thread.sleep(10000000);
                
                // The Thread.sleep() need to be executed inside a try-catch
                // block and we need to catch the InterruptedException.
            } catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
                    
                  }
            }
        });
        /*
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                Status.setText(comboBox.getValue().toString());
                test1.setStatus(comboBox.getValue().toString());
                //Status.setText();
            }
        });
        
        printD1.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                test1.Print();
            }
        });
        
        plus.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                if(!HMed.getText().isEmpty())
                {
                    test1.sethoeveelheidMedicijnen(test1.gethoeveelheidMedicijnen()+Integer.parseInt(HMed.getText()));
                    HMed.setText("");
                    aantalMedicijnen.setText(test1.gethoeveelheidMedicijnen().toString());
                    //System.out.println(test1.gethoeveelheidMedicijnen()); 
                } 
            }
        });
        
        min.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                if(!HMed.getText().isEmpty())
                {
                    test1.sethoeveelheidMedicijnen(test1.gethoeveelheidMedicijnen()-Integer.parseInt(HMed.getText()));
                    HMed.setText("");
                    aantalMedicijnen.setText(test1.gethoeveelheidMedicijnen().toString());
                    //System.out.println(test1.gethoeveelheidMedicijnen());
                }
            }
        });*/
    }

    public static void main(String[] args) 
    {
        launch(args);
        
     
        
        
    }
}
