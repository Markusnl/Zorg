package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class loginScherm extends GridPane
{
    private connectdtb database= new connectdtb();
    
    protected loginScherm(StackPane panes)
    {
        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Welkom");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        add(scenetitle, 0, 0, 2, 1);
        
        Label userName = new Label("Loginnaam:");
        add(userName, 0, 1);
        
        TextField unBox = new TextField();
        add(unBox, 1, 1);

        Label pw = new Label("Paswoord:");
        add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setMinWidth(160);
        add(pwBox, 1, 2);

        Button loginbtn = new Button("Login");
        HBox hbBtn = new HBox(10);//om een andere allignment (anders dan de default) for de knop te regelen.
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginbtn);
        add(hbBtn, 1, 4);
        
        Text actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        add(actiontarget, 1, 6);
        
        //Handelers 
        unBox.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent t) 
            {
                if(t.getCode()==KeyCode.ENTER)
                {
                    try
                    {
                        if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:1"))
                        {
                            loginactie(unBox,pwBox, actiontarget,new SuperNurseTerminal(), panes);
                        }
                    
                        else if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:2"))
                        {
                            loginactie(unBox,pwBox, actiontarget,new VerzorgingsTerminal(), panes);
                        }
                    
                        else if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:3"))
                        {
                            actiontarget.setText("Alleen mobiel gebruik mogelijk.");
                        }
                    
                        else
                        {
                            actiontarget.setText("Inloggegevens zijn incorrect.");
                        }
                    }
	
                    catch(Exception ex)
                    {
                        ex.printStackTrace();	
                    }
                }
            }
        });
        
        pwBox.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent t) 
            {
                if(t.getCode()==KeyCode.ENTER)
                {                    
                    try
                    {
                        if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:1"))
                        {
                            loginactie(unBox,pwBox, actiontarget,new SuperNurseTerminal(), panes);
                        }
                    
                        else if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:2"))
                        {
                            loginactie(unBox,pwBox, actiontarget,new VerzorgingsTerminal(), panes);
                        }
                    
                        else if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:3"))
                        {
                            actiontarget.setText("Alleen mobiel gebruik mogelijk.");
                        }
                    
                    else
                    {
                        actiontarget.setText("Inloggegevens zijn incorrect.");
                    }
                    }
	
                    catch(Exception ex)
                    {
                        ex.printStackTrace();	
                    }
                }
            }
        });
        
        loginbtn.setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {  
                try
                {
                    if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:1"))
                    {
                        loginactie(unBox,pwBox, actiontarget,new SuperNurseTerminal(), panes);
                    }
                    
                    else if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:2"))
                    {
                        loginactie(unBox,pwBox, actiontarget,new VerzorgingsTerminal(), panes);
                    }
                    
                    else if(database.loginCheck(unBox.getText(), pwBox.getText()).equals("Good:3"))
                    {
                         actiontarget.setText("Alleen mobiel gebruik mogelijk.");
                    }
                    
                    else
                    {
                        actiontarget.setText("Inloggegevens zijn incorrect.");
                    }
                }
	
                catch(Exception ex)
                {
                    ex.printStackTrace();	
                }
            }
        });
    }
    
    protected void loginactie(TextField unBox, PasswordField pwBox, Text actiontarget, BorderPane terminal, StackPane panes)
    {        
            unBox.setText("");
            pwBox.setText("");
            actiontarget.setText("");
            panes.getChildren().remove(0);
            panes.getChildren().add(terminal);
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
