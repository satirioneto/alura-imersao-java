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

         String url = "https://imdb-api.com/en/API/Top250Movies/k_eipz0jwh";
//        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_eipz0jwh";
//        String url = "https://imdb-api.com/en/API/ComingSoon/k_eipz0jwh";

        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        /* Podemos usar o <var> para substituir a definição do tipo da variável
        var enredeco = URI.create(url); 
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(enredeco).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        var body = response.body();
         */

        // extrair os dados que são interessantes para a aplicação (título, poster, classificação)

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes =  parser.parse(body);

        // Exibir e manupular os dados

        System.out.println("\u001b[1m" + "Top 250 movies" + "\u001b[m");

        for(Map<String, String> filme : listaDeFilmes) {

            String rank = filme.get("rank");
            var year = filme.get("year");
            String title = filme.get("title");
//            String image = filme.get("image");
            String imdbRating = filme.get("imDbRating");
            double rating = Double.parseDouble(imdbRating);


            System.out.printf("Rank:    " + "\u001b[1m" + "%s\n" + "\u001b[0m", rank);
            System.out.printf("Title:   " + "\u001b[1m" + "%s\n" + "\u001b[0m", title);
            System.out.printf("Year:    " + "\u001b[1m" + "%s\n" + "\u001b[0m", year);
//            System.out.printf("Poster:  " + "\u001b[1m" + "%s\n" + "\u001b[m", image);
            System.out.printf("IMDB Rating: \u001b[1m %s \u001b[0m", imdbRating);
            ratingStars(rating);
        }
    }

//    static String ratingStars(double rating) {
//        if(rating > 9.0) {
//            return "\u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50";
//        } else if(rating > 8.0) {
//            return "\u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50";
//        } else if(rating > 7.0) {
//            return "\u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50";
//        } else if(rating > 6.0) {
//            return "\u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50";
//        } else if(rating > 5.0) {
//            return "\u2B50 \u2B50 \u2B50 \u2B50 \u2B50 \u2B50";
//        } else if(rating > 4.0) {
//            return "\u2B50 \u2B50 \u2B50 \u2B50 \u2B50";
//        } else if(rating > 3.0) {
//            return "\u2B50 \u2B50 \u2B50 \u2B50";
//        } else if(rating > 2.0) {
//            return "\u2B50 \u2B50 \u2B50";
//        } else if(rating > 1.0) {
//            return "\u2B50 \u2B50";
//        } else if(rating > 0.0) {
//            return "\u2B50";
//        }else {
//            return "Sem resultado.";
//        }
//    }
    static void ratingStars(double rating) {
        for(int i = 0; i < rating; i++) {
            System.out.print("\u2B50");
        }
        System.out.println();
    }

}
