import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ApiJava {
    public static void main(String[] args) throws IOException, InterruptedException {

    //  Pega os dados do IMDB via conexao HTTP, e guardar a resposta dentro de uma string. Fazer um protocolo de conexao
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        var client  = HttpClient.newHttpClient();
        // uri: identificação única do recurso que vai ser usado
        // Cria uma uri associando a url passando para o bilder onde será feito o request
        URI endereco = URI.create(url);
        // Cria um request e faz um bilder
        var request = HttpRequest.newBuilder(endereco).GET().build(); // HttpRequest foi substituido por var
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
//        System.out.println(body);
    //  extrair (parsear) só os dados que interessam ( título, poster, classificação)
        // Para não ficar repetitivo foi substituido o JsonParser por var
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
//        System.out.println(listaDeFilmes.size());// Mostra o tamanho da lista. A quantidade de itens
        
    //  exibir e manipular os dados
        for (Map<String, String> filme: listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}

