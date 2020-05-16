package com.example.pxstie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditarPostActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView btnVolver, btnGuardar;
    AlertDialog.Builder alertdialog;
    EditText edPostEdit;
    private String idPost;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_post);

        try{
            Bundle b = getIntent().getExtras();
            idPost = b.getString("id");
        }catch (Exception e){}

        btnVolver = findViewById(R.id.btnVolver);
        btnGuardar = findViewById(R.id.btnGuardar);
        edPostEdit = findViewById(R.id.edPostEdit);

        btnVolver.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);

        alertdialog = new AlertDialog.Builder(this, R.style.DialogBasicCustomBlack);
        dialog = new ProgressDialog(this, R.style.DialogBasicCustomRose);
        getPostById();


        dialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVolver:
                alertdialog.setTitle("¿Descartar cambios?")
                .setMessage("¿Seguro que desea descartar los cambios?")
                .setIcon(R.drawable.advertencia)
                .setPositiveButton("Seguir editando", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                alertdialog.create();
                alertdialog.show();
                break;
            case R.id.btnGuardar:
                dialog.setMessage("Guardando...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                String edPE = edPostEdit.getText().toString();
                if (edPE.isEmpty()){
                    Toast.makeText(this, "No puedes dejar el texto vacío", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    saveEdit(edPE, dialog);
                }
                break;
        }
    }

    private void getPostById(){
       /* dialog.setMessage("Cargando...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();*/

        String url = "https://thejopipedia.000webhostapp.com/wsJSONGetPostById.php?id=" + idPost;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray json = response.optJSONArray("post");

                try {
                    for (int i = 0; i < json.length(); i++){
                        JSONObject jsonObject = null;
                        jsonObject = json.getJSONObject(i);

                        edPostEdit.setText(jsonObject.getString("caption"));
                    }
                    dialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(EditarPostActivity.this, R.string.no_estab_conex, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarPostActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void saveEdit(final String caption, final ProgressDialog dialog){
        String url = "https://thejopipedia.000webhostapp.com/wsJSONEditarPost.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent i = new Intent(EditarPostActivity.this, PostCompletoActivity.class);
                Bundle b = new Bundle();
                b.putString("id", idPost);
                i.putExtras(b);
                startActivity(i);
                finish();
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarPostActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", idPost);
                map.put("caption", caption);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
