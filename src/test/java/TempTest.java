import org.junit.jupiter.api.Test;
import org.kolpashchikov.csv.CsvParserAdapter;

import java.io.IOException;
import java.util.Arrays;

import static java.util.Objects.isNull;

public class TempTest {

    @Test
    void test() throws IOException {

        try (var a = new CsvParserAdapter("/home/maxim/Загрузки/vse_studenty_bez_pd_2024-05-04T18_28_16_975008Z(1).csv")) {
            var headers = a.getHeaders();

            while (true) {
                var b = a.next();
                if (isNull(b)) {
                    break;
                }

                String[] arr = new String[b.size()];
                for (int i = 0; i < b.size(); i++) {
                    arr[i] = b.get(i).asString();
                }
                System.out.println(Arrays.toString(arr));
            }

            System.out.println("END!!!");
        }


    }

}
