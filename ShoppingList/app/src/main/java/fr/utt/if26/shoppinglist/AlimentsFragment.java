package fr.utt.if26.shoppinglist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.shoppinglist.adapters.AlimentListAdapter;
import fr.utt.if26.shoppinglist.entities.AlimentEntity;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;


public class AlimentsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private EditText editTextNom;
    private Spinner spinnerCategorie;
    private ImageButton validateButton;

    private static AlimentViewModel alimentViewModel;

    private List<String> spinnerOptions = new ArrayList<String>();
    private String spinnerChoice = "Céréales";

    public AlimentsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_aliments, container, false);

        editTextNom = result.findViewById(R.id.fragment_aliments_et);
        spinnerCategorie = result.findViewById(R.id.fragment_aliments_spinner);
        validateButton = result.findViewById(R.id.fragment_aliments_ib);

        recyclerView = result.findViewById(R.id.fragment_aliments_rv);
        final AlimentListAdapter adapter = new AlimentListAdapter(new AlimentListAdapter.AlimentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(result.getContext()));

        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);
        alimentViewModel.getAllAliments().observe(getViewLifecycleOwner(), adapter::submitList);

        spinnerOptions.add("Tahıl urunleri");
        spinnerOptions.add("Icecekler");
        spinnerOptions.add("Et ve Balık");
        spinnerOptions.add("Sebzeler ve meyveler");
        spinnerOptions.add("Tatlı");
        spinnerOptions.add("Sut Urunleri");
        spinnerOptions.add("Karısık yemekler");
        spinnerOptions.add("Diger");
        spinnerCategorie.setOnItemSelectedListener(this);

        ArrayAdapter spinnerAdapter = new ArrayAdapter(result.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(spinnerAdapter);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextNom.getText().toString().equals("")) {
                    AlimentEntity aliment = new AlimentEntity(editTextNom.getText().toString(), spinnerChoice);
                    alimentViewModel.insert(aliment);
                    editTextNom.setText("");
                }
            }
        });

        return result;
    }

    public static AlimentViewModel getAlimentViewModel() {
        return alimentViewModel;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerChoice = spinnerOptions.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}