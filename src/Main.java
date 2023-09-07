import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-09-08&end_date=2022-09-10";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        extrator.extraiConteudos(json);
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var gerador = new GeradorDeFigurinhas();

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            gerador.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}