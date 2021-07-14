package com.example.a51_consumorestfullwebapiplantillas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonleer;
    TextView txtdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtdatos = (TextView) findViewById(R.id.txtdatos);
        this.botonleer = (Button) findViewById(R.id.botonleerservicio);
    }

    public void leerServicio(View view) {
        try {
            String url = "https://webapiplantillas20210713122517.azurewebsites.net/api/Plantillas";
            new HttpAsyncTask().execute(url);
        } catch (Exception e) {
// manage exceptions
            txtdatos.setText(e.getMessage());
        }
    }

    public String recuperarContenido(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        String resultado = null;
        HttpGet httpget = new HttpGet(url);
        HttpResponse respuesta = null;
        InputStream stream = null;
        try {
            respuesta = httpclient.execute(httpget);
            HttpEntity entity = respuesta.getEntity();

            if (entity != null) {
                stream = entity.getContent();
                resultado = convertirInputToString(stream);
            }
        } catch (Exception e) {
            txtdatos.setText(e.getMessage());
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception e) {
                txtdatos.setText(e.getMessage());
            }
        }
        return resultado;
    }

    private String convertirInputToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String resultado = "";
        while ((line = bufferedReader.readLine()) != null)
            resultado += line;
        inputStream.close();
        return resultado;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return recuperarContenido(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Datos recibidos!", Toast.LENGTH_LONG).show();

            try {
                JSONArray jsonarray = new JSONArray(result);
                List<Plantilla> lista = convertirJsonPlantillas(jsonarray);
                String datos = "";
                for (Plantilla d : lista) {
                    datos += d.toString() + "\n";
                }
                txtdatos.setText(datos);
            } catch (JSONException e) {
                txtdatos.setText(e.getMessage());
            }

        }
    }

    public List<Plantilla> convertirJsonPlantillas(JSONArray jsonarray) throws JSONException {
        List<Plantilla> lista = new ArrayList<>();
        for (int i = 0; i < jsonarray.length(); i++) {
            Plantilla plant = new Plantilla();
            String idHos, idSal, idEmp, ape, func, turn, sal;


            idHos = jsonarray.getJSONObject(i).optString("idHospital").toString();
            idSal = jsonarray.getJSONObject(i).optString("idSala").toString();
            idEmp = jsonarray.getJSONObject(i).optString("idEmpleado").toString();
            ape = jsonarray.getJSONObject(i).optString("apellido").toString();
            func = jsonarray.getJSONObject(i).optString("funcion").toString();
            turn = jsonarray.getJSONObject(i).optString("turno").toString();
            sal = jsonarray.getJSONObject(i).optString("salario").toString();


            plant.setIdHospital(idHos);
            plant.setIdSala(idSal);
            plant.setIdEmpleado((idEmp));
            plant.setApellido(ape);
            plant.setFuncion(func);
            plant.setTurno(turn);
            plant.setSalario(sal);
            lista.add(plant);
        }
        return lista;
    }


}