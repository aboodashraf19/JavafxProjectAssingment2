/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.project;

import Model.Users;
import Model.UsersJpaController;
import View.RegisterPage;
import View.ViewManager;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Yahya
 */
public class JavaFXProject extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewManager.openRegisterPage();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankPU");
        UsersJpaController usersController = new UsersJpaController(emf);

//        
//
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Users.findAll");
        List<Users> results = query.getResultList();
        results.forEach(e -> System.out.println(e.getUsername()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
