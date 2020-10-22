/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.Scanner;
import security.SecureManager;
import tools.creators.BookManager;
import tools.creators.LibraryManager;
import tools.creators.ReaderManager;
import tools.savers.BookSaver;
import tools.savers.HistorySaver;
import tools.savers.ReaderSaver;
import tools.savers.UserSaver;

/**
 *
 * @author Melnikov
 */
public class ManagerUI {
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private LibraryManager libraryManager = new LibraryManager();
    private BookSaver bookSaver = new BookSaver();
    private ReaderSaver readerSaver = new ReaderSaver();
    private HistorySaver historySaver = new HistorySaver();
    private SecureManager secureManager = new SecureManager();
    private UserSaver userSaver = new UserSaver();
    
    public void getManagerUI(Reader[]readers, User[] users, Book[] books, History[] histories){
        boolean repeat = true;
        do{
            System.out.println("Задачи: ");
            System.out.print("0. Выйти из программы |");
            System.out.print("1. Добавить новую книгу |");
            System.out.print("2. Список книг |");
            System.out.print("3. Зарегистрировать читателя |");
            System.out.println("4. Список читателей |");
            System.out.print("5. Выдать книгу читателю |");
            System.out.print("6. Вернуть книгу в библиотеку |");
            System.out.print("7. Список выданных книг |");
             System.out.println("8. Удалить книгу |");
            System.out.println("Выберите задачу: ");
            Scanner scanner = new Scanner(System.in);
            String task = scanner.nextLine();
            switch (task) {
                case "0":
                    System.out.println("--- конец программы ---");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("--- Добавить новую книгу ---");
                    Book book = bookManager.createBook();
                    bookManager.addBookToArray(book,books);
                    bookSaver.saveBooks(books);
                    break;
                case "2":
                    System.out.println("--- Список книг ---");
                    bookManager.printListBooks(books);
                    break;
                case "3":
                    System.out.println("--- Зарегистрировать читателя ---");
                    Reader reader = readerManager.createReader();
                    readerManager.addReaderToArray(reader,readers);
                    readerSaver.saveReaders(readers);
                    break;
                case "4":
                    System.out.println("--- Список читателей ---");
                    readerManager.printListReaders(readers);
                    break;
                            
                case "5":
                    System.out.println("--- Выдать книгу читателю ---");
                    History history = libraryManager.takeOnBook(books, readers);
                    libraryManager.addHistoryToArray(history,histories);
                    historySaver.saveHistories(histories);
                    break;
                case "6":
                    System.out.println("--- Вернуть книгу в библиотеку ---");
                    libraryManager = new LibraryManager();
                    libraryManager.returnBook(histories);
                    historySaver = new HistorySaver();
                    historySaver.saveHistories(histories);
                    break;
                case "7":
                    System.out.println("--- Список выданных книг ---");
                    libraryManager.printListReadBooks(histories);
                    break;
                
                case "8":
                    System.out.println("--- Удаление книги ---");
                    bookManager.printListBooks(books);
                    System.out.println("Choose book for del");
                    String numBookStr = scanner.nextLine();
                    int numBook = Integer.parseInt(numBookStr);
                    books[numBook-1]=null;
                      break;
                    
                default:
                    System.out.println("Нет такой задачи.");
            }
        }while(repeat);
    }
}
