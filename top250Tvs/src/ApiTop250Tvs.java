import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ApiTop250Tvs {
    public static void main(String[] args) throws IOException, InterruptedException {

        //  fazer uma conexão http e buscar os top 250 Tvs shows
        String url = "https://imdb-api.com/en/API/Top250TVs/k_74kqqgix";
        var client = HttpClient.newHttpClient();
        URI endereco = URI.create(url);
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JasonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
//        System.out.println(listaDeFilmes.size());
        //  exibir e manipular os dados
        for (Map<String, String> filme: listaDeFilmes) {
            System.out.println(filme.get("rank") );
            System.out.println(filme.get("title") );
            System.out.println(filme.get("year") );
            System.out.println();
        }
    }
}
