package com.bookstore.api.load;

import com.bookstore.api.lambda.persistence.MongoDBConnectionService;
import com.bookstore.api.lambda.persistence.dao.AuthorDAO;
import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.dao.GenreDAO;
import com.bookstore.api.lambda.persistence.dao.impl.AuthorDAOImpl;
import com.bookstore.api.lambda.persistence.dao.impl.BookDAOImpl;
import com.bookstore.api.lambda.persistence.dao.impl.GenreDAOImpl;
import com.bookstore.api.lambda.persistence.entity.*;
import static com.bookstore.api.lambda.persistence.entity.Book.BookAuthor;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gregorio on 13/11/17.
 */
public class TestLoadData {

    private static BookDAO bookDAO;
    private static AuthorDAO authorDAO;
    private static GenreDAO genreDAO;


    @BeforeClass
    public static void init() throws Exception{
        Datastore ds = new MongoDBConnectionService().getDatastore();
         bookDAO = new BookDAOImpl(Book.class, ds);
         authorDAO = new AuthorDAOImpl(Author.class, ds);
         genreDAO = new GenreDAOImpl(Genre.class, ds);
    }


    @Ignore
    @Test
    public void testLoadGenre(){

        String csvFile = "/Users/Gregorio/bookstore/genres.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] lineSplitted = line.split(cvsSplitBy);
                Genre genre = new Genre(lineSplitted[0]);
                genreDAO.save(genre);
                System.out.println("Genre [name= " + lineSplitted[0] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Ignore
    @Test
    public void testLoadBooks(){

        String csvFile = "/Users/Gregorio/bookstore/books.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int i = 1;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            long count = genreDAO.count();

            while ((line = br.readLine()) != null && i <= 2000) {

                Double randomSkip = Math.floor(Math.random() * count);

                String[] lineSplitted = line.split(cvsSplitBy);
                if(!lineSplitted[10].equals("")  && !lineSplitted[7].equals("") && !lineSplitted[5].equals("") && !lineSplitted[8].equals("")){

                    double start = 100;
                    double end = 1500;
                    double random = new Random().nextDouble();
                    double result = start + (random * (end - start));

                    List<Book.BookAuthor> authorList = new ArrayList<Book.BookAuthor>();
                    String[] authorsNames = lineSplitted[7].split(",");

                    for(String name: authorsNames){
                        Author author = authorDAO.findByName(name);
                        if(author != null){
                            Book.BookAuthor bookAuthor = new Book.BookAuthor(author.getId().toString(),author.getName());
                            authorList.add(bookAuthor);
                        }else{
                            Key<Author> keyAuthor = authorDAO.save(new Author(name, new Rating(0.0, 0L)));
                            Book.BookAuthor bookAuthor = new Book.BookAuthor(keyAuthor.getId().toString(),name);
                            authorList.add(bookAuthor);
                            System.out.println("Author [name= " + name+ "]");
                        }
                    }

                    Genre genre = genreDAO.getRandomGenre(randomSkip.intValue());

                    Book book = new Book();
                    book.setIsbn(lineSplitted[5]);
                    book.setTitle(lineSplitted[10]);
                    book.setAuthorsNames(lineSplitted[7]);
                    book.setPublicationYear(Integer.valueOf(lineSplitted[8]));
                    book.setRating(new Rating(0.0, 0L));
                    book.setImageUrl(new ImageUrl(lineSplitted[21],lineSplitted[22]));
                    book.setPrice(result);
                    book.setGenre(new Book.BookGenre(genre.getId().toString(), genre.getName()));
                    book.setAuthors(authorList);

                    try{
                        bookDAO.save(book);
                        System.out.println("--> Book [name= " + lineSplitted[10] + " line ="+i+"]");
                    }catch (Exception ex){
                        System.out.println("----> Error [ " + ex.getLocalizedMessage() + "]");
                    }

                    i++;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
