package fr.utt.if26.shoppinglist;

import android.content.Context;
import android.util.JsonReader;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.utt.if26.shoppinglist.converters.DateConverter;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.entities.ComposeEntity;
import fr.utt.if26.shoppinglist.entities.ListeEntity;

@Database(entities = {AlimentEntity.class, ListeEntity.class, ComposeEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDAO appDAO();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .addCallback(roomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            databaseWriteExecutor.execute(() -> {

                AppDAO dao = INSTANCE.appDAO();
                dao.deleteAllCompose();
                dao.deleteAllListe();
                dao.deleteAllAliment();


                ListeEntity liste = new ListeEntity("Cumartesi tarifleri", "Süpermarket", new Date());
                dao.insert(liste);
                ListeEntity liste2 = new ListeEntity("Pazar tarifleri", "Pazar", new Date());
                dao.insert(liste2);
                ListeEntity liste3 = new ListeEntity("Pazartesi Tarifleri", "Fırın", new Date());
                dao.insert(liste3);

                AlimentEntity aliment1 = new AlimentEntity("Ekmek", "Tahıl ürünleri");
                AlimentEntity aliment2 = new AlimentEntity("Coca-Cola", "İcecekler");
                AlimentEntity aliment3 = new AlimentEntity("Tavuk", "Et ve Balık");
                AlimentEntity aliment4 = new AlimentEntity("Havuc", "Sebzeler ve meyveler");
                AlimentEntity aliment5 = new AlimentEntity("Kek", "Tatlı");
                AlimentEntity aliment6 = new AlimentEntity("Beyaz peynir", "Sut urunleri");
                AlimentEntity aliment7 = new AlimentEntity("Lazanya", "Karısık yemekler");
                AlimentEntity aliment8 = new AlimentEntity("Surme", "Dıger");
                AlimentEntity aliment9 = new AlimentEntity("Riz", "Céréales");
                AlimentEntity aliment10 = new AlimentEntity("Bière", "Boissons");
                AlimentEntity aliment11 = new AlimentEntity("Saumon", "Viande et poisson");
                AlimentEntity aliment12 = new AlimentEntity("Tomate", "Légumes et fruits");
                AlimentEntity aliment13 = new AlimentEntity("Tarte", "Produits sucrés");
                AlimentEntity aliment14 = new AlimentEntity("Crème fraiche", "Produits laitiers");
                AlimentEntity aliment15 = new AlimentEntity("Chili con carne", "Plats composés");
                AlimentEntity aliment16 = new AlimentEntity("Nutella", "Autre");
                AlimentEntity aliment17 = new AlimentEntity("Flocons d'avoine", "Céréales");
                AlimentEntity aliment18 = new AlimentEntity("Jus d'orange", "Boissons");
                AlimentEntity aliment19 = new AlimentEntity("Porc", "Viande et poisson");
                AlimentEntity aliment20 = new AlimentEntity("Aubergine", "Légumes et fruits");
                AlimentEntity aliment21 = new AlimentEntity("Biscuits", "Produits sucrés");
                AlimentEntity aliment22 = new AlimentEntity("Yaourt", "Produits laitiers");
                AlimentEntity aliment23 = new AlimentEntity("Raclette", "Plats composés");
                AlimentEntity aliment24 = new AlimentEntity("Huile d'olive", "Autre");
                AlimentEntity aliment25 = new AlimentEntity("Maïs", "Céréales");
                AlimentEntity aliment26 = new AlimentEntity("Cidre", "Boissons");
                AlimentEntity aliment27 = new AlimentEntity("Thon", "Viande et poisson");
                AlimentEntity aliment28 = new AlimentEntity("Salade", "Légumes et fruits");
                AlimentEntity aliment29 = new AlimentEntity("Glace", "Produits sucrés");
                AlimentEntity aliment30 = new AlimentEntity("Beurre", "Produits laitiers");
                AlimentEntity aliment31 = new AlimentEntity("Paella", "Plats composés");
                AlimentEntity aliment32 = new AlimentEntity("Miel", "Autre");
                AlimentEntity aliment33 = new AlimentEntity("Quinoa", "Céréales");
                AlimentEntity aliment34 = new AlimentEntity("Limonade", "Boissons");
                AlimentEntity aliment35 = new AlimentEntity("Agneau", "Viande et poisson");
                AlimentEntity aliment36 = new AlimentEntity("Courgette", "Légumes et fruits");
                AlimentEntity aliment37 = new AlimentEntity("Tarte au chocolat", "Produits sucrés");
                AlimentEntity aliment38 = new AlimentEntity("Lait", "Produits laitiers");
                AlimentEntity aliment39 = new AlimentEntity("Fondue", "Plats composés");
                AlimentEntity aliment40 = new AlimentEntity("Ketchup", "Autre");
                AlimentEntity aliment41 = new AlimentEntity("Avoine", "Céréales");
                AlimentEntity aliment42 = new AlimentEntity("Smoothie", "Boissons");
                AlimentEntity aliment43 = new AlimentEntity("Bar", "Viande et poisson");
                AlimentEntity aliment44 = new AlimentEntity("Poireau", "Légumes et fruits");
                AlimentEntity aliment45 = new AlimentEntity("Muffins", "Produits sucrés");
                AlimentEntity aliment46 = new AlimentEntity("Crème fraiche épaisse", "Produits laitiers");
                AlimentEntity aliment47 = new AlimentEntity("Tajine", "Plats composés");
                AlimentEntity aliment48 = new AlimentEntity("Vinaigre balsamique", "Autre");
                AlimentEntity aliment49 = new AlimentEntity("Pain aux céréales", "Céréales");
                AlimentEntity aliment50 = new AlimentEntity("Vin", "Boissons");
                AlimentEntity aliment51 = new AlimentEntity("Dinde", "Viande et poisson");
                AlimentEntity aliment52 = new AlimentEntity("Concombre", "Légumes et fruits");
                AlimentEntity aliment53 = new AlimentEntity("Gâteau au chocolat", "Produits sucrés");
                AlimentEntity aliment54 = new AlimentEntity("Fromage à pâte molle", "Produits laitiers");
                AlimentEntity aliment55 = new AlimentEntity("Poulet rôti", "Plats composés");
                AlimentEntity aliment56 = new AlimentEntity("Moutarde", "Autre");
                AlimentEntity aliment57 = new AlimentEntity("Granola", "Céréales");
                AlimentEntity aliment58 = new AlimentEntity("Eau minérale", "Boissons");
                AlimentEntity aliment59 = new AlimentEntity("Boeuf", "Viande et poisson");
                AlimentEntity aliment60 = new AlimentEntity("Chou", "Légumes et fruits");
                AlimentEntity aliment61 = new AlimentEntity("Gaufres", "Produits sucrés");
                AlimentEntity aliment62 = new AlimentEntity("Lait de coco", "Produits laitiers");
                AlimentEntity aliment63 = new AlimentEntity("Risotto", "Plats composés");
                AlimentEntity aliment64 = new AlimentEntity("Beurre de cacahuète", "Autre");
                AlimentEntity aliment65 = new AlimentEntity("Sarrasin", "Céréales");
                AlimentEntity aliment66 = new AlimentEntity("Café", "Boissons");
                AlimentEntity aliment67 = new AlimentEntity("Hareng", "Viande et poisson");
                AlimentEntity aliment68 = new AlimentEntity("Fenouil", "Légumes et fruits");
                AlimentEntity aliment69 = new AlimentEntity("Gelée", "Produits sucrés");
                AlimentEntity aliment70 = new AlimentEntity("Crème fraiche allégée", "Produits laitiers");
                AlimentEntity aliment71 = new AlimentEntity("Couscous", "Plats composés");
                AlimentEntity aliment72 = new AlimentEntity("Huile de tournesol", "Autre");
                AlimentEntity aliment73 = new AlimentEntity("Bouillon", "Céréales");
                AlimentEntity aliment74 = new AlimentEntity("Soda", "Boissons");
                AlimentEntity aliment75 = new AlimentEntity("Saumon fumé", "Viande et poisson");
                AlimentEntity aliment76 = new AlimentEntity("Courge", "Légumes et fruits");
                AlimentEntity aliment77 = new AlimentEntity("Meringue", "Produits sucrés");
                AlimentEntity aliment78 = new AlimentEntity("Fromage à pâte dure", "Produits laitiers");
                AlimentEntity aliment79 = new AlimentEntity("Fajitas", "Plats composés");
                AlimentEntity aliment80 = new AlimentEntity("Marmelade", "Autre");
                AlimentEntity aliment81 = new AlimentEntity("Seigle", "Céréales");
                AlimentEntity aliment82 = new AlimentEntity("Jus d'ananas", "Boissons");
                AlimentEntity aliment83 = new AlimentEntity("Cabillaud", "Viande et poisson");
                AlimentEntity aliment84 = new AlimentEntity("Haricots verts", "Légumes et fruits");
                AlimentEntity aliment85 = new AlimentEntity("Compote", "Produits sucrés");
                AlimentEntity aliment86 = new AlimentEntity("Fromage frais", "Produits laitiers");
                AlimentEntity aliment87 = new AlimentEntity("Lasagne végétarienne", "Plats composés");
                AlimentEntity aliment88 = new AlimentEntity("Vinaigre", "Autre");
                AlimentEntity aliment89 = new AlimentEntity("Orge", "Céréales");
                AlimentEntity aliment90 = new AlimentEntity("Limonade gazeuse", "Boissons");
                AlimentEntity aliment91 = new AlimentEntity("Canard", "Viande et poisson");
                AlimentEntity aliment92 = new AlimentEntity("Cerise", "Légumes et fruits");
                AlimentEntity aliment93 = new AlimentEntity("Chocolat", "Produits sucrés");
                AlimentEntity aliment94 = new AlimentEntity("Crème liquide", "Produits laitiers");
                AlimentEntity aliment95 = new AlimentEntity("Hachis parmentier", "Plats composés");
                AlimentEntity aliment96 = new AlimentEntity("Mayonnaise", "Autre");
                AlimentEntity aliment97 = new AlimentEntity("Boulghour", "Céréales");
                AlimentEntity aliment98 = new AlimentEntity("Thé", "Boissons");
                AlimentEntity aliment99 = new AlimentEntity("Sauté de porc", "Viande et poisson");
                AlimentEntity aliment100 = new AlimentEntity("Melon", "Légumes et fruits");
                dao.insert(aliment1);
                dao.insert(aliment2);
                dao.insert(aliment3);
                dao.insert(aliment4);
                dao.insert(aliment5);
                dao.insert(aliment6);
                dao.insert(aliment7);
                dao.insert(aliment8);
                dao.insert(aliment9);
                dao.insert(aliment10);
                dao.insert(aliment11);
                dao.insert(aliment12);
                dao.insert(aliment13);
                dao.insert(aliment14);
                dao.insert(aliment15);
                dao.insert(aliment16);
                dao.insert(aliment17);
                dao.insert(aliment18);
                dao.insert(aliment19);
                dao.insert(aliment20);
                dao.insert(aliment21);
                dao.insert(aliment22);
                dao.insert(aliment23);
                dao.insert(aliment24);
                dao.insert(aliment25);
                dao.insert(aliment26);
                dao.insert(aliment27);
                dao.insert(aliment28);
                dao.insert(aliment29);
                dao.insert(aliment30);
                dao.insert(aliment31);
                dao.insert(aliment32);
                dao.insert(aliment33);
                dao.insert(aliment34);
                dao.insert(aliment35);
                dao.insert(aliment36);
                dao.insert(aliment37);
                dao.insert(aliment38);
                dao.insert(aliment39);
                dao.insert(aliment40);
                dao.insert(aliment41);
                dao.insert(aliment42);
                dao.insert(aliment43);
                dao.insert(aliment44);
                dao.insert(aliment45);
                dao.insert(aliment46);
                dao.insert(aliment47);
                dao.insert(aliment48);
                dao.insert(aliment49);
                dao.insert(aliment50);
                dao.insert(aliment51);
                dao.insert(aliment52);
                dao.insert(aliment53);
                dao.insert(aliment54);
                dao.insert(aliment55);
                dao.insert(aliment56);
                dao.insert(aliment57);
                dao.insert(aliment58);
                dao.insert(aliment59);
                dao.insert(aliment60);
                dao.insert(aliment61);
                dao.insert(aliment62);
                dao.insert(aliment63);
                dao.insert(aliment64);
                dao.insert(aliment65);
                dao.insert(aliment66);
                dao.insert(aliment67);
                dao.insert(aliment68);
                dao.insert(aliment69);
                dao.insert(aliment70);
                dao.insert(aliment71);
                dao.insert(aliment72);
                dao.insert(aliment73);
                dao.insert(aliment74);
                dao.insert(aliment75);
                dao.insert(aliment76);
                dao.insert(aliment77);
                dao.insert(aliment78);
                dao.insert(aliment79);
                dao.insert(aliment80);
                dao.insert(aliment81);
                dao.insert(aliment82);
                dao.insert(aliment83);
                dao.insert(aliment84);
                dao.insert(aliment85);
                dao.insert(aliment86);
                dao.insert(aliment87);
                dao.insert(aliment88);
                dao.insert(aliment89);
                dao.insert(aliment90);
                dao.insert(aliment91);
                dao.insert(aliment92);
                dao.insert(aliment93);
                dao.insert(aliment94);
                dao.insert(aliment95);
                dao.insert(aliment96);
                dao.insert(aliment97);
                dao.insert(aliment98);
                dao.insert(aliment99);
                dao.insert(aliment100);

                ComposeEntity compose1 = new ComposeEntity(1, 1, 1, 1, false);
                ComposeEntity compose2 = new ComposeEntity(20, 1, 1, 1, false);
                ComposeEntity compose3 = new ComposeEntity(14, 1, 1, 1, false);
                ComposeEntity compose4 = new ComposeEntity(2, 1, 1, 1, false);
                ComposeEntity compose5 = new ComposeEntity(4, 1, 1, 1, false);
                ComposeEntity compose6 = new ComposeEntity(7, 1, 1, 1, false);
                ComposeEntity compose7 = new ComposeEntity(64, 1, 1, 1, false);
                dao.insert(compose1);
                dao.insert(compose2);
                dao.insert(compose3);
                dao.insert(compose4);
                dao.insert(compose5);
                dao.insert(compose6);
                dao.insert(compose7);

                ComposeEntity compose8 = new ComposeEntity(10, 2, 5, 1, false);
                ComposeEntity compose9 = new ComposeEntity(25, 2, 17, 1, false);
                ComposeEntity compose10 = new ComposeEntity(35, 2, 1, 1, false);
                ComposeEntity compose11 = new ComposeEntity(45, 2, 6, 1, false);
                dao.insert(compose8);
                dao.insert(compose9);
                dao.insert(compose10);
                dao.insert(compose11);

                ComposeEntity compose12 = new ComposeEntity(55, 3, 5, 1, false);
                ComposeEntity compose13 = new ComposeEntity(60, 3, 1, 1, true);
                dao.insert(compose12);
                dao.insert(compose13);

            });
        }
    };


}
