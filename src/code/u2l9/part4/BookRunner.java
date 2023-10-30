package src.code.u2l9.part4;

import java.util.Scanner;

public class BookRunner {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your favorite book?");
        Book favBook = new Book(scanner.next());

        System.out.println("What is another book?");
        favBook.setTitle(scanner.nextLine());

        System.out.println("Favorite Book Title: " + favBook.getTitle());
    }
}
