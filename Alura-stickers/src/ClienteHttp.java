import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {

  public String buscarDado(String url) {
    HttpResponse<String> response;
    try {
      URI endereco = URI.create(url); // o que é URI?
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
      response = client.send(request, BodyHandlers.ofString());
      String body = response.body();
      return body;
    } catch (IOException | InterruptedException ex) {
      throw new ClienteHttpException("Erro ao consular a URL.");
    }
  }
}
