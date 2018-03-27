package me.happy.win3win.network;

import com.google.gson.JsonObject;

import me.happy.win3win.fragment.tab.model.Chaeyong;
import me.happy.win3win.fragment.tab.model.ReqItems;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    /*GET ALL DATA*/
    @GET("list")
    Call<Chaeyong> getList
            (@Query("numOfRows") int rows,
             @Query("pageNo") int pg,
             @Query("_type") String _type,
             @Query(value = "serviceKey", encoded = true) String serviceKey);

    /*GET SEARCH RESULT*/
    @GET("category/all/{text}")
    Call<ReqItems> getSearchResult(
            @Header("x-auth-token") String token,
            @Path(value = "text", encoded = true) String text);

    /*GET thumbnail*/
    @GET("info/thumbnail/{cygonggNo}")
    Call<JsonObject> getThumbnail(
            @Header("x-auth-token") String token,
            @Path("id") int id);


    /*GET ALL List*/
    @GET("category/all")
    Call<ReqItems> getHomeList(
            @Header("x-auth-token") String token);


    /*GET SPE List*/
    @GET("info/{id}")
    Call<JsonObject> getSpeList(
            @Header("x-auth-token") String token,
            @Path("id") int id);


    /*GET Area List*/
    @GET("category/area/{area}")
    Call<ReqItems> getAreaList(
            @Header("x-auth-token") String token,
            @Path(value = "area", encoded = true) String area);


    /*GET Occupation List*/
    @GET("category/occupation/{occupation}")
    Call<ReqItems> getJobList(
            @Header("x-auth-token") String token,
            @Path(value = "occupation", encoded = true) String occupation);


    /*GET Experience List*/
    @GET("category/experience/{experience}")
    Call<ReqItems> getExperienceList(
            @Header("x-auth-token") String token,
            @Path(value = "experience", encoded = true) String experience);


    /*GET Grade List*/
    @GET("category/grade/{grade}")
    Call<ReqItems> getGradeList(
            @Header("x-auth-token") String token,
            @Path(value = "grade", encoded = true) String grade);


    /*GET Welfare List*/
    @GET("category/welfare/{welfare}")
    Call<ReqItems> getWelfareList(
            @Header("x-auth-token") String token,
            @Path(value = "welfare", encoded = true) String welfare);


    /*GET SmartMatch*/
    @GET("category/smartMatch")
    Call<JsonObject> getSmartMatch(
            @Header("x-auth-token") String token);


    /*GET Recommend*/
    @GET("recommend")
    Call<ReqItems> getRecommendList(
            @Header("x-auth-token") String token );


    /*Login*/
    @POST("login")
    Call<Void> login(@Body UserLogin userRequest);

    class UserLogin {
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
    Call<JsonObject> requestJoin(@Body User userRequest);

    class User {
        String email;
        String username;
        String password;

        public User(String email, String username, String password) {

            this.email = email;
            this.username = username;
            this.password = password;
        }
    }

    /*
    GET RESUME
     */
    @GET("resume/")
    Call<me.happy.win3win.fragment.tab.model.Resume> getMyResume(
            @Header("x-auth-token") String token );

    /*
   DELETE RESUME
     */
    @DELETE("resume/delete")
    Call<Void> deleteResume (
            @Header("x-auth-token") String token );


    /*UPDATE RESUME*/
    @POST("resume/update")
    Call<Void> updateResume(
            @Header("x-auth-token") String token,
            @Body Resume resumeRequest );


    /*SEND RESUME*/
    @POST("resume")
    Call<Void> sendResume(
            @Header("x-auth-token")String token,
            @Body Resume resumeRequest);

    class Resume {
        String address;
        String grade;
        String license;
        String misscellaneous; //기타경력사항
        String objective; //희망직종
        String specialty; //특기사항

        public Resume(){}

        public Resume(String address, String grade, String license, String misscellaneous, String objective, String specialty) {
            this.address = address;
            this.grade = grade;
            this.license = license;
            this.misscellaneous = misscellaneous;
            this.objective = objective;
            this.specialty = specialty;

        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public void setMisscellaneous(String misscellaneous) {
            this.misscellaneous = misscellaneous;
        }

        public void setObjective(String objective) {
            this.objective = objective;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }
    }

}

