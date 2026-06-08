package fr.info.user_interface.controleur;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import org.springframework.stereotype.Service;
import fr.info.user_interface.modele.Catalogue;
import java.util.ArrayList;
import java.util.List;

@Service
public class Services {

  private List<Catalogue> listeCatalogue;
  private final OkHttpClient httpClient;

  private String[] titre = {
    "Asterix gladiateur",
    "Lucky Luke, Sur la piste des Dalton",
    "Garfield prend du poids",
    "L'Anniversaire d'Asterix et Obelix"
  };
  private String[] auteur = {
    "R. Goscinny, A. Uderzo",
    "Morris, R. Goscinny",
    "J. Davis",
    "R. Goscinny, A. Uderzo"
  };
  private String categorie = "bande dessinee";

  public Services() {
    listeCatalogue = new ArrayList<>();
    init();
    httpClient = new OkHttpClient();
  }

  public void init() {
    for (int i = 0; i < titre.length; i++) {
      Catalogue c = new Catalogue();
      c.init(titre[i], auteur[i], categorie);
      listeCatalogue.add(c);
    }
  }

  public List<Catalogue> liste() {
    return listeCatalogue;
  }

  public Catalogue selection() {
    Catalogue catalogue = new Catalogue();
    catalogue.init("Asterix, Le Bouclier Arverne", "R. Goscinny, A. Uderzo", "bande dessinee");
    return catalogue;
  }

  public Catalogue ajouterCatalogue(Catalogue catalogue) {
    listeCatalogue.add(catalogue);
    return catalogue;
  }

  public JSONObject search(String query) throws IOException {
    Request request = new Request.Builder()
        .url("https://nominatim.openstreetmap.org/search?format=json&q=" + query)
        .header("User-Agent", "Mozilla/5.0")
        .header("Accept", "application/json")
        .build();
    try (Response response = httpClient.newCall(request).execute()) {
        if (!response.isSuccessful()) {
            System.out.println("\n\tUnexpected code " + response);
            return null;
        }
        JSONArray jsonArray = new JSONArray(response.body().string());
        if (jsonArray.length() > 0) {
            return jsonArray.getJSONObject(0);
        }
        return null;
    }
  }
}