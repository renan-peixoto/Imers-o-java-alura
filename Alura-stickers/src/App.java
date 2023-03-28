import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {

  public static void main(String[] args) throws Exception {
    // Pegar as info do arquivo properties
    Properties prop = new Properties();
    InputStream input = new FileInputStream(
      "Alura-stickers/resources/config.properties"
    );
    prop.load(input);
    // fazer uma conexão HTTP e buscar os top 250 filmes

    String url = prop.getProperty("TOP_TV");

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
    // Exibir e manipular os dados
    var fabricaDeFigurinhas = new FabricaDeFigurinhas();

    for (Map<String, String> filme : listaDeFilmes) {
      String urlImage = filme.get("image");
      String titulo = filme.get("title");

      InputStream inputStream = new URL(urlImage).openStream();
      String nomeArquivo = titulo + ".png";
      String textoDaImagem = "Melhor Série";

      fabricaDeFigurinhas.criar(inputStream, textoDaImagem, nomeArquivo);

      System.out.println(
        "\u001b[37m \u001b[44mTítulo:\u001b[m " + titulo + "\n"
      );

      System.out.println("\n");
    }
  }
}
