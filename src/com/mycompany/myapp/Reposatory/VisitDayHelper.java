package com.mycompany.myapp.Reposatory;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.util.Callback;
import com.mycompany.myapp.Components.DatabaseInfo;
import static com.mycompany.myapp.Components.DatabaseInfo.API_URL;
import com.mycompany.myapp.Components.PatientList;
import com.mycompany.myapp.Model.VisitDayInformation;
import com.mycompany.myapp.Model.VisitInformation;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class VisitDayHelper implements DatabaseInfo {

    ConnectionRequest connection;

    public void addVisitDay(VisitInformation visit, Callback callback, VisitDayInformation visitDay) {

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

                                //}
                                map.clear();

                                findIsAvailable = false;

                            }

                        }

                    }

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

        String jsonInputString = "{"
                + "\"patientId\": \"" + visit.getPatientId() + "\","
                + "\"entryDate\": \"" + visit.getEntryTime() + "\","
                + "\"exitDate\": \"" + visit.getExitTime() + "\""
                + "}";

        if (visit.getWardName().equals("Children Ward")) {

            url += "/addChildrenVisitDay";

            jsonInputString = "{"
                    + "\"ChildrenVisitId\": \"" + visitDay.getVisitId() + "\","
                    + "\"Date\": \"" + visitDay.getDate() + "\""
                    + "}";

        } else if (visit.getWardName().equals("Female Ward")) {

            url += "/addFemaleVisitDay";

            jsonInputString = "{"
                    + "\"FemaleVisitId\": \"" + visitDay.getVisitId() + "\","
                    + "\"Date\": \"" + visitDay.getDate() + "\""
                    + "}";

        } else if (visit.getWardName().equals("Male Ward")) {

            url += "/addMaleVisitDay";

            jsonInputString = "{"
                    + "\"MaleVisitId\": \"" + visitDay.getVisitId() + "\","
                    + "\"Date\": \"" + visitDay.getDate() + "\""
                    + "}";

        } else if (visit.getWardName().equals("Maternity Ward")) {

            url += "/addMaternityVisitDay";

            jsonInputString = "{"
                    + "\"MaternityVisitId\": \"" + visitDay.getVisitId() + "\","
                    + "\"Date\": \"" + visitDay.getDate() + "\""
                    + "}";

        } else if (visit.getWardName().equals("OPD")) {

            url += "/addOPDVisitDay";

            jsonInputString = "{"
                    + "\"OPDVisitId\": \"" + visitDay.getVisitId() + "\","
                    + "\"Date\": \"" + visitDay.getDate() + "\""
                    + "}";

        }

        System.out.println("request url :- " + url);

        connection.setUrl(url);
        connection.setContentType("application/json");

        connection.setRequestBody(jsonInputString);

        NetworkManager.getInstance().addToQueue(connection);

    }

    public void readSearchingVisitDay(String date, String wardName, PatientList visitList, List list) {

        connection = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {

                String responseString = Util.readToString(input);

                System.out.println("Raw Response: " + responseString);

                if (responseString == null || responseString.isEmpty()) {
                    System.err.println("Empty response received.");
                    return;
                }

                // Handle JSON parsing if responseString is valid
                // You can use a JSONParser to parse this into a usable Map or List
                System.out.println("Parsed Response: " + responseString);

                // Call the callback with the parsed response (you can adjust this based on actual data structure)
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

                                StringBuilder sb = new StringBuilder();

                                sb.append(map_.get("entryDate")).append(" ")
                                        .append(map_.get("patientName")).append(" ")
                                        .append(map_.get("duration"));

                                if (map_.get("entryDate") != null) {

                                    list.addItem(sb.toString());

                                    visitList.add(map_);

                                }

                                //}
                                map.clear();

                                findIsAvailable = false;

                            }

                        }

                    }

                }

            }

            @Override
            protected void handleErrorResponseCode(int code, String message) {

                //      Dialog.show("Database Info", code + " :- " + message, "OK", "Cancel");
            }
        };

        String url = API_URL;

        if (wardName.equals("Children Ward")) {
            url += "/childrenSearchByDate?Date=" + date;
        } else if (wardName.equals("Female Ward")) {
            url += "/femaleSearchByDate?Date=" + date;
        } else if (wardName.equals("Male Ward")) {
            url += "/maleSearchByDate?Date=" + date;
        } else if (wardName.equals("Maternity Ward")) {
            url += "/maternitySearchByDate?Date=" + date;
        } else if (wardName.equals("OPD")) {
            url += "/opdSearchByDate?Date=" + date;
        }

        System.out.println("Search request URL: " + url);

        connection.setUrl(url);
        connection.setContentType("application/json");

        connection.setPost(false);

        NetworkManager.getInstance().addToQueueAndWait(connection);

    }

    public void deleteVisitDay(String wardName, String visitDayId, Callback callback) {
        connection = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                String responseString = Util.readToString(input);
                System.out.println("Raw Response: " + responseString);

                if (responseString == null || responseString.isEmpty()) {
                    System.err.println("Empty response received.");
                    return;
                }

                if (callback != null) {
                    callback.onSucess(responseString + " deleted successfully.");
                }
            }

            @Override
            protected void handleErrorResponseCode(int code, String message) {
                if (callback != null) {
                    callback.onError(null, new NullPointerException(), code, message);
                }
            }
        };

        String url = API_URL;

        if (wardName.equals("Children Ward")) {
            url += "/deleteChildrenVisitDay";
        } else if (wardName.equals("Female Ward")) {
            url += "/deleteFemaleVisitDay";
        } else if (wardName.equals("Male Ward")) {
            url += "/deleteMaleVisitDay";
        } else if (wardName.equals("Maternity Ward")) {
            url += "/deleteMaternityVisitDay";
        } else if (wardName.equals("OPD")) {
            url += "/deleteOPDVisitDay";
        }

        connection.setUrl(url);
        connection.setPost(true);
        connection.setContentType("application/json");

        System.out.println("visit id :- " + visitDayId);
        
        String jsonInputString = "{\"id\": \"" + visitDayId + "\"}";

        connection.setRequestBody(jsonInputString);
        System.out.println("Delete request URL: " + url);
        System.out.println("Delete request body: " + jsonInputString);

        NetworkManager.getInstance().addToQueueAndWait(connection);
    }

}
