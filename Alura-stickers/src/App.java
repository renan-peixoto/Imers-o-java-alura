import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

  public static void main(String[] args) throws Exception {
    // fazer uma conexão HTTP e buscar os top 250 filmes

    String url =
      "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

    // String url = System.getenv("IMDB_API_KEY");
    URI endereco = URI.create(url); // o que é URI?
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = client.send(
      request,
      BodyHandlers.ofString()
    );
    String body = response.body();
    // extrair só os dados que interessam (titulo, poster, classificação)
    JsonParser parser = new JsonParser();
    List<Map<String, String>> listaDeFilmes = parser.parse(body);
    // Exibit e manipular os dados

    for (Map<String, String> filme : listaDeFilmes) {
      System.out.println(
        "\u001b[37m \u001b[44mTítulo:\u001b[m " + filme.get("title") + "\n"
      );

      System.out.println(
        "\u001b[37m \u001b[44mCapa:\u001b[m " + filme.get("image") + "\n"
      );
      System.out.println(
        "\u001b[37m \u001b[44mClassificação:\u001b[m " + filme.get("imDbRating")
      );
      double classificacao = Double.parseDouble(filme.get("imDbRating"));
      int numeroEstrelas = (int) classificacao;
      for (int i = 0; i <= numeroEstrelas; i++) {
        System.out.print("⭐");
      }
      System.out.println("\n");
    }
  }
}
