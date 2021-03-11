package edu.tsatualdypov.app.common;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Future extends CompletableFuture<Response> implements Callback {
    
    public void onResponse(Call call, Response response) {
        super.complete(response);
    }

    public void onFailure(Call call, IOException e){
        super.completeExceptionally(e);
    }

}
