package kr.happy.myarmy.Server;

import com.google.gson.JsonObject;

import kr.happy.myarmy.Menu.Chaeyong;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by JY on 2017-04-26.
 */

public interface RetroInterface {

    /*ALL LIST*/
    @GET("list")
    Call<Chaeyong> getList
    (@Query("numOfRows") int rows,
     @Query("pageNo") int pg,
     @Query("_type") String _type,
     @Query(value = "serviceKey", encoded = true) String serviceKey); //물음표 뒤에있는 것들은 쿼리로.


    /*GET thumbnail*/
    @GET("info/thumbnail/{cygonggNo}")
    Call<JsonObject> getThumbnail (
            @Header("x-auth-token") String token,
            @Path(value = "cygonggoNo", encoded = true) String cygongoNo);



    /*GET Area List*/
    @GET("category/area/{area}")
    Call<JsonObject> getAreaList  (
            @Header("x-auth-token") String token,
            @Path(value = "area", encoded = true) String area);

    /*GET Occupation List*/
    @GET("category/occupation/{occupation}")
    Call<Items> getJobList  (
            @Header("x-auth-token") String token,
            @Path(value = "occupation", encoded = true) String occupation);


    /*GET Experience List*/
    @GET("category/experience/{experience}")
    Call<JsonObject> getExperienceList  (
            @Header("x-auth-token") String token,
            @Path(value = "experience", encoded = true) String experience);


    /*GET Grade List*/
    @GET("category/grade/{grade}")
    Call<JsonObject> getGradeList  (
            @Header("x-auth-token") String token,
            @Path(value = "grade", encoded = true) String grade);


    /*GET Welfare List*/
    @GET("category/welfare/{welfare}")
    Call<JsonObject> getWelfareList  (
            @Header("x-auth-token") String token,
            @Path(value = "welfare", encoded = true) String welfare);


    /*GET SmartMatch*/
    @GET("category/smartMatch/{uid}")
    Call<JsonObject> getSmartMatch  (
            @Header("x-auth-token") String token,
            @Path(value = "uid", encoded = true) String uid);


    /*Login*/
    @POST("login")
    Call<Void> login(@Body UserLogin userRequest);

    class UserLogin{
        String email;
        String password;

        public UserLogin(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }


    /*SEND EMAIL*/
    @FormUrlEncoded
    @POST("mail/send")
    Call<JsonObject> sendMail(@Field(value = "email", encoded = true) String email);


    /*SIGNUP*/
    @POST("manage")
    Call<JsonObject> requestJoin (@Body User userRequest);

    class User {
        String email;
        String username;
        String password;

        public User(String email, String username,String password) {

            this.email = email;
            this.username = username;
            this.password = password;

        }
    }


    /*SEND RESUME*/
    @POST("resume")
    Call<Resume> sendResume(@Body Resume resumeRequest);

    class Resume{
        String address;
        String grade;
        String license;
        String misscellaneous;
        String objective;
        String specialty;
        String uid;

        public Resume(String address, String grade, String license, String misscellaneous, String objective, String specialty, String uid) {
            this.address = address;
            this.grade = grade;
            this.license = license;
            this.misscellaneous = misscellaneous;
            this.objective = objective;
            this.specialty = specialty;
            this.uid = uid;
        }
    }

}

