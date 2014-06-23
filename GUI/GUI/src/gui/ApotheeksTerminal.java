package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ApotheeksTerminal extends BorderPane
{
    public ApotheeksTerminal(Stage primaryStage, StackPane panes)
    {
        Text apotheekterminalTitle = new Text("Apotheeksterminal");
        apotheekterminalTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        //top
        HBox titel=new HBox();
        titel.setAlignment(Pos.CENTER);
        titel.setPadding(new Insets(5, 5, 5, 5));
        titel.getChildren().add(apotheekterminalTitle);
        setTop(titel);
        //
        
        //center
        Pane Center = new Pane();
        setCenter(Center);
        
        Pane addUser = new Pane();
        addUser.setStyle("-fx-background-color: DAE6F3;");
        Pane Knop2 = new Pane();
        Knop2.setStyle("-fx-background-color: DAF5F3;");
        //
        
        //Left
        FlowPane apotheekFlowPane = new FlowPane();
        apotheekFlowPane.setPadding(new Insets(5, 5, 5, 5));
        apotheekFlowPane.setVgap(4);
        apotheekFlowPane.setHgap(4);
        apotheekFlowPane.setPrefWrapLength(230);
        
        Button test = new Button("", new ImageView(new Image("res/rode_knop.png")));
        test.setMinSize(100, 100);
        Button test1 = new Button("", new ImageView(new Image("res/rode_knop.png")));
        test1.setMinSize(100, 100);
        
        Button Logoutbtn = new Button("", new ImageView(new Image("res/SignOut.png")));
        Logoutbtn.setMinSize(100, 100);
        
        apotheekFlowPane.getChildren().add(test);
        apotheekFlowPane.getChildren().add(test1);
        apotheekFlowPane.getChildren().add(Logoutbtn);
        
        setLeft(apotheekFlowPane);
        //
        
        Logoutbtn.setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                //uitLoggen(primaryStage,panes);
            }
        });
        
        test.setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                setCenter(addUser);
            }
        });
        
        test1.setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                setCenter(Knop2);
            }
        });
    }
    
    private void uitLoggen(Stage primaryStage, StackPane panes)
    {
        //primaryStage.setScene(loginscene);
        panes.getChildren().remove(0);
        panes.getChildren().add(new loginScherm(panes));        
    }
}
