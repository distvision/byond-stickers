import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão http e buscar os dados da API
        String url = "https://api.mocki.io/v2/549a5d8b";
        var endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String data = response.body();
        // System.out.println(data);

        // Os dados que nos interessam extrair sao [Titulo, poster, e rating]
        var parser = new JsonParser();

        List<Map<String, String>> listaDeFilmes = parser.parse(data);
        System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0));

        // exibir e manipular os dados extraidos da API

        var geradora = new StickersGen();
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            // System.out.println(filme.get("image"));
            // System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
