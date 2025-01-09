package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.core.ResponseInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InventoryFindFunction implements RequestHandler<HttpQueryStringRequest, String> {

    @Override
    public String handleRequest(HttpQueryStringRequest request, Context context) {
        String id = request.getParameters().get("id");
        return getProductById(context, id);
    }

    private String getProductById(Context context, String id) {

        Region region = Region.AP_SOUTH_1;
        try (S3Client s3 = S3Client.builder().region(region).build()) {
            ResponseInputStream<?> responseInputStream = s3.getObject(GetObjectRequest.builder()
                    .bucket("sacawsreadbkt")
                    .key("products.txt")
                    .build());


            Products[] products = null;
            try (InputStreamReader isr = new InputStreamReader(responseInputStream);
                 BufferedReader br = new BufferedReader(isr)) {
                Gson gson = new Gson();
                products = gson.fromJson(br, Products[].class);
                return gson.toJson(Arrays.stream(products).filter(p -> p.getId().equals(id)).toArray());
            }
        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
        }
        return "hello from InventoryFindFunction";
    }
}

