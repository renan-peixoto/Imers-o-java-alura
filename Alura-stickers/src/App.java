import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

  public static void main(String[] args) throws Exception {
    API api = API.TOP_MOVIES;

    String url = api.getUrl();
    ExtratorDeConteudo extrator = api.getExtrator();

    var http = new ClienteHttp();
    String json = http.buscarDado(url);

    // Exibir e manipular os dados
    List<Conteudo> conteudos = extrator.extrairConteudos(json);

    var fabricaDeFigurinhas = new FabricaDeFigurinhas();

    for (Conteudo conteudo : conteudos) {
      InputStream inputStream = new URL(conteudo.urlImage()).openStream();
      String nomeArquivo = conteudo.titulo() + ".png";
      String textoDaImagem = "Melhores Fotos";

      fabricaDeFigurinhas.criar(inputStream, textoDaImagem, nomeArquivo);

      System.out.println(
        "\u001b[37m \u001b[44mTítulo:\u001b[m " + conteudo.titulo() + "\n"
      );

      System.out.println("\n");
    }
  }
}
