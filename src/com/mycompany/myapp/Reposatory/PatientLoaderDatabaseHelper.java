package com.mycompany.myapp.Reposatory;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.ComboBox;
import com.mycompany.myapp.Components.DatabaseInfo;
import com.mycompany.myapp.Components.PatientList;
import com.mycompany.myapp.Model.PatientInformation;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PatientLoaderDatabaseHelper implements DatabaseInfo {

    ConnectionRequest connection;

    public void loadPatients(PatientInformation patient, PatientList list, ComboBox patientName) {

        try {

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

                                    list.add(map_);

                                    patientName.addItem(map_.get("PatientName"));

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

                    System.out.println(code + " :- " + message);

                }

            };

            connection.setPost(false);

            String url = API_URL;

            if (patient.getWardName().equals("Children Ward")) {

                url += "/readAllChildrenPatient";

            } else if (patient.getWardName().equals("Female Ward")) {

                url += "/readAllFemalePatient";

            } else if (patient.getWardName().equals("Male Ward")) {

                url += "/readAllMalePatient";

            } else if (patient.getWardName().equals("Maternity Ward")) {

                url += "/readAllMaternityPatient";

            } else if (patient.getWardName().equals("OPD")) {

                url += "/readAllOPDPatient";

            }

            System.out.println("url :- " + url);

            connection.setUrl(url);

            NetworkManager.getInstance().addToQueueAndWait(connection);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

}
