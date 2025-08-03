package guru.springframework.sdjpa_intro;

import guru.springframework.sdjpa_intro.domain.Book;
import guru.springframework.sdjpa_intro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
/*
The annotation @SpringBootTest just brings up the entire spring context.In a more
complex project where we have a lot of moving pieces and a lot more things
being wired in and configured then it can really slow down out tests !

Hence, what the springboot team has done is that they have set up something called
as test splices where it just brings up a minimal context for us to work with.So
if we are only going to be working with the database layer then it would allow up to
bring up dependencies related explicitly to the database layer
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//used to specify the order in which the test cases would run
@DataJpaTest
public class SpringBootJpaTestSlice {
    @Autowired
    BookRepository bookRepository;
/*
note that in this class we are testing the database functionality and
hence what springboot will do here is that it will create a transaction
before each method and then once it's done executing that method it will roll back
that transaction.
So for every test case,for every method annotated with @Test a new transaction
is created and then the transaction is rolled back after the method is done executing
 */
    @Order(1)
    @Test
    void testJpaTestSplice()
    {
        long countBefore=bookRepository.count();
        assertThat(countBefore).isEqualTo(0);
        bookRepository.save(new Book("My Book","1235555","Self"));
        long countAfter=bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction()
    {
        long countBefore= bookRepository.count();
        assertThat(countBefore).isEqualTo(1);
    }
}