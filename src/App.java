import java.io.File; 
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class App {
    public static void main(String[] args) throws Exception {
        // Ruta json
        String filePath = "Familias.json";

        try {
            //parser para el json
            JSONParser parser = new JSONParser();

            //leer json
            Object obj = parser.parse(new FileReader(new File(filePath)));
            
            //convertir a un obj json
            JSONObject jsonObject = (JSONObject) obj;

            //iterar sobre cada familia en el json
            for(String familiaKey : (Iterable<String>) jsonObject.keySet()) {
                //crear instancia clase familia
                Familia familia = new Familia(familiaKey);
            }
        }
    }
}
