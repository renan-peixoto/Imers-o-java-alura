public enum API {
  TOP_MOVIES(
    "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
    new ExtratorDeConteudoIMDB()
  ),
  TOP_TV(
    "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json",
    new ExtratorDeConteudoIMDB()
  ),
  MOST_POPULAR_MOVIES(
    "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json",
    new ExtratorDeConteudoIMDB()
  ),
  MOST_POPULAR_TVS(
    "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json",
    new ExtratorDeConteudoIMDB()
  ),

  NASA(
    "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14",
    new ExtratorDeConteudoNasa()
  );

  private String url;
  private ExtratorDeConteudo extrator;

  API(String url, ExtratorDeConteudo extrator) {
    this.url = url;
    this.extrator = extrator;
  }

  public String getUrl() {
    return url;
  }

  public ExtratorDeConteudo getExtrator() {
    return extrator;
  }
}
