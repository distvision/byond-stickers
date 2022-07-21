import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersGen {

  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

    // Leitura da imagem
    // InputStream inputStream = new FileInputStream(new
    // File("entrada/TopMovies_1.jpg"));
    // InputStream inputStream = new
    // URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();

    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // criar nova imagem com transparencia e redimensionada
    int largura = imagemOriginal.getWidth();
    int novaLargura = largura / 4;
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // copiar a imagem original para nova imagem (em memria)
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, null, 0, 0);

    // configurar o tamanho da fonte
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
    graphics.setColor(Color.MAGENTA);
    graphics.setFont(font);

    // escrever uma frase na imagem
    graphics.drawString("TOPZERA", novaLargura, novaAltura - 100);

    // Escrever a nova imagem em um novo arquivo
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));
  }

}
