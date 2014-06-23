package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SuperNurseTerminal extends BorderPane 
{    
    protected SuperNurseTerminal()
    {
        Text adminterminalTitle = new Text("Verzorgingsterminal");
        adminterminalTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        //top
        HBox titel=new HBox();
        titel.setAlignment(Pos.CENTER);
        titel.setPadding(new Insets(5, 5, 5, 5));
        titel.getChildren().add(adminterminalTitle);
        setTop(titel);
        //
        
        //center
        Pane Center = new Pane();
        setCenter(Center);
        
        //left
        FlowPane adminFlowPane = new FlowPane();
        adminFlowPane.setPadding(new Insets(5, 5, 5, 5));
        adminFlowPane.setVgap(4);
        adminFlowPane.setHgap(4);
        adminFlowPane.setPrefWrapLength(230);
        
        Button addUser = new Button("", new ImageView(new Image("res/AddUser.png")));
        addUser.setMinSize(100, 100);
        
        Button addClient = new Button("", new ImageView(new Image("res/Client.png")));
        
        Button addDispenser = new Button("", new ImageView(new Image("res/Dispensers.png")));
        
        Button Logoutbtn = new Button("", new ImageView(new Image("res/SignOut.png")));
        Logoutbtn.setMinSize(100, 100);
        
        adminFlowPane.getChildren().add(addUser);
        adminFlowPane.getChildren().add(addClient);
        adminFlowPane.getChildren().add(addDispenser);
        adminFlowPane.getChildren().add(GUI.cOphalen);
        adminFlowPane.getChildren().add(GUI.Logoutbtn);
        
        setLeft(adminFlowPane);
        //
        
        addUser.setOnAction((ActionEvent e) -> {
            setCenter(addUser());
        });
        
        addClient.setOnAction((ActionEvent e) -> {
            setCenter(addClient());
        });
        
        addDispenser.setOnAction((ActionEvent e) -> {
            setCenter(addDispenser());
        });
        
        GUI.cOphalen.setOnAction((ActionEvent e) -> {
            setCenter(GUI.clientenOphalen());
        });
    }
    
    protected GridPane addUser()
    {
        connectdtb database= new connectdtb();
        
        GridPane adduserPane = new GridPane();
        //adduserPane.setAlignment(Pos.CENTER);
        adduserPane.setHgap(10);
        adduserPane.setVgap(10);
        adduserPane.setPadding(new Insets(25, 25, 25, 25));
        
        Text melding = new Text();
        melding.setFill(Color.FIREBRICK);
        
        Text scenetitle = new Text("Nieuwe gebruiker toevoegen");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        adduserPane.add(scenetitle, 0, 0, 2, 1);
        
        Label userName = new Label("Loginnaam:");
        adduserPane.add(userName, 0, 1);
        
        TextField unBox = new TextField();
        adduserPane.add(unBox, 1, 1);

        Label pw = new Label("Paswoord:");
        adduserPane.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        adduserPane.add(pwBox, 1, 2);
        
        Label email = new Label("E-mail:");
        adduserPane.add(email, 0, 3);

        TextField emailBox = new TextField();
        adduserPane.add(emailBox, 1, 3);
        
        Label uRights = new Label("Rechten niveau:");
        adduserPane.add(uRights, 0, 4);

        TextField uRightsBox = new TextField();
        adduserPane.add(uRightsBox, 1, 4);

        Button adduserBtn = new Button("Toevoegen");
        adduserPane.add(adduserBtn, 0, 5);
        adduserPane.add(melding, 0, 6);
        /////////
        GridPane test = new GridPane();//filler
        test.setMinSize(270, 0);
        adduserPane.add(test, 3, 0);
        
        Text scenetitleverwijder = new Text("Gebruiker verwijderen");
        scenetitleverwijder.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        adduserPane.add(scenetitleverwijder, 4, 0, 2, 1);
        
        Label userNamev = new Label("Loginnaam:");
        adduserPane.add(userNamev, 4, 1);
        
        TextField unBoxv = new TextField();
        adduserPane.add(unBoxv, 5, 1);  
            
        Text removemelding = new Text();
        removemelding.setFill(Color.FIREBRICK);
        
        Button removeuserBtn = new Button("Verwijderen");
        adduserPane.add(removeuserBtn, 4, 2);
        adduserPane.add(removemelding, 4, 3);
        
        removeuserBtn.setOnAction((ActionEvent e) -> {
            try 
            {
                removemelding.setText(database.removeUser(unBoxv.getText()));
                unBoxv.setText("");
            }
            
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
                
        adduserBtn.setOnAction((ActionEvent e) -> {
            try 
            {
                melding.setText(database.addUser(unBox.getText(), pwBox.getText(), emailBox.getText(), uRightsBox.getText()));
                unBox.setText("");
                pwBox.setText("");
                emailBox.setText("");
                uRightsBox.setText("");
            }
            
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        //
        
        return adduserPane;
    }
    
    protected GridPane addClient()
    {
        connectdtb database= new connectdtb();
        
        GridPane addClientsPane = new GridPane();
        //adduserPane.setAlignment(Pos.CENTER);
        addClientsPane.setHgap(10);
        addClientsPane.setVgap(10);
        addClientsPane.setPadding(new Insets(25, 25, 25, 25));
        
        Text Clientsmelding = new Text();
        Clientsmelding.setFill(Color.FIREBRICK);
        
        Text Clientsscenetitle = new Text("Nieuwe Client toevoegen");
        Clientsscenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        addClientsPane.add(Clientsscenetitle, 0, 0, 2, 1);
        
        Label ClientsKamerID = new Label("Kamer ID:");
        addClientsPane.add(ClientsKamerID, 0, 1);
        
        TextField ClientsKamerBox = new TextField();
        addClientsPane.add(ClientsKamerBox, 1, 1);
        
        Label ClientsName = new Label("Naam:");
        addClientsPane.add(ClientsName, 0, 2);
        
        TextField ClientsnameBox = new TextField();
        addClientsPane.add(ClientsnameBox, 1, 2);
        
        Label Clientsbirth = new Label("Geboorte datum:");
        addClientsPane.add(Clientsbirth, 0, 3);

        TextField ClientsbirthBox = new TextField();
        addClientsPane.add(ClientsbirthBox, 1, 3);
        
        Label birthFormat = new Label("Format: YYYY-MM-DD");
        addClientsPane.add(birthFormat, 2, 3);
        
        Label ClientsbirthP = new Label("Geboorte plaats:");
        addClientsPane.add(ClientsbirthP, 0, 4);

        TextField ClientsbirthPBox = new TextField();
        addClientsPane.add(ClientsbirthPBox, 1, 4);
        
        Label ClientsAantal_Med = new Label("Aantal soort medicijnen:");
        addClientsPane.add(ClientsAantal_Med, 0, 5);
        
        TextField ClientsAantal_MedBox = new TextField();
        addClientsPane.add(ClientsAantal_MedBox, 1, 5);

        Button addClientsBtn = new Button("Client Toevoegen");
        addClientsPane.add(addClientsBtn, 0, 6);
        addClientsPane.add(Clientsmelding, 0, 7);
        
        ///////Verwijder deel///////
        GridPane test = new GridPane();
        test.setMinSize(180, 0);
        addClientsPane.add(test, 3, 0);
        
        Text Clients_Delete_melding = new Text();
        Clients_Delete_melding.setFill(Color.FIREBRICK);
        
        Text CLients_Delete_scenetitle = new Text("Client verwijderen");
        CLients_Delete_scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        addClientsPane.add(CLients_Delete_scenetitle, 4, 0, 2, 1);
        
        Label Clients_Delete = new Label("Client Naam:");
        addClientsPane.add(Clients_Delete, 4, 1);
        
        TextField Clients_DeleteBox = new TextField();
        addClientsPane.add(Clients_DeleteBox, 5, 1);
        
        Button addClientsDeleteBtn = new Button("Client Verwijderen");
        addClientsPane.add(addClientsDeleteBtn, 4, 2);
        addClientsPane.add(Clients_Delete_melding, 4, 3);        
        
        addClientsBtn.setOnAction((ActionEvent e) -> 
        {
            try
            {
               Clientsmelding.setText(database.addClient(ClientsKamerBox.getText(), ClientsnameBox.getText(), ClientsbirthBox.getText(), ClientsbirthPBox.getText(), ClientsAantal_MedBox.getText()));
               
               if(Clientsmelding.getText().equals("good"))
               {
                    ClientsKamerBox.setText(""); 
                    ClientsnameBox.setText("");
                    ClientsbirthBox.setText("");
                    ClientsbirthPBox.setText("");
                    ClientsAantal_MedBox.setText("");
               }
            }
            
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        
        addClientsDeleteBtn.setOnAction((ActionEvent e) -> 
        {
            try
            {
               Clientsmelding.setText(database.removeClient(Clients_DeleteBox.getText()));
               Clients_DeleteBox.setText("");
            }
            
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        
        return addClientsPane;
    }
    
    protected GridPane addDispenser()
    {
        connectdtb database= new connectdtb();
        
        GridPane addDispensersPane = new GridPane();
        //adduserPane.setAlignment(Pos.CENTER);
        addDispensersPane.setHgap(10);
        addDispensersPane.setVgap(10);
        addDispensersPane.setPadding(new Insets(25, 25, 25, 25));
        
        Text Dis_scenetitle = new Text("Toevoegen/verwijderen");
        Dis_scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        addDispensersPane.add(Dis_scenetitle, 0, 0, 2, 1);

        int aantalclienten = 0;
        try
        {
            Boolean bool = true;
            String clienten= database.getClienten("all");
            
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
            //System.out.println(x);
        }
        
        
        catch(Exception ex)
        {
            ex.printStackTrace();	
        }
        
        String[] cnamen = new String[aantalclienten];
        
        try
        {
            String clienten= database.getClienten("all");
            String naam="";
            Boolean bool = true;
            int y=0;
                    
            for(int a=0; a<clienten.length(); a++)
            {
                if(clienten.charAt(a)==':')
                {
                    if(!bool)
                    {
                        bool=true;                                
                        //System.out.println(naam);
                        cnamen[y]=naam;
                        y++;
                        naam="";
                    }
                            
                    else
                    {
                        bool=false;
                    }
                }
                        
                else
                {
                    if(!bool)
                    {
                        naam+=clienten.charAt(a);
                    }
                }                
            }
        }
	
        catch(Exception ex)
        {
            ex.printStackTrace();	
        }
            
               

        Label Dis_KamerId = new Label("Naam:");
        addDispensersPane.add(Dis_KamerId, 0, 1);
        
        ObservableList<String> options =  FXCollections.observableArrayList(cnamen);    
        ComboBox comboBox = new ComboBox(options);
        addDispensersPane.add(comboBox, 1, 1);

        Button addDispensersBtn = new Button("Toevoegen");
        addDispensersPane.add(addDispensersBtn, 0, 2);
        
        Button verwijderDispensersBtn = new Button("Verwijderen");
        addDispensersPane.add(verwijderDispensersBtn, 1, 2);
        
        Text actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        addDispensersPane.add(actiontarget, 0, 3);
        
        addDispensersBtn.setOnAction((ActionEvent e) -> 
        {
            try
            {
               actiontarget.setText(database.addDispenser(comboBox.getValue().toString()));
               //Dis_KamerIdBox.setText(""); 
            }
            
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        
        verwijderDispensersBtn.setOnAction((ActionEvent e) -> 
        {
            try
            {
               actiontarget.setText(database.removeDispenser(comboBox.getValue().toString()));
               //Dis_KamerIdBox.setText(""); 
            }
            
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        });
        
        return addDispensersPane;
    }
}
