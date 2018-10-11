
import com.sirgiyenko.dao.Dao;
import com.sirgiyenko.dao.FileSystemDaoImpl;
import com.sirgiyenko.models.Human;
import com.sirgiyenko.service.ObjectParser;
import com.sirgiyenko.service.ObjectParserImpl;

import java.time.LocalDate;


/**
 * Demo class.
 * Object from model {@link Human} is created for demonstration purposes.
 */
public class App {

    public static void main(String[] args) {

        Human human = new Human("Yu", "Sirh",
                "football", LocalDate.of(2005, 10, 10));

        Dao dao = new FileSystemDaoImpl();
        ObjectParser objectParser = new ObjectParserImpl(dao);
        objectParser.toJson(human);

    }

}
