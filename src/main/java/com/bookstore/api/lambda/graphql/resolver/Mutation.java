package com.bookstore.api.lambda.graphql.resolver;

import com.bookstore.api.lambda.graphql.type.input.RateInput;
import com.bookstore.api.lambda.graphql.type.response.MutationResponse;
import com.bookstore.api.lambda.persistence.dao.AuthorDAO;
import com.bookstore.api.lambda.persistence.dao.AuthorRateDAO;
import com.bookstore.api.lambda.persistence.dao.BookDAO;
import com.bookstore.api.lambda.persistence.dao.BookRateDAO;
import com.bookstore.api.lambda.persistence.entity.Book;
import com.bookstore.api.lambda.persistence.entity.BookRate;
import com.bookstore.api.lambda.persistence.entity.Rating;
import com.bookstore.api.lambda.persistence.entity.aggregation.AverageRateResult;
import com.coxautodev.graphql.tools.GraphQLRootResolver;
import org.bson.types.ObjectId;

/**
 * Created by Gregorio on 12/11/17.
 */
public class Mutation implements GraphQLRootResolver{

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final BookRateDAO bookRateDAO;
    private final AuthorRateDAO authorRateDAO;

    public Mutation(BookDAO bookDAO, AuthorDAO authorDAO, BookRateDAO bookRateDAO,
        AuthorRateDAO authorRateDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
        this.bookRateDAO = bookRateDAO;
        this.authorRateDAO = authorRateDAO;
    }

    public MutationResponse rateBook(RateInput rateInput){

        bookRateDAO.save(new BookRate(rateInput.getRate(), rateInput.getId()));
        Book book = bookDAO.get(new ObjectId(rateInput.getId()));
        AverageRateResult averageRateResult = bookRateDAO.getAverageRating(rateInput.getId());
        book.setRating(new Rating(averageRateResult.getAvg(), averageRateResult.getCount()));
        bookDAO.save(book);
       return new MutationResponse(true);
    }
}
