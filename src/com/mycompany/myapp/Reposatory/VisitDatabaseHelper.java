package com.mycompany.myapp.Reposatory;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.util.Callback;
import com.mycompany.myapp.Components.DatabaseInfo;
import com.mycompany.myapp.Model.VisitDayInformation;
import com.mycompany.myapp.Model.VisitInformation;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class VisitDatabaseHelper implements DatabaseInfo {

    ConnectionRequest connection;

    String date;

    VisitDayHelper visitDayHelper = new VisitDayHelper();

    public void addVisitInfo(VisitInformation visit, Callback callback) {

        System.out.println("date here :- " + date);
        
        connection = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                String responseString = Util.readToString(input);

                System.out.println("Raw Response: " + responseString);

                if (responseString == null || responseString.isEmpty()) {

                    System.err.println("Empty response received.");
                    return;

                }

                if (responseString.startsWith("[") && responseString.endsWith("]")) {

                    responseString = responseString.substring(1, responseString.length() - 1);

                }

                System.out.println("after removing [] :- " + responseString);

                Map<String, Object> map = new HashMap<>();
                StringBuilder keyBuilder = new StringBuilder();
                StringBuilder valueBuilder = new StringBuilder();
                boolean isKey = true;
                boolean insideString = false;

                boolean findIsAvailable = false;

                for (int i = 0; i < responseString.length(); i++) {

                    char currentChar = responseString.charAt(i);

                    if (currentChar == '"') {

                        insideString = !insideString;

                    } else if (insideString) {

                        if (isKey) {

                            keyBuilder.append(currentChar);

                        } else {

                            valueBuilder.append(currentChar);

                        }

                    } else {

                        if (currentChar == ':') {

                            if (keyBuilder.toString().equals("isAvaiable")) {

                                //System.out.println("I am isAvailable");
                                if (responseString.charAt(i + 1) == 't') {

                                    valueBuilder.append("Active");

                                } else {

                                    valueBuilder.append("close");

                                }

                            }

                            isKey = false;

                        } else if (currentChar == ',' || currentChar == '}') {

                            map.put(keyBuilder.toString().trim(), valueBuilder.toString().trim());

                            keyBuilder.setLength(0);
                            valueBuilder.setLength(0);
                            isKey = true;

                            if (currentChar == '}') {

                                // StringBuilder sb = new StringBuilder();
                                //System.out.println("getted title :- " + map.get("title") + " searching title :- " + title);
                                //if (map.get("title").toString().equalsIgnoreCase(title)) {
                                /*for (Object val : map.values()) {

                                    sb.append(val).append(" ");

                                }*/
                                Map<String, Object> map_ = new HashMap<>();

                                for (String j : map.keySet()) {

                                    map_.put(j, map.get(j));

                                }

                                VisitDayInformation visitDay = new VisitDayInformation(map_.get("_id").toString(), date);

                                visitDayHelper.addVisitDay(visit, callback, visitDay);

                                //}
                                map.clear();

                                findIsAvailable = false;

                            }

                        }

                    }

                }

                if (callback != null) {

                    callback.onSucess(responseString);

                }

            }

            @Override
            protected void handleErrorResponseCode(int code, String message) {

                if (callback != null) {

                    callback.onError(null, new NullPointerException(), code, message);

                }

            }

        };

        connection.setPost(true);

        String url = API_URL;

        if (visit.getWardName().equals("Children Ward")) {

            url += "/addChildrenPatientVisit";

        } else if (visit.getWardName().equals("Female Ward")) {

            url += "/addFemalePatientVisit";

        } else if (visit.getWardName().equals("Male Ward")) {

            url += "/addMalePatientVisit";

        } else if (visit.getWardName().equals("Maternity Ward")) {

            url += "/addMaternityPatientVisit";

        } else if (visit.getWardName().equals("OPD")) {

            url += "/addOPDPatientVisit";

        }

        System.out.println("request url :- " + url);

        connection.setUrl(url);
        connection.setContentType("application/json");

        String jsonInputString = "{"
                + "\"patientId\": \"" + visit.getPatientId() + "\","
                + "\"entryDate\": \"" + visit.getEntryTime() + "\","
                + "\"exitDate\": \"" + visit.getExitTime() + "\""
                + "}";

        connection.setRequestBody(jsonInputString);

        NetworkManager.getInstance().addToQueue(connection);

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
