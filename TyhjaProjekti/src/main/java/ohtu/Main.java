
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import ohtu.Submission;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "014020733";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2016.herokuapp.com/students/" + studentNr + "/submissions";
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        
        InputStream stream = method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        splitAssigments(bodyText, subs);

        print(subs);
    }
    private static void print(Submission[] subs) {
        System.out.println("opiskelijanumero " + subs[0].getStudent_number());
        System.out.println("");
        int totalTime = 0;
        int totalSubmitAssigments = 0;
        for (Submission submission : subs) {
            System.out.println("\t" + submission);
            totalTime += submission.getHours();
            totalSubmitAssigments += submission.numberOfAssigmentsDone();
        }
        System.out.println("");
        System.out.println("yhteensä: " + totalSubmitAssigments + " tehtävää " + totalTime + " tuntia");
    }

    private static void splitAssigments(String bodyText, Submission[] subs) {
        String[] subsAsString = bodyText.split("}");
        int start = 10;
        int stop = 30;
        for (int i = 0; i < subs.length; ++i) {
            String sub = subsAsString[i];
            String[] fields = sub.split(",");
            for (int j = start; j < stop; j++) {
                String[] assigment = fields[j].split(":");
                subs[i].setAssigment((j - start), assigment[1]);
                start = 11;
                stop = 31;
            }
        }
    }
}
