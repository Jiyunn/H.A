package kr.happy.myarmy.Retrofit2;

import kr.happy.myarmy.Menu.Chaeyong;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JY on 2017-04-26.
 */

public interface RetroInterface { //이걸 초기화 해야 통신준비가 완료된다는데

        @GET("list")
        Call<Chaeyong> getList
                (@Query("numOfRows") int rows,
                 @Query("pg") int pg,
                 @Query("_type") String _type,
                 @Query(value = "serviceKey", encoded = true) String serviceKey); //물음표 뒤에있는 것들은 이런형식으로 보내기
    }

