import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
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

    String url = prop.getProperty("TOP_MOVIES");

    var http = new ClienteHttp();
    String json = http.buscarDado(url);

    // Exibir e manipular os dados
    ExtratorDeConteudo extratorDeConteudoIMDB = new ExtratorDeConteudoIMDB();
    List<Conteudo> conteudos = extratorDeConteudoIMDB.extrairConteudos(json);
    // ExtratorDeConteudo extratorDeConteudoNasa = new ExtratorDeConteudoNasa();
    // List<Conteudo> conteudos = extratorDeConteudoNasa.extrairConteudos(json);

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
