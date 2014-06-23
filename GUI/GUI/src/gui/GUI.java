package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application 
{    
    //Stage == Frame/window
    //Scene == Panel
    //pane == inhoud scene?
    
    protected static Button Logoutbtn = new Button("", new ImageView(new Image("res/SignOut.png")));
    protected static Button cOphalen = new Button("", new ImageView(new Image("res/Search.png")));
    
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Terminal");
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("res/Icon.png"));
        //primaryStage.setFullScreen(true);

        StackPane panes = new StackPane();
        panes.getChildren().add(new loginScherm(panes));
        
        cOphalen.setMinSize(100, 100);
        
        Logoutbtn.setMinSize(100, 100);
        Logoutbtn.setOnAction((ActionEvent e) -> {
            panes.getChildren().remove(0);
            panes.getChildren().add(new loginScherm(panes));
        });
        
        Scene loginscene = new Scene(panes);
        primaryStage.setScene(loginscene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    protected static void main(String[] args) 
    {
        launch(args);
    }
    
    static protected GridPane clientenOphalen()
    {
        connectdtb database= new connectdtb();
        
        GridPane cophalenPane = new GridPane();
        cophalenPane.setHgap(10);
        cophalenPane.setVgap(10);
        cophalenPane.setPadding(new Insets(25, 25, 25, 25));
        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(1000, 1000);
       
        cophalenPane.add(sp, 1, 5);
        
        Text scenetitle = new Text("Cliënten ophalen");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        cophalenPane.add(scenetitle, 0, 0, 2, 1);
        
        Label userName = new Label("Naam cliënt:");
        cophalenPane.add(userName, 0, 1);
        
        TextField naamcBox = new TextField();
        naamcBox.setMaxWidth(175);
        cophalenPane.add(naamcBox, 1, 1);

        //Button getclientbtn = new Button("Ophalen");
        //Button reloadclientbtn = new Button("Reload");
        HBox hbBtn = new HBox(10);//om een andere allignment (anders dan de default) for de knop te regelen.
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        //hbBtn.getChildren().add(getclientbtn);
        cophalenPane.add(hbBtn, 1, 3); 
        //cophalenPane.add(reloadclientbtn, 2, 3); 
        
        naamcBox.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent t) 
            {
                if(t.getCode()==KeyCode.ENTER)
                {   
                    GridPane gp = new GridPane();
                    
                    Text cg = new Text("Cliënt gegevens");
                    cg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    Text ds = new Text("Dispenser status");
                    ds.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    Text hm = new Text("Hoeveelheid medicijnen");
                    hm.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    
                    gp.setPadding(new Insets(5, 5, 5, 5));
                    gp.setHgap(20);
                    gp.setVgap(15);
                    
                    gp.add(cg, 0, 0);
                    gp.add(ds, 1, 0);
                    gp.add(hm, 2, 0);
                    sp.setContent(gp);
                            
                    int aantalclienten = 0;
                    try//kijkt hoeveel clienten er worden opgevraagd
                    {
                        Boolean bool = true;
                        String clienten= database.getClienten(naamcBox.getText());
            
                        for(int a=0; a<clienten.length(); a++)
                        {
                            if(clienten.charAt(a)==':')
                            {
                                if(!bool)
                                {
                                    bool=true;                                
                                    aantalclienten++;
                                }
                            
                                else
                                {
                                    bool=false;
                                }
                            } 
                        }
                        
                        //System.out.println(aantalclienten);
                    }
                    
                    catch(Exception ex)
                    {
                        ex.printStackTrace();	
                    }
                    
                    String[] cgegevens = new String[aantalclienten];
                    String[] hMedicijnen = new String[aantalclienten];
                    String[] cnamen= new String[aantalclienten];
                    
                    try//Zet de kamerid's en clienten in de String[] cnamen
                    {
                        String clienten = database.getClienten(naamcBox.getText());
                        String naam = "";
                        String kamerid = "";
                        Boolean bool = true;
                        int y = 0;

                        for (int a = 0; a < clienten.length(); a++) 
                        {
                            if (clienten.charAt(a) == ':') 
                            {
                                if (!bool) 
                                {
                                    bool = true;
                                    //System.out.println(naam);
                                    //System.out.println(cnamen.length);
                                    cgegevens[y]+= "Naam: "+naam;
                                    cnamen[y]=naam;
                                    hMedicijnen[y]=database.getmedicijnHoeveelheid(naam);
                                    y++;
                                    naam = "";
                                } 
                                
                                else 
                                {
                                    bool = false;
                                    cgegevens[y]="KamerID: "+kamerid+"\n";
                                    kamerid="";
                                }
                            } 
                            
                            else 
                            {
                                if (!bool) 
                                {
                                    naam+= clienten.charAt(a);
                                }
                                
                                else
                                {
                                    kamerid+=clienten.charAt(a);
                                }
                            }
                        }
                    } 
                
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                    
                    try//maakt voor elke client een label aan en plaatst deze in de gridpane
                    {
                        
                        Label[] gLabel = new Label[cgegevens.length];  
                        Label[] hmLabel= new Label[cgegevens.length];
                        
                        for(int x=0; x<gLabel.length; x++)
                        {
                            gLabel[x]=new Label();
                            gLabel[x].setText(cgegevens[x]);
                            gp.add(gLabel[x], 0, x+1);
                            
                            gp.setAlignment(Pos.CENTER);
                            
                            hmLabel[x]=new Label();
                            if(hMedicijnen[x].equals("No data found"))
                            {
                                gp.add(hmLabel[x], 1, x+1);
                            }
                            
                            else
                            {
                               hmLabel[x].setText(hMedicijnen[x]); 
                               gp.add(hmLabel[x], 2, x+1);
                            }
 
                            if(database.getStatus(cnamen[x]).equals("Goed"))
                            {
                                gp.add(new ImageView(new Image("res/OK.png")), 1, x+1);
                            }
                            
                            else if(database.getStatus(cnamen[x]).equals("Leeg"))
                            {
                                gp.add(new ImageView(new Image("res/notOK.png")), 1, x+1);
                            }
                            
                            else if(database.getStatus(cnamen[x]).equals("Nieuw"))
                            {
                                gp.add(new ImageView(new Image("res/New.png")), 1, x+1);
                            }
                            
                            else if(database.getStatus(cnamen[x]).equals("No data found"))
                            {
                                hmLabel[x].setText("Geen dispenser toegewezen.");
                            }
                            
                            else
                            {
                                gp.add(new ImageView(new Image("res/Error.png")), 1, x+1);
                            }
                            
                            naamcBox.setText("");
                        }
                    } 
                
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                }
            }
        }); 
        
        /*getclientbtn.setOnAction((ActionEvent e) -> {
        });*/
        
        return cophalenPane;
    }
}//606