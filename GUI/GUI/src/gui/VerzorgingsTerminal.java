package gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VerzorgingsTerminal extends BorderPane
{    
    protected VerzorgingsTerminal()
    {
        Text verzorgingsterminalTitle = new Text("Verzorgingsterminal");
        verzorgingsterminalTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        //top
        HBox titel=new HBox();
        titel.setAlignment(Pos.CENTER);
        titel.setPadding(new Insets(5, 5, 5, 5));
        titel.getChildren().add(verzorgingsterminalTitle);
        setTop(titel);
        Pane Center = new Pane();
        setCenter(Center);
        //
        
        //left
        FlowPane verzorgingsFlowPane = new FlowPane();
        verzorgingsFlowPane.setPadding(new Insets(5, 5, 5, 5));
        verzorgingsFlowPane.setVgap(4);
        verzorgingsFlowPane.setHgap(4);
        verzorgingsFlowPane.setPrefWrapLength(230);
        
        Button Logoutbtn = new Button("", new ImageView(new Image("res/SignOut.png")));
        Logoutbtn.setMinSize(100, 100);
        
        verzorgingsFlowPane.getChildren().add(GUI.cOphalen);
        verzorgingsFlowPane.getChildren().add(GUI.Logoutbtn);
        
        setLeft(verzorgingsFlowPane);
        //
        
        GUI.cOphalen.setOnAction((ActionEvent e) -> {
            setCenter(GUI.clientenOphalen());
        });
    }
    
    /*public GridPane clientenOphalen()
    {
        GridPane cophalenPane = new GridPane();
        cophalenPane.setHgap(10);
        cophalenPane.setVgap(10);
        cophalenPane.setPadding(new Insets(25, 25, 25, 25));
        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(1000, 1000);
        GridPane gp4sp = new GridPane();
        gp4sp.setPadding(new Insets(5, 5, 5, 5));
        gp4sp.setHgap(10);
        //gp4sp.setVgap(15);
        sp.setContent(gp4sp);
        
        Label cinfo = new Label("");
        FlowPane status = new FlowPane();
        status.setPrefWrapLength(50);
        status.setVgap(34);
        Label medHoeveelheid = new Label("");
        gp4sp.add(cinfo, 0, 0);
        gp4sp.add(status, 2, 0);
        gp4sp.add(medHoeveelheid, 3, 0);
        cophalenPane.add(sp, 1, 5);
        
        Text scenetitle = new Text("Cliënten ophalen");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        cophalenPane.add(scenetitle, 0, 0, 2, 1);
        
        Label userName = new Label("Naam cliënt:");
        cophalenPane.add(userName, 0, 1);
        
        TextField naamcBox = new TextField();
        naamcBox.setMaxWidth(175);
        cophalenPane.add(naamcBox, 1, 1);

        Button getclientbtn = new Button("Ophalen");
        HBox hbBtn = new HBox(10);//om een andere allignment (anders dan de default) for de knop te regelen.
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(getclientbtn);
        cophalenPane.add(hbBtn, 1, 3); 
        
        getclientbtn.setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                try
                {
                    String clienten= database.getClienten(naamcBox.getText());
                    //System.out.println(clienten);
                    String result="KamerID: ";
                    status.getChildren().clear();
                    String medicijnen="Hoeveelheid medicijnen: ";
                    String naam="";
                    Boolean bool = true;
                    int aantalc=0;
                    
                    for(int a=0; a<clienten.length(); a++)
                    {
                        if(clienten.charAt(a)==':')
                        {
                            if(!bool)
                            {
                                try //String die van de database terug krijgt eindigd met een ":". om problemen (ophalen medicijnen) te voorkomen wordt er bij de laatste ":" gekeken naar een exception om als aller laatst het aantal medicijnen te printen 
                                {
                                    clienten.charAt(a+1);
                                }
                                
                                catch(StringIndexOutOfBoundsException ex)
                                {
                                    medicijnen+=database.getmedicijnHoeveelheid(naam); //pas als de volledige naam compleet is wordt het aantal medicijnen opgehaald.
                                    naam="";
                                    break;
                                }
                                
                                bool=true;
                                medicijnen+=database.getmedicijnHoeveelheid(naam)+"\n\n\n\n\nHoeveelheid medicijnen: ";
                                naam="";
                                result+="\n\n\n\nKamerID: ";
                            }
                            
                            else
                            {
                                bool=false;
                                result+="\nNaam: ";
                                status.getChildren().add(new ImageView(new Image("res/OK.png")));
                                aantalc++;
                            }
                        }
                        
                        else
                        {
                            if(bool)
                            {
                                result+=clienten.charAt(a);
                                
                            }
                            
                            else
                            {
                                result+=clienten.charAt(a);
                                naam+=clienten.charAt(a);
                            }
                        }                
                        
                    }
                    
                    cinfo.setText(result);
                    medHoeveelheid.setText(medicijnen);
                    
                    naamcBox.setText("");
                }
	
                catch(Exception ex)
                {
                    ex.printStackTrace();	
                }
            }
        });
        
        return cophalenPane;
    }*/
}
