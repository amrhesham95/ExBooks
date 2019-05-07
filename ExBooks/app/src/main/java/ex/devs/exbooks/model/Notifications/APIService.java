package ex.devs.exbooks.model.Notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA7wPU7Bs:APA91bEScgitbNydwjBLbcW-dGTJLp5DdQ_kMQxynQLrJKYqDOBNjYGsNk7xxGYLw-cRzmGKF_0R_cuwr7e4VJ88TivKMgB44BeQDW4mlZfZsd-JkXSRFYxDfRYw0PgmldAneW8l987U"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
