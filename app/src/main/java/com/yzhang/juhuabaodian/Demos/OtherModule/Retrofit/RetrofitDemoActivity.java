package com.yzhang.juhuabaodian.Demos.OtherModule.Retrofit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yzhang on 2017/3/17.
 */

public class RetrofitDemoActivity extends AppCompatActivity {

    TextView textView;

    Retrofit retrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setTextSize(20f);
        textView.setTextColor(Color.BLACK);
        setContentView(textView);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<List<User>> call = service.getUsersCall(0, 10);

        /**
         * 同步请求
         * List<User> response = call.execute().body();
         * 注意:网络请求一定要在子线程中完成，不能直接在UI线程执行，不然会crash
         */
        /*new Thread(){
            @Override
            public void run() {
                super.run();
                UserService service = retrofit.create(UserService.class);
                Call<List<User>> call = service.getUsersCall(0, 10);
                try {
                    List<User> response = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };*/


        /**
         * 异步请求
         */
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                textView.setText("异步请求结果: " + response.body().get(0).toString());
                Log.e("test", "test");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("test", "test error");
            }
        });


        /**
         * 与RxJava的结合
         */
        service.getUsers(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onNext(List<User> user) {
                        Log.e("Retrofit", "rxjava success");
                        textView.setText("异步请求结果: " + user.get(0).toString());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable error) {
                        // Error handling
                        Log.e("Retrofit", error.toString());
                    }
                });
    }

    public interface UserService {
        String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

        @Headers({HEADER_API_VERSION})
        @GET("/users")
            //以get方式访问baseUrl的"/users" 方法
        Observable<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);
        //@Query    Get方法请求参数都会以key=value的方式拼接在url后面，上面的方法会转化成baseUrl/users?since=lastIdQueried&per_page=perPage
        //如果Query参数比较多，那么可以通过@QueryMap方式将所有的参数集成在一个Map统一传递

        /**
         * 假如你需要添加相同Key值，但是value却有多个的情况，一种方式是添加多个@Query参数，还有一种简便的方式是将所有的value放置在列表中，然后在同一个@Query下完成添加
         * public interface BlueService {
         *      @GET("book/search")
         *      Call<BookSearchResponse> getSearchBooks(@Query("q") List<String> name);
         * }
         * 最后得到的url地址为
         * https://api.douban.com/v2/book/search?q=leadership&q=beyond%20feelings
         */

        /**
         * 如果请求的相对地址也是需要调用方传递，那么可以使用@Path注解
         *
         * @GET("book/{id}") Call<BookResponse> getBook(@Path("id") String id);
         * https://api.douban.com/v2/book/1003078
         */

        @Headers({HEADER_API_VERSION})
        @GET("/users")
        Call<List<User>> getUsersCall(@Query("since") int lastIdQueried, @Query("per_page") int perPage);


        @FormUrlEncoded
        //@FormUrlEncoded将会自动将请求参数的类型调整为application/x-www-form-urlencoded，假如content传递的参数为Good Luck，那么最后得到的请求体就是content=Good+Luck
        @POST("book/reviews")
            //以POST方式访问baseUrl的"/book/reviews" 方法
        Call<String> addReviews(@Field("book") String bookId, @Field("title") String title, //@Field 是post传递参数的标志
                                @Field("content") String content, @Field("rating") String rating); //同理@QueryMap，有@FieldMap

        /**
         * @Field 注解将每一个请求参数都存放至请求体中，还可以添加encoded参数，该参数为boolean型，具体的用法为
         * @Field (value = "book", encoded = true) String book
         * encoded参数为true的话，key-value-pair将会被编码，即将中文和特殊字符进行编码转换
         */

        /**
         * 如果Post请求参数有多个，那么统一封装到类中应该会更好，这样维护起来会非常方便,可以使用@Body
         *
         * @POST("book/reviews") Call<String> addReviews(@Body Reviews reviews);
         * <p>
         * public class Reviews {
         * public String book;
         * public String title;
         * public String content;
         * public String rating;
         * }
         */

        // 上传单个文件
        @Multipart
        @POST("upload")
        Call<ResponseBody> uploadFile(
                @Part("description") RequestBody description,
                @Part MultipartBody.Part file);

        // 上传多个文件
        @Multipart
        @POST("upload")
        Call<ResponseBody> uploadMultipleFiles(
                @Part("description") RequestBody description,
                @Part MultipartBody.Part file1,
                @Part MultipartBody.Part file2);
    }

    public class User {
        private final int id;
        private final String login;
        private final String avatar_url;

        public User(int id, String login, String avatar_url) {
            this.id = id;
            this.login = login;
            this.avatar_url = avatar_url;
        }

        public String getAvatarUrl() {
            if (avatar_url.isEmpty()) return avatar_url;
            return avatar_url.split("\\?")[0];
        }


        public int getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        @Override
        public String toString() {
            return "id -> " + id + " login -> " + login;
        }
    }
}
