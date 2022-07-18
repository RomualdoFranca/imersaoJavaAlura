import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

//      A expressão regular vai passar o código no texto JSON e tentar rastrear padrões no texto
        // Atributos estáticos
        // Serve para representar uma expressão regular no Java
        private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");// Extrai os dados de dentro dos colchetes
        private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");
        // Essa lista seria uma estrutura de dados associativos. Associa uma chave com um valor.
        // <String, String> Primeira 'String' indica o tipo da chave, no caso, texto, Segunda 'String' é valor, que tambem é um texto
        // A IDE importou 2 blibliotecas a List e a Map
        public List<Map<String, String>> parse(String json) {
                Matcher matcher = REGEX_ITEMS.matcher(json);
                if (!matcher.find()) {

                        throw new IllegalArgumentException("Não encontrou items.");
                }

                String[] items = matcher.group(1).split("\\},\\{");

                List<Map<String, String>> dados = new ArrayList<>();

                for (String item : items) {

                        Map<String, String> atributosItem = new HashMap<>();

                        Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
                        while (matcherAtributosJson.find()) {
                                String atributo = matcherAtributosJson.group(1);
                                String valor = matcherAtributosJson.group(2);
                                atributosItem.put(atributo, valor);
                        }

                        dados.add(atributosItem);
                }
                return dados;

        }

}
