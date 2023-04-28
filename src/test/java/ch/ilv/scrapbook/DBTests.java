package ch.ilv.scrapbook;

import ch.ilv.scrapbook.scrapbook.Scrapbook;
import ch.ilv.scrapbook.scrapbook.ScrapbookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private ScrapbookRepository scrapbookRepository;


    @Test
    void CRUD() {
        //CREATE
        Scrapbook scrapbook = new Scrapbook();
        scrapbook.setTitle("test");
        scrapbook = this.scrapbookRepository.save(scrapbook);
        Assertions.assertNotNull(scrapbook.getId());

        //UPDATE
        scrapbook.setTitle("testupdate");
        scrapbook = this.scrapbookRepository.save(scrapbook);
        Assertions.assertEquals("testupdate", scrapbook.getTitle());

        //READ
        Assertions.assertEquals(scrapbook.getTitle(), this.scrapbookRepository.findById(scrapbook.getId()).get().getTitle());

        //DELETE
        this.scrapbookRepository.deleteById(scrapbook.getId());
        Assertions.assertEquals(this.scrapbookRepository.findById(scrapbook.getId()), Optional.empty());
    }
}

