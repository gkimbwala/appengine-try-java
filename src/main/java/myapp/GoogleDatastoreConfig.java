package myapp;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;


//project for google Datastore Allowance-0c706eeb98b9.json

public class GoogleDatastoreConfig {

    public static void main(String... args) throws Exception {
        // Instantiates a client
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

        // The kind for the new entity
        String kind = "User";
        // The name/ID for the new entity
        String name = "ChoreID";
        // The Cloud Datastore key for the new entity
        Key choreKey = datastore.newKeyFactory().setKind(kind).newKey(name);

        // Prepares the new entity
        Entity chore = Entity.newBuilder(choreKey)
                .set("description", "go grocery shopping")
                .build();

        // Saves the entity
        datastore.put(chore);

        System.out.printf("Saved %s: %s%n", chore.getKey().getName(), chore.getString("description"));

        //Retrieve entity
        Entity retrieved = datastore.get(choreKey);

        System.out.printf("Retrieved %s: %s%n", choreKey.getName(), retrieved.getString("description"));

    }
}

