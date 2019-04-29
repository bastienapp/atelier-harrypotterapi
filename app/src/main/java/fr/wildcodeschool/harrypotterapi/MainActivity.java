package fr.wildcodeschool.harrypotterapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 1 : call API

        // Crée une file d'attente pour les requêtes vers l'API
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // TODO : URL de la requête vers l'API
        String url = "https://hp-api.herokuapp.com/api/characters";

        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        List<Character> characters = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject characterJson = (JSONObject) response.get(i);
                                String image = characterJson.getString("image");
                                Character character = new Character(image);
                                characters.add(character);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RecyclerView characterList = findViewById(R.id.characterContainer);

                        // use this setting to improve performance if you know that changes
                        // in content do not change the layout size of the RecyclerView
                        characterList.setHasFixedSize(true);

                        // use a linear layout manager
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        characterList.setLayoutManager(layoutManager);

                        CharacterAdapter adapter = new CharacterAdapter(characters, MainActivity.this);
                        characterList.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        // On ajoute la requête à la file d'attente
        requestQueue.add(jsonObjectRequest);

        // TODO 2 : loop through characters
    }
}
