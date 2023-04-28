package ch.ilv.scrapbook;

import ch.ilv.scrapbook.scrapbook.Scrapbook;
import ch.ilv.scrapbook.scrapbook.ScrapbookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private ScrapbookRepository scrapbookRepository;

    @Test
    void insertScrapbook() {
        Scrapbook objCar = this.scrapbookRepository.save(new Scrapbook("EinName", 5));
        Assertions.assertNotNull(objCar.getId());
        Scrapbook objBike = this.scrapbookRepository.save(new Scrapbook("ZweiNamen", 4));
        Assertions.assertNotNull(objBike.getId());
    }

   /* @Test
    void readScrapbook() {
        Scrapbook objCar = this.scrapbookRepository.findById(53);
        Assertions.assertNotNull(objCar.getId());
        Scrapbook objBike = this.scrapbookRepository.save(new Scrapbook("ZweiNamen", 4));
        Assertions.assertNotNull(objBike.getId());
    }

    @Test
    void updateScrapbook() {
        Scrapbook objCar = this.scrapbookRepository.findById(53);
        Assertions.assertNotNull(objCar.getId());
        Scrapbook objBike = this.scrapbookRepository.save(new Scrapbook("ZweiNamen", 4));
        Assertions.assertNotNull(objBike.getId());
    }
    @Test
    void deleteScrapbook() {
        Scrapbook objCar = this.scrapbookRepository.delete();
        Assertions.assertNotNull(objCar.getId());
        Scrapbook objBike = this.scrapbookRepository.save(new Scrapbook("ZweiNamen", 4));
        Assertions.assertNotNull(objBike.getId());
    }*/

}

