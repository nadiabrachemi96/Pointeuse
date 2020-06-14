package com.upec.androidtemplate20192020.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.upec.androidtemplate20192020.Model.Etudiant;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class ConnectDB  extends AsyncTask<String, Void, String> {

    private static final String url = "jdbc:mysql://192.168.0.43:3306/Pointeuse.db";
    private static final String user = "Root";
    private static final String pass = "";
    ArrayList<Etudiant> etudiants = new ArrayList<>();


    Context c ;

    public ConnectDB(Context c){
        this.c = c;
    }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {


                DatabaseHelper dh = new DatabaseHelper(this.c);
                Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, user, pass);
                    System.out.println("Databaseection success");


                Toast.makeText ( this.c,"data",Toast.LENGTH_SHORT ).show ();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from Etudiant");
                    ResultSetMetaData rsmd = rs.getMetaData();



                while (rs.next()) {
                    dh.createEtudiant(
                            new Etudiant(
                                rs.getString(1).toString(),
                                    rs.getString(2).toString(),
                                     rs.getString(3).toString(),
                                    rs.getString(1).toString(),
                                    rs.getString(2).toString(),
                                    rs.getString(3).toString()
                                    ));

                }
                rs.close();




            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String pass) {

        }


    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) { }
        return "";
    }

}
