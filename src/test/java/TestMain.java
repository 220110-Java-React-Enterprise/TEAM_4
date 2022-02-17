import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.team4.beans.apiResponseDAO.propertiesList.*;
import com.revature.team4.beans.controllers.FindListingsController;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class TestMain {
    public static void main(String ...args) throws JsonProcessingException {
        System.out.println("TestMain");

        FindListingsController fl = new FindListingsController();
        System.out.println(fl.findListings());
    }
}
