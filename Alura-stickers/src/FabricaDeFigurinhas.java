import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class FabricaDeFigurinhas {

  public void criar(
    InputStream inputStream,
    String textoDaImagem,
    String nomeDoArquivo
  ) throws Exception {
    // leitura da imagem

    BufferedImage original = ImageIO.read(inputStream);

    int largura = original.getWidth();
    int altura = original.getHeight();
    int novaAltura = altura + 200;

    BufferedImage novaImagem = new BufferedImage(
      largura,
      novaAltura,
      BufferedImage.TRANSLUCENT
    );
    // copiar a nova imagem para nova imagem (em memória)

    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(original, 0, 0, null);

    // configurar fonte

    Font fonte = new Font("Impact", Font.BOLD, 64);
    graphics.setFont(fonte);
    graphics.setColor(Color.YELLOW);

    // escrever uma frase na nova imagem

    String texto = textoDaImagem;
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraDoTexto = (int) retangulo.getWidth();
    int posicaoTextoX = (largura - larguraDoTexto) / 2;

    graphics.drawString(texto, posicaoTextoX, novaAltura - 100);

    // escrever a nova imagem em um arquivo
    ImageIO.write(
      novaImagem,
      "png",
      new File("Alura-stickers/saida/" + nomeDoArquivo)
    );
  }
}
