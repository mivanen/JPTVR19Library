/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creators;

import entity.Reader;
import entity.User;
import java.util.Scanner;
import security.SecureManager;

/**
 *
 * @author Melnikov
 */
public class UserManager {
        private Scanner scanner = new Scanner(System.in);

    public User createUser() {
        ReaderManager readerManager = new ReaderManager();
        Reader reader = readerManager.createReader();
        User user = new User();
        System.out.println("--- Создание пользователя ---");
        System.out.print("Введите логин: ");
        user.setLogin(scanner.nextLine());
        System.out.print("Введите пароль: ");
        user.setPassword(scanner.nextLine());
        int numRole;
        do{
            System.out.print("Cписок ролей: ");
            for (int i = 0; i < SecureManager.role.values().length; i++) 
            {
                //Object object = SecureManager[i];
                System.out.printf("%d. %s%n"
                ,i+1
                ,SecureManager.role.values()[i]
                );

            }
            System.out.print("Укажите номер роли: ");
           String numRoleStr=scanner.nextLine();
           try
           {
            numRole=Integer.parseInt(numRoleStr);
            break;
           }
           catch(Exception e)
           {
             System.out.println("Error! Please enter just numbers");
           }
        }while(true);
        //user.setRole(scanner.nextLine());
      
        user.setRole(SecureManager.role.values()[numRole-1].toString());
        user.setReader(reader);
        System.out.println("Пользователь создан: "+user.toString());
        return user;
    }

    public void addUserToArray(User user, User[] users) {
        for (int i = 0; i < users.length; i++) {
            if(users[i] == null){
                users[i] = user;
                break;
            }
        }
    }

    public void printListUsers(User[] users) {
        for (int i = 0; i < users.length; i++) {
            if(users[i] != null){
                System.out.println(i+1+". " + users[i].toString());
            }
        }   
    }
    
}
