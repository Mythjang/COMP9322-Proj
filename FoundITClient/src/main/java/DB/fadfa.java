package DB;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import api.JobManagerApi;
import dao.JobsDao;
import model.Job;

public class fadfa {

	public static void main(String[] args) {
		JobManagerApi.searchJob("12@gmail.com", null);

	}

}
