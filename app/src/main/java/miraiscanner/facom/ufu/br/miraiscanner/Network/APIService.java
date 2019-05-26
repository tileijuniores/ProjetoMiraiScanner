package miraiscanner.facom.ufu.br.miraiscanner.Network;

//com.chikeandroid.retrofittutorial2.data.model.Post;
//com.chikeandroid.retrofittutorial2.data.model;

import miraiscanner.facom.ufu.br.miraiscanner.Network.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    //"/api/produto"

//    @POST("/api/produto")
//    @FormUrlEncoded
//    Call<Post> savePost(@Field("enderecoIp") String enderecoIp,
//                        @Field("fabricanteMac") String fabricanteMac,
//                        @Field("enderecoMac") String enderecoMac,
//                        @Field("porta23") String porta23,
//                        @Field("porta2323") String porta2323,
//                        @Field("porta48101") String porta48101,
//                        @Field("usuario") String usuario,
//                        @Field("senha") String senha);

    @POST("api/produto")
    //@FormUrlEncoded
    Call<Post> savePost(@Body Post post);

}