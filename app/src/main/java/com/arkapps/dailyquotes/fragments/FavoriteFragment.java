package com.arkapps.dailyquotes.fragments;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arkapps.dailyquotes.R;
import com.arkapps.dailyquotes.adapters.QuoteAdapter;
import com.arkapps.dailyquotes.cls.MyFunction;
import com.arkapps.dailyquotes.cls.QuoteData;
import com.arkapps.dailyquotes.databinding.FragmentFavoriteBinding;
import com.arkapps.dailyquotes.roomdb.FavoriteData;
import com.arkapps.dailyquotes.roomdb.MyDao;
import com.arkapps.dailyquotes.roomdb.MyDatabase;
import com.arkapps.dailyquotes.roomdb.MyRepository;
import com.arkapps.dailyquotes.roomdb.MyViewModel;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements QuoteAdapter.OnButtonsClickListener {

private FragmentFavoriteBinding binding;
    private QuoteAdapter adapter;
    private ArrayList<QuoteData> arrayList;
    private MyRepository repository;
    private MyViewModel myViewModel;



    public FavoriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding  = FragmentFavoriteBinding.inflate(inflater,container,false);
        return  binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }




    private void init() {
        arrayList = new ArrayList<>();
        adapter = new QuoteAdapter(arrayList, this);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);


        MyDao myDao = MyDatabase.getInstance(getContext()).myDao();
        repository = new MyRepository(myDao);
        myViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MyViewModel.class);

        myViewModel.allData.observe(this, new Observer<List<FavoriteData>>() {
            @Override
            public void onChanged(List<FavoriteData> favoriteData) {
           arrayList.clear();
           adapter.notifyDataSetChanged();
                for (FavoriteData data : favoriteData) {
                    Log.d("TAG", "onChanged: FAV id is "+ data.getId());
                    QuoteData quoteData = MyFunction.getQuoteDataFromFavoriteData(data);
                    quoteData.setFavorite(true);
                 arrayList.add(quoteData);
                 adapter.notifyDataSetChanged();
                }
            }
        });





    }

    @Override
    public void onDownloadClick(int position,View view) {

    }

    @Override
    public void onShareClick(int position,View view) {

    }

    @Override
    public void onFavoriteClick(int position) {

         QuoteData data = arrayList.get(position);
         repository.deleteFavoriteDataById(data.getId());
        Toast.makeText(getContext(), "Item removed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWallpaperClick(int position,View view) {

    }


}