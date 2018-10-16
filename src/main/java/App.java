
import com.sirgiyenko.dao.Dao;
import com.sirgiyenko.dao.FileSystemDaoImpl;
import com.sirgiyenko.models.Human;
import com.sirgiyenko.service.ObjectParser;
import com.sirgiyenko.service.ObjectParserImpl;

import java.io.File;
import java.time.LocalDate;


/**
 * Demo class.
 * Object from model {@link Human} is created for demonstration purposes.
 */
public class App {

    private static final File FILE = new File("JsonVisual.json");

    public static void main(String[] args) {

        Dao dao = new FileSystemDaoImpl(FILE);
        ObjectParser objectParser = new ObjectParserImpl(dao);

        Human human = new Human("Yu", null,
                "football", LocalDate.of(2010, 10, 10));
        objectParser.toJson(human);

    }

}
