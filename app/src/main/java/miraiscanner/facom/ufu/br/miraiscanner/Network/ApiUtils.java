package miraiscanner.facom.ufu.br.miraiscanner.Network;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://api-miraiapp.herokuapp.com/" ;
//"https://api-miraiapp.herokuapp.com/"
    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
