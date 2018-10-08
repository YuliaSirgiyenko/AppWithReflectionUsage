
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

        Human human = new Human("Vit", "Tarn",
                "ball", LocalDate.of(1982, 9, 8));
        ObjectParser objectParser = new ObjectParserImpl();
        objectParser.toJson(human);

    }

}
